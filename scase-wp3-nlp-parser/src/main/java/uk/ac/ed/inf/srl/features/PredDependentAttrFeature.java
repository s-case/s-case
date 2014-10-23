package uk.ac.ed.inf.srl.features;

import uk.ac.ed.inf.srl.corpus.Predicate;
import uk.ac.ed.inf.srl.corpus.Sentence;
import uk.ac.ed.inf.srl.corpus.Word;
import uk.ac.ed.inf.srl.corpus.Word.WordData;

public class PredDependentAttrFeature extends AttrFeature {
	private static final long serialVersionUID = 1L;

	protected PredDependentAttrFeature(FeatureName name, WordData attr,	TargetWord tw, boolean usedForPredicateIdentification, String POSPrefix) {
		super(name, attr, tw, false, usedForPredicateIdentification, POSPrefix);
	}
	
	@Override
	public String getFeatureString(Sentence s, int predIndex, int argIndex) {
		if(wordExtractor.getWord(s, predIndex, argIndex)==null) return null;
		return wordExtractor.getWord(s, predIndex, argIndex).getAttr(attr);
	}

	@Override
	public String getFeatureString(Predicate pred, Word arg) {
		if(wordExtractor.getWord(pred, arg)==null) return null;
		return wordExtractor.getWord(pred, arg).getAttr(attr);
	}

}
