package uk.ac.ed.inf.srl.languages;

// import se.lth.cs.srl.preprocessor.tokenization.WhiteSpaceTokenizer;
import uk.ac.ed.inf.srl.options.FullPipelineOptions;
import uk.ac.ed.inf.srl.preprocessor.tokenization.StanfordPTBTokenizer;
import uk.ac.ed.inf.srl.preprocessor.tokenization.Tokenizer;

public class NullLanguage
        extends AbstractDummyLanguage
{

    @Override
    public String toLangNameString()
    {
        return FullPipelineOptions.NULL_LANGUAGE_NAME;
    }

    @Override
    public L getL()
    {
        return L.nul;
    }

    // Not sure what is the better tokenizer... I'll leave it with Stanford for now.
    Tokenizer getDefaultTokenizer()
    {
        // return new WhiteSpaceTokenizer();
        return new StanfordPTBTokenizer();
    }

}
