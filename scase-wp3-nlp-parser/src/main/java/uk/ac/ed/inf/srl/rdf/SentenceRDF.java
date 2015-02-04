package uk.ac.ed.inf.srl.rdf;

import java.util.List;
import java.util.ArrayList;

import com.hp.hpl.jena.rdf.model.*;

import uk.ac.ed.inf.srl.corpus.ConstituentBuilder;
import uk.ac.ed.inf.srl.corpus.Predicate;
import uk.ac.ed.inf.srl.corpus.Sentence;
import uk.ac.ed.inf.srl.corpus.Word;
import uk.ac.ed.inf.srl.corpus.Word.WordData;
import uk.ac.ed.inf.srl.util.SemLink;

public class SentenceRDF extends ArrayList<Resource>
{

    String snum;
    RDF rdf;
    Sentence sen;
    SemLink semlink;

    public SentenceRDF(RDF rdf, SemLink semlink, Sentence sen, String sentID) {
        this.rdf = rdf;
        this.semlink = semlink;
        this.sen = sen;
        this.snum = sentID;
        this.add(null); // ROOT token :-)

        process();
    }

    public RDF getRDF() {
    	return rdf;
    }
    
    private void process()
    {
        Resource rdf_r = rdf.createResource(rdf.o_nms + "FR" + snum);
        rdf_r.addProperty(rdf.TYPE, rdf.o_nms + "FunctionalRequirement");

        StringBuilder sb = new StringBuilder();
        int size = sen.size();
        for (int i = 1; i < size; i++) {
            Word w = sen.get(i);
            sb.append(w.getForm());
            sb.append(" ");
            this.add(null);
            // addWord(rdf_r, w);
        }

        rdf_r.addProperty(rdf.createProperty(rdf.syntax_nms + "comment"), sb.toString().trim());

        for (Predicate p : sen.getPredicates()) {
            addPredicate(rdf_r, p);
        }
    }

    private Resource getWord(Resource rdf_r, int i)
    {
        if (this.get(i) == null)
            this.set(i, createWordRDF(rdf_r, sen.get(i)));
        return this.get(i);
    }

    private void addPredicate(Resource rdf_r, Predicate p)
    {
        if (p.getArgMap().size() < 2)
            return;
        Resource rdf_p = getWord(rdf_r, p.getIdx());
        rdf_r.addProperty(rdf.HAS_CONCEPT, rdf_p);
        // (p.getAttr(WordData.OntPred).equals("OWNED"))
        //     rdf_p.addProperty(rdf.TYPE, rdf.o_nms + "ownership");
        //else
            rdf_p.addProperty(rdf.TYPE, rdf.o_nms + p.getSense().toLowerCase());

        for (Word w : p.getArgMap().keySet()) {
            String label = p.getArgMap().get(w);

            /*
             * System.err.println(p.getSense());
             * 
             * String label = Ontology.getLabel(semlink, p.getSense(), arg);
             * 
             * System.err.println("  " + arg + ": " + w.getForm());
             */

            Resource rdf_a = getWord(rdf_r, w.getIdx());

            /*
             * if(label == null) { System.err.println("Unknown label: " + arg +
             * " ("+p.getForm()+")"); } else
             */
            if (label.equals("Actor")) {
                addProperty(rdf_a, rdf.o_nms + "is_actor_of", rdf_p);
            	if(!rdf_a.hasProperty(rdf.TYPE, rdf.o_nms + "actor"))
            		rdf_a.addProperty(rdf.TYPE, rdf.o_nms + "actor");
                addProperty(rdf_p, rdf.o_nms + "has_actor", rdf_a);
            } else if (label.equals("Theme")) {
            	if(!rdf_a.hasProperty(rdf.TYPE, rdf.o_nms + "object"))
            		rdf_a.addProperty(rdf.TYPE, rdf.o_nms + "object");
                addProperty(rdf_a, rdf.o_nms + "receives_action", rdf_p);
                addProperty(rdf_p, rdf.o_nms + "acts_on", rdf_a);
            /*} else if (label.equals("Patient")) {
                addProperty(rdf_a, rdf.o_nms + "affected_by", rdf_p);
                addProperty(rdf_p, rdf.o_nms + "affects", rdf_a);
            } else if (label.equals("Agent")) {
                addProperty(rdf_a, rdf.o_nms + "is_instrument_of", rdf_p);
                addProperty(rdf_p, rdf.o_nms + "has_instrument", rdf_a);*/
            } else if (label.equals("Property")) {
            	if(!rdf_a.hasProperty(rdf.TYPE, rdf.o_nms + "property"))
            		rdf_a.addProperty(rdf.TYPE, rdf.o_nms + "property");
                addProperty(rdf_a, rdf.o_nms + "is_property_of", rdf_p);
                addProperty(rdf_p, rdf.o_nms + "has_property", rdf_a);
            } else {
                // addProperty(rdf_a, rdf.o_nms + "TODO_" + label, rdf_p);
                // addProperty(rdf_p, rdf.o_nms + "TODO_" + label, rdf_a);
            }
        }
    }

    private Resource createWordRDF(Resource rdf_r, Word w)
    {
        Resource rdf_w = rdf.createResource(rdf.o_nms + w.getLemma() + snum + "-" + w.getIdx());
        String constituent = new ConstituentBuilder(sen, w).toString();
        rdf_w.addProperty(rdf.createProperty(rdf.syntax_nms + "comment"), constituent);
        rdf_w.addProperty(rdf.LABEL, w.getLemma());
        return rdf_w;
        // tokens.add(rdf_w);
    }

    private void addProperty(Resource s, String p, Resource o)
    {
   		s.addProperty(rdf.createProperty(p), o);
    }
}
