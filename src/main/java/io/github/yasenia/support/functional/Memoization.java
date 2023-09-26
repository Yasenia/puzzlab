package io.github.yasenia.support.functional;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static java.util.Collections.synchronizedMap;

/**
 * Utility class for memoization, a technique used for storing the results of expensive function calls
 * and returning the cached result when the same inputs occur again.
 *
 * <p>This class provides static utility methods to memoize {@link Supplier}, {@link Function}, and {@link Predicate} instances.
 * Memoization is particularly useful when working with pure functions that always return the same result for the same input.
 *
 * <p>This implementation is thread-safe.
 *
 * <p><strong>Important:</strong> This implementation does not cache {@code null} values. Callers should be
 * aware of this to avoid cache penetration issues. When a function returns a {@code null}, the function will be invoked again
 * every time it is called with the same input.
 *
 * <p><strong>Note:</strong> The caching mechanism uses a {@link WeakHashMap}, so the cached values are held weakly
 * and may be garbage collected when they are not needed and memory is limited.
 */
public class Memoization {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private Memoization() {
    }

    /**
     * Memoizes a {@link Supplier}. If a value has been computed, it will be returned in subsequent calls.
     *
     * @param supplier The supplier to be memoized.
     * @param <T>      The type of results supplied by this supplier.
     * @return A memoized supplier. <strong>Note:</strong> {@code null} values are not cached.
     */
    public static <T> Supplier<T> memoize(Supplier<T> supplier) {
        return new Supplier<>() {
            private final AtomicReference<T> value = new AtomicReference<>();

            @Override
            public T get() {
                var result = value.get();
                if (result == null) {
                    synchronized (value) {
                        result = value.get();
                        if (result == null) {
                            result = supplier.get();
                            value.set(result);
                        }
                    }
                }
                return result;
            }
        };
    }

    /**
     * Memoizes a {@link Function}. If a value has been computed for a specific input, it will be returned in subsequent calls with the same input.
     *
     * <p>This method returns a thread-safe memoized function.
     *
     * <p><strong>Note:</strong> The caching mechanism uses a {@link WeakHashMap}, so the cached values are held weakly
     * and may be garbage collected when they are not needed and memory is limited.
     *
     * @param function The function to be memoized.
     * @param <T>      The type of the input to the function.
     * @param <R>      The type of the result of the function.
     * @return A memoized function. <strong>Important:</strong> {@code null} values are not cached.
     */
    public static <T, R> Function<T, R> memoize(Function<T, R> function) {
        return new Function<>() {
            private final Map<T, R> value = synchronizedMap(new WeakHashMap<>());

            @Override
            public R apply(T t) {
                return value.computeIfAbsent(t, function);
            }
        };
    }

    /**
     * Memoizes a {@link Predicate}. If a value has been computed for a specific input, it will be returned in subsequent calls with the same input.
     *
     * <p>This method returns a thread-safe memoized predicate.
     *
     * <p><strong>Note:</strong> The caching mechanism uses a {@link WeakHashMap}, so the cached values are held weakly
     * and may be garbage collected when they are not needed and memory is limited.
     *
     * @param predicate The predicate to be memoized.
     * @param <T>       The type of the input to the predicate.
     * @return A memoized predicate. <strong>Important:</strong> {@code null} values are not cached.
     */
    public static <T> Predicate<T> memoize(Predicate<T> predicate) {
        return new Predicate<>() {
            private final Map<T, Boolean> value = synchronizedMap(new WeakHashMap<>());

            @Override
            public boolean test(T t) {
                return value.computeIfAbsent(t, predicate::test);
            }
        };
    }
}
