package io.github.yasenia.puzzle.cube.standard.geometric.accessor;

import io.github.yasenia.puzzle.cube.standard.geometric.orientation.Orientation;

import java.util.function.Function;

/**
 * Represents an object that provides access to associated objects in various orientations.
 *
 * <p>
 * Classes implementing this interface can provide access to associated objects based
 * on the specified orientation, allowing for a flexible and extensible way to retrieve
 * objects based on their orientation relative to the implementing object.
 *
 * @param <O> the type of orientation, which should implement the {@link Orientation} interface
 * @param <T> the type of associated objects that can be accessed based on the orientation
 */
public interface OrientationAccessible<O extends Orientation, T> {

    /**
     * Returns the associated object in the specified orientation.
     *
     * <p>
     * Implementations should define the behavior of this method to return
     * the object associated with the implementing object in the given orientation.
     *
     * @param orientation the orientation in which to access the associated object
     * @return the associated object in the specified orientation
     */
    T at(O orientation);

    /**
     * Transforms the associated objects based on a provided mapping function.
     *
     * <p>
     * This method applies the given function to each associated object and returns a new instance of OrientationAccessible with the transformed associations.
     *
     * @param mapper the function to apply to the associated objects.
     * @return a new instance of OrientationAccessible with the transformed associations.
     */
    default <R> OrientationAccessible<O, R> map(Function<T, R> mapper) {
        return orientation -> mapper.apply(OrientationAccessible.this.at(orientation));
    }
}
