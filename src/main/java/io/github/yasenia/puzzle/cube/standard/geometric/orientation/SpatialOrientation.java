package io.github.yasenia.puzzle.cube.standard.geometric.orientation;

import io.github.yasenia.puzzle.cube.standard.geometric.accessor.OrientationAccessible;
import io.github.yasenia.puzzle.cube.standard.geometric.accessor.PlanarAccessor;
import io.github.yasenia.puzzle.cube.standard.geometric.accessor.SpatialAccessor;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

/**
 * Enumeration representing the six possible orientations in a three-dimensional space.
 * <p>
 * {@code SpatialOrientation} provides a set of predefined constants to represent the orientations in a three-dimensional context, namely:
 * <ul>
 *     <li>{@link #UP}</li>
 *     <li>{@link #DOWN}</li>
 *     <li>{@link #LEFT}</li>
 *     <li>{@link #RIGHT}</li>
 *     <li>{@link #FRONT}</li>
 *     <li>{@link #BACK}</li>
 * </ul>
 * </p>
 * Additionally, this enumeration implements the {@link OrientationAccessible} interface, allowing for accessing associated {@code SpatialOrientation} based on a given {@link PlanarOrientation}, essentially performing a back-projection in three-dimensional space.
 * <p>
 * Each {@code SpatialOrientation} constant provides methods to:
 * <ul>
 *     <li>Back-project a {@link PlanarOrientation} to derive a new {@code SpatialOrientation} using {@link #backProject(PlanarOrientation)} or {@link #at(PlanarOrientation)} method.</li>
 *     <li>Get the opposite of the current orientation using {@link #opposite()} method.</li>
 * </ul>
 * </p>
 * <p>
 * <strong>Example Usage:</strong>
 * <pre>{@code
 * SpatialOrientation frontOrientation = SpatialOrientation.FRONT;
 * SpatialOrientation aboveFront = frontOrientation.at(PlanarOrientation.TOP); // Returns SpatialOrientation.UP
 * SpatialOrientation oppositeToFront = frontOrientation.opposite(); // Returns SpatialOrientation.BACK
 * }</pre>
 * </p>
 * <p>
 * This enumeration can be useful in various scenarios such as defining directions in 3D games, performing geometrical transformations, and solving 3D puzzles like Rubik's Cube.
 * </p>
 *
 * @see OrientationAccessible
 * @see PlanarOrientation
 */
public enum SpatialOrientation implements Orientation, OrientationAccessible<PlanarOrientation, SpatialOrientation> {

    UP, DOWN, LEFT, RIGHT, FRONT, BACK;

    /**
     * A pre-configured {@code SpatialAccessor} instance that represents the relationships
     * between different {@code SpatialOrientation}s and their corresponding projected {@code PlanarAccessor}s.
     *
     * <p>
     * This instance serves as a centralized access point for retrieving the associated
     * {@code PlanarAccessor} for each {@code SpatialOrientation}, allowing for efficient
     * projections and back-projections in spatial orientation logic.
     */
    private static final SpatialAccessor<PlanarAccessor<SpatialOrientation>> PROJECTIONS = new SpatialAccessor<>(
        new PlanarAccessor<>(LEFT, RIGHT, BACK, FRONT),
        new PlanarAccessor<>(LEFT, RIGHT, FRONT, BACK),
        new PlanarAccessor<>(BACK, FRONT, UP, DOWN),
        new PlanarAccessor<>(FRONT, BACK, UP, DOWN),
        new PlanarAccessor<>(LEFT, RIGHT, UP, DOWN),
        new PlanarAccessor<>(RIGHT, LEFT, UP, DOWN)
    );

    /**
     * This method is a convenience method that directly calls and returns the result of {@link #backProject(PlanarOrientation)}.
     * It performs a back-projection, mapping a {@link PlanarOrientation} to a {@code SpatialOrientation} from the viewpoint
     * of the current orientation. For detailed behavior and examples, refer to the documentation of {@link #backProject(PlanarOrientation)}.
     *
     * @param orientation the {@link PlanarOrientation} representing the direction in the projected plane from the viewpoint of the current SpatialOrientation
     * @return the back-projected {@code SpatialOrientation} from the viewpoint of the current orientation and the given planar direction
     * @see #backProject(PlanarOrientation)
     */
    @Override
    public SpatialOrientation at(PlanarOrientation orientation) {
        return this.backProject(orientation);
    }

