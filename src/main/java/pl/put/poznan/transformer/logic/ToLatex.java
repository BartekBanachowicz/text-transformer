package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ToLatex extends TextDecorator {

    public ToLatex(Transformer t) {
        super(t);
    }

    private static final Logger logger = LoggerFactory.getLogger(ToLatex.class);

    @Override
    public String getText() {
        String s = super.getText();
        StringBuilder newString = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '&') {
                newString.append("\\&");
            } else if (s.charAt(i) == '$') {
                newString.append("\\$");
            } else {
                newString.append(s.charAt(i));
            }
        }
        String result = newString.toString();
        logger.debug("return: " + result);
        return result;
    }

}
