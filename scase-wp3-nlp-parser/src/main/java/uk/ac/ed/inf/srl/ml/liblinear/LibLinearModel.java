package uk.ac.ed.inf.srl.ml.liblinear;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import de.bwaldvogel.liblinear.InvalidInputDataException;
import de.bwaldvogel.liblinear.Linear;
import de.bwaldvogel.liblinear.Train;

import uk.ac.ed.inf.srl.Learn;
import uk.ac.ed.inf.srl.ml.Model;

public class LibLinearModel implements Model {
	private static final long serialVersionUID = 1L;

	private WeightVector weightVector;
	private List<Integer> labels;
	private int features;
	private double bias;
	@SuppressWarnings("unused")
	private String solverType; //For future reference.
	
	LibLinearModel(File modelFile,boolean sparse) throws IOException {
		BufferedReader in=new BufferedReader(new FileReader(modelFile));
		parseHeader(in);
		weightVector=WeightVector.parseWeights(in, features, labels.size(), bias, sparse);
	}
	
//	LibLinearModel(File modelFile) throws IOException {
//		this(modelFile,false);
//	}

	@Override
        public Integer classify(Collection<Integer> indices, Map<Integer,Double> nonbinFeats) {
            if(labels.get(0)==0 && labels.get(1)==1) {
                double[] probs=weightVector.computeAllProbs(indices,nonbinFeats);
                probs[0]-=0.2;
                probs[1]+=0.2;
                return (probs[0]>probs[1]?0:1);
            } else
                return labels.get(weightVector.computeBestClass(indices, nonbinFeats));
	}

	@Override
	public List<Label> classifyProb(Collection<Integer> indices, Map<Integer,Double> nonbinFeats) {
		ArrayList<Label> ret=new ArrayList<Label>(labels.size());
		double[] probs=weightVector.computeAllProbs(indices,nonbinFeats);
		for(int i=0;i<probs.length;++i){
			ret.add(new Label(labels.get(i),probs[i]));
		}
		Collections.sort(ret,Collections.reverseOrder());
		return ret;
	}	

	public void sparsify() {
		if(isSparse())
			return;
		if(weightVector.classes==2)
			weightVector=new WeightVector.BinarySparseVector((WeightVector.BinaryLibLinearVector) weightVector);
		else
			weightVector=new WeightVector.MultipleSparseVector((WeightVector.MultipleLibLinearVector) weightVector);
	}
	
	private boolean isSparse(){
		return weightVector instanceof WeightVector.BinarySparseVector || weightVector instanceof WeightVector.MultipleSparseVector;
	}
	
	protected void parseHeader(BufferedReader in) throws IOException {
		labels=new ArrayList<Integer>();
		solverType=in.readLine().substring("solver_type ".length());
        if(Short.parseShort(in.readLine().substring("nr_class ".length()))==0)
        	throw new IOException("Error while parsing header! Model is empty!");
        String[] l=in.readLine().substring("label ".length()).split(" ");
        for (String s:l)
        	labels.add(Integer.parseInt(s));
        features=Integer.parseInt(in.readLine().substring("nr_feature ".length()));
        bias=Double.parseDouble(in.readLine().substring("bias ".length()));
        in.readLine(); //This is the line with the w, we discard it.
	}
	
	
	static void trainModel(File dataFile,File outputFile) throws IOException, InterruptedException {
		//String[] llargs=new String[]{"-q","-s","0","-B","1",dataFile.toString(),outputFile.toString()};
		
		if(Learn.learnOptions.liblinearBinary!=null){
                    String[] llargs=new String[]{"-q","-B", "1.0","-s","0",dataFile.toString(),outputFile.toString()};
			StringBuilder cmd=new StringBuilder(Learn.learnOptions.liblinearBinary.toString());
			for(String arg:llargs){
				cmd.append(' ').append(arg);
			}
			Process p=Runtime.getRuntime().exec(cmd.toString());
			if(p.waitFor()!=0) {
				throw new Error("LibLinear binary exited with non-zero exit value: "+p.exitValue());
			}
		} else {
			try {
                            String[] llargs=new String[]{"-s","0","-B","1.0",dataFile.toString(),outputFile.toString()};
				Linear.disableDebugOutput(); //We would like to have llargs=new String[]{"-q","-s","0",dataFile.toString(),outputFile.toString()};, but there is something buggy with the java implmentation.
				Train.main(llargs);
			} catch (InvalidInputDataException e) {
				e.printStackTrace();
				System.err.println("LibLinear java failed. Look into this.");
				System.exit(1);
			}
		}
	}

}
