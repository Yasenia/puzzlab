package io.github.yasenia.puzzle.cube.standard.geometric.orientation;

import io.github.yasenia.puzzle.cube.standard.geometric.accessor.AxisAccessor;
import io.github.yasenia.puzzle.cube.standard.geometric.accessor.OrientationAccessible;
import io.github.yasenia.puzzle.cube.standard.geometric.accessor.PlanarAccessor;
import io.github.yasenia.puzzle.cube.standard.geometric.rotation.RotateDirection;

/**
 * Enumeration representing the four possible orientations in a two-dimensional plane.
 * <p>
 * {@code PlanarOrientation} provides a set of predefined constants to represent the orientations in a two-dimensional context, namely:
 * <ul>
 *     <li>{@link #LEFT}</li>
 *     <li>{@link #RIGHT}</li>
 *     <li>{@link #TOP}</li>
 *     <li>{@link #BOTTOM}</li>
 * </ul>
 * </p>
 * Additionally, this enumeration implements the {@link OrientationAccessible} interface, allowing for accessing associated {@code PlanarOrientation} based on a given {@link AxisOrientation}, essentially performing a back-projection in two-dimensional space.
 * <p>
 * Each {@code PlanarOrientation} constant provides methods to:
 * <ul>
 *     <li>Back-project an {@link AxisOrientation} to derive a new {@code PlanarOrientation} using {@link #backProject(AxisOrientation)} or {@link #at(AxisOrientation)} method.</li>
 *     <li>Get the opposite of the current orientation using {@link #opposite()} method.</li>
 * </ul>
 * </p>
 * <p>
 * <strong>Example Usage:</strong>
 * <pre>{@code
 * PlanarOrientation leftOrientation = PlanarOrientation.LEFT;
 * PlanarOrientation oppositeToLeft = leftOrientation.opposite(); // Returns PlanarOrientation.RIGHT
 * }</pre>
 * </p>
 * <p>
 * This enumeration can be useful in various scenarios such as defining directions in 2D games, performing geometrical transformations, and solving 2D puzzles.
 * </p>
 *
 * @see OrientationAccessible
 * @see AxisOrientation
 */
public enum PlanarOrientation implements Orientation, OrientationAccessible<AxisOrientation, PlanarOrientation> {
    LEFT, RIGHT, TOP, BOTTOM;

    /**
     * A pre-configured {@code PlanarAccessor} instance that represents the relationships
     * between different {@code PlanarOrientation}s and their corresponding projected {@code AxisAccessor}s.
     *
     * <p>
     * This instance serves as a centralized access point for retrieving the associated
     * {@code AxisAccessor} for each {@code PlanarOrientation}, allowing for efficient
     * projections and back-projections in planar orientation logic.
     */
    @SuppressWarnings("SuspiciousNameCombination")
    private static final PlanarAccessor<AxisAccessor<PlanarOrientation>> PROJECTIONS = new PlanarAccessor<>(
        new AxisAccessor<>(TOP, BOTTOM),
        new AxisAccessor<>(BOTTOM, TOP),
        new AxisAccessor<>(RIGHT, LEFT),
        new AxisAccessor<>(LEFT, RIGHT)
    );

    @SuppressWarnings("SuspiciousNameCombination")
    private static final PlanarAccessor<PlanarOrientation> NEXT_ORIENTATIONS = new PlanarAccessor<>(TOP, BOTTOM, RIGHT, LEFT);

    @SuppressWarnings("SuspiciousNameCombination")
    private static final PlanarAccessor<PlanarOrientation> PREVIOUS_ORIENTATIONS = new PlanarAccessor<>(BOTTOM, TOP, LEFT, RIGHT);

    /**
     * This method is a convenience method that directly calls and returns the result of {@link #backProject(AxisOrientation)}.
     * It performs a back-projection, mapping an {@link AxisOrientation} to a {@code PlanarOrientation} from the viewpoint
     * of the current orientation. For detailed behavior and examples, refer to the documentation of {@link #backProject(AxisOrientation)}.
     *
     * @param orientation the {@link AxisOrientation} representing the direction in the projected plane from the viewpoint of the current PlanarOrientation
     * @return the back-projected {@code PlanarOrientation} from the viewpoint of the current orientation and the given axis direction
     * @see #backProject(AxisOrientation)
     */
    @Override
    public PlanarOrientation at(AxisOrientation orientation) {
        return this.backProject(orientation);
    }

