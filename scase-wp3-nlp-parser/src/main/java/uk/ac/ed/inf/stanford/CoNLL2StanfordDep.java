package uk.ac.ed.inf.stanford;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import uk.ac.ed.inf.srl.corpus.CorpusSentence;
import uk.ac.ed.inf.srl.corpus.Sentence;
import uk.ac.ed.inf.srl.io.AllCoNLL09Reader;
import uk.ac.ed.inf.srl.io.CoNLL09Writer;
import uk.ac.ed.inf.srl.io.SentenceWriter;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.Label;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.Tokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.trees.Dependency;
import edu.stanford.nlp.trees.GrammaticalStructure;
import edu.stanford.nlp.trees.GrammaticalStructureFactory;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreebankLanguagePack;
import edu.stanford.nlp.trees.TypedDependency;

public class CoNLL2StanfordDep {

	LexicalizedParser lp;
	GrammaticalStructureFactory gsf;
	public static void main(String[] args) throws IOException {
		CoNLL2StanfordDep c2s = new CoNLL2StanfordDep();
		//c2s.tag("../../mate++/CoNLL2008-ST-English-development.txt");
		//c2s.tag("../../mate++/CoNLL2008-ST-English-ood.txt");
		//c2s.tag("eng.out");
		
		c2s.tag("annotations/fn15_test_coref.conll");
	}
	
	public CoNLL2StanfordDep() {
	    lp = LexicalizedParser.loadModel("edu/stanford/nlp/models/lexparser/englishRNN.ser.gz");
	    TreebankLanguagePack tlp = lp.getOp().langpack();
	    gsf = tlp.grammaticalStructureFactory();
	}
	
	public void tag(String filename) throws IOException {
		AllCoNLL09Reader reader = new AllCoNLL09Reader(new File(filename));
		List<Sentence> sentences = reader.readAll();
		reader.close();
		
		int i=0;
		String lastsource = "";
		for(Sentence s : sentences) {
			tag(s);
			//if(i++==3)
			//	break;
			if(((CorpusSentence)s).getMyCorpus().indexOf(s)==0) {
				if(!lastsource.equals(""))
					((CorpusSentence)sentences.get(i-1)).getMyCorpus().addCoreferenceChainsFromFile("/disk/scratch/discourse_srl/fn_coref/"+lastsource);
				
				lastsource = ((CorpusSentence)s).getMyCorpus().getName();				
			}
			i++;
		}
		((CorpusSentence)sentences.get(i-1)).getMyCorpus().addCoreferenceChainsFromFile("/disk/scratch/discourse_srl/fn_coref/"+lastsource);
		
		//System.exit(0);
		SentenceWriter writer=new CoNLL09Writer(new File("annotations/fn15_test_coref_stanford.conll"));
		//SentenceWriter writer=new CoNLL09Writer(new File("../../mate++/"+(new File("Free918.parsed").getName())+"+stanforddep"));
		for(Sentence s : sentences)
			writer.specialwrite(s);
		writer.close();
		
	}
		
	public void tag(Sentence s) {
		List<String> sentence = new LinkedList<String>();
		Integer[] old_index = new Integer[s.size()];
		for(int i=0; i<old_index.length; i++)
			old_index[i]=-1;
			
		int ii = 0;
		old_index[ii++] = 0;
		int begin = 0;
		for(int i=1; i<old_index.length; i++) {
			if(s.get(i).getForm().equals("_"))
				continue;
			
			s.get(i).setBegin(begin);
			s.get(i).setEnd(begin+s.get(i).getForm().length());
			begin = s.get(i).getEnd()+1;
			
			String w = s.get(i).getForm();
			if(w.equals("(") || w.equals("[") || w.equals("{"))
				w = "-LRB-";
			if(w.equals(")") || w.equals("]") || w.equals("}"))
				w = "-RRB-";
			
			sentence.add(w);
			old_index[ii++] = i;
		}
		
		String[] sent = new String[sentence.size()];
		for(int i=0; i<sent.length; i++)
			sent[i] = sentence.get(i);
		
		//System.err.println(Arrays.toString(sent));
		System.err.print(".");	
		 
		
	    Tree parse = parse(sent);
	    
	    for(TypedDependency dep : getDeps(parse)) {
	    	int i=dep.dep().label().index();
	    	int head=dep.gov().label().index();
	    	//System.err.println(dep.dep().label().word() +" ("+i+") -"+ dep.reln().getShortName() +"> "+ dep.gov().label().word() +" ("+head+")");

	    	String rel = dep.reln().getShortName().toUpperCase();
	    	
	    	s.get(old_index[i]).setHead(s.get(old_index[head]));
	    	s.get(old_index[i]).setHeadId(old_index[head]);
	    	s.get(old_index[i]).setDeprel(rel);
	    }
	    
	    //int i=0;
	    //for(CoreLabel l : parse.taggedLabeledYield()) {
	    //	if(old_index[i]>-1)
	    //		s.get(old_index[i]).setPOS(l.tag());    	
	    //	i++;
	    //}
    
	    //parse.pennPrint();
	    //System.out.println(s.toString());
	}
	
	public Tree parse(String[] words) {
		List<CoreLabel> rawWords = edu.stanford.nlp.ling.Sentence.toCoreLabelList(words);   
		return lp.apply(rawWords);
	}
	
	public Collection<TypedDependency> getDeps(Tree parse) {
		// typedDependenciesCCprocessed();
	    GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);
	    return gs.allTypedDependencies();
	}
}
