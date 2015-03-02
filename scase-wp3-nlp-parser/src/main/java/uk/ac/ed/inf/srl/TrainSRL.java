package uk.ac.ed.inf.srl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipOutputStream;

import uk.ac.ed.inf.srl.io.ANNConverter;
import uk.ac.ed.inf.srl.options.CompletePipelineCMDLineOptions;
import uk.ac.ed.inf.srl.options.LearnOptions;
import uk.ac.ed.inf.srl.pipeline.Reranker;

public class TrainSRL {
	
	public static void main(String[] args) throws IOException {
		if(args.length<3) {// || !new File(args[0]).exists() || !new File(args[1]).exists()) {
			System.err.println("Usage:");
			System.err.println(" java -cp <classpath> " + TrainSRL.class.getName()
					+ " [PREPROCESSINGOPTIONS] <input-text> <input-annotations> <model-file>");
			System.exit(1);
		}
		
		String[] opts = new String[args.length+1];
		opts[0] = "eng";
		opts[1] = "-tokenize";
		opts[2] = "-srl";
		opts[3] = "/dev/null";
		for(int i=0; i<args.length-3; i++) opts[i+4] = args[i];
		CompletePipelineCMDLineOptions options = new CompletePipelineCMDLineOptions(opts);
		
		File f = File.createTempFile("srlinput", ".conll"); // temporarily create a preprocessed version of the input
		//f.deleteOnExit();
		
		options.output = f;
		if(args[args.length-2].endsWith(".ann")) {
			ANNConverter conv = new ANNConverter(options);
			conv.convertInput(args[args.length-3], args[args.length-2]);
			conv.close();
		} else  {
			/** TODO: TTL not yet supported **/
			if(args[args.length-2].endsWith(".ttl")) { }
			
			System.err.println("Unsupported annotation format: " +args[args.length-2].replaceAll("^.*\\.", ""));
			System.exit(1);
		}
		
		String[] LearnOptionArgs = new String[]{"eng", f.getAbsolutePath(), args[args.length-1], "-reranker"};
		Learn.main(LearnOptionArgs);
		
        /*LearnOptions learnOptions = new LearnOptions(LearnOptionArgs);        
        ZipOutputStream zos = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(learnOptions.modelFile)));        
        new Reranker(learnOptions, zos);*/

	}

}
