package pl.put.poznan.transformer.logic;

import java.util.Locale;

/**
 * Part of the decorator pattern.
 * This class makes it possible to convert the result of other text transformations to uppercase letters only.
 */
public class ToUpper extends TextDecorator {

    /**
     * @see TextDecorator#TextDecorator
     */
    public ToUpper(Transformer t) { super(t); }

    /**
     * Returns the result of the uppercase conversion.
     */
    @Override
    public String getText() {
        return super.getText().toUpperCase(Locale.ENGLISH);
    }
}
