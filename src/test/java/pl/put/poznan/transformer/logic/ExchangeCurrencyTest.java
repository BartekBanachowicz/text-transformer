package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class ExchangeCurrencyTest {

    @Test
    void testCurrencyHolder_Connection_expectingSuccess() throws URISyntaxException, IOException {
        CurrencyHolder holder = new CurrencyHolder();
        System.out.println(holder.getCurrencyCodes().toString());
    }
}
