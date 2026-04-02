package zl08;

import java.util.Objects;

public class Pair<A, B> {
    private A first;
    private B second;

    private Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }

    public static <A, B> Pair<A,B> of(A a, B b) {
        return new Pair<>(a, b);
    }

    public Pair<B,A> swap() {
        return new Pair<>(second, first);
    }

    public boolean equals(Pair p) {
        if (this == p) return true;
        if (p == null) return false;
        return first.equals(p.first) && second.equals(p.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}
