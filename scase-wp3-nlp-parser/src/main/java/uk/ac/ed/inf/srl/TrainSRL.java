package uk.ac.ed.inf.srl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipOutputStream;

import uk.ac.ed.inf.srl.io.ANNConverter;
import uk.ac.ed.inf.srl.options.LearnOptions;
import uk.ac.ed.inf.srl.pipeline.Reranker;

public class TrainSRL {
	
	public static void main(String[] args) throws IOException {
		if(args.length<3) {// || !new File(args[0]).exists() || !new File(args[1]).exists()) {
			System.err.println("Usage:");
			System.err.println(" java -cp <classpath> " + TrainSRL.class.getName()
					+ " <input-text> <input-annotations> <model-file>");
			System.exit(1);
		}
		
		File f = File.createTempFile("srlinput", ".conll"); // temporarily create a preprocessed version of the input
		//f.deleteOnExit();
		
		if(args[1].endsWith(".ann")) {
			ANNConverter conv = new ANNConverter(f);
			conv.convertInput(args[0], args[1]);
			conv.close();
		} else  {
			/** TODO: TTL not yet supported **/
			if(args[1].endsWith(".ttl")) { }
			
			System.err.println("Unsupported annotation format: " +args[1].replaceAll("^.*\\.", ""));
			System.exit(1);
		}
		
		String[] LearnOptionArgs = new String[]{"eng", f.getAbsolutePath(), args[2], "-reranker"};
		Learn.main(LearnOptionArgs);
		
        /*LearnOptions learnOptions = new LearnOptions(LearnOptionArgs);        
        ZipOutputStream zos = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(learnOptions.modelFile)));        
        new Reranker(learnOptions, zos);*/

	}

}
