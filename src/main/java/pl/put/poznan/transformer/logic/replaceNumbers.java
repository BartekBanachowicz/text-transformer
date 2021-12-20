package pl.put.poznan.transformer.logic;
import java.util.HashMap;


import java.util.Objects;

public class replaceNumbers extends TextDecorator {

    private HashMap<String, String> numbers = new HashMap<String, String>();

    public replaceNumbers(Transformer t) {
        super(t);
        load();
    }

    private boolean isNumber(String s) {
        return s.matches("-?\\d+(\\,\\d+)?");
    }

    private String toStr(String s, boolean zero) {
        String[] ns = s.split("");
        String result = null;
        boolean isZero = true;

        for (int i = ns.length-1; i>=0; i--) {
            if(i==1){
                if(Objects.equals(ns[ns.length - 1 - i], "1")) {
                    String search = ns[ns.length-1-i] + ns[ns.length-1-i+1];
                    isZero = false;
                    result += numbers.get(search) + " ";
                    i--;
                }
                else {
                    if (Objects.equals(ns[ns.length - 1 - i], "0")) {
                        if (zero && isZero) {
                            result += "zero ";
                        }
                    }
                    else {
                        String search = ns[ns.length-1-i] +"0".repeat(i);
                        isZero = false;
                        result += numbers.get(search) + " ";
                    }

                }
            }
            else if (i==0) {
                if (Objects.equals(ns[ns.length - 1 - i], "0")) {
                    if (isZero) {
                        result += "zero";
                    }
                }
                else {
                    String search = ns[ns.length-1-i] +"0".repeat(i);
                    isZero = false;
                    result += numbers.get(search);
                }


            }
            else {
                if (Objects.equals(ns[ns.length - 1 - i], "0")) {
                    if (isZero && zero) {
                        result += "zero ";
                    }
                }
                else {
                    String search = ns[ns.length-1-i] +"0".repeat(i);
                    isZero = false;
                    result += numbers.get(search) + " ";
                }

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

                newString += toStr(comma[0], false);
                if(comma.length == 2) {
                    newString += " i ";
                    newString += toStr(comma[1], true);
                }

            } else {
                newString += s + " ";
            }
        }

        return newString;
    }

    private void load() {
        numbers.put("0", "zero");
        numbers.put("1", "jeden");
        numbers.put("2", "dwa");
        numbers.put("3", "trzy");
        numbers.put("4", "cztery");
        numbers.put("5", "pięć");
        numbers.put("6", "sześć");
        numbers.put("7", "siedem");
        numbers.put("8", "osiem");
        numbers.put("9", "dziewięć");
        numbers.put("10", "dziesięć");
        numbers.put("11", "jedenaście");
        numbers.put("12", "dwanaście");
        numbers.put("13", "trzynaście");
        numbers.put("14", "czternaście");
        numbers.put("15", "piętnaście");
        numbers.put("16", "szesnaście");
        numbers.put("17", "siedemnaście");
        numbers.put("18", "osiemnaście");
        numbers.put("19", "dziewiętnaście");
        numbers.put("20", "dwadzieścia");
        numbers.put("30", "trzydzieści");
        numbers.put("40", "czterdzieści");
        numbers.put("50", "pięćdziesiąt");
        numbers.put("60", "sześćdziesiąt");
        numbers.put("70", "siedemdziesiąt");
        numbers.put("80", "osiemdziesiąt");
        numbers.put("90", "dziewięćdziesiąt");
        numbers.put("100", "sto");
        numbers.put("200", "dwieście");
        numbers.put("300", "trzysta");
        numbers.put("400", "czterysta");
        numbers.put("500", "pięćset");
        numbers.put("600", "sześćset");
        numbers.put("700", "siedemset");
        numbers.put("800", "osiemset");
        numbers.put("900", "dziewięćset");
        numbers.put("1000", "tysiąc");

    }
}
