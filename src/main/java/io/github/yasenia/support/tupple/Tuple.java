package io.github.yasenia.support.tupple;

import java.util.List;
import java.util.stream.Stream;

/**
 * Represents a generalized Tuple interface that can hold different numbers of elements.
 * This interface is sealed, permitting only specific implementations,
 * ensuring type safety and controlled hierarchy.
 *
 * @param <T> the type of the elements
 */
public sealed interface Tuple<T>
    permits Pair, Triple, Quintuple, Quadruple, Sextuple, Septuple, Octuple, Nonuple, Decuple {

    /**
     * Creates a tuple with two elements.
     *
     * @param first  the first element
     * @param second the second element
     * @return a Pair containing the provided elements
     */
    static <T> Pair<T> tuple2(T first, T second) {
        return Pair.of(first, second);
    }

    /**
     * Creates a tuple with three elements.
     *
     * @param first  the first element
     * @param second the second element
     * @param third  the third element
     * @return a Triple containing the provided elements
     */
    static <T> Triple<T> tuple3(T first, T second, T third) {
        return Triple.of(first, second, third);
    }

    /**
     * Creates a tuple with four elements.
     *
     * @param first  the first element
     * @param second the second element
     * @param third  the third element
     * @param fourth the fourth element
     * @return a Quadruple containing the provided elements
     */
    static <T> Quadruple<T> tuple4(T first, T second, T third, T fourth) {
        return Quadruple.of(first, second, third, fourth);
    }

    /**
     * Creates a tuple with five elements.
     *
     * @param first  the first element
     * @param second the second element
     * @param third  the third element
     * @param fourth the fourth element
     * @param fifth  the fifth element
     * @return a Quintuple containing the provided elements
     */
    static <T> Quintuple<T> tuple5(T first, T second, T third, T fourth, T fifth) {
        return Quintuple.of(first, second, third, fourth, fifth);
    }

    /**
     * Creates a tuple with six elements.
     *
     * @param first  the first element
     * @param second the second element
     * @param third  the third element
     * @param fourth the fourth element
     * @param fifth  the fifth element
     * @param sixth  the sixth element
     * @return a Sextuple containing the provided elements
     */
    static <T> Sextuple<T> tuple6(T first, T second, T third, T fourth, T fifth, T sixth) {
        return Sextuple.of(first, second, third, fourth, fifth, sixth);
    }

    /**
     * Creates a tuple with seven elements.
     *
     * @param first   the first element
     * @param second  the second element
     * @param third   the third element
     * @param fourth  the fourth element
     * @param fifth   the fifth element
     * @param sixth   the sixth element
     * @param seventh the seventh element
     * @return a Septuple containing the provided elements
     */
    static <T> Septuple<T> tuple7(T first, T second, T third, T fourth, T fifth, T sixth, T seventh) {
        return Septuple.of(first, second, third, fourth, fifth, sixth, seventh);
    }

    /**
     * Creates a tuple with eight elements.
     *
     * @param first   the first element
     * @param second  the second element
     * @param third   the third element
     * @param fourth  the fourth element
     * @param fifth   the fifth element
     * @param sixth   the sixth element
     * @param seventh the seventh element
     * @param eighth  the eighth element
     * @return an Octuple containing the provided elements
     */
    static <T> Octuple<T> tuple8(T first, T second, T third, T fourth, T fifth, T sixth, T seventh, T eighth) {
        return Octuple.of(first, second, third, fourth, fifth, sixth, seventh, eighth);
    }

    /**
     * Creates a tuple with nine elements.
     *
     * @param first   the first element
     * @param second  the second element
     * @param third   the third element
     * @param fourth  the fourth element
     * @param fifth   the fifth element
     * @param sixth   the sixth element
     * @param seventh the seventh element
     * @param eighth  the eighth element
     * @param ninth   the ninth element
     * @return a Nonuple containing the provided elements
     */
    static <T> Nonuple<T> tuple9(T first, T second, T third, T fourth, T fifth, T sixth, T seventh, T eighth, T ninth) {
        return Nonuple.of(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth);
    }

    /**
     * Creates a tuple with ten elements.
     *
     * @param first   the first element
     * @param second  the second element
     * @param third   the third element
     * @param fourth  the fourth element
     * @param fifth   the fifth element
     * @param sixth   the sixth element
     * @param seventh the seventh element
     * @param eighth  the eighth element
     * @param ninth   the ninth element
     * @param tenth   the tenth element
     * @return a Decuple containing the provided elements
     */
    static <T> Decuple<T> tuple10(T first, T second, T third, T fourth, T fifth, T sixth, T seventh, T eighth, T ninth, T tenth) {
        return Decuple.of(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth);
    }

    Stream<T> toStream();

    default List<T> toList() {
        return this.toStream().toList();
    }
}
