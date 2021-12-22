package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FindInDictionaryTest {

    @Test
    void testFindInDictionary_ShortcutToWord_expectingSuccess(){
        FindInDictionary dictionary = new FindInDictionary("src/main/resources/dictionary.csv");
        String shortcut = "itd.";
        assertEquals(dictionary.findWordAndReplace(shortcut), "i tak dalej");
    }

    @Test
    void testFindInDictionary_WordToShortcut_expectingSuccess(){
        FindInDictionary dictionary = new FindInDictionary("src/main/resources/dictionary.csv");
        String word = "i tym podobne";
        assertEquals(dictionary.findShortcutAndReplace(word), "itp.");
    }

}