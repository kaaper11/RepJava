package pd09;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringTransformations {

    public static final Transformer<String> removeSpaces() {
        return input -> input.trim();
    }

    public static final Transformer<String> toUpper() {
        return input -> input.toUpperCase();
    }

    public static final Transformer<String> substrToMax(int max) {
        return input -> input.length() <= max ? input : input.substring(0, max);
    }

    public static final Transformer<String> addPrefix(String prefix) {
        return input -> prefix + input;
    }
}
