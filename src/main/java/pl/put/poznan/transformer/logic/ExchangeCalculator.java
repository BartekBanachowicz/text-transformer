package pl.put.poznan.transformer.logic;

public class ExchangeCalculator {

    public static float calculate(CurrencyHolder holder, String srcCurrencyCode, String trgCurrencyCode, Float value){
        float result = value;

        if(!srcCurrencyCode.equals(trgCurrencyCode)){

            if(srcCurrencyCode.equals("PLN")){
                float trgCurrencyRate = holder.getAsk(trgCurrencyCode);
                result = value/trgCurrencyRate;
            }

            else if(trgCurrencyCode.equals("PLN")){
                float srcCurrencyRate = holder.getBid(srcCurrencyCode);
                result = value*srcCurrencyRate;
            }

            else{
                float srcCurrencyRate = holder.getBid(srcCurrencyCode);
                result = value*srcCurrencyRate;
                float trgCurrencyRate = holder.getAsk(trgCurrencyCode);
                result = result/trgCurrencyRate;
            }
        }

        return result;
    }

}
