package pl.put.poznan.transformer.logic;

public class Currency {

    /**
     * common currency name.
     */
    private String currency;

    /**
     * currency code name (e.g. USD, PLN).
     */
    private String code;

    /**
     * the price at which the market will buy currency.
     */
    private Float bid;

    /**
     * the price at which the market will sell currency.
     */
    private Float ask;

    /**
     * default constructor.
     */
    Currency(){}

    /**
     * constructor which takes arguments: currency, code, bid, ask, to initialize object fields with values.
     */
    Currency(String currency, String code, Float bid, Float ask) {
        this.currency = currency;
        this.code = code;
        this.bid = bid;
        this.ask = ask;
    }

    /**
     * return price at which the market will buy currency.
     */
    public Float getBid() {
        return bid;
    }

    /**
     * return price at which the market will sell currency.
     */
    public Float getAsk(){
        return ask;
    }

    /**
     * return currency code (e.g. USD, PLN).
     */
    public String getCode() {
        return code;
    }

    /**
     * return currency name in Polish (e.g. dolar amerykanski).
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * set price at which the market will buy currency.
     */
    public void setBid(Float bid){
        this.bid = bid;
    }

    /**
     * set price at which the market will sell currency.
     */
    public void setAsk(Float ask) {
        this.ask = ask;
    }

    /**
     * set currency code (e.g. USD, PLN).
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * set currency name (e.g. dolar amerykanski).
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
