package uk.ac.ed.inf.srl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

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
import uk.ac.ed.inf.srl.util.FileExistenceVerifier;
import uk.ac.ed.inf.srl.util.StandOffAnnotation;
import uk.ac.ed.inf.srl.util.Util;

public class PreprocessAndMerge {
	private static CompletePipelineCMDLineOptions options;
	private static Preprocessor pp;

	
	public static void main(String[] args) throws Exception{
		
		args = new String[]{"eng", "-tokenize",
							"-lemma", "models/lemma-train-eng.model",
							"-parser", "models/parse-train-eng.model",
							"-tagger", "models/tagger-train-eng.model",
							"-srl", "/dev/null",
							};
		AnnotationMerger am = new AnnotationMerger();

		long startTime=System.currentTimeMillis();
		options=new CompletePipelineCMDLineOptions();
		options.parseCmdLineArgs(args);
		String error=FileExistenceVerifier.verifyCompletePipelineAllNecessaryModelFiles(options);
		if(error!=null){
			System.err.println(error);
			System.err.println();
			System.err.println("Aborting.");
			System.exit(1);
		}
		pp=Language.getLanguage().getPreprocessor(options);
		BufferedReader reader = null;			
		
		int total_senCount = 0;
		for(String input : new String[]{
				"annotations/new/restmarks.txt",
				"annotations/new/last126.txt",
				"annotations/new/next126.txt",
				"annotations/new/first60.txt"}) {
			
			pp.resetStartPosition();
			
			SentenceWriter writer=new CoNLL09Writer(new File(input.replace(".txt", ".conll")));
			
			StandOffAnnotation anno1 = new StandOffAnnotation(new File(input.replace(".txt", "_cs.txt")));
			StandOffAnnotation anno2 = new StandOffAnnotation(new File(input.replace(".txt", "_vk.txt")));
			
			int senCount=0;
			int agreements = 0;
			int disagreements = 0;
			
			try {
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(input)),Charset.forName("UTF-8")));
				String line = "";
				while((line = reader.readLine())!=null) {
	
					//if(line.startsWith("Client application must be able to retrieve a prioritized friends list"))
					//	line = line.replace("/", " ");
					//if(line.startsWith("User must be able to select a GiftCase-user"))
					//	line = line.replace("-"," ");
					
					
					/** Hacks for some of the next126 sentences **/
					//line = line.replace("-", " "); 
					line = line.replace("/", " ");
					
					System.out.printf("%3d: %s\n", (senCount+1), line);
					Sentence s = parseX(line);
					int[] counts = am.mergeAndApply(anno1.get(senCount), anno2.get(senCount), s);
					
					agreements += counts[0];
					disagreements += counts[1];
					// disagreements += counts[2]; // count conflicts + missing
					
					//anno.get(senCount).apply(s);
					System.out.println("---");
					senCount++;
					writer.write(s);
				}
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);			
			} finally {
				try {
					writer.close();
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
					System.exit(1);
				}
			}
			total_senCount += senCount;
		}
		
		long totalTime=System.currentTimeMillis()-startTime;
		System.out.println("Done.");
		System.out.println();
		//System.out.printf("Raw agreement: %2f\n", (double)(agreements)/(double)(agreements+disagreements) );
		am.computeKappa();
		System.out.println();
		System.out.println("Processed "+total_senCount+ " sentence(s)");
		System.out.println("Total execution time: "+Util.insertCommas(totalTime)+"ms");
	}
	
	public static Sentence parseX(String line) throws Exception{
		List<StringInText> words = Arrays.asList(pp.tokenizeplus(line));		
		String[] array = new String[words.size()];
		for(int i=0; i<array.length; i++) array[i] = words.get(i).word();
		Sentence s=new Sentence(pp.preprocess(array),false);
		for(int i=0; i<array.length; i++) {
			s.get(i).setBegin(words.get(i).begin());
			s.get(i).setEnd(words.get(i).end());
		}
		
		return s;
	}
}
