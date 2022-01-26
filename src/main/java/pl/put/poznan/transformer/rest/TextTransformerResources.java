package pl.put.poznan.transformer.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.put.poznan.transformer.storage.TransformsManager;

@Component
public class TextTransformerResources {
    private static final Logger logger = LoggerFactory.getLogger(TextTransformerResources.class);

    @Scheduled(fixedDelay = 60000)
    public void deleteStaleResources() {
        TextTransformerResources.logger.info("Running the scheduled clean up task");
        TransformsManager.clear();
    }
}
