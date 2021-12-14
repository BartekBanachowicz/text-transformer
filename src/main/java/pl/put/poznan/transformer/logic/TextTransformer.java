package pl.put.poznan.transformer.logic;

public class TextTransformer {

    private final String[] transforms;

    public TextTransformer(String[] transforms){
        this.transforms = transforms;
    }

    public String transform(String text){

        Transformer resultTransformer = new TextHolder(text);
        String[] transformations = transforms[0].split(",");

        for (String transformation : transformations) {

            switch (transformation) {
                case "ToUpper":
                    resultTransformer = new ToUpper(resultTransformer);
                    break;
                case "ToLower":
                    break;
                case "Capitalize":
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
