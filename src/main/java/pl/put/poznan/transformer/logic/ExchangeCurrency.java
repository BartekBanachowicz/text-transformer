package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static pl.put.poznan.transformer.logic.TextTransformer.*;

public class ExchangeCurrency extends TextDecorator{

    private static final Logger logger = LoggerFactory.getLogger(pl.put.poznan.transformer.logic.ExchangeCurrency.class);
    private String targetCurrency;
    public ExchangeCurrency(Transformer t, String trgCurrency) {
        super(t);
        targetCurrency = trgCurrency;
    }

    private String exchange(String text){
        String[] words = splitter.split(text);
        List<String> symbols = Arrays.asList(currencyHolder.getListOfSymbols());

        for(int i=0; i<words.length-1; i++){
            if(words[i].matches("[0-9]*[\\.|\\,]?[0-9]+")){
                if(words[i+1].matches("[a-zA-Z]+\\p{Punct}") && symbols.contains(words[i+1].replaceAll("\\p{Punct}", "").toUpperCase())) {
                    char punct = words[i+1].charAt(words[i+1].length()-1);
                    words[i] = String.valueOf(ExchangeCalculator.calculate(currencyHolder,
                            currencyHolder.getCodeFoSymbol(words[i+1].replaceAll("\\p{Punct}", "").toUpperCase()),
                            targetCurrency, Float.valueOf(words[i])));

                    words[i+1] = targetCurrency+punct;
                }
                else if(symbols.contains(words[i+1].toUpperCase())){
                    words[i] = String.valueOf(ExchangeCalculator.calculate(currencyHolder,
                            currencyHolder.getCodeFoSymbol(words[i+1].toUpperCase()), targetCurrency, Float.valueOf(words[i])));
                    words[i+1] = targetCurrency;
                }
            }
        }
        return splitter.mergeToText(words);
    }

    @Override
    public String getText(){
        String result = exchange(super.getText());
        logger.debug("return: " + result);
        return result;
    }
}

