package uk.ac.ed.inf.srl.pipeline;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import uk.ac.ed.inf.srl.Learn;
import uk.ac.ed.inf.srl.Parse;
import uk.ac.ed.inf.srl.corpus.Predicate;
import uk.ac.ed.inf.srl.corpus.PredicateReference;
import uk.ac.ed.inf.srl.corpus.Sentence;
import uk.ac.ed.inf.srl.features.Feature;
import uk.ac.ed.inf.srl.features.FeatureSet;
import uk.ac.ed.inf.srl.ml.LearningProblem;
import uk.ac.ed.inf.srl.ml.Model;
import uk.ac.ed.inf.srl.ml.liblinear.LibLinearLearningProblem;

public class PredicateDisambiguator
        implements PipelineStep
{

    public static final String FILE_PREFIX = "pd_";

    private FeatureSet featureSet;
    private PredicateReference predicateReference;

    // This is a map filename -> model
    protected Map<String, Model> models;

    private Map<String, List<String>> lexicon;
    private Map<String, List<Predicate>> instances;

    public PredicateDisambiguator(FeatureSet featureSet, PredicateReference predicateReference) {
        this.featureSet = featureSet;
        this.predicateReference = predicateReference;

        if ((Parse.parseOptions != null && Parse.parseOptions.framenet)
                || ((Learn.learnOptions != null && Learn.learnOptions.framenet)))
            lexicon = createLexicon("/disk/scratch/mroth/framenet/fndata-1.5/frame/");
    }

    private Map<String, List<String>> createLexicon(String lexicondir)
    {
        Map<String, List<String>> retval = new HashMap<String, List<String>>();
        File[] files = new File(lexicondir).listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name)
            {
                return name.endsWith(".xml");
            }
        });

        BufferedReader br = null;
        for (File f : files) {
            try {
                br = new BufferedReader(new FileReader(f));
                String framename = f.getName().replaceAll("\\..*", "");
                String line = "";
                while ((line = br.readLine()) != null) {
                    if (!line.contains("<lexeme "))
                        continue;
                    String lexeme = line.replaceAll(".*name=\"", "").replaceAll("\".*", "");
                    if (!retval.containsKey(lexeme))
                        retval.put(lexeme, new LinkedList<String>());
                    retval.get(lexeme).add(framename);
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            } finally {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        }

        return retval;
    }

    public void parse(Sentence s)
    {
        for (Predicate pred : s.getPredicates()) {
            String POSPrefix = getPOSPrefix(pred);
            String lemma = pred.getLemma();
            String sense = "-1";
            if (lexicon != null && lexicon.containsKey("lemma") && lexicon.get(lemma).size() == 1) {
                sense = lexicon.get(lemma).get(0);
            } else if (POSPrefix == null) {
                sense = predicateReference.getSimpleSense(pred, null);
            } else {

                String filename = predicateReference.getFileName(lemma, POSPrefix);
                if (filename == null) {
                    sense = predicateReference.getSimpleSense(pred, POSPrefix);
                } else {
                    Model m = getModel(filename);
                    Collection<Integer> indices = new TreeSet<Integer>();
                    Map<Integer, Double> nonbinFeats = new TreeMap<Integer, Double>();
                    Integer offset = 0;
                    for (Feature f : featureSet.get(POSPrefix)) {
                        f.addFeatures(indices, nonbinFeats, pred, null, offset, false);
                        offset += f.size(false);
                    }

                    if ((Parse.parseOptions != null && !Parse.parseOptions.framenet)
                            || ((Learn.learnOptions != null && !Learn.learnOptions.framenet))) {
                        Integer label = m.classify(indices, nonbinFeats);
                        sense = predicateReference.getSense(lemma, POSPrefix, label);
                    } else {
                        boolean foundone = false;

                        /** with lexicon! **/
                        List<uk.ac.ed.inf.srl.ml.liblinear.Label> labels = m.classifyProb(indices, nonbinFeats);
                        if (!lexicon.containsKey(lemma))
                            sense = predicateReference.getSense(lemma, POSPrefix, labels.get(0).getLabel());
                        // + "," +
                        // predicateReference.getSense(lemma,POSPrefix,labels.get(1).getLabel());
                        else {
                            for (uk.ac.ed.inf.srl.ml.liblinear.Label l : labels) {
                                if (lexicon.get(lemma).contains(
                                        predicateReference.getSense(lemma, POSPrefix, l.getLabel()))) {
                                    // if(foundone) {
                                    // String tmp =
                                    // predicateReference.getSense(lemma,POSPrefix,l.getLabel());
                                    // pred.addPotentialSense(tmp, l.getProb());
                                    // break;
                                    // } else {
                                    sense = predicateReference.getSense(lemma, POSPrefix, l.getLabel());
                                    // pred.addPotentialSense(sense, l.getProb());
                                    pred.setSense(sense);
                                    foundone = true;
                                    /**/break;/**/
                                    // }
                                }
                            }

                            if (sense.equals("-1")) {
                                sense = predicateReference.getSense(lemma, POSPrefix, labels.get(0).getLabel());
                            }
                        }
                    }

                }
            }

            pred.setSense(sense);
        }
    }

    private Model getModel(String filename)
    {
        return models.get(filename);
    }

    public void extractInstances(Sentence s)
    {
        for (Predicate pred : s.getPredicates()) {
            // Note we would prefer to get the gold standard POS. Cause this always makes sense.
            String POSPrefix = getPOSPrefix(pred);
            if (POSPrefix == null) {
                if (Learn.learnOptions.skipNonMatchingPredicates) {
                    continue;
                } else {
                    POSPrefix = featureSet.POSPrefixes[0];
                }
            }
            String filename = predicateReference.getFileName(pred.getLemma(), POSPrefix);
            if (filename == null)
                continue;
            if (!instances.containsKey(filename))
                instances.put(filename, new ArrayList<Predicate>());
            instances.get(filename).add(pred);
        }
    }

    private String getPOSPrefix(Predicate pred)
    {
        for (String prefix : featureSet.POSPrefixes) {
            if (pred.getPOS().startsWith(prefix))
                return prefix;
        }
        return "N";
        /** HACK by MR, 28 Aug **/
        // return null;
    }

    public void prepareLearning()
    {
        instances = new HashMap<String, List<Predicate>>();
    }

    private void addInstance(Predicate pred, LearningProblem lp)
    {
        String POSPrefix = getPOSPrefix(pred);
        if (POSPrefix == null) {
            POSPrefix = featureSet.POSPrefixes[0];
        }
        Collection<Integer> indices = new TreeSet<Integer>();
        Map<Integer, Double> nonbinFeats = new TreeMap<Integer, Double>();
        Integer offset = 0;
        for (Feature f : featureSet.get(POSPrefix)) {
            f.addFeatures(indices, nonbinFeats, pred, null, offset, false);
            offset += f.size(false);
        }
        Integer label = predicateReference.getLabel(pred.getLemma(), POSPrefix, pred.getSense());
        lp.addInstance(label, indices, nonbinFeats);
    }

    public void writeInstance(Predicate pred)
    {
        String POSPrefix = getPOSPrefix(pred);
        if (POSPrefix == null) {
            POSPrefix = featureSet.POSPrefixes[0];
        }
        Collection<Integer> indices = new TreeSet<Integer>();
        Map<Integer, Double> nonbinFeats = new TreeMap<Integer, Double>();
        Integer offset = 0;
        for (Feature f : featureSet.get(POSPrefix)) {
            f.addFeatures(indices, nonbinFeats, pred, null, offset, false);
            offset += f.size(false);
        }
        Integer label = predicateReference.getLabel(pred.getLemma(), POSPrefix, pred.getSense());

        System.err.print(label);
        for (Integer i : indices) {
            System.err.print(" ");
            System.err.print(i + ":" + 1);
        }
        System.err.println();
    }

    @Override
    public void done()
    {
        // we do nothing here since we have no filehandles open. All writing of training data goes
        // on in train()
    }

    @Override
    public void train()
    {
        models = new HashMap<String, Model>();
        // Here we need to do them one at a time, thats the whole reason why we collected the
        // Map<String,List<Predicate>> instances map. Otherwise we would easily run out of
        // filehandles.
        Iterator<String> it = instances.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            File dataFile = new File(Learn.learnOptions.tempDir, FILE_PREFIX + key);
            LibLinearLearningProblem lp = new LibLinearLearningProblem(dataFile, false);
            for (Predicate pred : instances.get(key)) {
                // if(pred.getLemma().equals("import"))
                // System.out.println("here");
                /** Only add ambiguous predictes (MR, Oct 2) => only made performance worse :( **/
                // if(!lexicon.containsKey(pred.getLemma()) ||
                // lexicon.get(pred.getLemma()).size()>1)
                addInstance(pred, lp);
            }
            lp.done();
            Model m = lp.train(true);
            models.put(key, m);
            it.remove(); // This way we should lose references to the words and sentences we no
                         // longer need, and the gc should be able to clean this up for us.
        }
    }

    @Override
    public void writeModels(ZipOutputStream zos)
            throws IOException
    {
        AbstractStep.writeModels(zos, models, getModelFileName());
    }

    @Override
    public void readModels(ZipFile zipFile)
            throws IOException, ClassNotFoundException
    {
        models = new HashMap<String, Model>();
        AbstractStep.readModels(zipFile, models, getModelFileName());
    }

    private String getModelFileName()
    {
        return FILE_PREFIX + ".models";
    }

}
