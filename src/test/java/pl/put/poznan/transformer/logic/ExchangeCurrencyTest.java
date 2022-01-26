package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ExchangeCurrencyTest {

    @Test
    void testCurrencyHolder_Connection_expectingSuccess() throws URISyntaxException, IOException {
        CurrencyHolder holder = new CurrencyHolder();
    }

    @Test
    void testExchangeCalculatorEURtoPLN_expectingSuccess(){
        CurrencyHolder holder = mock(CurrencyHolder.class);
        when(holder.getBid("EUR")).thenReturn(4.00F);

        Float result = ExchangeCalculator.calculate(holder,"EUR", "PLN", 20.00F);
        verify(holder).getBid("EUR");
        assertEquals(result,80.00F);
    }

    @Test
    void testExchangeCalculatorPLNtoEUR_expectingSuccess(){
        CurrencyHolder holder = mock(CurrencyHolder.class);
        when(holder.getAsk("EUR")).thenReturn(4.00F);

        Float result = ExchangeCalculator.calculate(holder,"PLN", "EUR", 80.00F);
        verify(holder).getAsk("EUR");
        assertEquals(result,20.00F);
    }

    @Test
    void testExchangeCalculatorUSDtoEUR_expectingSuccess(){
        CurrencyHolder holder = mock(CurrencyHolder.class);
        when(holder.getBid("USD")).thenReturn(3.50F);
        when(holder.getAsk("EUR")).thenReturn(4.00F);

        Float result = ExchangeCalculator.calculate(holder,"USD", "EUR", 35.00F);
        verify(holder).getBid("USD");
        verify(holder).getAsk("EUR");
        assertEquals(result,30.63F);
    }

    @Test
    void testExchangeCalculatorUSDtoEURorder_expectingSuccess(){
        CurrencyHolder holder = mock(CurrencyHolder.class);
        when(holder.getBid("USD")).thenReturn(3.50F);
        when(holder.getAsk("EUR")).thenReturn(4.00F);

        Float result = ExchangeCalculator.calculate(holder,"USD", "EUR", 35.00F);

        verify(holder).getBid("USD");
        verify(holder).getAsk("EUR");

        InOrder inOrder = Mockito.inOrder(holder);
        inOrder.verify(holder).getBid("USD");
        inOrder.verify(holder).getAsk("EUR");
    }

}
