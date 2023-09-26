package io.github.yasenia.support.tupple;

import java.util.stream.Stream;

/**
 * A Quintuple is a Tuple which contains five elements of the same type.
 * It is a record class in Java, which is a special kind of class in Java
 * used to model immutable data in applications.
 *
 * <p>Quintuple is a form of a 5-Tuple which means it contains five elements in a specific order.
 * It is a convenient way to manage five related pieces of information as a single unit.
 *
 * <p>This Quintuple class provides a static factory method {@code of} to make the
 * instantiation of a Quintuple more expressive and concise.
 *
 * @param <T> the type of the elements stored in this Quintuple.
 */
public record Quintuple<T>(
    T first,
    T second,
    T third,
    T fourth,
    T fifth
) implements Tuple<T> {

    /**
     * A static factory method to create a new instance of Quintuple.
     * This provides a concise way to create instances of Quintuple without
     * using the new keyword.
     *
     * @param first  the first element of the Quintuple
     * @param second the second element of the Quintuple
     * @param third  the third element of the Quintuple
     * @param fourth the fourth element of the Quintuple
     * @param fifth  the fifth element of the Quintuple
     * @return a new instance of Quintuple containing the provided elements
     */
    public static <T> Quintuple<T> of(T first, T second, T third, T fourth, T fifth) {
        return new Quintuple<>(first, second, third, fourth, fifth);
    }

    @Override
    public Stream<T> toStream() {
        return Stream.of(first, second, third, fourth, fifth);
    }
}
