package pl.put.poznan.transformer.logic;

import java.util.List;

public class SplitToWords {

    public String[] split(String text){

        String[] result;

        List<String> list = List.of(text.split(" "));

        //TODO - Detection and handling of punctuation marks

        result  = list.toArray(new String[0]);
        return result;
    }

    public String mergeToText(String [] words){
        StringBuilder result = new StringBuilder();
        for(int i=0; i<words.length; i++){
            result.append(words[i]);

            if(i != words.length-1) {result.append(" ");}
        }
        return result.toString();
    }
}