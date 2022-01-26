package pl.put.poznan.transformer.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

public class TransformsManagerTest {
    @BeforeEach
    void setUp() {
        TransformsManager.clear();
    }

    @Test
    void testSaveTransforms_emptyStorage_expectingSuccess() {
        ArrayList<List<String>> transforms = new ArrayList<>();
        ArrayList<String> transform1 = new ArrayList<>();
        ArrayList<String> transform2 = new ArrayList<>();
        int id;

        transform1.add("Capitalize");
        transform2.add("Exchange");
        transform2.add("PLN");
        transforms.add(transform1);
        transforms.add(transform2);
        id = transforms.hashCode();

        assertEquals(id, TransformsManager.saveTransforms(transforms, 0));
    }

    @Test
    void testSaveTransforms_entryExists_expectingSuccess() {
        ArrayList<List<String>> transforms = new ArrayList<>();
        ArrayList<String> transform1 = new ArrayList<>();
        ArrayList<String> transform2 = new ArrayList<>();
        int id;

        transform1.add("Capitalize");
        transform2.add("Exchange");
        transform2.add("PLN");
        transforms.add(transform1);
        transforms.add(transform2);
        id = transforms.hashCode();

        TransformsManager.saveTransforms(transforms, 0);

        assertEquals(id, TransformsManager.saveTransforms(transforms, 0));
    }

    @Test
    void testGetTransforms_entryExists_expectingSuccess() {
        ArrayList<List<String>> transforms = new ArrayList<>();
        ArrayList<String> transform1 = new ArrayList<>();
        ArrayList<String> transform2 = new ArrayList<>();
        int id;

        transform1.add("Capitalize");
        transform2.add("Exchange");
        transform2.add("PLN");
        transforms.add(transform1);
        transforms.add(transform2);
        id = transforms.hashCode();

        TransformsManager.saveTransforms(transforms, 0);

        assertEquals(transforms, TransformsManager.getTransforms(id));
    }

    @Test
    void testGetTransforms_clearStorage_expectingFailure() {
        int id = 1;

        assertNull(TransformsManager.getTransforms(id));
    }
}
