package pl.put.poznan.transformer.logic;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private final List<List<String>> transforms;

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
    public TextTransformer(List<List<String>> transforms){
        this.transforms = transforms;
    }

    /**
     * Initializes a new {@link TextTransformer} object.
     *
     * @param transforms valid transform names
     */
    public TextTransformer(String[] transforms){
        List<List<String>> result = new ArrayList<>();
        for (String transform : transforms) {
            result.add(List.of(transform));
        }
        this.transforms = result;
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

        for (List<String> transform : transforms) {
            switch (transform.get(0)) {
                case "ToUpper":
                    logger.info("Queued: " + transform.get(0));
                    resultTransformer = new ToUpper(resultTransformer);
                    break;

                case "ToLower":
                    logger.info("Queued: " + transform.get(0));
                    resultTransformer = new ToLower(resultTransformer);
                    break;

                case "Capitalize":
                    logger.info("Queued: " + transform.get(0));
                    resultTransformer = new Capitalize(resultTransformer);
                    break;

                case "Invert":
                    logger.info("Queued: " + transform.get(0));
                    resultTransformer = new Invert(resultTransformer);
                    break;

                case "ReplaceNumbers":
                    logger.info("Queued: " + transform.get(0));
                    resultTransformer = new ReplaceNumbers(resultTransformer);
                    break;

                case "ReplaceAbbreviations":
                    logger.info("Queued: " + transform.get(0));
                    resultTransformer = new ReplaceAbbreviations(resultTransformer);
                    break;

                case "ReplaceFullWords":
                    logger.info("Queued: " + transform.get(0));
                    resultTransformer = new ReplaceFullWords(resultTransformer);
                    break;

                case "DeleteDuplicates":
                    logger.info("Queued: " + transform.get(0));
                    resultTransformer = new DeleteDuplicates(resultTransformer);

                case "Exchange":
                    logger.info("Queued: " + transform.get(0));
                    if(transform.size()>1){
                        resultTransformer = new ExchangeCurrency(resultTransformer, transform.get(1));
                    } else {
                        resultTransformer = new ExchangeCurrency(resultTransformer, "PLN");
                    }

                    break;
            }
        }

        logger.info("Starting the transformation");
        String result = resultTransformer.getText();
        logger.debug("return: " + result);
        return result;
    }
}
