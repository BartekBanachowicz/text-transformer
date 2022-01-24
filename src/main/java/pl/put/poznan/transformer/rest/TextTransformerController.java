package pl.put.poznan.transformer.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.TextTransformer;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/{text}")
public class TextTransformerController {

    private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public String get(@PathVariable String text,
                      @RequestParam(value="transforms", defaultValue="") String[] transforms) {

        logger.info("Received a request");

        // log the parameters
        logger.debug("text: " + text);
        logger.debug("transforms: " + Arrays.toString(transforms));

        // perform the transformation, you should run your logic here, below is just a silly example
        TextTransformer transformer = new TextTransformer(transforms);
        String result = transformer.transform(text);
        logger.debug("return: " + result);
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public String post(@PathVariable String text,
                       @RequestBody List<List<String>> transforms) {

        logger.info("Received a request");

        // log the parameters
        logger.debug("text: " + text);
        logger.debug("transforms: " + transforms.toString());

        // perform the transformation, you should run your logic here, below is just a silly example
        TextTransformer transformer = new TextTransformer(transforms);
        String result = transformer.transform(text);
        logger.debug("return: " + result);
        return result;
    }



}


