package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

class TextTransformerTest {
    private final String text = "MOIm zDAniEm to NIE ma TaK, że doBRZe albo że nie DOBrze!";

    @Test
    void testTransform_toUpperGiven_expectingSuccess() {
        TextTransformer transformer = new TextTransformer(List.of(List.of("ToUpper")));
        String result = transformer.transform(text);

        assertEquals(result, "MOIM ZDANIEM TO NIE MA TAK, ŻE DOBRZE ALBO ŻE NIE DOBRZE!");
    }

    @Test
    void testTransform_toUpperGiven_expectingFailure() {
        TextTransformer transformer = new TextTransformer(List.of(List.of("ToUpper")));
        String result = transformer.transform(text);

        assertNotSame(result, "moim zdaniem to nie tak, że dobrze albo że nie dobrze!");
    }

    @Test
    void testTransform_noTransformationGiven_ExpectingUneditedInput() {
        TextTransformer transformer = new TextTransformer(List.of(List.of("")));
        String result = transformer.transform(text);

        assertEquals(result, "MOIm zDAniEm to NIE ma TaK, że doBRZe albo że nie DOBrze!");
    }

    @Test
    void testTransform_invertGiven_ExpectingSuccess() {
        TextTransformer transformer = new TextTransformer(List.of(List.of("Invert")));
        String result = transformer.transform(text);

        assertEquals(result, "!EZrboD eiN eż oBLA ezrBoD eż ,kaT Am ein ot meinaDZ miom");
    }

    @Test
    void testTransform_replaceNumbers_ExpectingSuccess() {
        String test = "Po -5,25 zł";
        TextTransformer transformer = new TextTransformer(List.of(List.of("ReplaceNumbers")));
        String result = transformer.transform(test);
        assertEquals("Po minus pięć i dwadzieścia pięć setnych zł",result);
    }

    @Test
    void testTransform_replaceNumbers_Zero_ExpectingSuccess() {
        String test = "Po -005,25 zł";
        TextTransformer transformer = new TextTransformer(List.of(List.of("ReplaceNumbers")));
        String result = transformer.transform(test);
        assertEquals("Po minus pięć i dwadzieścia pięć setnych zł",result);
    }

    @Test
    void testTransform_replaceNumbers_ZeroEnd_ExpectingSuccess() {
        String test = "Po -005,250 zł";
        TextTransformer transformer = new TextTransformer(List.of(List.of("ReplaceNumbers")));
        String result = transformer.transform(test);
        assertEquals("Po minus pięć i dwieście pięćdziesiąt tysięcznych zł",result);
    }
}