package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Part of the decorator pattern.
 * This class makes it possible to convert the result of other text transformations
 * by changing normal characters to Latex characters.
 */
public class ToLatex extends TextDecorator {
    /**
     * @see TextDecorator#TextDecorator
     */
    public ToLatex(Transformer t) {
        super(t);
    }

    /**
     * logging utility
     */
    private static final Logger logger = LoggerFactory.getLogger(ToLatex.class);

    /**
     * Returns the result of the conversion.
     */
    @Override
    public String getText() {
        String s = super.getText();
        StringBuilder newString = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '&') {
                newString.append("\\&");
            } else if (s.charAt(i) == '$') {
                newString.append("\\$");
            } else {
                newString.append(s.charAt(i));
            }
        }
        String result = newString.toString();
        logger.debug("return: " + result);
        return result;
    }

}