    /**
     * Back-projects and returns a new PlanarOrientation based on the current orientation as the viewpoint
     * and the given AxisOrientation as the direction from this viewpoint in the projected plane.
     *
     * <p>
     * This method effectively maps an {@link AxisOrientation} to a {@code PlanarOrientation} based on the viewpoint of the current orientation.
     *
     * @param source the AxisOrientation representing the direction in the projected plane from the viewpoint of the current PlanarOrientation
     * @return the back-projected PlanarOrientation from the viewpoint of the current orientation and the given axis direction
     * @see #at(AxisOrientation)
     */
    public PlanarOrientation backProject(AxisOrientation source) {
        return this.project().at(source);
    }

    /**
     * Returns an AxisAccessor representing the projection of the current PlanarOrientation in the two-dimensional plane.
     *
     * <p>
     * The returned accessor can be used to access associated PlanarOrientations based on AxisOrientation in the context of the current PlanarOrientation.
     *
     * @return the AxisAccessor representing the projected orientations from the viewpoint of the current PlanarOrientation
     * @see #backProject(AxisOrientation)
     */
    public AxisAccessor<PlanarOrientation> project() {
        return PROJECTIONS.at(this);
    }

    /**
     * Rotates the current {@code PlanarOrientation} instance in the specified direction.
     *
     * <p>
     * This method utilizes pre-configured {@code PlanarAccessor} instances representing the relationships between different orientations to efficiently perform the rotation logic.
     *
     * <p>
     * Example:
     * <pre>{@code
     * PlanarOrientation currentOrientation = PlanarOrientation.TOP;
     * PlanarOrientation nextOrientation = currentOrientation.rotateForward(RotateDirection.CLOCKWISE); // Returns PlanarOrientation.RIGHT
     * }</pre>
     *
     * @param direction the direction in which to rotate the {@code PlanarOrientation}
     * @return a new {@code PlanarOrientation} instance representing the orientation after the rotation.
     */
    public PlanarOrientation rotateForward(RotateDirection direction) {
        return switch (direction) {
            case CLOCKWISE -> NEXT_ORIENTATIONS.at(this);
            case COUNTER_CLOCKWISE -> PREVIOUS_ORIENTATIONS.at(this);
            case DOUBLE -> opposite();
        };
    }

    /**
     * Rotates the current {@code PlanarOrientation} instance in the opposite direction of the specified one.
     *
     * <p>
     * This method acts as a convenience method, inverting the specified rotation direction and then performing the rotation.
     *
     * <p>
     * Example:
     * <pre>{@code
     * PlanarOrientation currentOrientation = PlanarOrientation.TOP;
     * PlanarOrientation previousOrientation = currentOrientation.rotateBackward(RotateDirection.CLOCKWISE); // Returns PlanarOrientation.LEFT
     * }</pre>
     *
     * @param direction the direction in which to rotate the {@code PlanarOrientation} in the opposite manner.
     * @return a new {@code PlanarOrientation} instance representing the orientation after the backward rotation.
     */
    public PlanarOrientation rotateBackward(RotateDirection direction) {
        return rotateForward(direction.inverse());
    }

    /**
     * Returns the opposite of the current PlanarOrientation.
     * <p>
     * The opposite orientations are well-defined as follows:
     * <ul>
     *     <li>LEFT's opposite is RIGHT</li>
     *     <li>RIGHT's opposite is LEFT</li>
     *     <li>TOP's opposite is BOTTOM</li>
     *     <li>BOTTOM's opposite is TOP</li>
     * </ul>
     * This provides a clear and concise way to obtain the inverse of any given PlanarOrientation.
     * </p>
     *
     * @return the opposite PlanarOrientation, representing the inverse direction or orientation of the current PlanarOrientation.
     */
    public PlanarOrientation opposite() {
        return switch (this) {
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
            case TOP -> BOTTOM;
            case BOTTOM -> TOP;
        };
    }
}
