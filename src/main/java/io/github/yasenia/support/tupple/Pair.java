package io.github.yasenia.support.tupple;

import java.util.stream.Stream;

/**
 * A Pair is a Tuple which contains two elements of the same type.
 * It is a record class in Java, which is a special kind of class in Java
 * used to model immutable data in applications.
 *
 * <p>Pair is a form of a 2-Tuple which means it contains two elements in a specific order.
 * It is a convenient way to manage two related pieces of information as a single unit.
 *
 * <p>This Pair class provides a static factory method {@code of} to make the
 * instantiation of a Pair more expressive and concise.
 *
 * @param <T> the type of the elements stored in this Pair.
 */
public record Pair<T>(
    T first,
    T second
) implements Tuple<T> {

    /**
     * A static factory method to create a new instance of Pair.
     * This provides a concise way to create instances of Pair without
     * using the new keyword.
     *
     * @param first  the first element of the Pair
     * @param second the second element of the Pair
     * @return a new instance of Pair containing the provided elements
     */
    public static <T> Pair<T> of(T first, T second) {
        return new Pair<>(first, second);
    }

    @Override
    public Stream<T> toStream() {
        return Stream.of(first, second);
    }
}
