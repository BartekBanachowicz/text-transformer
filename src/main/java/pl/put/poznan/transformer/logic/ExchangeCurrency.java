package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;

import static pl.put.poznan.transformer.logic.TextTransformer.*;

/**
 * Part of the decorator pattern.
 * This class makes it possible to convert the result of other text transformations
 * by exchanging cash values in one currency to another.
 */
public class ExchangeCurrency extends TextDecorator{

    /**
     * logging utility
     */
    private static final Logger logger = LoggerFactory.getLogger(pl.put.poznan.transformer.logic.ExchangeCurrency.class);

    /**
     * target currency
     */
    private final String targetCurrency;

    /**
     * @see TextDecorator#TextDecorator
     */
    public ExchangeCurrency(Transformer t, String trgCurrency) {
        super(t);
        targetCurrency = trgCurrency;
    }

    /**
     * Process provided data. Responsible for detecting cash value and its currency.
     * @see ExchangeCalculator
     * @see CurrencyHolder
     */
    private String exchange(String text){
        String[] words = splitter.split(text);
        List<String> symbols = Arrays.asList(currencyHolder.getListOfSymbols());

        NumberFormat formatter = new DecimalFormat("0,00");

        for(int i=0; i<words.length-1; i++){
            if(words[i].matches("[0-9]*[\\.|\\,]?[0-9]+")){
                if(words[i+1].matches("[a-zA-Z]+\\p{Punct}") && symbols.contains(words[i+1].replaceAll("\\p{Punct}", "").toUpperCase())) {
                    char punct = words[i+1].charAt(words[i+1].length()-1);
                    words[i] = String.valueOf(ExchangeCalculator.calculate(currencyHolder,
                            currencyHolder.getCodeForSymbol(words[i+1].replaceAll("\\p{Punct}", "").toUpperCase()),
                            targetCurrency, Float.valueOf(words[i])));

                    words[i+1] = targetCurrency+punct;
                }
                else if(symbols.contains(words[i+1].toUpperCase())){
                    words[i] = String.valueOf(ExchangeCalculator.calculate(currencyHolder,
                            currencyHolder.getCodeForSymbol(words[i+1].toUpperCase()), targetCurrency, Float.valueOf(words[i])));
                    words[i+1] = targetCurrency;
                }
            }
        }
        return splitter.mergeToText(words);
    }

    /**
     * Returns the result of the conversion.
     */
    @Override
    public String getText(){
        String result = exchange(super.getText());
        logger.debug("return: " + result);
        return result;
    }
}

