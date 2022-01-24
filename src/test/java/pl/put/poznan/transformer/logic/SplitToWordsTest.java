package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SplitToWordsTest {
    private String text;
    private SplitToWords splitter;
    private String[] result;

    @Test
    void testSplitToWords_splitWithoutPunctuationMarks_ExpectingSuccess(){
        text = "Siała baba mak nie wiedziała jak a dziad wiedział nie powiedział a to było tak";
        splitter = new SplitToWords();

        result = splitter.split(text);
        String[] actual = {"Siała", "baba", "mak" , "nie", "wiedziała", "jak", "a", "dziad",
                "wiedział", "nie", "powiedział","a", "to", "było", "tak"};

        assertArrayEquals(result, actual);
    }

    /*@Test
    void testSplitToWords_splitWithPunctuationMarks_ExpectingSuccess(){
        text = "Siała baba mak, nie wiedziała jak, a dziad wiedział, nie powiedział, a to było tak!";
        splitter = new SplitToWords();

        result = splitter.split(text);
        String[] actual = {"Siała", "baba", "mak" , ",", "nie", "wiedziała", "jak", ",", "a", "dziad",
                "wiedział", ",", "nie", "powiedział", ",", "a", "to", "było", "tak"};

        assertArrayEquals(result, actual);
    }*/
}
