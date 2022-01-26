package pl.put.poznan.transformer.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.transformer.logic.TextTransformer;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

/**
 * This class is a storage container and manager for {@link TransformsResource} objects.
 * The storage is initialized on the beginning of execution
 * and preserves the stored values until termination.
 */
public class TransformsManager {

    /**
     * logging utility
     */
    private static final Logger logger = LoggerFactory.getLogger(TransformsManager.class);

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
    public static synchronized int saveTransforms(List<List<String>> transforms, int minutes) {
        int key = transforms.hashCode();

        TransformsManager.logger.info("Saving transforms");

        TransformsManager.transformsStorage.compute(
                key,
                (k, v) -> (v == null) ? new TransformsResource(transforms, minutes) : v.refresh(minutes)
        );

        TransformsManager.logger.debug("return: " + key);
        return key;
    }

    /**
     * Returns the transforms list identified by the given id.
     *
     * @param key the resource's id
     * @return the transforms list, or {null} if the resource doesn't exist
     */
    public static synchronized List<List<String>> getTransforms(int key) {
        TransformsManager.logger.info("Accessing transforms");

        TransformsResource transformsResource = TransformsManager.transformsStorage.get(key);

        if (transformsResource == null) {
            TransformsManager.logger.debug("return: null");
            return null;
        }
        else {
            TransformsManager.logger.debug("return: " +  transformsResource.unpack());
            return transformsResource.unpack();
        }
    }

    /**
     * Deletes stale resources.
     */
    public static synchronized void clear() {
        TransformsManager.logger.info("Clearing stale transforms");

        LocalDateTime localDateTime = LocalDateTime.now();
        for (Integer key : new HashSet<>(TransformsManager.transformsStorage.keySet())) {
            TransformsManager.transformsStorage.computeIfPresent(
                    key,
                    (k, v) -> v.hasExpired(localDateTime) ? null : v
            );
        }
    }
}
