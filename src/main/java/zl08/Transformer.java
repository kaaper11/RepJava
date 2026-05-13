package zl08;
@FunctionalInterface
public interface Transformer<T,R> {
    R transform(T input);
}
