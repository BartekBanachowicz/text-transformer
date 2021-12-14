package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextTransformerTest {
    private TextTransformer transformer;
    private String text = "MOIm zDAniEm to NIE ma TaK, że doBRZe albo że nie DOBrze!";

    @Test
    void testTransform_toUpperGiven_expectingSuccess() {
        String[] transforms = {"ToUpper"};
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
}