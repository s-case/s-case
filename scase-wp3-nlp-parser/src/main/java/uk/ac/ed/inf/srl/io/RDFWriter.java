package uk.ac.ed.inf.srl.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

import uk.ac.ed.inf.srl.util.SemLink;
import uk.ac.ed.inf.srl.corpus.Sentence;
import uk.ac.ed.inf.srl.rdf.RDF;
import uk.ac.ed.inf.srl.rdf.SentenceRDF;

public class RDFWriter
        implements SentenceWriter
{

    private BufferedWriter out;
    private SemLink semlink;
    private RDF rdf;
    private int snum;

    public RDFWriter(File filename) {
        snum = 0;
        System.out.println("Writing RDF corpus to " + filename + "...");
        semlink = new SemLink("semlink/vn-pb/vnpbMappings/");
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), Charset.forName("UTF-8")));
            // out = new BufferedWriter(new FileWriter(filename));
        } catch (Exception e) {
            System.out.println("Failed while opening writer...%n" + e.toString());
            System.exit(1);
        }
    }

    public void write(Sentence s)
    {
        try {
            rdf = new RDF();
            SentenceRDF rdfs = new SentenceRDF(rdf, semlink, s, ++snum);
            rdf.write(out);
            // out.write(s.toString()+"%n%n");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to write sentance.");
            System.exit(1);
        }
    }

    public void close()
    {
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to close writer.");
            System.exit(1);
        }
    }

    @Override
    public void specialwrite(Sentence s)
    {
        write(s);
    }

}
