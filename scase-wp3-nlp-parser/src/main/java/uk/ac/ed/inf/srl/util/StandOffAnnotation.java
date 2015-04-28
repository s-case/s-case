package uk.ac.ed.inf.srl.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StandOffAnnotation
{

    List<String> sentences;
    List<Integer> sentEndIndices;
    SentenceAnnotation[] annos;
    Map<String, Integer> id2sent;

    public SentenceAnnotation get(int i)
    {
        return annos[i];
    }

    public StandOffAnnotation(File textfile, File annofile) {
        sentences = readSentences(textfile);

        annofile = new File(annofile.getPath().replaceAll(".txt", ".ann"));
        annos = new SentenceAnnotation[sentEndIndices.size()];
        for (int i = 0; i < annos.length; i++)
            annos[i] = new SentenceAnnotation(i == 0 ? 0 : sentEndIndices.get(i - 1) + 1, sentEndIndices.get(i));

        id2sent = new HashMap<String, Integer>();
        readAnnotations(annofile);
    }

    private void readAnnotations(File inputCorpus)
    {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(inputCorpus));
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] num_anno_word = line.split("\t");
                int currentsentence = -1;

                // word annotation
                if (num_anno_word.length == 3) {
                    int startCharacter = Integer.parseInt(num_anno_word[1].split(" ")[1]);
                    int endCharacter = Integer.parseInt(num_anno_word[1].split(" ")[2]);
                    currentsentence = getSentence(endCharacter);

                    int offset = 0;
                    if (currentsentence > 0)
                        offset = sentEndIndices.get(currentsentence - 1);
                    String sanitycheck = sentences.get(currentsentence).substring(startCharacter - offset,
                            endCharacter - offset);
                    if (!sanitycheck.equals(num_anno_word[2])) {
                        System.err.println("WARNING: Spelling mismatch " + sanitycheck + " vs. " + num_anno_word[2]);
                        // System.err.println("Sanity check went wrong. Errorneous annotation file?");
                        // System.exit(1);
                    }
                }
                // word-word annotation
                else {
                    String label = num_anno_word[1].split("Arg2:")[1];
                    currentsentence = id2sent.get(label);
                }

                id2sent.put(num_anno_word[0], currentsentence);
                annos[currentsentence].addAnnotation(num_anno_word);
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

    private int getSentence(int charpos)
    {
        for (int i = 0; i < sentEndIndices.size(); i++)
            if (charpos < sentEndIndices.get(i))
                return i;
        return -1;
    }

    private List<String> readSentences(File inputCorpus)
    {
        List<String> retval = new LinkedList<String>();
        sentEndIndices = new LinkedList<Integer>();
        int offset = 0;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(inputCorpus));
            String line = "";
            while ((line = br.readLine()) != null) {
                retval.add(line);
                offset++;
                sentEndIndices.add(offset + line.length());
                offset += line.length();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        return retval;
    }
}
