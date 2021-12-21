package pl.put.poznan.transformer.logic;

/**
 * Part of the decorator pattern.
 * This interface makes it possible to apply various modifications on <code>Strings</code>.
 */
public interface Transformer {

    /**
     * Implementors should return a result of their operations with this method.
     */
    String GetText();
}
