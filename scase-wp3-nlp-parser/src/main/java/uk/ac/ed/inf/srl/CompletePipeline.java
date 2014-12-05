package uk.ac.ed.inf.srl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import uk.ac.ed.inf.srl.corpus.Sentence;
import uk.ac.ed.inf.srl.corpus.StringInText;
import uk.ac.ed.inf.srl.io.ANNWriter;
import uk.ac.ed.inf.srl.io.CoNLL09Writer;
import uk.ac.ed.inf.srl.io.RDFWriter;
import uk.ac.ed.inf.srl.io.SentenceWriter;
import uk.ac.ed.inf.srl.languages.Language;
import uk.ac.ed.inf.srl.options.CompletePipelineCMDLineOptions;
import uk.ac.ed.inf.srl.options.FullPipelineOptions;
import uk.ac.ed.inf.srl.pipeline.Pipeline;
import uk.ac.ed.inf.srl.pipeline.Reranker;
import uk.ac.ed.inf.srl.pipeline.Step;
import uk.ac.ed.inf.srl.preprocessor.Preprocessor;
import uk.ac.ed.inf.srl.util.ChineseDesegmenter;
import uk.ac.ed.inf.srl.util.FileExistenceVerifier;
import uk.ac.ed.inf.srl.util.Util;

public class CompletePipeline
{

    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\s+");

    public Preprocessor pp;
    public SemanticRoleLabeler srl;

    public static CompletePipeline getCompletePipeline(FullPipelineOptions options)
            throws ZipException, IOException, ClassNotFoundException
    {
        Preprocessor pp = Language.getLanguage().getPreprocessor(options);
        Parse.parseOptions = options.getParseOptions();
        SemanticRoleLabeler srl;
        if (options.reranker) {
            srl = new Reranker(Parse.parseOptions);
        } else {
            ZipFile zipFile = new ZipFile(Parse.parseOptions.modelFile);
            if (Parse.parseOptions.skipPI) {
                srl = Pipeline.fromZipFile(zipFile, new Step[] { Step.pd, Step.ai, Step.ac });// ,Step.po,Step.ao});
            } else {
                srl = Pipeline.fromZipFile(zipFile);
            }
            zipFile.close();
        }
        CompletePipeline pipeline = new CompletePipeline(pp, srl);
        return pipeline;
    }

    private CompletePipeline(Preprocessor preprocessor, SemanticRoleLabeler srl) {
        this.pp = preprocessor;
        this.srl = srl;
    }

    public Sentence parse(String sentence)
            throws Exception
    {
        return parseX(Arrays.asList(pp.tokenizeplus(sentence)));
    }

    public Sentence parse(List<String> words)
            throws Exception
    {
        Sentence s = new Sentence(pp.preprocess(words.toArray(new String[words.size()])), false);
        srl.parseSentence(s);
        return s;
    }

    public Sentence parseX(List<StringInText> words)
            throws Exception
    {
        String[] array = new String[words.size()];
        for (int i = 0; i < array.length; i++)
            array[i] = words.get(i).word();
        Sentence s = new Sentence(pp.preprocess(array), false);
        for (int i = 0; i < array.length; i++) {
            s.get(i).setBegin(words.get(i).begin());
            s.get(i).setEnd(words.get(i).end());
        }
        srl.parse(s);
        return s;
    }

    public Sentence parseOraclePI(List<String> words, List<Boolean> isPred)
            throws Exception
    {
        Sentence s = new Sentence(pp.preprocess(words.toArray(new String[words.size()])), false);
        for (int i = 0; i < isPred.size(); ++i) {
            if (isPred.get(i)) {
                s.makePredicate(i);
            }
        }
        srl.parseSentence(s);
        return s;
    }

