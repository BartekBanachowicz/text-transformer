package pl.put.poznan.transformer.logic;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CurrencyHolder {

    private final Path pathFile = Path.of("src/main/resources/currencyExchangeRates.json");
    private List<Currency> exchangeRates;

    private List<Currency> readCurrencyFromJSON() throws IOException {
        List<Currency> ratesList;

        ObjectMapper objectMapper = new ObjectMapper();
        String dataInString = Files.readString(pathFile);
        JsonNode jsonNode = objectMapper.readTree(dataInString).get(0);
        JsonNode rates = jsonNode.get("rates");
        ratesList = objectMapper.readValue(rates.toString(), new TypeReference<List<Currency>>() {});
        return ratesList;
    }


    CurrencyHolder() throws URISyntaxException, IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://api.nbp.pl/api/exchangerates/tables/C/"))
                .GET()
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofFile(pathFile))
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();

        System.out.println(request.toString());

        exchangeRates  = readCurrencyFromJSON();
        System.out.println(exchangeRates.toString());

    }

}
