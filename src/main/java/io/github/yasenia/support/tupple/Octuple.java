package io.github.yasenia.support.tupple;

import java.util.stream.Stream;

/**
 * An Octuple is a Tuple that holds eight elements of the same type.
 * It is an immutable data structure and is part of the record class in Java.
 *
 * <p>This class is used to group together eight related elements to be treated as
 * a single unit. It can be useful in scenarios where there is a need to return
 * eight values from a method or to store them in a collection.
 *
 * @param <T> the type of elements stored in this Octuple
 */
public record Octuple<T>(
    T first,
    T second,
    T third,
    T fourth,
    T fifth,
    T sixth,
    T seventh,
    T eighth
) implements Tuple<T> {

    /**
     * A static factory method to create a new instance of Octuple.
     * This method provides a concise way to create an instance of Octuple
     * without using the new keyword.
     *
     * @param first   the first element of the Octuple
     * @param second  the second element of the Octuple
     * @param third   the third element of the Octuple
     * @param fourth  the fourth element of the Octuple
     * @param fifth   the fifth element of the Octuple
     * @param sixth   the sixth element of the Octuple
     * @param seventh the seventh element of the Octuple
     * @param eighth  the eighth element of the Octuple
     * @return a new instance of Octuple containing the provided elements
     */
    public static <T> Octuple<T> of(T first, T second, T third, T fourth, T fifth, T sixth, T seventh, T eighth) {
        return new Octuple<>(first, second, third, fourth, fifth, sixth, seventh, eighth);
    }

    @Override
    public Stream<T> toStream() {
        return Stream.of(first, second, third, fourth, fifth, sixth, seventh, eighth);
    }
}
