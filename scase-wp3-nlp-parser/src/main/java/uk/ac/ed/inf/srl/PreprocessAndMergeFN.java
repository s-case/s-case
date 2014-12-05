package uk.ac.ed.inf.srl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import uk.ac.ed.inf.srl.corpus.Corpus;
import uk.ac.ed.inf.srl.corpus.CorpusSentence;
import uk.ac.ed.inf.srl.corpus.Sentence;
import uk.ac.ed.inf.srl.corpus.StringInText;
import uk.ac.ed.inf.srl.io.CoNLL09Writer;
import uk.ac.ed.inf.srl.io.DepsOnlyCoNLL09Reader;
import uk.ac.ed.inf.srl.io.SRLOnlyCoNLL09Reader;
import uk.ac.ed.inf.srl.io.SentenceReader;
import uk.ac.ed.inf.srl.io.SentenceWriter;
import uk.ac.ed.inf.srl.languages.Language;
import uk.ac.ed.inf.srl.options.CompletePipelineCMDLineOptions;
import uk.ac.ed.inf.srl.options.FullPipelineOptions;
import uk.ac.ed.inf.srl.options.ParseOptions;
import uk.ac.ed.inf.srl.pipeline.Pipeline;
import uk.ac.ed.inf.srl.pipeline.Reranker;
import uk.ac.ed.inf.srl.pipeline.Step;
import uk.ac.ed.inf.srl.preprocessor.Preprocessor;
import uk.ac.ed.inf.srl.preprocessor.tokenization.WhiteSpaceTokenizer;
import uk.ac.ed.inf.srl.util.FileExistenceVerifier;
import uk.ac.ed.inf.srl.util.StandOffAnnotation;
import uk.ac.ed.inf.srl.util.Util;

public class PreprocessAndMergeFN
{
    private static CompletePipelineCMDLineOptions options;
    private static Preprocessor pp;

    final static String[] test_files = new String[] { "ANC__110CYL067.xml", "ANC__110CYL069.xml", "ANC__112C-L013.xml",
            "ANC__IntroHongKong.xml", "ANC__StephanopoulosCrimes.xml", "ANC__WhereToHongKong.xml", "KBEval__atm.xml",
            "KBEval__Brandeis.xml", "KBEval__cycorp.xml", "KBEval__parc.xml", "KBEval__Stanford.xml",
            "KBEval__utd-icsi.xml", "LUCorpus-v0.3__20000410_nyt-NEW.xml", "LUCorpus-v0.3__AFGP-2002-602187-Trans.xml",
            "LUCorpus-v0.3__enron-thread-159550.xml", "LUCorpus-v0.3__IZ-060316-01-Trans-1.xml",
            "LUCorpus-v0.3__SNO-525.xml", "LUCorpus-v0.3__sw2025-ms98-a-trans.ascii-1-NEW.xml",
            "Miscellaneous__Hound-Ch14.xml", "Miscellaneous__SadatAssassination.xml",
            "NTI__NorthKorea_Introduction.xml", "NTI__Syria_NuclearOverview.xml", "PropBank__AetnaLifeAndCasualty.xml", };

    final static String[] dev_files = new String[] { "LUCorpus-v0.3__20000420_xin_eng-NEW.xml",
            "NTI__SouthAfrica_Introduction.xml", "LUCorpus-v0.3__CNN_AARONBROWN_ENG_20051101_215800.partial-NEW.xml",
            "LUCorpus-v0.3__AFGP-2002-600045-Trans.xml", "PropBank__TicketSplitting.xml", "Miscellaneous__Hijack.xml",
            "LUCorpus-v0.3__artb_004_A1_E1_NEW.xml", "NTI__WMDNews_042106.xml", "C-4__C-4Text.xml",
            "ANC__EntrepreneurAsMadonna.xml", "NTI__LibyaCountry1.xml", "NTI__NorthKorea_NuclearOverview.xml",
            "LUCorpus-v0.3__20000424_nyt-NEW.xml", "NTI__WMDNews_062606.xml", "ANC__110CYL070.xml",
            "LUCorpus-v0.3__CNN_ENG_20030614_173123.4-NEW-1.xml", };

