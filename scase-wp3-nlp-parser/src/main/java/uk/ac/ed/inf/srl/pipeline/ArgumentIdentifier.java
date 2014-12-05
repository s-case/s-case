package uk.ac.ed.inf.srl.pipeline;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.SortedSet;
import java.util.TreeSet;

import uk.ac.ed.inf.srl.corpus.ArgMap;
import uk.ac.ed.inf.srl.corpus.Predicate;
import uk.ac.ed.inf.srl.corpus.Sentence;
import uk.ac.ed.inf.srl.corpus.Word;
import uk.ac.ed.inf.srl.features.FeatureSet;
import uk.ac.ed.inf.srl.ml.Model;
import uk.ac.ed.inf.srl.ml.liblinear.Label;

public class ArgumentIdentifier
        extends ArgumentStep
{

    private static final String FILEPREFIX = "ai_";

    public ArgumentIdentifier(FeatureSet fs) {
        super(fs);
    }

    @Override
    public void extractInstances(Sentence s)
    {
        for (Predicate pred : s.getPredicates()) {
            for (int i = 1, size = s.size(); i < size; ++i) {
                super.addInstance(pred, s.get(i));
            }
        }
    }

    @Override
    public void parse(Sentence s)
    {
        for (Predicate pred : s.getPredicates()) {
            for (int i = 1, size = s.size(); i < size; ++i) {
                Word arg = s.get(i);
                Integer label = super.classifyInstance(pred, arg);
                if (label.equals(POSITIVE))
                    pred.addArgMap(arg, "ARG");
            }
        }

    }

    @Override
    protected Integer getLabel(Predicate pred, Word arg)
    {
        return pred.getArgMap().containsKey(arg) ? POSITIVE : NEGATIVE;
    }

    @Override
    public void prepareLearning()
    {
        super.prepareLearning(FILEPREFIX);
    }

    @Override
    protected String getModelFileName()
    {
        return FILEPREFIX + ".models";
    }

    List<ArgMap> beamSearch(Predicate pred, int beamSize)
    {
        List<ArgMap> candidates = new ArrayList<ArgMap>();
        candidates.add(new ArgMap());
        Sentence s = pred.getMySentence();
        SortedSet<ArgMap> newCandidates = new TreeSet<ArgMap>(ArgMap.REVERSE_PROB_COMPARATOR);
        String POSPrefix = super.getPOSPrefix(pred.getPOS());
        if (POSPrefix == null)
            POSPrefix = super.featureSet.POSPrefixes[0]; // TODO fix me. or discard examples with
                                                         // wrong POS-tags
        Model model = models.get(POSPrefix);

        for (int i = 1, size = s.size(); i < size; ++i) {
            newCandidates.clear();
            Word arg = s.get(i);
            Collection<Integer> indices = new TreeSet<Integer>();
            Map<Integer, Double> nonbinFeats = new TreeMap<Integer, Double>();
            super.collectFeatures(pred, arg, POSPrefix, indices, nonbinFeats);

            List<Label> probs = model.classifyProb(indices, nonbinFeats);
            for (ArgMap argmap : candidates) {
                for (Label label : probs) {
                    ArgMap branch = new ArgMap(argmap);
                    if (label.getLabel().equals(POSITIVE)) {
                        branch.put(arg, "ARG", label.getProb());
                    } else {
                        branch.multiplyProb(label.getProb());
                    }
                    newCandidates.add(branch);
                }
            }
            candidates.clear();
            Iterator<ArgMap> it = newCandidates.iterator();
            for (int j = 0; j < beamSize && it.hasNext(); j++)
                candidates.add(it.next());
        }
        for (ArgMap argmap : candidates) {
            argmap.setIdProb(argmap.getProb());
            argmap.resetProb();
        }
        return candidates;
    }
}
