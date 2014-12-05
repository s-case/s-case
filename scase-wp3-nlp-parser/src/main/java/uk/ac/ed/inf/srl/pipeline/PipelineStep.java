package uk.ac.ed.inf.srl.pipeline;

import java.io.IOException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import uk.ac.ed.inf.srl.corpus.Sentence;

public interface PipelineStep
{

    public void prepareLearning();

    public void extractInstances(Sentence s);

    public void done();

    public void train();

    public void writeModels(ZipOutputStream zos)
            throws IOException;

    public void readModels(ZipFile zipFile)
            throws IOException, ClassNotFoundException;

    public void parse(Sentence s);

}
