package pl.put.poznan.transformer.logic;

public class Currency {
    private String currency;
    private String code;
    private Float bid;
    private Float ask;

    Currency(){}

    Currency(String currency, String code, Float bid, Float ask) {
        this.currency = currency;
        this.code = code;
        this.bid = bid;
        this.ask = ask;
    }

    public Float getBid() {
        return bid;
    }

    public Float getAsk(){
        return ask;
    }

    public String getCode() {
        return code;
    }

    public String getCurrency() {
        return currency;
    }

    public void setBid(Float bid){
        this.bid = bid;
    }

    public void setAsk(Float ask) {
        this.ask = ask;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
