package uk.ac.ed.inf.srl.io;

import java.io.File;
import java.io.IOException;

import uk.ac.ed.inf.srl.corpus.Sentence;

public class DepsOnlyCoNLL09Reader
        extends AbstractCoNLL09Reader
{

    public DepsOnlyCoNLL09Reader(File file) {
        super(file);
    }

    @Override
    protected void readNextSentence()
            throws IOException
    {
        String str;
        Sentence sen = null;
        StringBuilder senBuffer = new StringBuilder();
        while ((str = in.readLine()) != null) {
            if (!str.trim().equals("")) {
                senBuffer.append(str).append("%n");
            } else {
                sen = Sentence.newDepsOnlySentence(NEWLINE_PATTERN.split(senBuffer.toString()));
                break;
            }
        }
        if (sen == null) {
            nextSen = null;
            in.close();
        } else {
            nextSen = sen;
        }
    }

}
