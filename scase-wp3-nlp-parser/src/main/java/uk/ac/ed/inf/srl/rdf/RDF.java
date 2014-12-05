package uk.ac.ed.inf.srl.rdf;

import java.io.BufferedWriter;

import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.util.FileManager;

public class RDF
{

    public final String o_nms = "http://www.owl-ontologies.com/Ontology1273059028.owl#";
    public final String syntax_nms = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
    public final String schema_nms = "http://www.w3.org/2000/01/rdf-schema#";

    Model rdfmodel;
    public Property TYPE;
    public Property LABEL;
    public Property HAS_CONCEPT;

    public RDF() {
        rdfmodel = ModelFactory.createDefaultModel();
        rdfmodel.setNsPrefix("", o_nms);
        rdfmodel.setNsPrefix("rdf", syntax_nms);
        rdfmodel.setNsPrefix("rdfs", schema_nms);
        // TEST
        // rdfmodel = FileManager.get().loadModel( "../rdfstuff/ontology.ttl" );

        TYPE = rdfmodel.createProperty(syntax_nms + "type");
        LABEL = rdfmodel.createProperty(schema_nms + "label");
        HAS_CONCEPT = rdfmodel.createProperty(o_nms + "requirement_has_concept");
    }

    public Resource createResource(String s)
    {
        return rdfmodel.createResource(s);
    }

    public Property createProperty(String s)
    {
        return rdfmodel.createProperty(s);
    }

    public void write()
    {
        rdfmodel.write(System.out, "TURTLE"/* "N-TRIPLES" */);
    }

    public void write(BufferedWriter out)
    {
        rdfmodel.write(out, "TURTLE");
    }

}
