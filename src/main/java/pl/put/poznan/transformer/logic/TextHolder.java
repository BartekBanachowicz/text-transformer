package pl.put.poznan.transformer.logic;

/**
 * Part of the decorator pattern.
 * This class is a storage container for a <code>String</code> object.
 */
public class TextHolder implements Transformer {

    /**
     * stored <code>String</code>
     */
    private final String InputText;

    /**
     * Wraps given <code>String</code> in a new {@link TextHolder} object.
     *
     * @param text stored <code>String</code>
     */
    public TextHolder(String text) {
        this.InputText = text;
    }

    /**
     * Returns <code>String</code> stored in {@link TextHolder#InputText}.
     */
    public String GetText() {
        return this.InputText;
    }
}
