package pl.put.poznan.transformer.logic;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CurrencyHolder {

    /**
     * logging utility
     */
    private static final Logger logger = LoggerFactory.getLogger(CurrencyHolder.class);

    /**
     * path to JSON file with currency exchange rates from NBP API
     */
    private final Path pathFile = Path.of("src/main/resources/currencyExchangeRates.json");

    /**
     * list of Currency objects. Store currency codes, names, ask rates and bid rates.
     * @see Currency
     */
    private final List<Currency> exchangeRates;

    /**
     * trading date for rates in exchangeRates
     */
    private String tradingDate;

    /**
     * Read data from JSON file delivered with NBP API. Return list of Currency objects with information about currency codes,
     * names, ask rates and bid rates. Update tradingDate field.
     */
    private List<Currency> readCurrencyFromJSON() throws IOException {
        List<Currency> ratesList;

        ObjectMapper objectMapper = new ObjectMapper();
        String dataInString = Files.readString(pathFile);
        JsonNode jsonNode = objectMapper.readTree(dataInString).get(0);
        this.tradingDate = jsonNode.get("tradingDate").toString();
        JsonNode rates = jsonNode.get("rates");
        ratesList = objectMapper.readValue(rates.toString(), new TypeReference<>() {});
        return ratesList;
    }

    /**
     * Use GET request to get data from NBP API. Use readCurrencyFromJSON() method to take data for exchangeRates List.
     */
    CurrencyHolder() throws URISyntaxException, IOException {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://api.nbp.pl/api/exchangerates/tables/C/"))
                .GET()
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofFile(pathFile))
                .thenApply(HttpResponse::body)
                .join();

        logger.debug("Connected to NBP API");
        exchangeRates  = readCurrencyFromJSON();
        logger.debug("Prepared currency exchange rates for: " + this.tradingDate);
    }

    /**
     * Returns List of codes of currencies stored in exchangeRates List.
     */
    public List<String> getCurrencyCodes(){
        List<String> codes = new ArrayList<>();
        for (Currency exchangeRate : this.exchangeRates) {
            codes.add(exchangeRate.getCode());
        }
        return codes;
    }

    /**
     * Return bid rate for currency with provided code name. Return null if there is no such currency.
     */
    public Float getBid(String code){
        for (Currency exchangeRate : exchangeRates) {
            if (exchangeRate.getCode().equals(code)) {
                return exchangeRate.getBid();
            }
        }
        return null;
    }

    /**
     * Return ask rate for currency with provided code name. Return null if there is no such currency.
     */
    public Float getAsk(String code){
        for (Currency exchangeRate : exchangeRates) {
            if (exchangeRate.getCode().equals(code)) {
                return exchangeRate.getAsk();
            }
        }
        return null;
    }
}
