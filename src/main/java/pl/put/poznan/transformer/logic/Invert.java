package pl.put.poznan.transformer.logic;

import static java.lang.Character.isUpperCase;
import static java.lang.Character.toUpperCase;

public class Invert extends TextDecorator{

    public Boolean[] findUpperLetters(String text){
        Boolean[] upperLettersPositions = new Boolean[text.length()];

        for(int i=0; i<text.length();i++){
            upperLettersPositions[i] = isUpperCase(text.charAt(i));
        }

        return upperLettersPositions;
    }

    private String makeLettersGreatAgain(String text, Boolean[] upperLettersPositions){
        StringBuilder result = new StringBuilder();

        for(int i=0; i<text.length(); i++){
            if(upperLettersPositions[i]){
                result.append(toUpperCase(text.charAt(i)));

            } else {
                result.append(text.charAt(i));
            }
        }

        return result.toString();
    }

    private String changeOrder(String text){
        StringBuilder result = new StringBuilder();

        for(int i=text.length()-1; i>=0; i--){
            result.append(text.charAt(i));
        }

        return result.toString();
    }

    private String reverse(String text){
        String result;

        result = text.toLowerCase();
        result = changeOrder(result);
        result = makeLettersGreatAgain(result,findUpperLetters(text));

        return result;
    }

    public Invert(Transformer t){
        super(t);
    }

    @Override
    public String getText() {
        return reverse(super.getText());
    }

}