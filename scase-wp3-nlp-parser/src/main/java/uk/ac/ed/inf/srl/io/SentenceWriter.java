package uk.ac.ed.inf.srl.io;

// import se.lth.cs.srl.corpus.Corpus;
import uk.ac.ed.inf.srl.corpus.Sentence;

public interface SentenceWriter
{

    public void write(Sentence s);

    public void close();

    public void specialwrite(Sentence s);

}