    public static void main(String[] args)
            throws Exception
    {
        args = new String[] { "eng", "-lemma", "models/lemma-train-eng.model", "-parser",
                "models/parse-train-eng.model", "-tagger", "models/tagger-train-eng.model",
                // "-lemma", "models/lemma-eng.model", /* models trained on all CoNLL data sets
                // (train/dev/test/ood?) */
                // "-parser", "models/parse-eng.model",
                // "-tagger", "models/tagger-eng.model",
                "-srl", "/dev/null", "-test", "/disk/scratch/discourse_srl/framenet/PropBank__TicketSplitting.xml" };

        long startTime = System.currentTimeMillis();
        options = new CompletePipelineCMDLineOptions();
        options.parseCmdLineArgs(args);
        String error = FileExistenceVerifier.verifyCompletePipelineAllNecessaryModelFiles(options);
        if (error != null) {
            System.err.println(error);
            System.err.println();
            System.err.println("Aborting.");
            System.exit(1);
        }

        File[] remaining_files = new File("/disk/scratch/discourse_srl/framenet/").listFiles(new FilenameFilter() {
            public boolean accept(File arg0, String arg1)
            {
                if (!arg1.endsWith(".xml"))
                    return false;
                for (String s : test_files)
                    if (arg1.equals(s))
                        return false;
                for (String s : dev_files)
                    if (arg1.equals(s))
                        return false;
                return true;
            }

        });

        String[] train_files = new String[remaining_files.length];
        for (int i = 0; i < train_files.length; i++)
            train_files[i] = remaining_files[i].getName();

        pp = Language.getLanguage().getPreprocessor(options);

        // preprocess(new String[]{"ANC__StephanopoulosCrimes.xml"}, "tmp.txt");
        // preprocess(train_files, "annotations/fn15_train_coref.conll");
        // preprocess(dev_files, "annotations/fn15_dev_coref.conll");
        preprocess(test_files, "annotations/fn15_test_coref.conll");

    }

    private static void preprocess(String[] files, String outfile)
            throws Exception
    {

        SentenceWriter writer = null;
        BufferedReader reader = null;

        try {
            writer = new CoNLL09Writer(new File(outfile));
            for (String filename : files) {
                /**/Corpus c = new Corpus(filename);/**/
                System.err.println("Processing " + filename);
                File file = new File("/disk/scratch/discourse_srl/framenet/" + filename);
                StandOffAnnotation anno = new StandOffAnnotation(file);

                boolean fix_dointonight = filename.contains("LUCorpus-v0.3__SNO-525.xml");

                int senCount = 0;

                try {
                    reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),
                            Charset.forName("UTF-8")));
                    String line = "";
                    while ((line = reader.readLine()) != null) {
                        if (!line.matches(".*<text>.*"))
                            continue;
                        line = line.replaceAll(".*<text>", "").replaceAll(" ?</text>.*", "");
                        // line = line.replaceAll("^ ","");
                        line = line.replaceAll("&amp;", "&");

                        if (fix_dointonight & line.contains("doin'tonight"))
                            line = line.replaceAll("doin'tonight", "doin tonight");

                        // System.err.println(line);
                        Sentence s = parseX(line);
                        anno.get(senCount).apply(s, true);
                        // System.out.println("---");
                        senCount++;
                        /**/c.addSentence(s);/**/
                        /** writer.write(s);/ **/
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    System.exit(1);
                } finally {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.exit(1);
                    }
                }

                /**/c.addCoreferenceChainsFromFile("/disk/scratch/discourse_srl/fn_coref/" + filename);/**/

                for (CorpusSentence cs : c) {
                    writer.specialwrite(cs);
                }/**/
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

    }

    public static Sentence parseX(String line)
            throws Exception
    {
        int offset = 0;
        while (line.startsWith(" ")) {
            line = line.substring(1);
            offset++;
        }

        List<StringInText> words = Arrays.asList(new WhiteSpaceTokenizer().tokenizeplus(line));
        String[] array = new String[words.size()];
        for (int i = 0; i < array.length; i++)
            array[i] = words.get(i).word();
        Sentence s = new Sentence(pp.preprocess(array), false);
        for (int i = 0; i < array.length; i++) {
            s.get(i).setBegin(words.get(i).begin() + offset);
            s.get(i).setEnd(words.get(i).end() + offset);
        }
        return s;
    }
}
