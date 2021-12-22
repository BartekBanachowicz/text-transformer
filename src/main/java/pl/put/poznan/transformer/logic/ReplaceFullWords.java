package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static pl.put.poznan.transformer.logic.TextTransformer.dictionary;

public class ReplaceFullWords extends TextDecorator {

    private static final Logger logger = LoggerFactory.getLogger(ReplaceFullWords.class);

    private String replace(String text){

        String[] keySet = dictionary.getListOfValues();

        for(String word : keySet){
            if(text.contains(word)) {
                text = text.replaceAll(word, dictionary.getKey(word));
            }
        }

        return text;
    }

    public ReplaceFullWords(Transformer t) {super(t);}

    @Override
    public String getText(){

        String result = replace(super.getText());
        logger.debug("return: " + result);
        return result;
    }
}
