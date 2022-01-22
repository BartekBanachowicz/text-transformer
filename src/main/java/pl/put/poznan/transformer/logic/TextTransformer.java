package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;

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
     * logging utility
     */
    private static final Logger logger = LoggerFactory.getLogger(TextTransformer.class);
    
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
     * currency exchanging utility
     */
    public static CurrencyHolder currencyHolder = null;

    static {
        try {
            currencyHolder = new CurrencyHolder();
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

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

        logger.info("Constructing a transformer");

        Transformer resultTransformer = new TextHolder(text);

        for (String transformation : transforms) {
            switch (transformation) {
                case "ToUpper":
                    logger.info("Queued: " + transformation);
                    resultTransformer = new ToUpper(resultTransformer);
                    break;

                case "ToLower":
                    logger.info("Queued: " + transformation);
                    resultTransformer = new ToLower(resultTransformer);
                    break;

                case "Capitalize":
                    logger.info("Queued: " + transformation);
                    resultTransformer = new Capitalize(resultTransformer);
                    break;

                case "Invert":
                    logger.info("Queued: " + transformation);
                    resultTransformer = new Invert(resultTransformer);
                    break;

                case "ReplaceNumbers":
                    logger.info("Queued: " + transformation);
                    resultTransformer = new ReplaceNumbers(resultTransformer);
                    break;

                case "ReplaceAbbreviations":
                    logger.info("Queued: " + transformation);
                    resultTransformer = new ReplaceAbbreviations(resultTransformer);
                    break;

                case "ReplaceFullWords":
                    logger.info("Queued: " + transformation);
                    resultTransformer = new ReplaceFullWords(resultTransformer);
                    break;
            }
        }

        logger.info("Starting the transformation");
        String result = resultTransformer.getText();
        logger.debug("return: " + result);
        return result;
    }
}
