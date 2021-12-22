package pl.put.poznan.transformer.logic;

/**
 * Part of the decorator pattern.
 * This class is a storage container for a <code>String</code> object.
 */
public class TextHolder implements Transformer {

    /**
     * stored <code>String</code>
     */
    private final String inputText;

    /**
     * Wraps given <code>String</code> in a new {@link TextHolder} object.
     *
     * @param text stored <code>String</code>
     */
    public TextHolder(String text) {
        this.inputText = text;
    }

    /**
     * Returns <code>String</code> stored in {@link TextHolder#inputText}.
     */
    public String getText() {
        return this.inputText;
    }
}
