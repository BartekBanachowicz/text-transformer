package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReplaceNumbersTest {

    @Test
    void getText() {
        String test = "Po -5,25 zł";
        TextTransformer transformer = new TextTransformer(List.of(List.of("ReplaceNumbers")));
        String result = transformer.transform(test);
        assertEquals("Po minus pięć i dwadzieścia pięć setnych zł",result);
    }
}