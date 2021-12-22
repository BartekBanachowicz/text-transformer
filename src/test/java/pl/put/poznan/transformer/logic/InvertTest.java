package pl.put.poznan.transformer.logic;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class InvertTest{
    private final String text="MOIm zDAniEm to NIE ma TaK, że doBRZe albo że nie DOBrze!";

    @Test
    void testInvert_findUpperLettersPositions_expectingSuccess(){
        Transformer mock = mock(Transformer.class);
        Invert invert= new Invert(mock);

        Boolean[] result;
        result = invert.findUpperLetters(text);

        StringBuilder resultString = new StringBuilder();
        for(int i=0; i<57;i++){
            resultString.append(result[i].toString());
            resultString.append(",");
        }

        assertEquals(resultString.toString(), "true,true,true,false,false,false,true,true,false,false," +
                "true,false,false,false,false,false,true,true,true,false,false,false,false,true,false,true," +
                "false,false,false,false,false,false,false,true,true,true,false,false,false,false,false,false," +
                "false,false,false,false,false,false,false,false,true,true,true,false,false,false,false,");
    }

    @Test
    void testInvert_expectingSuccess(){
        Transformer mock = mock(Transformer.class);
        Invert invert= new Invert(mock);

        when(mock.getText()).thenReturn(text);

        String result = invert.getText();

        assertEquals(result, "!EZrboD eiN eż oBLA ezrBoD eż ,kaT Am ein ot meinaDZ miom");
    }

    @Test
    void testInvert_caseFromBacklog_expectingSuccess(){
        Transformer mock = mock(Transformer.class);
        Invert invert= new Invert(mock);

        when(mock.getText()).thenReturn("MirEk");

        String result = invert.getText();

        assertEquals(result, "KerIm");
    }
}
