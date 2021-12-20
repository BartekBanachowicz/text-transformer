package pl.put.poznan.transformer.logic;

import java.util.Locale;

public class ToLower extends TextDecorator {

    public ToLower(Transformer t) {
        super(t);
    }

    @Override
    public String GetText() {
        return super.GetText().toLowerCase(Locale.ENGLISH);
    }
}
