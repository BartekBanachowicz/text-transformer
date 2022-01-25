package pl.put.poznan.transformer.storage;

import java.time.LocalDateTime;
import java.util.List;

public class TransformsResource {
    private final List<List<String>> transforms;
    private LocalDateTime expiration;

    public TransformsResource(List<List<String>> transforms, int minutes) {
        this.transforms = transforms;
        this.expiration = LocalDateTime.now().plusMinutes(minutes);
    }

    public TransformsResource refresh(int minutes) {
        LocalDateTime expiration = LocalDateTime.now().plusMinutes(minutes);
        if (expiration.compareTo(this.expiration) > 0) {
            this.expiration = expiration;
        }
        return this;
    }

    public boolean hasExpired(LocalDateTime localDateTime) {
        return localDateTime.compareTo(this.expiration) > 0;
    }

    public List<List<String>> unpack() {
        return this.transforms;
    }
}
