package pd09;
@FunctionalInterface
public interface Transformer<T> {
    T transformer(T t);

    public default Transformer<T> chainMethod(Transformer<T> after) {
        return t -> after.transformer(transformer(t));
    }
}
