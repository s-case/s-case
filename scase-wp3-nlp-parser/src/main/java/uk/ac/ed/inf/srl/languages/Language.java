package uk.ac.ed.inf.srl.languages;

import is2.lemmatizer.Lemmatizer;
import is2.parser.Parser;
import is2.tag.Tagger;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Pattern;

import uk.ac.ed.inf.srl.corpus.Predicate;
import uk.ac.ed.inf.srl.corpus.Word;
import uk.ac.ed.inf.srl.options.FullPipelineOptions;
import uk.ac.ed.inf.srl.preprocessor.Preprocessor;
import uk.ac.ed.inf.srl.preprocessor.tokenization.Tokenizer;
import uk.ac.ed.inf.srl.preprocessor.tokenization.WhiteSpaceTokenizer;
import uk.ac.ed.inf.srl.util.BohnetHelper;

public abstract class Language
{

    public enum L
    {
        cat, chi, cze, eng, ger, jap, spa, swe, fre, nul
    };

    private static Language language;
    static final Pattern BAR_PATTERN = Pattern.compile("\\|");

    public abstract String toLangNameString();

    public static Language getLanguage()
    {
        return language;
    }

    public static String getLsString()
    {
        return "chi, eng, ger";
    }

    public static Language setLanguage(L l)
    {
        switch (l) {
        case eng:
            language = new English();
            break;
        case ger:
            language = new German();
            break;
        case spa:
            language = new Spanish();
            break;
        case nul:
            language = new NullLanguage();
            break;
        default:
            throw new IllegalArgumentException("Unknown language: '" + l + "'");
        }
        return language;
    }

    public Pattern getFeatSplitPattern()
    {
        return BAR_PATTERN;
    }

    public abstract String getDefaultSense(Predicate pred);

    public abstract String getCoreArgumentLabelSequence(Predicate pred, Map<Word, String> proposition);

    public abstract L getL();

    public abstract String getLexiconURL(Predicate pred);

    public Preprocessor getPreprocessor(FullPipelineOptions options)
            throws IOException
    {
        Tokenizer tokenizer = (options.loadPreprocessorWithTokenizer ? getTokenizer(options.tokenizer) : null);
        Lemmatizer lemmatizer = getLemmatizer(options.lemmatizer);
        Tagger tagger = options.tagger == null ? null : BohnetHelper.getTagger(options.tagger);
        is2.mtag.Tagger mtagger = options.morph == null ? null : BohnetHelper.getMTagger(options.morph);
        Parser parser = options.parser == null ? null : BohnetHelper.getParser(options.parser);
        Preprocessor pp = new Preprocessor(tokenizer, lemmatizer, tagger, mtagger, parser);
        return pp;
    }

    public abstract String verifyLanguageSpecificModelFiles(FullPipelineOptions options);

    Tokenizer getDefaultTokenizer()
    {
        return new WhiteSpaceTokenizer();
    }

    public Tokenizer getTokenizer(File tokenModelFile)
            throws IOException
    {
        return getDefaultTokenizer();
    }

    Lemmatizer getLemmatizer(File lemmaModelFile)
            throws IOException
    {
        if (lemmaModelFile == null) {
            return null;
        }
        return BohnetHelper.getLemmatizer(lemmaModelFile);
    }

}
