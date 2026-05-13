package zl08;

import java.util.ArrayList;
import java.util.List;

public class TransforemUtils {

    public static <T, R> List<R> transformAll(List<T> input, Transformer<T, R> transformer) {
        List<R> result = new ArrayList<>();
        for (T item : input) {
            result.add(transformer.transform(item));
        }
        return result;
    }
}
