package uk.ac.ed.inf.srl.scorer;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import uk.ac.ed.inf.srl.corpus.Predicate;
import uk.ac.ed.inf.srl.corpus.Sentence;
import uk.ac.ed.inf.srl.corpus.Word;

public class ArgumentClassificationScorer extends AbstractScorer {

	
	public static double score(Sentence gold, Sentence parsed) {
		if(gold.getPredicates().size()==0 && parsed.getPredicates().size()==0)
			return 1;
		int tp=0,fp=0,fn=0;
		for(Predicate pred:gold.getPredicates()){
			Map<Word, String> goldmap = pred.getArgMap();
			Map<Word, String> predmap = ((Predicate)parsed.get(pred.getIdx())).getArgMap();
			for(Word w : goldmap.keySet()) {
				int index=w.getIdx();
				if(goldmap.get(w).equals(predmap.get(parsed.get(index)))) {
					tp++;
				} else {
					fp++;
				}
			}

		}
		double p=(double) tp/(tp+fp);
		double r=(double) tp/(tp+fp);
		if(p+r>0)
			return 2*p*r/(p+r);
		else
			return 0;		
	}

	@Override
	public double computeScore(Sentence gold, Sentence parsed) {
		return score(gold,parsed);
	}

	
	
}
