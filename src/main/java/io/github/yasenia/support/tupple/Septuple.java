package io.github.yasenia.support.tupple;

import java.util.stream.Stream;

/**
 * A Septuple is a Tuple that contains seven elements of the same type.
 * It is a record class in Java, which is a special kind of class in Java
 * used to model immutable data in applications.
 *
 * <p>Septuple is a form of a 7-Tuple which means it contains seven elements in a specific order.
 * It is a convenient way to manage seven related pieces of information as a single unit.
 *
 * <p>This Septuple class provides a static factory method {@code of} to make the
 * instantiation of a Septuple more expressive and concise.
 *
 * @param <T> the type of the elements stored in this Septuple.
 */
public record Septuple<T>(
    T first,
    T second,
    T third,
    T fourth,
    T fifth,
    T sixth,
    T seventh
) implements Tuple<T> {

    /**
     * A static factory method to create a new instance of Septuple.
     * This provides a concise way to create instances of Septuple without
     * using the new keyword.
     *
     * @param first   the first element of the Septuple
     * @param second  the second element of the Septuple
     * @param third   the third element of the Septuple
     * @param fourth  the fourth element of the Septuple
     * @param fifth   the fifth element of the Septuple
     * @param sixth   the sixth element of the Septuple
     * @param seventh the seventh element of the Septuple
     * @return a new instance of Septuple containing the provided elements
     */
    public static <T> Septuple<T> of(T first, T second, T third, T fourth, T fifth, T sixth, T seventh) {
        return new Septuple<>(first, second, third, fourth, fifth, sixth, seventh);
    }

    @Override
    public Stream<T> toStream() {
        return Stream.of(first, second, third, fourth, fifth, sixth, seventh);
    }
}
