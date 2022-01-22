package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExchangeCurrencyTest {

    @Test
    void testCurrencyHolder_Connection_expectingSuccess() throws URISyntaxException, IOException {
        CurrencyHolder holder = new CurrencyHolder();
        System.out.println(holder.getCurrencyCodes().toString());
    }

    @Test
    void testExchangeCurrency(){
        Transformer mock = mock(Transformer.class);
        ExchangeCurrency exchanger = new ExchangeCurrency(mock);
        String result, text;

        text = "Mam 20 USD i 30 EUR.";
        when(mock.getText()).thenReturn(text);

        result = exchanger.getText();
        assertEquals(result, "Mam X PLN i X PLN");
    }

}
