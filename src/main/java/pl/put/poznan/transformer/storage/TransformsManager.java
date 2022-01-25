package pl.put.poznan.transformer.storage;

import java.time.LocalDateTime;
import java.util.Hashtable;
import java.util.List;

public class TransformsManager {
    private static final Hashtable<Integer, TransformsResource> transformsStorage = new Hashtable<>();

    public static int saveTransforms(List<List<String>> transforms, int minutes) {
        int key = transforms.hashCode();
        TransformsManager.transformsStorage.compute(
                key,
                (k, v) -> (v == null) ? new TransformsResource(transforms, minutes) : v.refresh(minutes)
        );
        return key;
    }

    public static List<List<String>> getTransforms(int id) {
        TransformsResource transformsResource = TransformsManager.transformsStorage.get(id);
        return transformsResource == null ? null : transformsResource.unpack();
    }

    public static void clear() {
        LocalDateTime localDateTime = LocalDateTime.now();
        for (Integer key : TransformsManager.transformsStorage.keySet()) {
            TransformsManager.transformsStorage.computeIfPresent(
                    key,
                    (k, v) -> v.hasExpired(localDateTime) ? null : v
            );
        }
    }
}
