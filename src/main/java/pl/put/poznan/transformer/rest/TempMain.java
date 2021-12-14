package pl.put.poznan.transformer.rest;

import pl.put.poznan.transformer.logic.TextTransformer;

public class TempMain {

    // for early testing purposes
    public static void main(String[] args) {
        String text = "MOIm zDAniEm to NIE ma TaK, że doBRZe albo że nie DOBrze. GDYbyM miAł POWIEdzIeć, cO ceNIę w życIU NAJbardziej, powiedZIAłbym, że LUDZI.";
        String[] transforms = {""};
        TextTransformer transformer = new TextTransformer(transforms);
        String result = transformer.transform(text);
        System.out.println(result);
    }

}
