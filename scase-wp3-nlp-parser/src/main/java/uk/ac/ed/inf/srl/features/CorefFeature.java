package uk.ac.ed.inf.srl.features;

import java.util.Collection;
import java.util.Map;

import uk.ac.ed.inf.srl.corpus.CorpusSentence;
import uk.ac.ed.inf.srl.corpus.Predicate;
import uk.ac.ed.inf.srl.corpus.Sentence;
import uk.ac.ed.inf.srl.corpus.Word;

public class CorefFeature
        extends SingleFeature
{
    private static final long serialVersionUID = 1L;
    /*
     * Binary feature indicating whether word denotes an entity that is mentioned at several places
     * in discourse
     */
    public static final String SUBJ = "S";
    public static final String OBJ = "O";
    public static final String X = "X";
    public static final String NO = "N";

    protected CorefFeature(String POSPrefix) {
        super(FeatureName.Coref, true, false, POSPrefix);
        indices.put(X, Integer.valueOf(1));
        indices.put(NO, Integer.valueOf(2));
        indices.put(SUBJ, Integer.valueOf(3));
        indices.put(OBJ, Integer.valueOf(4));
        indexcounter = 5;
    }

    @Override
    public void addFeatures(Sentence s, Collection<Integer> indices, Map<Integer, Double> nonbinFeats, int predIndex,
            int argIndex, Integer offset, boolean allWords)
    {
        indices.add(indexOf(getFeatureString(s, predIndex, argIndex)) + offset);
    }

    @Override
    public void addFeatures(Collection<Integer> indices, Map<Integer, Double> nonbinFeats, Predicate pred, Word arg,
            Integer offset, boolean allWords)
    {
        indices.add(indexOf(getFeatureString(pred, arg)) + offset);
    }

    @Override
    protected void performFeatureExtraction(Sentence s, boolean allWords)
    {
        // Do nothing, the map is constructed in the constructor.
    }

    @Override
    public String getFeatureString(Sentence s, int predIndex, int argIndex)
    {
        // String cc =
        // ((CorpusSentence)s).getMyCorpus().findChainInPrvSentence(((CorpusSentence)s).getMyCorpus().corefId(s.get(argIndex)),
        // s);
        String cc = ((CorpusSentence) s).getMyCorpus().corefId(s.get(argIndex));
        // System.out.println(cc);
        if (cc.equals("_"))
            return NO;
        else if (cc.contains("SBJ"))
            return SUBJ;
        else if (cc.contains("OBJ"))
            return OBJ;
        else
            return X;
    }

    @Override
    public String getFeatureString(Predicate pred, Word arg)
    {
        Sentence s = arg.getMySentence();
        // String cc =
        // ((CorpusSentence)s).getMyCorpus().findChainInPrvSentence(((CorpusSentence)s).getMyCorpus().corefId(arg),
        // s);
        String cc = ((CorpusSentence) s).getMyCorpus().corefId(arg);
        // System.out.println(cc);
        if (cc.equals("_"))
            return NO;
        else if (cc.contains("SBJ"))
            return SUBJ;
        else if (cc.contains("OBJ"))
            return OBJ;
        else
            return X;
    }

}
