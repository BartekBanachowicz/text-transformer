package pl.put.poznan.transformer.logic;

public class TextHolder implements Transformer {

    private String InputText = null;

    public TextHolder(String text) {
        this.InputText = text;
    }

    public String GetText() {
        return this.InputText;
    }
}
