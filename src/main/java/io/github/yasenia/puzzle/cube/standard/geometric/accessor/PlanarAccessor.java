package io.github.yasenia.puzzle.cube.standard.geometric.accessor;

import io.github.yasenia.puzzle.cube.standard.geometric.orientation.PlanarOrientation;
import io.github.yasenia.puzzle.cube.standard.geometric.rotation.RotateDirection;

import java.util.function.Function;
import java.util.function.Supplier;

import static io.github.yasenia.puzzle.cube.standard.geometric.orientation.PlanarOrientation.BOTTOM;
import static io.github.yasenia.puzzle.cube.standard.geometric.orientation.PlanarOrientation.LEFT;
import static io.github.yasenia.puzzle.cube.standard.geometric.orientation.PlanarOrientation.RIGHT;
import static io.github.yasenia.puzzle.cube.standard.geometric.orientation.PlanarOrientation.TOP;

/**
 * A concrete implementation of {@link OrientationAccessible} that provides access to associated objects
 * in four planar directions: left, right, top, and bottom.
 *
 * @param <T> the type of associated objects.
 */
public class PlanarAccessor<T> implements OrientationAccessible<PlanarOrientation, T> {

    /**
     * A constant instance of PlanarAccessor representing the four basic planar orientations.
     */
    public static final PlanarAccessor<PlanarOrientation> PLANAR_ORIENTATIONS = new PlanarAccessor<>(LEFT, RIGHT, TOP, BOTTOM);

    private final Supplier<T> leftSupplier;
    private final Supplier<T> rightSupplier;
    private final Supplier<T> topSupplier;
    private final Supplier<T> bottomSupplier;

    /**
     * Constructs a PlanarAccessor with suppliers for each direction.
     *
     * <p>
     * This constructor is useful when the associated objects need to be computed lazily or
     * are dynamically generated.
     *
     * @param leftSupplier   the supplier of the object associated with the left direction.
     * @param rightSupplier  the supplier of the object associated with the right direction.
     * @param topSupplier    the supplier of the object associated with the top direction.
     * @param bottomSupplier the supplier of the object associated with the bottom direction.
     */
    public PlanarAccessor(Supplier<T> leftSupplier, Supplier<T> rightSupplier, Supplier<T> topSupplier, Supplier<T> bottomSupplier) {
        this.leftSupplier = leftSupplier;
        this.rightSupplier = rightSupplier;
        this.topSupplier = topSupplier;
        this.bottomSupplier = bottomSupplier;
    }

    /**
     * Constructs a PlanarAccessor with a concrete object for each direction.
     *
     * @param left   the object associated with the left direction.
     * @param right  the object associated with the right direction.
     * @param top    the object associated with the top direction.
     * @param bottom the object associated with the bottom direction.
     */
    public PlanarAccessor(T left, T right, T top, T bottom) {
        this(() -> left, () -> right, () -> top, () -> bottom);
    }

    /**
     * Constructs a PlanarAccessor by consuming an OrientationAccessible object.
     *
     * <p>
     * This constructor allows the creation of a PlanarAccessor instance based on another object
     * implementing the OrientationAccessible interface, enabling the user to access the associated objects
     * in different orientations directly.
     *
     * @param accessible an object implementing the OrientationAccessible interface.
     */
    public PlanarAccessor(OrientationAccessible<PlanarOrientation, T> accessible) {
        this(() -> accessible.at(LEFT), () -> accessible.at(RIGHT), () -> accessible.at(TOP), () -> accessible.at(BOTTOM));
    }

    public T left() {
        return leftSupplier.get();
    }

    public T right() {
        return rightSupplier.get();
    }

    public T top() {
        return topSupplier.get();
    }

    public T bottom() {
        return bottomSupplier.get();
    }

    /**
     * Returns the associated object in the specified planar orientation.
     *
     * @param orientation the planar orientation
     * @return the associated object in the specified orientation
     */
    @Override
    public T at(PlanarOrientation orientation) {
        return switch (orientation) {
            case LEFT -> left();
            case RIGHT -> right();
            case TOP -> top();
            case BOTTOM -> bottom();
        };
    }

    /**
     * Transforms the associated objects based on a provided mapping function and returns a new PlanarAccessor instance
     * with transformed associations.
     *
     * <p>
     * This method applies the given function to each associated object and returns a new PlanarAccessor with the
     * transformed associations. This method is useful for converting the types of the associated objects or
     * performing some computations on them before creating a new PlanarAccessor instance.
     *
     * @param mapper the function to apply to the associated objects.
     * @return a new PlanarAccessor instance with the transformed associations.
     */
    @Override
    public <R> PlanarAccessor<R> map(Function<T, R> mapper) {
        return new PlanarAccessor<>(OrientationAccessible.super.map(mapper));
    }

    /**
     * Rotates the associated objects in the specified direction and returns a new PlanarAccessor representing the rotated baseState.
     *
     * <p>
     * The associated objects are rearranged based on the rotation direction specified.
     *
     * @param direction the direction to rotate the associated objects.
     * @return a new PlanarAccessor representing the baseState after rotation.
     */
    @SuppressWarnings("SuspiciousNameCombination")
    public PlanarAccessor<T> rotate(RotateDirection direction) {
        return switch (direction) {
            case CLOCKWISE -> new PlanarAccessor<>(this::bottom, this::top, this::left, this::right);
            case COUNTER_CLOCKWISE -> new PlanarAccessor<>(this::top, this::bottom, this::right, this::left);
            case DOUBLE -> new PlanarAccessor<>(this::right, this::left, this::bottom, this::top);
        };
    }
}
