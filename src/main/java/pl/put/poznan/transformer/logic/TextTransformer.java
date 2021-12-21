package pl.put.poznan.transformer.logic;

public class TextTransformer {

    private final String[] transforms;

    public TextTransformer(String[] transforms){
        this.transforms = transforms;
    }

    public static final FindInDictionary dictionary = new FindInDictionary();
    public static final SplitToWords splitter = new SplitToWords();

    public String transform(String text){

        Transformer resultTransformer = new TextHolder(text);

        for (String transformation : transforms) {

            switch (transformation) {
                case "ToUpper":
                    resultTransformer = new ToUpper(resultTransformer);
                    break;
                case "ToLower":
                    break;
                case "Capitalize":
                    break;
                case "Invert":
                    resultTransformer = new Invert(resultTransformer);
                    break;
                case "ReplaceNumbers":
                    break;
                case "ReplaceShortcuts":
                    resultTransformer = new ReplaceShortcuts(resultTransformer);
                    break;
                case "ReplaceFullWords":
                    resultTransformer = new ReplaceFullWords(resultTransformer);
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
