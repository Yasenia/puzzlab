package io.github.yasenia.support.tupple;

import java.util.stream.Stream;

/**
 * A Triple is a Tuple which contains three elements of the same type.
 * It is a record class in Java, which is a special kind of class in Java
 * used to model immutable data in applications.
 *
 * <p>Triple is a form of a 3-Tuple which means it contains three elements in a specific order.
 * It is a convenient way to manage three related pieces of information as a single unit.
 *
 * <p>This Triple class provides a static factory method {@code of} to make the
 * instantiation of a Triple more expressive and concise.
 *
 * @param <T> the type of the elements stored in this Triple.
 */
public record Triple<T>(
    T first,
    T second,
    T third
) implements Tuple<T> {

    /**
     * A static factory method to create a new instance of Triple.
     * This provides a concise way to create instances of Triple without
     * using the new keyword.
     *
     * @param first  the first element of the Triple
     * @param second the second element of the Triple
     * @param third  the third element of the Triple
     * @return a new instance of Triple containing the provided elements
     */
    public static <T> Triple<T> of(T first, T second, T third) {
        return new Triple<>(first, second, third);
    }

    @Override
    public Stream<T> toStream() {
        return Stream.of(first, second, third);
    }
}
