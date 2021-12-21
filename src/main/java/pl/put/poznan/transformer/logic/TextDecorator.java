package pl.put.poznan.transformer.logic;

/**
 * Part of the decorator pattern.
 * This class makes it possible to chain many <code>String</code> modifiers,
 * while preserving their execution order.
 */
public abstract class TextDecorator implements Transformer {

    /**
     * <code>String</code> modifier, holder, or generator
     */
    protected Transformer transformer;

    /**
     * Prepends a given {@link Transformer} object to the execution order.
     *
     * @param t <code>String</code> modifier, holder, or generator
     */
    protected TextDecorator(Transformer t){
        this.transformer = t;
    }

    /**
     * By default, returns <code>String</code> from nested {@link Transformer} object.
     * This method should be overridden and then called using <code>super.GetText()</code>,
     * to apply additional <code>String</code> modifications.
     */
    public String GetText() {
        return transformer.GetText();
    }
}
