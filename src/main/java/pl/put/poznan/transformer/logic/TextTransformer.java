package pl.put.poznan.transformer.logic;

/**
 * TextTransformer allows to perform a set of text based transformations on a given String.
 * All transformations are guaranteed to work on english UTF-8 letters.
 * <p>
 * This class handles following {@link TextTransformer#transforms}:
 * <ul>
 * <li>ToUpper
 * <li>ToLower
 * <li>Capitalize
 * <li>Invert
 * <li>ReplaceNumbers
 * <li>ReplaceShortcuts
 * <li>ReplaceFullWords
 * </ul>
 */
public class TextTransformer {
    /**
     * Valid transform names
     */
    private final String[] transforms;

    /**
     * Initializes a new {@link TextTransformer} object.
     *
     * @param transforms valid transform names
     */
    public TextTransformer(String[] transforms){
        this.transforms = transforms;
    }

    /**
     * Applies transforms defined in {@link TextTransformer#transforms} to a given <code>String</code>
     *
     * @param text text scheduled for transformation
     * @return transformed text
     */
    public String transform(String text){

        Transformer resultTransformer = new TextHolder(text);

        for (String transformation : transforms) {
            switch (transformation) {
                case "ToUpper":
                    resultTransformer = new ToUpper(resultTransformer);
                    break;
                case "ToLower":
                    resultTransformer = new ToLower(resultTransformer);
                    break;
                case "Capitalize":
                    resultTransformer = new Capitalize(resultTransformer);
                    break;
                case "Invert":
                    break;
                case "ReplaceNumbers":
                    break;
                case "ReplaceShortcuts":
                    break;
                case "ReplaceFullWords":
                    break;
                case "ToLatex":
                    break;
                case "DeleteMultipiedWords":
                    break;
                case "ToColor":
                    break;
                default:
                    return text;
            }
        }

        return resultTransformer.GetText();
    }
}
