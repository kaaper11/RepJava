package pd09;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static <T> List<T> toList(List<T> list, Transformer<T> transformer) {
        ArrayList<T> result = new ArrayList<>();
        for (T t : list) {
            result.add(transformer.transformer(t));
        }
        return result;
    }

    public static void main(String[] args) {

        List<String> stringList = List.of(" aaaaaaa", "  bbbbbb  b", "   ccccccc  ");
        Transformer<String> stringTransform = StringTransformations
                .removeSpaces()
                .chainMethod(StringTransformations.addPrefix("COS"))
                .chainMethod(StringTransformations.substrToMax(5))
                .chainMethod(StringTransformations.toUpper());

        List<String> result = toList(stringList, stringTransform);
        System.out.println(result);

    }
}