    public static void main(String[] args)
            throws Exception
    {
        CompletePipelineCMDLineOptions options = new CompletePipelineCMDLineOptions();
        options.parseCmdLineArgs(args);
        String error = FileExistenceVerifier.verifyCompletePipelineAllNecessaryModelFiles(options);
        if (error != null) {
            System.err.println(error);
            System.err.println();
            System.err.println("Aborting.");
            System.exit(1);
        }

        CompletePipeline pipeline = getCompletePipeline(options);
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(options.input),
                Charset.forName("UTF-8")));
        SentenceWriter writer = null;
        System.out.println(options.printRDF);
        if (options.printANN)
            writer = new ANNWriter(options.output);
        else if (options.printRDF)
            writer = new RDFWriter(options.output);
        else
            writer = new CoNLL09Writer(options.output);

        long start = System.currentTimeMillis();
        int senCount;

        if (options.loadPreprocessorWithTokenizer) {
            senCount = parseNonSegmentedLineByLine(options, pipeline, in, writer);
        } else {
            senCount = parseCoNLL09(options, pipeline, in, writer);
        }

        in.close();
        writer.close();

        long time = System.currentTimeMillis() - start;
        System.out.println(pipeline.getStatusString());
        System.out.println();
        System.out.println("Total parsing time (ms):  " + Util.insertCommas(time));
        System.out.println("Overall speed (ms/sen):   " + Util.insertCommas(time / senCount));

    }

    private static int parseNonSegmentedLineByLine(CompletePipelineCMDLineOptions options, CompletePipeline pipeline,
            BufferedReader in, SentenceWriter writer)
            throws IOException, Exception
    {
        int senCount = 0;
        String str;

        while ((str = in.readLine()) != null) {
            Sentence s = pipeline.parse(str);
            writer.write(s);
            senCount++;
            if (senCount % 100 == 0)
                System.out.println("Processing sentence " + senCount); // TODO, same as below.
        }

        return senCount;
    }

    private static int parseCoNLL09(CompletePipelineCMDLineOptions options, CompletePipeline pipeline,
            BufferedReader in, SentenceWriter writer)
            throws IOException, Exception
    {
        List<String> forms = new ArrayList<String>();
        forms.add("<root>");
        List<Boolean> isPred = new ArrayList<Boolean>();
        isPred.add(false);
        String str;
        int senCount = 0;

        while ((str = in.readLine()) != null) {
            if (str.trim().equals("")) {
                Sentence s;
                if (options.desegment) {
                    s = pipeline.parse(ChineseDesegmenter.desegment(forms.toArray(new String[0])));
                } else {
                    s = options.skipPI ? pipeline.parseOraclePI(forms, isPred) : pipeline.parse(forms);
                }
                forms.clear();
                forms.add("<root>");
                isPred.clear();
                isPred.add(false); // Root is not a predicate
                writer.write(s);
                senCount++;
                if (senCount % 100 == 0) { // TODO fix output in general, don't print to System.out.
                                           // Wrap a printstream in some (static) class, and allow
                                           // people to adjust this. While doing this, also add the
                                           // option to make the output file be -, ie so it prints
                                           // to stdout. All kinds of errors should goto stderr, and
                                           // nothing should be printed to stdout by default
                    System.out.println("Processing sentence " + senCount);
                }
            } else {
                String[] tokens = WHITESPACE_PATTERN.split(str);
                forms.add(tokens[1]);
                if (options.skipPI)
                    isPred.add(tokens[12].equals("Y"));
            }
        }

        if (forms.size() > 1) { // We have the root token too, remember!
            writer.write(pipeline.parse(forms));
            senCount++;
        }
        return senCount;
    }

    public String getStatusString()
    {
        // StringBuilder ret=new StringBuilder("Semantic role labeling pipeline status%n%n");
        StringBuilder ret = new StringBuilder();
        long allocated = Runtime.getRuntime().totalMemory() / 1024;
        long free = Runtime.getRuntime().freeMemory() / 1024;
        ret.append("Memory usage:%n");
        ret.append("Allocated:\t\t\t" + Util.insertCommas(allocated) + "kb%n");
        ret.append("Used:\t\t\t\t" + Util.insertCommas((allocated - free)) + "kb%n");
        ret.append("Free:\t\t\t\t" + Util.insertCommas(free) + "kb%n");
        System.gc();
        long freeWithGC = Runtime.getRuntime().freeMemory() / 1024;
        ret.append("Free (after gc call):\t" + Util.insertCommas(freeWithGC) + "kb%n");
        ret.append("%n");
        // ret.append("Time spent doing tokenization (ms):           "+Util.insertCommas(pp.tokenizeTime)+"%n");
        // ret.append("Time spent doing lemmatization (ms):          "+Util.insertCommas(pp.lemmatizeTime)+"%n");
        // ret.append("Time spent doing pos-tagging (ms):            "+Util.insertCommas(pp.tagTime)+"%n");
        // ret.append("Time spent doing morphological tagging (ms):  "+Util.insertCommas(pp.mtagTime)+"%n");
        // ret.append("Time spent doing dependency parsing (ms):     "+Util.insertCommas(pp.dpTime)+"%n");
        ret.append(pp.getStatus()).append("%n");
        ret.append("Time spent doing semantic role labeling (ms): " + Util.insertCommas(srl.parsingTime) + "%n");
        ret.append("%n%n");
        ret.append(srl.getStatus());
        return ret.toString().trim();
    }
}
