package uk.ac.ed.inf.srl.features;

import java.util.LinkedList;
import java.util.List;

import uk.ac.ed.inf.srl.corpus.Corpus;
import uk.ac.ed.inf.srl.corpus.CorpusSentence;
import uk.ac.ed.inf.srl.corpus.Predicate;
import uk.ac.ed.inf.srl.corpus.Sentence;
import uk.ac.ed.inf.srl.corpus.Word;
import uk.ac.ed.inf.srl.corpus.Word.WordData;

public class CorefSetFeature extends SetFeature {
	private static final long serialVersionUID = 1L;

	WordData attr;
	
	protected CorefSetFeature(FeatureName name,WordData attr,boolean usedForPredicateIdentification,String POSPrefix) {
		super(name,false,usedForPredicateIdentification,POSPrefix);
		this.attr=attr;
	}
	
	@Override
	public String[] getFeatureStrings(Sentence s, int predIndex, int argIndex) {
		return makeFeatureStrings(s.get(argIndex)); 
	}

	@Override
	public String[] getFeatureStrings(Predicate pred, Word arg) {
		return makeFeatureStrings(arg);
	}
	
	private String[] makeFeatureStrings(Word arg){
		if(arg==null) return new String[0];
		
		List<String> roles = new LinkedList<String>();
		
		Corpus c = ((CorpusSentence)arg.getMySentence()).getMyCorpus();
		String ccid = c.corefId(arg);
		
		int curr_snum = c.indexOf(arg.getMySentence());
		int curr_wnum = arg.getIdx();
		
		if(ccid.equals("_"))
			return new String[0];
		
		for(String mention : c.chains.get(Integer.parseInt(ccid))) {
			String[] snum_wnum = mention.split("\\.");
			int snum = Integer.parseInt(snum_wnum[0]);
			int wnum = Integer.parseInt(snum_wnum[1]);
			if(snum>curr_snum || (snum==curr_snum && wnum<=curr_wnum) ) continue;
			if(snum<curr_snum-2) continue; // only look at previous 2 sentences
			
			Sentence s = c.get(snum);
			Word w = s.get(wnum);
			for(Predicate p : s.getPredicates()) {
				if(p.getArgMap().containsKey(w))
					roles.add(p.getArgMap().get(w));
			}
		}
	
		String[] ret=new String[roles.size()];
		ret = roles.toArray(ret);

		return ret;		
	}

	@Override
	protected void performFeatureExtraction(Sentence curr_s, boolean allWords) {
		for(int i=1,size=curr_s.size();i<size;++i) {
			Word arg = curr_s.get(i);
			
			Corpus c = ((CorpusSentence)arg.getMySentence()).getMyCorpus();
			String ccid = c.corefId(arg);
		
			int curr_snum = c.indexOf(arg.getMySentence());
			int curr_wnum = arg.getIdx();
		
			if(ccid.equals("_"))
				continue;
		
			for(String mention : c.chains.get(Integer.parseInt(ccid))) {
				String[] snum_wnum = mention.split("\\.");
				int snum = Integer.parseInt(snum_wnum[0]);
				int wnum = Integer.parseInt(snum_wnum[1]);
				if(snum>curr_snum || (snum==curr_snum && wnum<=curr_wnum) ) continue;
				if(snum<curr_snum-2) continue; // only look at previous 2 sentences

				Sentence s = c.get(snum);
				Word w = s.get(wnum);
				for(Predicate p : s.getPredicates()) {
					if(p.getArgMap().containsKey(w))
						addMap(p.getArgMap().get(w));
				}
			}
		}
		/*if(allWords){
			for(int i=1,size=s.size();i<size;++i){
				if(doExtractFeatures(s.get(i)))
					for(Word child:s.get(i).getChildren()){
						addMap(child.getAttr(attr));
					}
			}
		} else {
			for(Predicate pred:s.getPredicates()){
				if(doExtractFeatures(pred))
					for(Word child:pred.getChildren())
						addMap(child.getAttr(attr));
			}
		}*/
	}
}
