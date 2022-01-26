package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

/**
 * Part of the decorator pattern.
 * This class makes it possible to convert the result of other text transformations to uppercase letters only.
 */
public class ToUpper extends TextDecorator {

    /**
     * logging utility
     */
    private static final Logger logger = LoggerFactory.getLogger(ToUpper.class);

    /**
     * @see TextDecorator#TextDecorator
     */
    public ToUpper(Transformer t) { super(t); }

    /**
     * Returns the result of the uppercase conversion.
     */
    @Override
    public String getText() {

        String result = super.getText().toUpperCase(Locale.ENGLISH);
        logger.debug("return: " + result);
        return result;
    }
}