    /**
     * Back-projects a {@code PlanarOrientation} to derive a new {@code SpatialOrientation} based on the current orientation
     * as the viewpoint and the given {@code PlanarOrientation} as the direction from this viewpoint in the projected plane.
     *
     * <p>
     * This method is intended to provide a way to retrieve the spatial orientation that corresponds
     * to a specific planar direction from the perspective of the current spatial orientation.
     *
     * @param source the {@code PlanarOrientation} representing the direction in the projected plane from the viewpoint of the current {@code SpatialOrientation}
     * @return the back-projected {@code SpatialOrientation} from the viewpoint of the current orientation and the given planar direction
     */
    public SpatialOrientation backProject(PlanarOrientation source) {
        return this.project().at(source);
    }

    /**
     * Projects the current {@code SpatialOrientation} to its associated {@code PlanarAccessor} that represents
     * the relationships between the planar orientations and their corresponding spatial orientations.
     *
     * <p>
     * This method is intended to facilitate the transition from a three-dimensional context
     * to a two-dimensional context by providing access to the associated planar orientations.
     *
     * @return the {@code PlanarAccessor<SpatialOrientation>} associated with the current {@code SpatialOrientation}
     */
    public PlanarAccessor<SpatialOrientation> project() {
        return PROJECTIONS.at(this);
    }

    /**
     * Returns the opposite of the current SpatialOrientation.
     *
     * <p>
     * This method is used to get the opposite orientation of the current orientation.
     * It is useful in scenarios where there is a need to get the reverse direction or orientation.
     * For example, if there is a need to get the opposite side of a cube or to find the reverse
     * direction of a movement, this method can be utilized.
     *
     * <p>
     * The opposite orientations are well-defined as follows:
     * <ul>
     *     <li>UP's opposite is DOWN</li>
     *     <li>DOWN's opposite is UP</li>
     *     <li>LEFT's opposite is RIGHT</li>
     *     <li>RIGHT's opposite is LEFT</li>
     *     <li>FRONT's opposite is BACK</li>
     *     <li>BACK's opposite is FRONT</li>
     * </ul>
     * This provides a clear and concise way to obtain the inverse of any given SpatialOrientation.
     *
     * @return the opposite SpatialOrientation, representing the inverse direction or orientation of the current SpatialOrientation.
     */
    public SpatialOrientation opposite() {
        return switch (this) {
            case UP -> DOWN;
            case DOWN -> UP;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
            case FRONT -> BACK;
            case BACK -> FRONT;
        };
    }

    /**
     * Determines the {@link PlanarOrientation} of the invoking {@code SpatialOrientation} object relative to the adjacent face defined by the provided {@code PlanarOrientation} parameter.
     * <p>
     * This method calculates the planar orientation of the current spatial orientation in relation to the provided adjacent planar orientation. It essentially determines which side (or planar orientation) of the current spatial orientation corresponds to the adjacent planar orientation.
     * </p>
     * <strong>Example:</strong> If the current object is {@code SpatialOrientation.LEFT}, and the parameter is {@code PlanarOrientation.RIGHT}, this method will return {@code PlanarOrientation.LEFT}, representing that the {@code SpatialOrientation.LEFT} is to the left of its adjacent face corresponding to the right planar orientation.
     *
     * @param planarOrientation the {@link PlanarOrientation} representing the direction of the adjacent face in relation to the current {@code SpatialOrientation}.
     * @return the {@code PlanarOrientation} representing the relative direction of the current {@code SpatialOrientation} in relation to the adjacent face defined by the provided {@code PlanarOrientation}.
     * @throws NoSuchElementException if no corresponding {@code PlanarOrientation} is found, which should not occur under normal circumstances.
     */
    public PlanarOrientation determineAdjacentPlanarOrientation(PlanarOrientation planarOrientation) {
        var adjacentOrientation = this.backProject(planarOrientation);
        return Stream.of(PlanarOrientation.values())
            .filter(op -> adjacentOrientation.backProject(op).equals(this))
            .findFirst().orElseThrow(NoSuchElementException::new);
    }
}
