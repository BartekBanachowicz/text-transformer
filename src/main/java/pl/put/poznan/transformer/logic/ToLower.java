package pl.put.poznan.transformer.logic;

import java.util.Locale;

/**
 * Part of the decorator pattern.
 * This class makes it possible to convert the result of other text transformations to lowercase letters only.
 */
public class ToLower extends TextDecorator {

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
        return super.getText().toLowerCase(Locale.ENGLISH);
    }
}
