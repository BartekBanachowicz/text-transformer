package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

    @Test
    void testSplitToWords_splitWithPunctuationMarks_ExpectingSuccess(){
        text = "Siała baba mak, nie wiedziała jak, a dziad wiedział, nie powiedział, a to było tak!";
        splitter = new SplitToWords();

        result = splitter.split(text);
        String[] actual = {"Siała", "baba", "mak" , ",", "nie", "wiedziała", "jak", ",", "a", "dziad",
                "wiedział", ",", "nie", "powiedział", ",", "a", "to", "było", "tak"};

        assertArrayEquals(result, actual);
    }
}
