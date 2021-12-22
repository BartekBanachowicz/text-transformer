package pl.put.poznan.transformer.logic;

import static pl.put.poznan.transformer.logic.TextTransformer.dictionary;
import static pl.put.poznan.transformer.logic.TextTransformer.splitter;

public class ReplaceShortcuts extends TextDecorator{

    /*private String replace(String text){

        String result;

        String [] words = splitter.split(text);

        for(int i = 0; i< words.length; i++){
            words[i] = dictionary.findWordAndReplace(words[i]);
        }

        result = splitter.mergeToText(words);

        return result;
    }*/

    private String replace(String text){

        String [] keySet = dictionary.getListOfShortcuts();

        for(String shortcut : keySet){
            if(text.contains(shortcut)) {
                text = text.replaceAll(shortcut, dictionary.getWord(shortcut));
            }
        }

        return text;
    }

    public ReplaceShortcuts(Transformer t) {super(t);}

    @Override
    public String GetText(){
        return replace(super.GetText());
    }
}
