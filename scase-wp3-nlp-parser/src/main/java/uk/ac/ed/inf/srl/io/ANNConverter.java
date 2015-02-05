package uk.ac.ed.inf.srl.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import uk.ac.ed.inf.srl.corpus.Sentence;
import uk.ac.ed.inf.srl.corpus.StringInText;
import uk.ac.ed.inf.srl.languages.Language;
import uk.ac.ed.inf.srl.options.CompletePipelineCMDLineOptions;
import uk.ac.ed.inf.srl.preprocessor.Preprocessor;
import uk.ac.ed.inf.srl.util.StandOffAnnotation;

public class ANNConverter {

    private SentenceWriter writer;
    private static CompletePipelineCMDLineOptions options;
    private static Preprocessor pp;
    
    public ANNConverter(File f) {
		try {
			writer = new CoNLL09Writer(f);
	        options = new CompletePipelineCMDLineOptions(new String[] {"eng", "-tokenize", "-lemma", "models/lemma-train-eng.model", "-parser",
	                "models/parse-train-eng.model", "-tagger", "models/tagger-train-eng.model", "-srl", "/dev/null", });
	        pp = Language.getLanguage().getPreprocessor(options);
		} catch (IOException e) {
			System.err.println("Could not open output file " + f);
		}
	}
	
	public void convertInput(String textfile, String annofile) {
		pp.resetStartPosition();
		
        StandOffAnnotation anno = new StandOffAnnotation(new File(textfile), new File(annofile));
        try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(textfile)),
			        Charset.forName("UTF-8")));
			
			int senCount=0;
            String line = "";
            while ((line = reader.readLine()) != null) {
                Sentence s = preprocess(line);
                anno.get(senCount++).apply(s, false);
                writer.write(s);
            }
            
            reader.close();
			
		} catch (IOException e) {
			System.err.println("Coult not read input file");
		}

	}

	public void close() {
		writer.close();
	}
	
    private Sentence preprocess(String line) {
        List<StringInText> words = Arrays.asList(pp.tokenizeplus(line));
        String[] array = new String[words.size()];
        for (int i = 0; i < array.length; i++)
            array[i] = words.get(i).word();
        Sentence s = new Sentence(pp.preprocess(array), false);
        for (int i = 0; i < array.length; i++) {
            s.get(i).setBegin(words.get(i).begin());
            s.get(i).setEnd(words.get(i).end());
        }
        return s;
    }

}
