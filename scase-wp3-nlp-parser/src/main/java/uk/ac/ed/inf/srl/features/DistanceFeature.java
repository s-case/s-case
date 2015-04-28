package uk.ac.ed.inf.srl.features;

import java.util.List;

import uk.ac.ed.inf.srl.corpus.Predicate;
import uk.ac.ed.inf.srl.corpus.Sentence;
import uk.ac.ed.inf.srl.corpus.Word;
import uk.ac.ed.inf.srl.corpus.Word.WordData;

public class DistanceFeature
        extends SingleFeature
{
    private static final long serialVersionUID = 1L;

    private boolean consider_deptree;
    private WordData attr;

    protected DistanceFeature(FeatureName name, WordData attr, boolean consider_deptree, String POSPrefix) {
        super(name, true, false, POSPrefix);
        this.consider_deptree = consider_deptree;
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
        return new Integer(Math.abs(predIndex - argIndex)).toString();
        // return makeFeatureString(s.get(predIndex),s.get(argIndex));
    }

    @Override
    public String getFeatureString(Predicate pred, Word arg)
    {
        return new Integer(Math.abs(pred.getIdx() - arg.getIdx())).toString();
        // return makeFeatureString(pred,arg);
    }

    /*
     * public String makeFeatureString(Word pred,Word arg){
     * 
     * StringBuilder ret=new StringBuilder(); if(consider_deptree) { ret.append("DEP"); return
     * ret.append(makeDepBasedFeatureString(pred, arg)).toString(); }
     * 
     * boolean up=true; if(pred.getIdx()<arg.getIdx()) up=false;
     * 
     * if(Math.abs(pred.getIdx()-arg.getIdx())==0) return " ";
     * 
     * Sentence s = pred.getMySentence(); for(int i=up?arg.getIdx():pred.getIdx();
     * i<(up?pred.getIdx():arg.getIdx()); i++) { ret.append(s.get(i).getAttr(attr));
     * ret.append(up?UP:DOWN); } return ret.toString(); }
     */

}
