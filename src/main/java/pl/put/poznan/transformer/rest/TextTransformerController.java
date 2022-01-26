package pl.put.poznan.transformer.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.put.poznan.transformer.logic.TextTransformer;
import pl.put.poznan.transformer.storage.TransformsManager;

import java.util.Arrays;
import java.util.List;


@RestController
public class TextTransformerController {

    private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);

    @RequestMapping(value="/convert", method=RequestMethod.GET, produces="application/json")
    public String getConvert(
        @RequestParam(value="text") String text,
        @RequestParam(value="transforms", defaultValue="") String[] transforms
    ) {
        logger.info("Received a request");

        logger.debug("text: " + text);
        logger.debug("transforms: " + Arrays.toString(transforms));

        TextTransformer transformer = new TextTransformer(transforms);
        String result = transformer.transform(text);
        logger.debug("return: " + result);
        return result;
    }

    @RequestMapping(value="/convert", method=RequestMethod.POST, consumes="application/json", produces="application/json")
    public String postConvert(
        @RequestParam(value="text") String text,
        @RequestBody List<List<String>> transforms
    ) {
        logger.info("Received a request");
        logger.debug("text: " + text);
        logger.debug("transforms: " + transforms);

        TextTransformer transformer = new TextTransformer(transforms);
        String result = transformer.transform(text);
        logger.debug("return: " + result);
        return result;
    }

    @RequestMapping(value="/convert/{id}", method=RequestMethod.GET, produces="application/json")
    public String getConvertId(
        @PathVariable int id,
        @RequestParam(value="text") String text
    ) {
        List<List<String>> transforms;

        logger.info("Received a request");
        logger.debug("text: " + text);
        logger.debug("id: " + id);

        transforms = TransformsManager.getTransforms(id);
        if (transforms == null) {
            logger.debug("throw: NO_CONTENT");
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }

        logger.debug("transforms: " + transforms);

        TextTransformer transformer = new TextTransformer(transforms);
        String result = transformer.transform(text);
        logger.debug("return: " + result);
        return result;
    }

    @RequestMapping(value="/transforms", method=RequestMethod.POST, consumes="application/json", produces="application/json")
    public ResponseEntity<String> postTransforms(
            @RequestParam(value="minutes", defaultValue="5") int minutes,
            @RequestBody List<List<String>> transforms
    ) {
        int id;

        logger.info("Received a request");
        logger.debug("transforms: " + transforms);

        id = TransformsManager.saveTransforms(transforms, minutes);
        logger.debug("return: " + id);
        return new ResponseEntity<>(String.valueOf(id), HttpStatus.CREATED);
    }
}
