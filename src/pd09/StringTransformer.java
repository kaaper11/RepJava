package pd09;

public class StringTransformer {

    public static Transformer<String> removeSpaces() {
        return t -> t.trim();
    }

    public static Transformer<String> toUpper() {
        return t -> t.toUpperCase();
    }

    public static Transformer<String> substtToMax(int max) {
        return t -> t.length() < max ? t : t.substring(0, max);
    }

    public static Transformer<String> addPrefix(String prefix) {
        return t -> prefix + t;
    }
}
