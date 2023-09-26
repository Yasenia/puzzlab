package io.github.yasenia.puzzle.cube.standard.geometric.accessor;

import io.github.yasenia.puzzle.cube.standard.geometric.orientation.SpatialOrientation;
import io.github.yasenia.puzzle.cube.standard.geometric.rotation.RotateDirection;

import java.util.function.Function;
import java.util.function.Supplier;

import static io.github.yasenia.puzzle.cube.standard.geometric.orientation.SpatialOrientation.BACK;
import static io.github.yasenia.puzzle.cube.standard.geometric.orientation.SpatialOrientation.DOWN;
import static io.github.yasenia.puzzle.cube.standard.geometric.orientation.SpatialOrientation.FRONT;
import static io.github.yasenia.puzzle.cube.standard.geometric.orientation.SpatialOrientation.LEFT;
import static io.github.yasenia.puzzle.cube.standard.geometric.orientation.SpatialOrientation.RIGHT;
import static io.github.yasenia.puzzle.cube.standard.geometric.orientation.SpatialOrientation.UP;

/**
 * A concrete implementation of {@link OrientationAccessible} that provides access to associated objects
 * in six spatial directions: up, down, left, right, front, and back.
 *
 * @param <T> the type of associated objects.
 */
public class SpatialAccessor<T> implements OrientationAccessible<SpatialOrientation, T> {

    /**
     * A constant instance of SpatialAccessor representing the six basic spatial orientations.
     */
    public static final SpatialAccessor<SpatialOrientation> SPATIAL_ORIENTATIONS = new SpatialAccessor<>(UP, DOWN, LEFT, RIGHT, FRONT, BACK);

    private final Supplier<T> upSupplier;
    private final Supplier<T> downSupplier;
    private final Supplier<T> leftSupplier;
    private final Supplier<T> rightSupplier;
    private final Supplier<T> frontSupplier;
    private final Supplier<T> backSupplier;

    /**
     * Constructs a SpatialAccessor with suppliers for each direction.
     *
     * <p>
     * This constructor is useful when the associated objects need to be computed lazily or
     * are dynamically generated.
     *
     * @param upSupplier    the supplier of the object associated with the up direction.
     * @param downSupplier  the supplier of the object associated with the down direction.
     * @param leftSupplier  the supplier of the object associated with the left direction.
     * @param rightSupplier the supplier of the object associated with the right direction.
     * @param frontSupplier the supplier of the object associated with the front direction.
     * @param backSupplier  the supplier of the object associated with the back direction.
     */
    public SpatialAccessor(Supplier<T> upSupplier, Supplier<T> downSupplier, Supplier<T> leftSupplier, Supplier<T> rightSupplier, Supplier<T> frontSupplier, Supplier<T> backSupplier) {
        this.upSupplier = upSupplier;
        this.downSupplier = downSupplier;
        this.leftSupplier = leftSupplier;
        this.rightSupplier = rightSupplier;
        this.frontSupplier = frontSupplier;
        this.backSupplier = backSupplier;
    }

    /**
     * Constructs a SpatialAccessor with a concrete object for each direction.
     *
     * @param up    the object associated with the up direction.
     * @param down  the object associated with the down direction.
     * @param left  the object associated with the left direction.
     * @param right the object associated with the right direction.
     * @param front the object associated with the front direction.
     * @param back  the object associated with the back direction.
     */
    public SpatialAccessor(T up, T down, T left, T right, T front, T back) {
        this(() -> up, () -> down, () -> left, () -> right, () -> front, () -> back);
    }

    /**
     * Constructs a SpatialAccessor by consuming an OrientationAccessible object.
     *
     * <p>
     * This constructor allows the creation of a SpatialAccessor instance based on another object
     * implementing the OrientationAccessible interface, enabling the user to access the associated objects
     * in different orientations directly.
     *
     * @param accessible an object implementing the OrientationAccessible interface.
     */
    public SpatialAccessor(OrientationAccessible<SpatialOrientation, T> accessible) {
        this(() -> accessible.at(UP), () -> accessible.at(DOWN), () -> accessible.at(LEFT), () -> accessible.at(RIGHT), () -> accessible.at(FRONT), () -> accessible.at(BACK));
    }

    public T up() {
        return upSupplier.get();
    }

    public T down() {
        return downSupplier.get();
    }

    public T left() {
        return leftSupplier.get();
    }

    public T right() {
        return rightSupplier.get();
    }

    public T front() {
        return frontSupplier.get();
    }

    public T back() {
        return backSupplier.get();
    }

    /**
     * Returns the associated object in the specified spatial orientation.
     *
     * @param orientation the spatial orientation.
     * @return the associated object in the specified orientation.
     */
    @Override
    public T at(SpatialOrientation orientation) {
        return switch (orientation) {
            case UP -> up();
            case DOWN -> down();
            case LEFT -> left();
            case RIGHT -> right();
            case FRONT -> front();
            case BACK -> back();
        };
    }

    /**
     * Transforms the associated objects based on a provided mapping function and returns a new SpatialAccessor instance
     * with transformed associations.
     *
     * <p>
     * This method applies the given function to each associated object and returns a new SpatialAccessor with the
     * transformed associations. This method is useful for converting the types of the associated objects or
     * performing some computations on them before creating a new SpatialAccessor instance.
     *
     * @param mapper the function to apply to the associated objects.
     * @return a new SpatialAccessor instance with the transformed associations.
     */
    @Override
    public <R> SpatialAccessor<R> map(Function<T, R> mapper) {
        return new SpatialAccessor<>(OrientationAccessible.super.map(mapper));
    }

    /**
     * Rotates the associated objects in the specified orientation and direction, and returns a new SpatialAccessor representing the rotated baseState.
     *
     * <p>
     * The associated objects are rearranged based on the rotation orientation and direction specified.
     * This method provides a mechanism to change the spatial arrangement of the associated objects.
     *
     * @param orientation the orientation around which the rotation is to be performed.
     * @param direction   the direction to rotate the associated objects.
     * @return a new SpatialAccessor representing the baseState after rotation.
     */
    public SpatialAccessor<T> rotate(SpatialOrientation orientation, RotateDirection direction) {
        var around = PlanarAccessor.PLANAR_ORIENTATIONS.rotate(direction).map(orientation::backProject).map(this::at);
        return switch (orientation) {
            case UP -> new SpatialAccessor<>(this::up, this::down, around::left, around::right, around::bottom, around::top);
            case DOWN -> new SpatialAccessor<>(this::up, this::down, around::left, around::right, around::top, around::bottom);
            case LEFT -> new SpatialAccessor<>(around::top, around::bottom, this::left, this::right, around::right, around::left);
            case RIGHT -> new SpatialAccessor<>(around::top, around::bottom, this::right, this::left, around::left, around::right);
            case FRONT -> new SpatialAccessor<>(around::top, around::bottom, around::left, around::right, this::front, this::back);
            case BACK -> new SpatialAccessor<>(around::top, around::bottom, around::right, around::left, this::back, this::front);
        };
    }
}
