package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static pl.put.poznan.transformer.logic.TextTransformer.emojiDirectory;
import static pl.put.poznan.transformer.logic.TextTransformer.splitter;

/**
 * Part of the decorator pattern.
 * This class makes it possible to convert the result of other text transformations
 * by changing emoji names to emoji codes.
 */
public class ToEmoji extends TextDecorator{

    /**
     * @see TextDecorator#TextDecorator
     */
    public ToEmoji(Transformer t) {
        super(t);
    }

    /**
     * logging utility
     */
    private static final Logger logger = LoggerFactory.getLogger(ReplaceNumbers.class);

    /**
     * Returns the result of the conversion.
     */
    @Override
    public String getText() {
        String[] tab = splitter.split(super.getText());
        StringBuilder newString = new StringBuilder();

        for (String s : tab) {
            String end = "";
            String tmpS = s;
            if(s.charAt(s.length()-1) == '?' || s.charAt(s.length()-1) == '!' || s.charAt(s.length()-1) == '.' || s.charAt(s.length()-1) == ',') {
                end = Character.toString(s.charAt(s.length()-1));
                tmpS = s.substring(0, s.length() - 1);
            }

            if(emojiDirectory.isKeyInDictionary(tmpS.toLowerCase())) {
                newString.append(emojiDirectory.getValue(tmpS.toLowerCase()));
            }
            else {
                newString.append(tmpS);
            }

            newString.append(end);
            newString.append(" ");
        }

        newString = new StringBuilder(newString.substring(0, newString.length() - 1));
        String result = newString.toString();
        logger.debug("return: " + result);
        return result;
    }
}
