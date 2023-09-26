package io.github.yasenia.support.tupple;

import java.util.stream.Stream;

/**
 * A Sextuple is a Tuple that contains six elements of the same type.
 * It is a record class in Java, which is a special kind of class in Java
 * used to model immutable data in applications.
 *
 * <p>Sextuple is a form of a 6-Tuple which means it contains six elements in a specific order.
 * It is a convenient way to manage six related pieces of information as a single unit.
 *
 * <p>This Sextuple class provides a static factory method {@code of} to make the
 * instantiation of a Sextuple more expressive and concise.
 *
 * @param <T> the type of the elements stored in this Sextuple.
 */
public record Sextuple<T>(
    T first,
    T second,
    T third,
    T fourth,
    T fifth,
    T sixth
) implements Tuple<T> {

    /**
     * A static factory method to create a new instance of Sextuple.
     * This provides a concise way to create instances of Sextuple without
     * using the new keyword.
     *
     * @param first  the first element of the Sextuple
     * @param second the second element of the Sextuple
     * @param third  the third element of the Sextuple
     * @param fourth the fourth element of the Sextuple
     * @param fifth  the fifth element of the Sextuple
     * @param sixth  the sixth element of the Sextuple
     * @return a new instance of Sextuple containing the provided elements
     */
    public static <T> Sextuple<T> of(T first, T second, T third, T fourth, T fifth, T sixth) {
        return new Sextuple<>(first, second, third, fourth, fifth, sixth);
    }

    @Override
    public Stream<T> toStream() {
        return Stream.of(first, second, third, fourth, fifth, sixth);
    }
}
