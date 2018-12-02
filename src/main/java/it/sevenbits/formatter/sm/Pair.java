package it.sevenbits.formatter.sm;

import java.util.Objects;

/**
 * class for pairing two objects
 */
public final class Pair<T, U> {

    private final T first;
    private final U second;

    /**
     * constructor for pair
     *
     * @param first  - 1st obj
     * @param second - 2nd obj
     */
    public Pair(final T first, final U second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(first, pair.first) &&
                Objects.equals(second, pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
