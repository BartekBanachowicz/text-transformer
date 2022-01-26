package pl.put.poznan.transformer.storage;

import java.time.LocalDateTime;
import java.util.Hashtable;
import java.util.List;

/**
 * This class is a storage container and manager for {@link TransformsResource} objects.
 * The storage is initialized on the beginning of execution
 * and preserves the stored values until termination.
 */
public class TransformsManager {
    /**
     * the actual container, used to store {@link TransformsResource} objects
     */
    private static final Hashtable<Integer, TransformsResource> transformsStorage = new Hashtable<>();

    /**
     * Saves the given transforms list as a {@link TransformsResource} instance.
     *
     * @param transforms the transforms list
     * @param minutes the expiration period, in number of minutes, used in the resource creation
     * @return the resource id, that can be later used to reference the stored object
     */
    public static int saveTransforms(List<List<String>> transforms, int minutes) {
        int key = transforms.hashCode();
        TransformsManager.transformsStorage.compute(
                key,
                (k, v) -> (v == null) ? new TransformsResource(transforms, minutes) : v.refresh(minutes)
        );
        return key;
    }

    /**
     * Returns the transforms list identified by the given id.
     *
     * @param id the resource's id
     * @return the transforms list, or {null} if the resource doesn't exist
     */
    public static List<List<String>> getTransforms(int id) {
        TransformsResource transformsResource = TransformsManager.transformsStorage.get(id);
        return transformsResource == null ? null : transformsResource.unpack();
    }

    /**
     * Deletes stale resources.
     */
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
