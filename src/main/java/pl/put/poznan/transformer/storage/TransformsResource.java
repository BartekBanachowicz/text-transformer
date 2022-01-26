package pl.put.poznan.transformer.storage;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The instance of this class is a wrapper around a transforms list.
 * It also stores an expiration timestamp.
 */
public class TransformsResource {
    /**
     * the transforms list
     */
    private final List<List<String>> transforms;

    /**
     * the expiration timestamp
     */
    private LocalDateTime expiration;

    /**
     * Initializes a new {@link TransformsResource} object, with given transforms list
     * and an expiration timestamp equal to current time plus the given expiration period.
     *
     * @param transforms the transforms list
     * @param minutes the expiration period, in number of minutes
     */
    public TransformsResource(List<List<String>> transforms, int minutes) {
        this.transforms = transforms;
        this.expiration = LocalDateTime.now().plusMinutes(minutes);
    }

    /**
     * Calculates a potential expiration timestamp based on current time and given expiration period.
     * If the calculated expiration timestamp is greater than the previous one, it replaces the old one.
     *
     * @param minutes the expiration period, in number of minutes
     * @return the refreshed object
     */
    public TransformsResource refresh(int minutes) {
        LocalDateTime expiration = LocalDateTime.now().plusMinutes(minutes);
        if (expiration.compareTo(this.expiration) > 0) {
            this.expiration = expiration;
        }
        return this;
    }

    /**
     * Checks if the resource is stale, by comparing its expiration time with the given timestamp.
     *
     * @param localDateTime the timestamp, used for comparison
     * @return {true} if the resource is stale, {false} otherwise
     */
    public boolean hasExpired(LocalDateTime localDateTime) {
        return localDateTime.compareTo(this.expiration) > 0;
    }

    /**
     * Returns the transforms list that is being stored.
     *
     * @return the transforms list
     */
    public List<List<String>> unpack() {
        return this.transforms;
    }
}
