package uk.ac.ed.inf.srl.io;

import java.util.List;

import uk.ac.ed.inf.srl.corpus.Sentence;

public interface SentenceReader extends Iterable<Sentence>{

	List<Sentence> readAll();

	public void close();

	
	
}
