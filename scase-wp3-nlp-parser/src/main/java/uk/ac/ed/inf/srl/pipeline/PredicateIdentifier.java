package uk.ac.ed.inf.srl.pipeline;

import java.util.Collection;
import java.util.TreeSet;
import java.util.Map;
import java.util.TreeMap;

import uk.ac.ed.inf.srl.Learn;
import uk.ac.ed.inf.srl.corpus.Predicate;
import uk.ac.ed.inf.srl.corpus.Sentence;
import uk.ac.ed.inf.srl.corpus.Word;
import uk.ac.ed.inf.srl.features.Feature;
import uk.ac.ed.inf.srl.features.FeatureSet;
import uk.ac.ed.inf.srl.ml.LearningProblem;
import uk.ac.ed.inf.srl.ml.Model;

public class PredicateIdentifier extends AbstractStep {

	private static final String FILEPREFIX="pi_";
	
	public PredicateIdentifier(FeatureSet fs) {
		super(fs);
	}

	public void extractInstances(Sentence s){
		/*
		 * We add an instance if it
		 * 1) Is a predicate. Then either to its specific classifier, or the fallback one. (if fallback behavior is specified, i.e. skipNonMatchingPredicates=false
		 * 2) Is not a predicate, but matches the POS-tag
		 */
		for(int i=1,size=s.size();i<size;++i){
			Word potentialPredicate=s.get(i);
			String POS=potentialPredicate.getPOS();
			String POSPrefix=null;
			for(String prefix:featureSet.POSPrefixes){
				if(POS.startsWith(prefix)){
					POSPrefix=prefix;
					break;
				}
			}
			if(POSPrefix==null){ //It matches a prefix, we will use it for sure.
				if(!Learn.learnOptions.skipNonMatchingPredicates && potentialPredicate instanceof Predicate){
					POSPrefix=featureSet.POSPrefixes[0];
				} else {
					continue; //Its just some word we dont care about
				}
			}
			Integer label= potentialPredicate instanceof Predicate ? POSITIVE : NEGATIVE;
			addInstance(s,i,POSPrefix,label);
		}
	}
	
	private void addInstance(Sentence s, int i,String POSPrefix,Integer label) {
		LearningProblem lp=learningProblems.get(POSPrefix);
		Collection<Integer> indices=new TreeSet<Integer>();
		Map<Integer, Double> nonbinFeats=new TreeMap<Integer, Double>();
		Integer offset=0;
		for(Feature f:featureSet.get(POSPrefix)){
		    f.addFeatures(s,indices,nonbinFeats,i,-1,offset,true);
		    offset+=f.size(true);
		} 
		lp.addInstance(label, indices, nonbinFeats);
	}

	public void parse(Sentence s){
		for(int i=1,size=s.size();i<size;++i){
			Integer label=classifyInstance(s,i);
			if(label.equals(POSITIVE))
				s.makePredicate(i);
		}
	}

	private Integer classifyInstance(Sentence s, int i) {
		String POSPrefix=null;
		String POS=s.get(i).getPOS();
		for(String prefix:featureSet.POSPrefixes){
			if(POS.startsWith(prefix)){
				POSPrefix=prefix;
				break;
			}
		}
		if(POSPrefix==null)
			return NEGATIVE;
		Model m=models.get(POSPrefix);
		Collection<Integer> indices=new TreeSet<Integer>();
		Map<Integer, Double> nonbinFeats=new TreeMap<Integer, Double>();
		Integer offset=0;
		for(Feature f:featureSet.get(POSPrefix)){
		    f.addFeatures(s,indices,nonbinFeats,i,-1,offset,true);
		    offset+=f.size(true);
		}
		return m.classify(indices, nonbinFeats);
	}

	@Override
	public void prepareLearning() {
		super.prepareLearning(FILEPREFIX);
	}

	@Override
	protected String getModelFileName() {
		return FILEPREFIX+".models";
	}
	
}
