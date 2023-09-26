package io.github.yasenia.support.tupple;

import java.util.stream.Stream;

/**
 * A Nonuple is a Tuple that contains nine elements of the same type.
 * It is an immutable data structure and is part of the record class in Java.
 *
 * <p>It is used to group together nine related elements to be treated as
 * a single unit, especially in scenarios where there is a need to return or store
 * nine values collectively.
 *
 * @param <T> the type of elements stored in this Nonuple
 */
public record Nonuple<T>(
    T first,
    T second,
    T third,
    T fourth,
    T fifth,
    T sixth,
    T seventh,
    T eighth,
    T ninth
) implements Tuple<T> {

    /**
     * A static factory method to create a new instance of Nonuple.
     * This method is provided for creating instances of Nonuple in a more
     * concise and expressive way without using the new keyword.
     *
     * @param first   the first element of the Nonuple
     * @param second  the second element of the Nonuple
     * @param third   the third element of the Nonuple
     * @param fourth  the fourth element of the Nonuple
     * @param fifth   the fifth element of the Nonuple
     * @param sixth   the sixth element of the Nonuple
     * @param seventh the seventh element of the Nonuple
     * @param eighth  the eighth element of the Nonuple
     * @param ninth   the ninth element of the Nonuple
     * @return a new instance of Nonuple containing the provided elements
     */
    public static <T> Nonuple<T> of(T first, T second, T third, T fourth, T fifth, T sixth, T seventh, T eighth, T ninth) {
        return new Nonuple<>(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth);
    }

    @Override
    public Stream<T> toStream() {
        return Stream.of(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth);
    }
}
