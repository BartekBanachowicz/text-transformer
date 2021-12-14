package pl.put.poznan.transformer.logic;

public abstract class TextDecorator implements Transformer{
    private Transformer transformer;

    protected TextDecorator(Transformer t){
        this.transformer = t;
    }

    @Override
    public String GetText() {
        return transformer.GetText();
    }
}
