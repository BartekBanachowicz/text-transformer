package pl.put.poznan.transformer.logic;

public class ReplaceFullWords extends TextDecorator {

    private String replace(String text){
        SplitToWords splitter = new SplitToWords();

        String result = "";

        String [] words = splitter.split(text);

        for(String word : words){
            //TODO - FindInDictionaryAndReplace
        }

        //TODO - Add mergeStringsToText when method ready in SplitToWords class

        //TODO - Change ReplaceFullWords.replace output when method ready
        return text;
    }

    public ReplaceFullWords(Transformer t) {super(t);}

    @Override
    public String GetText(){
        return replace(super.GetText());
    }
}
