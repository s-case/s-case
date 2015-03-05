package uk.ac.ed.inf.srl;

import java.io.File;
import java.io.IOException;

import uk.ac.ed.inf.srl.io.ANNConverter;
import uk.ac.ed.inf.srl.options.CompletePipelineCMDLineOptions;

public class TrainSRL {
	
	public static void main(String[] args) throws IOException {
		int pipelineOpts = 0;
		boolean modelfile = false;
		for(int i=0; i<args.length; i++) {
			if(modelfile && !args[i].startsWith("-")) {
				modelfile=false;
				pipelineOpts++;
			}
			else if(args[i].startsWith("-")) {
				modelfile=true;
				pipelineOpts++;				
			} else {
				break;
			}
		}
				
		if((args.length - pipelineOpts) < 3) {
			System.err.println("Usage:");
			System.err.println(" java -cp <classpath> " + TrainSRL.class.getName()
					+ " [PREPROCESSINGOPTIONS] <input-text> <input-annotations> [<input-text2> <input-annotations2> ...] <model-file>");
			System.exit(1);
		}
		
		String[] opts = new String[4+pipelineOpts];
		opts[0] = "eng";
		opts[1] = "-tokenize";
		opts[2] = "-srl";
		opts[3] = "/dev/null";
		for(int i=0; i<pipelineOpts; i++) opts[i+4] = args[i];
		CompletePipelineCMDLineOptions options = new CompletePipelineCMDLineOptions(opts);
		
		File f = File.createTempFile("srlinput", ".conll"); // temporarily create a preprocessed version of the input
		
		options.output = f;
		ANNConverter conv = new ANNConverter(options);
		for(int i=pipelineOpts; i<args.length-1; i+=2) {
			if(args[i+1].endsWith(".ann")) {
				conv.convertInput(args[i], args[i+1]);
			} else  {
				/** TODO: TTL not yet supported **/
				if(args[i+1].endsWith(".ttl")) { }				
				System.err.println("Unsupported annotation format: " +args[i+1].replaceAll("^.*\\.", ""));
				System.exit(1);
			}
		}
		conv.close();
		
		String[] LearnOptionArgs = new String[]{"eng", f.getAbsolutePath(), args[args.length-1], "-reranker"};
		Learn.main(LearnOptionArgs);
	}

}
