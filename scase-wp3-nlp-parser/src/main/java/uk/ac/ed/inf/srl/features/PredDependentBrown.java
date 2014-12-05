package uk.ac.ed.inf.srl.features;

import uk.ac.ed.inf.srl.corpus.Predicate;
import uk.ac.ed.inf.srl.corpus.Sentence;
import uk.ac.ed.inf.srl.corpus.Word;
import uk.ac.ed.inf.srl.util.BrownCluster;
import uk.ac.ed.inf.srl.util.BrownCluster.ClusterVal;

public class PredDependentBrown
        extends PredDependentAttrFeature
{
    private static final long serialVersionUID = 1L;

    private BrownCluster bc;
    private ClusterVal cv;

    protected PredDependentBrown(FeatureName name, TargetWord tw, boolean includeAllWords, String POSPrefix,
            BrownCluster bc, ClusterVal cv)
    {
        super(name, null, tw, includeAllWords, POSPrefix);
        this.bc = bc;
        this.cv = cv;
    }

    @Override
    public String getFeatureString(Sentence s, int predIndex, int argIndex)
    {
        Word w = wordExtractor.getWord(s, predIndex, argIndex);
        if (w.isBOS())
            return "ROOT";
        return bc.getValue(w.getForm(), cv);
    }

    @Override
    public String getFeatureString(Predicate pred, Word arg)
    {
        Word w = wordExtractor.getWord(pred, arg);
        if (w.isBOS())
            return "ROOT";
        return bc.getValue(w.getForm(), cv);
    }
}
