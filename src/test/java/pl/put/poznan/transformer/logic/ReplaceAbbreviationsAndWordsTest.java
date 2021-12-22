package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReplaceAbbreviationsAndWordsTest {

    @Test
    void replaceShortcutsTest_noPunctuationMarks_lowerLettersOnly(){
        Transformer mock = mock(Transformer.class);
        ReplaceAbbreviations replacer = new ReplaceAbbreviations(mock);
        String result, text;

        text = "Siała prof. baba m.in. mak itp. nie wiedziała jak a dziad wiedział nie powiedział itd. a to było tak";
        when(mock.getText()).thenReturn(text);

        result = replacer.getText();
        assertEquals(result, "Siała profesor baba między innymi mak i tym podobne nie wiedziała jak a dziad wiedział nie powiedział i tak dalej a to było tak");
    }

    @Test
    void replaceShortcutsTest_noPunctuationMarks_withUpperLetters(){
        Transformer mock = mock(Transformer.class);
        ReplaceAbbreviations replacer = new ReplaceAbbreviations(mock);
        String result, text;

        text = "Siała Prof. baba M.In. mak ITP. nie wiedziała NR jak a dziad Al. wiedział nie powiedział itd. a to było tak";
        when(mock.getText()).thenReturn(text);

        result = replacer.getText();
        assertEquals(result, "Siała Profesor baba Między Innymi mak I Tym Podobne nie wiedziała NUMER jak a dziad Aleja wiedział nie powiedział i tak dalej a to było tak");
    }

    @Test
    void replaceWordsTest_noPunctuationMarks(){
        Transformer mock = mock(Transformer.class);
        ReplaceFullWords replacer = new ReplaceFullWords(mock);

        String text = "Siała doktor baba na przykład mak i tym podobne nie wiedziała jak a dziad wiedział nie powiedział i tak dalej a to było tak";
        when(mock.getText()).thenReturn(text);

        String result = replacer.getText();
        assertEquals(result, "Siała dr baba np. mak itp. nie wiedziała jak a dziad wiedział nie powiedział itd. a to było tak");
    }
}
