package uk.ac.ed.inf.srl.features;

import java.util.List;

import uk.ac.ed.inf.srl.corpus.Predicate;
import uk.ac.ed.inf.srl.corpus.Sentence;
import uk.ac.ed.inf.srl.corpus.Word;
import uk.ac.ed.inf.srl.corpus.Word.WordData;
import uk.ac.ed.inf.srl.util.BrownCluster;
import uk.ac.ed.inf.srl.util.BrownCluster.ClusterVal;

public class BrownPathFeature
        extends SingleFeature
{
    private static final long serialVersionUID = 1L;

    private static final String UP = "0";
    private static final String DOWN = "1";

    private boolean consider_deptree;

    private BrownCluster bc;
    private ClusterVal cv;

    protected BrownPathFeature(FeatureName name, boolean consider_deptree, BrownCluster bc, ClusterVal cv,
            String POSPrefix)
    {
        super(name, true, false, POSPrefix);
        this.consider_deptree = consider_deptree;
        this.bc = bc;
        this.cv = cv;
    }

    @Override
    protected void performFeatureExtraction(Sentence s, boolean allWords)
    {
        for (Predicate pred : s.getPredicates()) {
            if (doExtractFeatures(pred))
                for (Word arg : pred.getArgMap().keySet()) {
                    addMap(getFeatureString(pred, arg));
                }
        }
    }

    @Override
    public String getFeatureString(Sentence s, int predIndex, int argIndex)
    {
        return makeFeatureString(s.get(predIndex), s.get(argIndex));
    }

    @Override
    public String getFeatureString(Predicate pred, Word arg)
    {
        return makeFeatureString(pred, arg);
    }

    public String makeFeatureString(Word pred, Word arg)
    {
        StringBuilder ret = new StringBuilder();
        if (consider_deptree) {
            ret.append("DEP");
            return ret.append(makeDepBasedFeatureString(pred, arg)).toString();
        }

        boolean up = true;
        if (pred.getIdx() < arg.getIdx())
            up = false;

        if (Math.abs(pred.getIdx() - arg.getIdx()) == 1)
            return " ";

        Sentence s = pred.getMySentence();
        for (int i = up ? arg.getIdx() : pred.getIdx(); i < (up ? pred.getIdx() : arg.getIdx()); i++) {
            ret.append(bc.getValue(s.get(i).getForm(), cv));
            ret.append(up ? UP : DOWN);
        }
        return ret.toString();
    }

    public String makeDepBasedFeatureString(Word pred, Word arg)
    {
        boolean up = true;
        List<Word> path = Word.findPath(pred, arg);

        if (path.size() == 0)
            return " ";
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < (path.size() - 1); ++i) {
            Word w = path.get(i);
            ret.append(bc.getValue(w.getForm(), cv));
            if (up) {
                if (w.getHead() == path.get(i + 1)) { // Arrow up
                    ret.append(UP);
                } else { // Arrow down
                    ret.append(DOWN);
                    up = false;
                }
            } else {
                ret.append(DOWN);
            }
        }
        return ret.toString();

    }
}
