package uk.ac.ed.inf.srl.util;

import java.io.File;

import uk.ac.ed.inf.srl.languages.Language;
import uk.ac.ed.inf.srl.options.FullPipelineOptions;

public class FileExistenceVerifier {
	
	/**
	 * Checks if a file can be exists, and if it can be read.
	 * @param files vararg number of files to check
	 * @return null if all is good, otherwise a String containing the error message.
	 */
	public static String verifyFiles(File... files){
		StringBuilder sb=new StringBuilder();
		for(File f:files){
			if(f==null || !f.exists()){
			    sb.append("File "+f+" does not exist.%n");
			}
			if(f==null || !f.canRead()){
			    sb.append("File "+f+" can not be read.%n");
			}
		}
		if(sb.length()==0)
			return null;
		else
			return sb.toString();
	}
	
	public static String verifyCompletePipelineAlwaysNecessaryFiles(FullPipelineOptions options){
		return verifyFiles(options.tagger,options.parser,options.srl);
	}
	
	public static String verifyCompletePipelineAllNecessaryModelFiles(FullPipelineOptions options){
		String error1=verifyCompletePipelineAlwaysNecessaryFiles(options);
		String error2=Language.getLanguage().verifyLanguageSpecificModelFiles(options);
		if(error1!=null){
			if(error2!=null){
				return error1+error2;
			} else {
				return error1;
			}
		} else if(error2!=null){
			return error2;
		} else {
			return null;
		}
	}
	
}
