package uk.ac.ed.inf.srl;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipOutputStream;

import uk.ac.ed.inf.srl.io.AllCoNLL09Reader;
import uk.ac.ed.inf.srl.io.SentenceReader;
import uk.ac.ed.inf.srl.options.LearnOptions;
import uk.ac.ed.inf.srl.pipeline.Pipeline;
import uk.ac.ed.inf.srl.pipeline.Reranker;
import uk.ac.ed.inf.srl.util.BrownCluster;
import uk.ac.ed.inf.srl.util.Util;
import uk.ac.ed.inf.srl.util.WordEmbedding;

public class Learn {

	public static LearnOptions learnOptions;
	
	
	public static void main(String[] args) throws IOException{
		long startTime=System.currentTimeMillis();
		learnOptions=new LearnOptions(args);
		learn();
		System.out.println("Total time consumtion: "+Util.insertCommas(System.currentTimeMillis()-startTime)+"ms");
	}
	
	private static void learn() throws IOException {
		ZipOutputStream zos=new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(learnOptions.modelFile)));
		if(learnOptions.trainReranker){
			new Reranker(learnOptions,zos);
		} else {
			BrownCluster bc=Learn.learnOptions.brownClusterFile==null?null:new BrownCluster(Learn.learnOptions.brownClusterFile);
			WordEmbedding we=Learn.learnOptions.wordEmbeddingFile==null?null:new WordEmbedding(Learn.learnOptions.wordEmbeddingFile);

			SentenceReader reader=new AllCoNLL09Reader(learnOptions.inputCorpus);
			Pipeline.trainNewPipeline(reader, learnOptions.getFeatureFiles(), zos, bc, we);
		}
		zos.close();
	}
}
