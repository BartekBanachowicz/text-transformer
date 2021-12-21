package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextTransformerTest {
    private TextTransformer transformer;
    private final String text = "MOIm zDAniEm to NIE ma TaK, że doBRZe albo że nie DOBrze!";

    @Test
    void testTransform_toUpperGiven_expectingSuccess() {
        String[] transforms = {"ToUpper"};
        transformer = new TextTransformer(transforms);
        String result = transformer.transform(text);

        assertEquals(result, "MOIM ZDANIEM TO NIE MA TAK, ŻE DOBRZE ALBO ŻE NIE DOBRZE!");
    }

    @Test
    void testTransform_toUpperGiven_expectingFailure() {
        String[] transforms = {"ToUpper"};
        transformer = new TextTransformer(transforms);
        String result = transformer.transform(text);

        assertNotSame(result, "moim zdaniem to nie tak, że dobrze albo że nie dobrze!");
    }

    @Test
    void testTransform_noTransformationGiven_ExpectingUneditedInput() {
        String[] transforms = {""};
        transformer = new TextTransformer(transforms);
        String result = transformer.transform(text);

        assertEquals(result, "MOIm zDAniEm to NIE ma TaK, że doBRZe albo że nie DOBrze!");
    }

    @Test
    void testTransform_invertGiven_ExpectingSuccess() {
        String[] transforms = {"Invert"};
        transformer = new TextTransformer(transforms);
        String result = transformer.transform(text);

        assertEquals(result, "!EZrboD eiN eż oBLA ezrBoD eż ,kaT Am ein ot meinaDZ miom");
    }

    @Test
    void testTransform_replaceNumbers_ExpectingSuccess() {
        String test = "Po -5,25 zł";
        TextTransformer transformer = new TextTransformer(new String[]{"ReplaceNumbers"});
        String result = transformer.transform(test);
        assertEquals("Po minus pięć i dwadzieścia pięć setnych zł",result);
    }

    @Test
    void testTransform_replaceNumbers_Zero_ExpectingSuccess() {
        String test = "Po -005,25 zł";
        TextTransformer transformer = new TextTransformer(new String[]{"ReplaceNumbers"});
        String result = transformer.transform(test);
        assertEquals("Po minus pięć i dwadzieścia pięć setnych zł",result);
    }

    @Test
    void testTransform_replaceNumbers_ZeroEnd_ExpectingSuccess() {
        String test = "Po -005,250 zł";
        TextTransformer transformer = new TextTransformer(new String[]{"ReplaceNumbers"});
        String result = transformer.transform(test);
        assertEquals("Po minus pięć i dwieście pięćdziesiąt tysięcznych zł",result);
    }
}