package pl.put.poznan.transformer.logic;

import java.util.Locale;

public class ToUpper extends TextDecorator {

    public ToUpper(Transformer t) {
        super(t);
    }

    @Override
    public String GetText() {
        return super.GetText().toUpperCase(Locale.ROOT);
    }
}
