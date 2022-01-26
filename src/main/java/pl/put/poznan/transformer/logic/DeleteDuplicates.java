package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Objects;

public class DeleteDuplicates extends TextDecorator {

    private static final Logger logger = LoggerFactory.getLogger(Invert.class);

    private String[] delete(String[] words) {
        String[] result = new String[0];
        if (words.length >= 1){
            result = new String[1];
            result[0] = words[0];

            for (int i=1; i<words.length; i++){
                if (!words[i].equalsIgnoreCase(result[result.length - 1])){
                    result = Arrays.copyOf(result, result.length+1);
                    result[result.length-1] = words[i];
                }
            }
        }
        return result;
    }

    public DeleteDuplicates(Transformer t) {
        super(t);
    }

    @Override
    public String getText() {
        SplitToWords splitter = new SplitToWords();

        String[] splitText = splitter.splitPunctuation(splitter.split(super.getText()));
        splitText = delete(splitText);
        String result = splitter.mergeToText(splitText);

        logger.debug("return: " + result);
        return result;
    }
}
