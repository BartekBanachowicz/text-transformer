package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SplitToWords {

    public String[] splitPunctuation(String[] wordsArray) {
        String[] result = new String[0];

        for (String word : wordsArray) {
            int patternIndex = word.length();
            if (Pattern.matches("^[\\p{L}\\p{Digit}]+\\p{Punct}+$", word)) {

                Pattern p = Pattern.compile("\\p{Punct}+$");
                Matcher m = p.matcher(word);
                if (m.find()) {patternIndex = m.start();}

                result = Arrays.copyOf(result, result.length + 2);
                result[result.length - 2] = word.substring(0, patternIndex);
                result[result.length - 1] = word.substring(patternIndex);

            } else {
                result = Arrays.copyOf(result, result.length + 1);
                result[result.length - 1] = word;
            }
        }
        return result;
    }

    public String[] split(String text){

        String[] result;

        List<String> list = List.of(text.split("\\s+"));

        result = list.toArray(new String[0]);

        return result;
    }

    public String mergeToText(String [] words){
        StringBuilder result = new StringBuilder();

        if (words.length >= 1){
            result.append(words[0]);
        }

        for(int i=0; i<words.length-1; i++){
            if (Pattern.matches("^\\p{Punct}+$", words[i+1])){
                result.append(words[i+1]);
            } else {
                result.append(" ");
                result.append(words[i+1]);
            }
        }

        return result.toString();
    }
}