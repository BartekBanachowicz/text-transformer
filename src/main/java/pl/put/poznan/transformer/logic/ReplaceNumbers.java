package pl.put.poznan.transformer.logic;

import java.util.Objects;

import static pl.put.poznan.transformer.logic.TextTransformer.numbersDirectory;
import static pl.put.poznan.transformer.logic.TextTransformer.splitter;

public class ReplaceNumbers extends TextDecorator {

    public ReplaceNumbers(Transformer t) {
        super(t);
    }

    private boolean isNumber(String s) {
        return s.matches("-?\\d+(,\\d+)?");
    }

    private String toStr(String s, boolean zero) {
        String[] ns = s.split("");
        StringBuilder result = new StringBuilder();
        boolean isZero = true;

        for (int i = ns.length-1; i>=0; i--) {
            if(i==1){
                if(Objects.equals(ns[ns.length - 1 - i], "1")) {
                    String search = ns[ns.length-1-i] + ns[ns.length-1-i+1];
                    isZero = false;
                    result.append(numbersDirectory.getValue(search)).append(" ");
                    i--;
                }
                else {
                    if (Objects.equals(ns[ns.length - 1 - i], "0")) {
                        if (zero && isZero) {
                            result.append("zero ");
                        }
                    }
                    else {
                        String search = ns[ns.length-1-i] +"0".repeat(i);
                        isZero = false;
                        result.append(numbersDirectory.getValue(search)).append(" ");
                    }

                }
            }
            else if (i==0) {
                if (Objects.equals(ns[ns.length - 1 - i], "0")) {
                    if (isZero) {
                        result.append("zero");
                    }
                }
                else {
                    String search = ns[ns.length-1-i] +"0".repeat(i);
                    isZero = false;
                    result.append(numbersDirectory.getValue(search));
                }


            }
            else {
                if (Objects.equals(ns[ns.length - 1 - i], "0")) {
                    if (isZero && zero) {
                        result.append("zero ");
                    }
                }
                else {
                    String search = ns[ns.length-1-i] +"0".repeat(i);
                    isZero = false;
                    result.append(numbersDirectory.getValue(search)).append(" ");
                }

            }
        }

        return result.toString().trim();
    }

    @Override
    public String getText() {
        String[] tab = splitter.split(super.getText());
        StringBuilder newString = new StringBuilder();


        for (String s : tab) {
            if (isNumber(s)) {
                String[] minusString = s.split("-");
                String afMinString;
                if(minusString.length == 2) {
                    newString.append("minus ");
                    afMinString = minusString[1];
                }
                else {
                    afMinString = minusString[0];
                }

                String[] comma = afMinString.split(",");

                newString.append(toStr(comma[0], false));
                if(comma.length == 2) {
                    newString.append(" i ");
                    newString.append(toStr(comma[1], true));
                    if(comma[1].length() == 1) {
                        newString.append(" dziesiątych");
                    }
                    else if (comma[1].length() == 2) {
                        newString.append(" setnych");
                    }
                    else {
                        newString.append(" tysięcznych");
                    }
                }
                newString.append(" ");

            } else {
                newString.append(s).append(" ");
            }
        }

        newString = new StringBuilder(newString.substring(0, newString.length() - 1));
        //return newString.trim();
        return newString.toString();
    }

}
