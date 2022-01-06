package pl.put.poznan.transformer.logic;

import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CurrencyHolder {

    private final String pathFile = "currencyExchangeRates.json";

    void readCurrencyFromJSON(){}



    CurrencyHolder() throws URISyntaxException, IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://api.nbp.pl/api/exchangerates/tables/A/"))
                .GET()
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofFile(Paths.get("src/main/resources", pathFile)))
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();

        System.out.println(request.toString());
    }

}
