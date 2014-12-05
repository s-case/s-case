package uk.ac.ed.inf.srl.features;

import uk.ac.ed.inf.srl.corpus.Predicate;
import uk.ac.ed.inf.srl.corpus.Sentence;
import uk.ac.ed.inf.srl.corpus.Word;
import uk.ac.ed.inf.srl.util.BrownCluster;
import uk.ac.ed.inf.srl.util.BrownCluster.ClusterVal;

public class ArgDependentBrown extends ArgDependentAttrFeature{
	private static final long serialVersionUID = 1L;
	
	private BrownCluster bc;
	private ClusterVal cv;
	protected ArgDependentBrown(FeatureName name,TargetWord tw,String POSPrefix,BrownCluster bc,ClusterVal cv) {
		super(name, null, tw, POSPrefix);
		this.bc=bc;
		this.cv=cv;
	}
	
	@Override
	protected void performFeatureExtraction(Sentence s, boolean allWords) {
		for(Predicate p:s.getPredicates()){
			if(doExtractFeatures(p))
				for(Word arg:p.getArgMap().keySet()){
					Word w=wordExtractor.getWord(null, arg);
					if(w!=null)
						addMap(bc.getValue(w.getForm(), cv));				}
		}
	}
	
	@Override
	public String getFeatureString(Sentence s, int predIndex, int argIndex) {
		Word w=wordExtractor.getWord(s, predIndex, argIndex);
		if(w==null)
			return null;
		else
			return bc.getValue(w.getForm(), cv);

	}
	@Override
	public String getFeatureString(Predicate pred, Word arg) {
		Word w=wordExtractor.getWord(pred, arg);
		if(w==null)
			return null;
		else
			return bc.getValue(w.getForm(), cv);
	}
}
