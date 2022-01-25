package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeleteDuplicatesTest {

    private String text;
    private DeleteDuplicates delete;
    private String actual;
    private String expected;
    private Transformer mock;

    @Test
    void testDeleteDuplicates_singleDuplicate_ExpectingSuccess(){
        text = "Siała Siała baba baba mak";

        mock = mock(Transformer.class);
        when(mock.getText()).thenReturn(text);
        DeleteDuplicates delete = new DeleteDuplicates(mock);

        actual = delete.getText();
        expected = "Siała baba mak";

        assertEquals(expected, actual);
    }

    @Test
    void testDeleteDuplicates_checkIfCaseIsIgnored_ExpectingSuccess(){
        text = "Siała siała baba mak nie  wiedziała jak a dziad    DZIAD  wiedział nie, nie powiedział a to było tak";

        mock = mock(Transformer.class);
        when(mock.getText()).thenReturn(text);
        DeleteDuplicates delete = new DeleteDuplicates(mock);

        actual = delete.getText();
        expected = "Siała baba mak nie wiedziała jak a dziad wiedział nie, nie powiedział a to było tak";

        assertEquals(expected, actual);
    }

    @Test
    void testDeleteDuplicates_multipleDuplicates_ExpectingSuccess(){
        text = "Siała siała SIAŁA... siała siała baba mak";

        mock = mock(Transformer.class);
        when(mock.getText()).thenReturn(text);
        DeleteDuplicates delete = new DeleteDuplicates(mock);

        actual = delete.getText();
        expected = "Siała... siała baba mak";

        assertEquals(expected, actual);
    }

}
