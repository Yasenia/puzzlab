package io.github.yasenia.support.tupple;

import java.util.stream.Stream;

/**
 * A Decuple is a Tuple that holds ten elements of the same type.
 * It is an immutable data structure and is part of the record class in Java.
 *
 * <p>This class is useful when there is a need to group ten related elements together
 * and treat them as a single unit, like when returning or storing ten values collectively.
 *
 * @param <T> the type of elements stored in this Decuple
 */
public record Decuple<T>(
    T first,
    T second,
    T third,
    T fourth,
    T fifth,
    T sixth,
    T seventh,
    T eighth,
    T ninth,
    T tenth
) implements Tuple<T> {

    /**
     * A static factory method to create a new instance of Decuple.
     * This method offers a concise and expressive way to instantiate
     * a Decuple without using the new keyword.
     *
     * @param first   the first element of the Decuple
     * @param second  the second element of the Decuple
     * @param third   the third element of the Decuple
     * @param fourth  the fourth element of the Decuple
     * @param fifth   the fifth element of the Decuple
     * @param sixth   the sixth element of the Decuple
     * @param seventh the seventh element of the Decuple
     * @param eighth  the eighth element of the Decuple
     * @param ninth   the ninth element of the Decuple
     * @param tenth   the tenth element of the Decuple
     * @return a new instance of Decuple containing the provided elements
     */
    public static <T> Decuple<T> of(T first, T second, T third, T fourth, T fifth, T sixth, T seventh, T eighth, T ninth, T tenth) {
        return new Decuple<>(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth);
    }

    @Override
    public Stream<T> toStream() {
        return Stream.of(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth);
    }
}
