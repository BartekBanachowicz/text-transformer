package pl.put.poznan.transformer.logic;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    private final Path pathFile = Path.of("src/main/resources/currencyExchangeRates.json");
    private final List<Currency> exchangeRates;

    private List<Currency> readCurrencyFromJSON() throws IOException {
        List<Currency> ratesList;

        ObjectMapper objectMapper = new ObjectMapper();
        String dataInString = Files.readString(pathFile);
        JsonNode jsonNode = objectMapper.readTree(dataInString).get(0);
        JsonNode rates = jsonNode.get("rates");
        ratesList = objectMapper.readValue(rates.toString(), new TypeReference<>() {});
        return ratesList;
    }


    CurrencyHolder() throws URISyntaxException, IOException {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://api.nbp.pl/api/exchangerates/tables/C/"))
                .GET()
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofFile(pathFile))
                .thenApply(HttpResponse::body)
                .join();

        exchangeRates  = readCurrencyFromJSON();
    }

    public List<String> getCurrenciesCodes(){
        List<String> codes = new ArrayList<>();
        for (Currency exchangeRate : this.exchangeRates) {
            codes.add(exchangeRate.getCode());
        }
        return codes;
    }

    public Float getBid(String code){
        for (Currency exchangeRate : exchangeRates) {
            if (exchangeRate.getCode().equals(code)) {
                return exchangeRate.getBid();
            }
        }
        return null;
    }

    public Float getAsk(String code){
        for (Currency exchangeRate : exchangeRates) {
            if (exchangeRate.getCode().equals(code)) {
                return exchangeRate.getAsk();
            }
        }
        return null;
    }




}
