package uk.ac.ed.inf.srl.ml.liblinear;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import uk.ac.ed.inf.srl.Learn;
import uk.ac.ed.inf.srl.ml.Model;
import de.bwaldvogel.liblinear.InvalidInputDataException;
import de.bwaldvogel.liblinear.Linear;
import de.bwaldvogel.liblinear.Train;

public class LibLinearModel
        implements Model
{
    private static final long serialVersionUID = 1L;

    private WeightVector weightVector;
    private List<Integer> labels;
    private int features;
    private double bias;
    @SuppressWarnings("unused")
    private String solverType; // For future reference.

    LibLinearModel(File modelFile, boolean sparse) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(modelFile));
        parseHeader(in);
        this.weightVector = WeightVector.parseWeights(in, this.features, this.labels.size(), this.bias, sparse);
    }

    // LibLinearModel(File modelFile) throws IOException {
    // this(modelFile,false);
    // }

    @Override
    public Integer classify(Collection<Integer> indices, Map<Integer, Double> nonbinFeats)
    {
        if (this.labels.get(0) == 0 && this.labels.get(1) == 1) {
            double[] probs = this.weightVector.computeAllProbs(indices, nonbinFeats);
            probs[0] -= 0.2;
            probs[1] += 0.2;
            return (probs[0] > probs[1] ? 0 : 1);
        } else {
            return this.labels.get(this.weightVector.computeBestClass(indices, nonbinFeats));
        }
    }

    @Override
    public List<Label> classifyProb(Collection<Integer> indices, Map<Integer, Double> nonbinFeats)
    {
        ArrayList<Label> ret = new ArrayList<Label>(this.labels.size());
        double[] probs = this.weightVector.computeAllProbs(indices, nonbinFeats);
        for (int i = 0; i < probs.length; ++i) {
            ret.add(new Label(this.labels.get(i), probs[i]));
        }
        Collections.sort(ret, Collections.reverseOrder());
        return ret;
    }

    public void sparsify()
    {
        if (isSparse()) {
            return;
        }
        if (this.weightVector.classes == 2) {
            this.weightVector = new WeightVector.BinarySparseVector((WeightVector.BinaryLibLinearVector) this.weightVector);
        } else {
            this.weightVector = new WeightVector.MultipleSparseVector((WeightVector.MultipleLibLinearVector) this.weightVector);
        }
    }

    private boolean isSparse()
    {
        return this.weightVector instanceof WeightVector.BinarySparseVector
                || this.weightVector instanceof WeightVector.MultipleSparseVector;
    }

    protected void parseHeader(BufferedReader in)
            throws IOException
    {
        this.labels = new ArrayList<Integer>();
        this.solverType = in.readLine().substring("solver_type ".length());
        if (Short.parseShort(in.readLine().substring("nr_class ".length())) == 0) {
            throw new IOException("Error while parsing header! Model is empty!");
        }
        String[] l = in.readLine().substring("label ".length()).split(" ");
        for (String s : l) {
            this.labels.add(Integer.parseInt(s));
        }
        this.features = Integer.parseInt(in.readLine().substring("nr_feature ".length()));
        this.bias = Double.parseDouble(in.readLine().substring("bias ".length()));
        in.readLine(); // This is the line with the w, we discard it.
    }

    static void trainModel(File dataFile, File outputFile)
            throws IOException, InterruptedException
    {
        // String[] llargs=new
        // String[]{"-q","-s","0","-B","1",dataFile.toString(),outputFile.toString()};

        if (Learn.learnOptions.liblinearBinary != null) {
            String[] llargs = new String[] { "-q", "-B", "1.0", "-s", "0", dataFile.toString(), outputFile.toString() };
            StringBuilder cmd = new StringBuilder(Learn.learnOptions.liblinearBinary.toString());
            for (String arg : llargs) {
                cmd.append(' ').append(arg);
            }
            Process p = Runtime.getRuntime().exec(cmd.toString());
            if (p.waitFor() != 0) {
                throw new Error("LibLinear binary exited with non-zero exit value: " + p.exitValue());
            }
        } else {
            try {
                String[] llargs = new String[] { "-s", "0", "-B", "1.0", dataFile.toString(), outputFile.toString() };
                Linear.disableDebugOutput(); // We would like to have llargs=new
                                             // String[]{"-q","-s","0",dataFile.toString(),outputFile.toString()};,
                                             // but there is something buggy with the java
                                             // implmentation.
                Train.main(llargs);
            } catch (InvalidInputDataException e) {
                e.printStackTrace();
                System.err.println("LibLinear java failed. Look into this.");
                System.exit(1);
            }
        }
    }

}
