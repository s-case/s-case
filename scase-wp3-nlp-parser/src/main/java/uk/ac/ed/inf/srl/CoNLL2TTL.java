package uk.ac.ed.inf.srl;

import java.io.File;
import java.io.IOException;
import java.util.TreeSet;

import uk.ac.ed.inf.srl.util.SemLink;
import uk.ac.ed.inf.srl.corpus.ConstituentBuilder;
import uk.ac.ed.inf.srl.corpus.Predicate;
import uk.ac.ed.inf.srl.corpus.Sentence;
import uk.ac.ed.inf.srl.corpus.Word;
import uk.ac.ed.inf.srl.io.*;
import uk.ac.ed.inf.srl.rdf.RDF;
import uk.ac.ed.inf.srl.rdf.SentenceRDF;

import com.hp.hpl.jena.rdf.model.*;

public class CoNLL2TTL
{

    public static void main(String[] args)
    {
        if (args.length != 1) {
            System.err.println("USAGE: java CoNLL2TLL [filename]");
            System.exit(1);
        }
        CoNLL2TTL converter = new CoNLL2TTL("semlink/vn-pb/vnpbMappings/");
        converter.process(args[0]);
    }

    SemLink semlink;

    private CoNLL2TTL(String filename) {
        semlink = new SemLink(filename);
    }

    private void process(String filename)
    {
        AbstractCoNLL09Reader reader = new AllCoNLL09Reader(new File(filename));
        int snum = 1;

        RDF rdf = new RDF();

        for (Sentence sen : reader.readAll()) {
            SentenceRDF rdfs = new SentenceRDF(rdf, semlink, sen, new Integer(snum).toString());
            snum++;
            break;
        }
        rdf.write();
    }
}
