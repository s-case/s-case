package uk.ac.ed.inf.srl;

import java.util.zip.ZipFile;

import uk.ac.ed.inf.srl.corpus.Sentence;
import uk.ac.ed.inf.srl.io.CoNLL09Writer;
import uk.ac.ed.inf.srl.io.DepsOnlyCoNLL09Reader;
import uk.ac.ed.inf.srl.io.SRLOnlyCoNLL09Reader;
import uk.ac.ed.inf.srl.io.SentenceReader;
import uk.ac.ed.inf.srl.io.SentenceWriter;
import uk.ac.ed.inf.srl.options.ParseOptions;
import uk.ac.ed.inf.srl.pipeline.Pipeline;
import uk.ac.ed.inf.srl.pipeline.Reranker;
import uk.ac.ed.inf.srl.pipeline.Step;
import uk.ac.ed.inf.srl.util.Util;

public class Parse {
	public static ParseOptions parseOptions;
	
	public static void main(String[] args) throws Exception{
		long startTime=System.currentTimeMillis();
		parseOptions=new ParseOptions(args);
		
		SemanticRoleLabeler srl;
		
		if(parseOptions.useReranker){
			srl = new Reranker(parseOptions);
			//srl = Reranker.fromZipFile(zipFile,parseOptions.skipPI,parseOptions.global_alfa,parseOptions.global_aiBeam,parseOptions.global_acBeam);
		} else {
			ZipFile zipFile=new ZipFile(parseOptions.modelFile);
			srl = parseOptions.skipPD ? Pipeline.fromZipFile(zipFile,new Step[]{Step.ai,Step.ac})
					: parseOptions.skipPI ? Pipeline.fromZipFile(zipFile,new Step[]{Step.pd,Step.ai,Step.ac/*,Step.po, Step.ao*/}) 
					: Pipeline.fromZipFile(zipFile);
			zipFile.close();
		}
		
		SentenceWriter writer=new CoNLL09Writer(parseOptions.output);
		SentenceReader reader=parseOptions.skipPI ? new SRLOnlyCoNLL09Reader(parseOptions.inputCorpus) : new DepsOnlyCoNLL09Reader(parseOptions.inputCorpus);
		int senCount=0;
		for(Sentence s:reader){
			senCount++;
			if(senCount%100==0)
				System.out.println("Parsing sentence "+senCount);
			srl.parseSentence(s);
			writer.write(s);
		}
		writer.close();
		reader.close();
		long totalTime=System.currentTimeMillis()-startTime;
		System.out.println("Done.");
		System.out.println(srl.getStatus());
		System.out.println();
		System.out.println("Total execution time: "+Util.insertCommas(totalTime)+"ms");
	}
}
