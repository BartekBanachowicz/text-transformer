package pl.put.poznan.transformer.logic;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ExchangeCalculator {

    public static float calculate(CurrencyHolder holder, String srcCurrencyCode, String trgCurrencyCode, Float value){
        float result = value;
        BigDecimal output;

        if(!srcCurrencyCode.equals(trgCurrencyCode)){

            if(srcCurrencyCode.equals("PLN")){
                float trgCurrencyRate = holder.getAsk(trgCurrencyCode);
                result = new BigDecimal(value/trgCurrencyRate).setScale(2, RoundingMode.HALF_UP).floatValue();
            }

            else if(trgCurrencyCode.equals("PLN")){
                float srcCurrencyRate = holder.getBid(srcCurrencyCode);
                result = new BigDecimal(value*srcCurrencyRate).setScale(2, RoundingMode.HALF_UP).floatValue();
            }

            else{
                float srcCurrencyRate = holder.getBid(srcCurrencyCode);
                result = value*srcCurrencyRate;
                float trgCurrencyRate = holder.getAsk(trgCurrencyCode);
                result = new BigDecimal(result/trgCurrencyRate).setScale(2, RoundingMode.HALF_UP).floatValue();
            }
        }

        return result;
    }

}
