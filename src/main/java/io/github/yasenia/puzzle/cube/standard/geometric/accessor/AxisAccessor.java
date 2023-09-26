package io.github.yasenia.puzzle.cube.standard.geometric.accessor;

import io.github.yasenia.puzzle.cube.standard.geometric.orientation.AxisOrientation;

import java.util.function.Function;
import java.util.function.Supplier;

import static io.github.yasenia.puzzle.cube.standard.geometric.orientation.AxisOrientation.LEFT;
import static io.github.yasenia.puzzle.cube.standard.geometric.orientation.AxisOrientation.RIGHT;

/**
 * A concrete implementation of {@link OrientationAccessible} that provides access to associated objects
 * in two axial directions: left and right.
 *
 * @param <T> the type of associated objects.
 */
public class AxisAccessor<T> implements OrientationAccessible<AxisOrientation, T> {

    private final Supplier<T> leftSupplier;
    private final Supplier<T> rightSupplier;

    /**
     * Constructs an AxisAccessor with suppliers for each direction.
     *
     * <p>
     * This constructor is useful when the associated objects need to be computed lazily or
     * are dynamically generated.
     *
     * @param leftSupplier  the supplier of the object associated with the left direction.
     * @param rightSupplier the supplier of the object associated with the right direction.
     */
    public AxisAccessor(Supplier<T> leftSupplier, Supplier<T> rightSupplier) {
        this.leftSupplier = leftSupplier;
        this.rightSupplier = rightSupplier;
    }

    /**
     * Constructs an AxisAccessor with a concrete object for each direction.
     *
     * @param left  the object associated with the left direction.
     * @param right the object associated with the right direction.
     */
    public AxisAccessor(T left, T right) {
        this(() -> left, () -> right);
    }

    /**
     * Constructs an AxisAccessor by consuming an OrientationAccessible object.
     *
     * <p>
     * This constructor allows the creation of an AxisAccessor instance based on another object
     * implementing the OrientationAccessible interface, enabling the user to access the associated objects
     * in different orientations directly.
     *
     * @param accessible an object implementing the OrientationAccessible interface.
     */
    public AxisAccessor(OrientationAccessible<AxisOrientation, T> accessible) {
        this(accessible.at(LEFT), accessible.at(RIGHT));
    }

    public T left() {
        return leftSupplier.get();
    }

    public T right() {
        return rightSupplier.get();
    }

    /**
     * Returns the associated object in the specified axial orientation.
     *
     * @param orientation the axial orientation.
     * @return the associated object in the specified orientation.
     */
    @Override
    public T at(AxisOrientation orientation) {
        return switch (orientation) {
            case LEFT -> left();
            case RIGHT -> right();
        };
    }

    /**
     * Transforms the associated objects based on a provided mapping function and returns a new AxisAccessor instance
     * with transformed associations.
     *
     * <p>
     * This method applies the given function to each associated object and returns a new AxisAccessor with the
     * transformed associations. This method is useful for converting the types of the associated objects or
     * performing some computations on them before creating a new AxisAccessor instance.
     *
     * @param mapper the function to apply to the associated objects.
     * @return a new AxisAccessor instance with the transformed associations.
     */
    @Override
    public <R> OrientationAccessible<AxisOrientation, R> map(Function<T, R> mapper) {
        return new AxisAccessor<>(OrientationAccessible.super.map(mapper));
    }
}
