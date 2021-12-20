package pl.put.poznan.transformer.logic;

import static pl.put.poznan.transformer.logic.TextTransformer.dictionary;

public class ReplaceFullWords extends TextDecorator {


    private String replace(String text){

        String[] keySet = dictionary.getListOfWords();

        for(String word : keySet){
            if(text.contains(word)) {
                text = text.replaceAll(word, dictionary.getShortcut(word));
            }
        }

        return text;
    }

    public ReplaceFullWords(Transformer t) {super(t);}

    @Override
    public String GetText(){
        return replace(super.GetText());
    }
}
