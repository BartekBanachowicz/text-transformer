package pl.put.poznan.transformer.logic;

import java.util.Locale;

public class ToUpper extends TextDecorator {
    private Transformer transformer;

    public ToUpper(Transformer t) {
        super(t);
        this.transformer = t;
    }

    @Override
    public String GetText() {
        return super.GetText().toUpperCase(Locale.ROOT);
    }
}
