package zl08;

public final class IntegerToString implements Transformer<Integer, String> {

    @Override
    public String transform(Integer input) {
        return input != null ? String.valueOf(input) : null;
    }
}
