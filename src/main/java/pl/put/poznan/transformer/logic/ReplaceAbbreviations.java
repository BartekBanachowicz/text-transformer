package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static pl.put.poznan.transformer.logic.TextTransformer.dictionary;

public class ReplaceAbbreviations extends TextDecorator{

    /*private String replace(String text){

        String result;

        String [] words = splitter.split(text);

        for(int i = 0; i< words.length; i++){
            words[i] = dictionary.findWordAndReplace(words[i]);
        }

        result = splitter.mergeToText(words);

        return result;
    }*/

    private static final Logger logger = LoggerFactory.getLogger(ReplaceAbbreviations.class);

    private String replace(String text){

        String [] keySet = dictionary.getListOfKeys();

        for(String shortcut : keySet){
            if(text.contains(shortcut)) {
                text = text.replaceAll(shortcut, dictionary.getValue(shortcut));
            }
        }

        return text;
    }

    public ReplaceAbbreviations(Transformer t) {super(t);}

    @Override
    public String getText(){

        String result = replace(super.getText());
        logger.debug("return: " + result);
        return result;
    }
}
