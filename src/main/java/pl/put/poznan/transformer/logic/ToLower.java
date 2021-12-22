package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

/**
 * Part of the decorator pattern.
 * This class makes it possible to convert the result of other text transformations to lowercase letters only.
 */
public class ToLower extends TextDecorator {

    /**
     * logging utility
     */
    private static final Logger log = LoggerFactory.getLogger(ToLower.class);

    /**
     * @see TextDecorator#TextDecorator
     */
    public ToLower(Transformer t) {
        super(t);
    }

    /**
     * Returns the result of the lowercase conversion.
     */
    @Override
    public String getText() {

        String result = super.getText().toLowerCase(Locale.ENGLISH);
        log.debug(" return: " + result);
        return result;
    }
}
