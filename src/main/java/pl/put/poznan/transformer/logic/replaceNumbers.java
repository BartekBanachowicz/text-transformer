package pl.put.poznan.transformer.logic;


import java.util.Objects;

public class replaceNumbers extends TextTransformer {

    public replaceNumbers(Transformer t) {
        super(t);
    }

    private boolean isNumber(String s) {
        return s.matches("-?\\d+(\\,\\d+)?");
    }

    private String toStr(String s) {
        String[] ns = s.split("");
        String result = null;

        for (int i = ns.length-1; i>=0; i--) {
            if(i==1){
                if(Objects.equals(ns[ns.length - 1 - i], "1")) {
                    String search = ns[ns.length-1-i] + ns[ns.length-1-i+1];
                    //result += + " ";
                    i--;
                }
                else {
                    String search = ns[ns.length-1-i] +"0".repeat(i);
                    //result += + " ";
                }
            }
            else if (i==0) {
                String search = ns[ns.length-1-i] +"0".repeat(i);
                //result += ;
            }
            else {
                String search = ns[ns.length-1-i] +"0".repeat(i);
                //result += + " ";
            }
        }

        return result;
    }

    @Override
    public String getText() {
        String[] tab = splitToWords(super.getText());
        String newString = null;

        for (String s : tab) {
            if (isNumber(s)) {
                String[] minusString = s.split("-");
                String afMinString = null;
                if(minusString.length == 2) {
                    newString += "minus ";
                    afMinString = minusString[1];
                }
                else {
                    afMinString = minusString[0];
                }

                String[] comma = afMinString.split(",");

                newString += toStr(comma[0]);
                if(comma.length == 2) {
                    newString += " i ";
                    newString += toStr(comma[1]);
                }

            } else {
                newString += s + " ";
            }
        }

        return newString;
    }
}
