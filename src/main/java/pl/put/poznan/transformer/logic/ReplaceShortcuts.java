package pl.put.poznan.transformer.logic;

import static pl.put.poznan.transformer.logic.TextTransformer.dictionary;

public class ReplaceShortcuts extends TextDecorator{

    private String replace(String text){
        SplitToWords splitter = new SplitToWords();

        String result;

        String [] words = splitter.split(text);

        for(int i = 0; i< words.length; i++){
            words[i] = dictionary.findWordAndReplace(words[i]);
        }

        result = splitter.mergeToText(words);

        return result;
    }

    public ReplaceShortcuts(Transformer t) {super(t);}

    @Override
    public String GetText(){
        return replace(super.GetText());
    }
}
