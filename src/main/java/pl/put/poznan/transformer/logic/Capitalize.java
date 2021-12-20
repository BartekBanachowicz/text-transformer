package pl.put.poznan.transformer.logic;

import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Part of the decorator pattern.
 * This class makes it possible to convert the result of other text transformations
 * by changing first letters of every word to uppercase.
 */
public class Capitalize extends TextDecorator {

    /**
     * @see TextDecorator#TextDecorator
     */
    public Capitalize(Transformer t) { super(t); }

    /**
     * Returns the result of the conversion.
     */
    @Override
    public String GetText() {
        String [] words = super.GetText().split("(?<=\\s)(?=\\S)");
        Pattern pattern = Pattern.compile("^.");

        return Arrays.stream(words).map((word) -> {
            Matcher matcher = pattern.matcher(word);
            if (matcher.find()) {
                return matcher.replaceFirst(matcher.group().toUpperCase(Locale.ENGLISH));
            }
            return word;
        }).collect(Collectors.joining());
    }

}
