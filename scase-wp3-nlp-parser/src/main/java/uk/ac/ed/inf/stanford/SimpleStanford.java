package uk.ac.ed.inf.stanford;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import uk.ac.ed.inf.srl.corpus.Sentence;
import uk.ac.ed.inf.srl.io.AllCoNLL09Reader;
import uk.ac.ed.inf.srl.io.CoNLL09Writer;
import uk.ac.ed.inf.srl.io.SentenceWriter;

import edu.stanford.nlp.process.Tokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;

public class SimpleStanford {

	LexicalizedParser lp;
	GrammaticalStructureFactory gsf;
	public static void main(String[] args) throws IOException {
		SimpleStanford c2s = new SimpleStanford();
	}
	
	public SimpleStanford() throws IOException {
	    lp = LexicalizedParser.loadModel("edu/stanford/nlp/models/lexparser/englishRNN.ser.gz");
	    TreebankLanguagePack tlp = lp.getOp().langpack();
		BufferedReader br = new BufferedReader(new FileReader(new File("../geoquery/berkeley/geo/train/geo880.e")));
	    
		String line = "";
		while((line = br.readLine())!=null) {
			TokenizerFactory<CoreLabel> tokenizerFactory =
				PTBTokenizer.factory(new CoreLabelTokenFactory(), "");
			Tokenizer<CoreLabel> tok =
				tokenizerFactory.getTokenizer(new StringReader(line));
			List<CoreLabel> rawWords2 = tok.tokenize();
			Tree parse = lp.apply(rawWords2);
			System.out.println(parse);
		}
	    br.close();
	    //GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
	    //GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);
	    //List<TypedDependency> tdl = gs.typedDependenciesCCprocessed();
	    //System.out.println(tdl);
	    //System.out.println();
	    
	}
}