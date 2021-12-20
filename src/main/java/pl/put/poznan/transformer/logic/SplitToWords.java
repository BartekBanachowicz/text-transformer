package pl.put.poznan.transformer.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SplitToWords {

    public String[] split(String text){

        String[] result;

        List<String> list = List.of(text.split(" "));

        //TODO - Detection and handling of punctuation marks

        result  = list.toArray(new String[0]);
        return result;
    }
}