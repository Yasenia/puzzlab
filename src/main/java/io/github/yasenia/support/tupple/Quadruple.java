package io.github.yasenia.support.tupple;

import java.util.stream.Stream;

/**
 * A Quadruple is a Tuple which contains four elements of the same type.
 * It is a record class in Java, which is a special kind of class in Java
 * used to model immutable data in applications.
 *
 * <p>Quadruple is a form of a 4-Tuple which means it contains four elements in a specific order.
 * It is a convenient way to manage four related pieces of information as a single unit.
 *
 * <p>This Quadruple class provides a static factory method {@code of} to make the
 * instantiation of a Quadruple more expressive and concise.
 *
 * @param <T> the type of the elements stored in this Quadruple.
 */
public record Quadruple<T>(
    T first,
    T second,
    T third,
    T fourth
) implements Tuple<T> {

    /**
     * A static factory method to create a new instance of Quadruple.
     * This provides a concise way to create instances of Quadruple without
     * using the new keyword.
     *
     * @param first  the first element of the Quadruple
     * @param second the second element of the Quadruple
     * @param third  the third element of the Quadruple
     * @param fourth the fourth element of the Quadruple
     * @return a new instance of Quadruple containing the provided elements
     */
    public static <T> Quadruple<T> of(T first, T second, T third, T fourth) {
        return new Quadruple<>(first, second, third, fourth);
    }

    @Override
    public Stream<T> toStream() {
        return Stream.of(first, second, third, fourth);
    }
}
