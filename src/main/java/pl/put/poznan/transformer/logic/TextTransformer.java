package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TextTransformer allows to perform a set of text based transformations on a given String.
 * All transformations are guaranteed to work on english UTF-8 letters.
 * <p>
 * This class handles following {@link TextTransformer#transforms}:
 * <ul>
 * <li>ToUpper
 * <li>ToLower
 * <li>Capitalize
 * <li>Invert
 * <li>ReplaceNumbers
 * <li>ReplaceAbbreviations
 * <li>ReplaceFullWords
 * </ul>
 */
public class TextTransformer {
    /**
     * valid transform names
     */
    private final String[] transforms;

    /**
     * abbreviation matching utility
     */
    public static final FindInDictionary dictionary = new FindInDictionary("src/main/resources/dictionary.csv");

    /**
     * number matching utility
     */
    public static final FindInDictionary numbersDirectory = new FindInDictionary("src/main/resources/numberDictionary.csv");

    /**
     * word splitting utility
     */
    public static final SplitToWords splitter = new SplitToWords();

    /**
     * Initializes a new {@link TextTransformer} object.
     *
     * @param transforms valid transform names
     */
    public TextTransformer(String[] transforms){
        this.transforms = transforms;
    }

    /**
     * Applies transforms defined in {@link TextTransformer#transforms} attribute on a given <code>String</code>
     *
     * @param text text scheduled for transformation
     * @return transformed text
     */
    public String transform(String text){

        Transformer resultTransformer = new TextHolder(text);

        for (String transformation : transforms) {
            switch (transformation) {
                case "ToUpper":
                    Logger logUp = LoggerFactory.getLogger(ToUpper.class);
                    logUp.info("Added : " + transformation);
                    resultTransformer = new ToUpper(resultTransformer);
                    break;

                case "ToLower":
                    Logger logLo = LoggerFactory.getLogger(ToLower.class);
                    logLo.info("Added : " + transformation);
                    resultTransformer = new ToLower(resultTransformer);
                    break;

                case "Capitalize":
                    Logger logCa = LoggerFactory.getLogger(Capitalize.class);
                    logCa.info("Added : " + transformation);
                    resultTransformer = new Capitalize(resultTransformer);
                    break;

                case "Invert":
                    Logger logIn = LoggerFactory.getLogger(Invert.class);
                    logIn.info("Added : " + transformation);
                    resultTransformer = new Invert(resultTransformer);
                    break;

                case "ReplaceNumbers":
                    Logger logReN = LoggerFactory.getLogger(ReplaceNumbers.class);
                    logReN.info("Added : " + transformation);
                    resultTransformer = new ReplaceNumbers(resultTransformer);
                    break;

                case "ReplaceAbbreviations":
                    Logger logReA = LoggerFactory.getLogger(ReplaceAbbreviations.class);
                    logReA.info("Added : " + transformation);
                    resultTransformer = new ReplaceAbbreviations(resultTransformer);
                    break;

                case "ReplaceFullWords":
                    Logger logReF = LoggerFactory.getLogger(ReplaceFullWords.class);
                    logReF.info("Added : " + transformation);
                    resultTransformer = new ReplaceFullWords(resultTransformer);
                    break;
            }
        }

        return resultTransformer.getText();
    }
}
