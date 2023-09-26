package io.github.yasenia.puzzle.cube.standard.rubiks;

import io.github.yasenia.puzzle.cube.standard.CubeRotation;
import io.github.yasenia.puzzle.cube.standard.geometric.orientation.SpatialOrientation;
import io.github.yasenia.puzzle.cube.standard.geometric.rotation.RotateDirection;

import static io.github.yasenia.puzzle.cube.standard.geometric.rotation.RotateDirection.CLOCKWISE;
import static io.github.yasenia.puzzle.cube.standard.geometric.rotation.RotateDirection.COUNTER_CLOCKWISE;
import static io.github.yasenia.puzzle.cube.standard.geometric.rotation.RotateDirection.DOUBLE;

/**
 * Enum representing the different possible rotations on a Rubik's Cube.
 * Each enum constant represents a unique rotation which includes
 * the orientation of the rotation and the direction in which
 * the rotation is performed, i.e., clockwise, counter-clockwise, or double.
 * <p>
 * The enum constants are grouped by the face they rotate:
 * <ul>
 *     <li>Up Face Rotations: {@code U}, {@code U_2}, {@code U_P}</li>
 *     <li>Down Face Rotations: {@code D}, {@code D_2}, {@code D_P}</li>
 *     <li>Front Face Rotations: {@code F}, {@code F_2}, {@code F_P}</li>
 *     <li>Back Face Rotations: {@code B}, {@code B_2}, {@code B_P}</li>
 *     <li>Left Face Rotations: {@code L}, {@code L_2}, {@code L_P}</li>
 *     <li>Right Face Rotations: {@code R}, {@code R_2}, {@code R_P}</li>
 * </ul>
 * </p>
 * <p>
 * Each rotation can be inverted using the {@link #inverse()} method.
 * The inverse of a clockwise rotation is a counter-clockwise rotation and vice versa.
 * The inverse of a double rotation is itself.
 * </p>
 *
 * @see RubiksCube
 * @see SpatialOrientation
 * @see RotateDirection
 */
public enum RubiksRotation implements CubeRotation<RubiksCube, RubiksRotation> {

    // Up Face Rotations
    U(SpatialOrientation.UP, CLOCKWISE),
    U_2(SpatialOrientation.UP, DOUBLE),
    U_P(SpatialOrientation.UP, COUNTER_CLOCKWISE),

    // Down Face Rotations
    D(SpatialOrientation.DOWN, CLOCKWISE),
    D_2(SpatialOrientation.DOWN, DOUBLE),
    D_P(SpatialOrientation.DOWN, COUNTER_CLOCKWISE),

    // Front Face Rotations
    F(SpatialOrientation.FRONT, CLOCKWISE),
    F_2(SpatialOrientation.FRONT, DOUBLE),
    F_P(SpatialOrientation.FRONT, COUNTER_CLOCKWISE),

    // Back Face Rotations
    B(SpatialOrientation.BACK, CLOCKWISE),
    B_2(SpatialOrientation.BACK, DOUBLE),
    B_P(SpatialOrientation.BACK, COUNTER_CLOCKWISE),

    // Left Face Rotations
    L(SpatialOrientation.LEFT, CLOCKWISE),
    L_2(SpatialOrientation.LEFT, DOUBLE),
    L_P(SpatialOrientation.LEFT, COUNTER_CLOCKWISE),

    // Right Face Rotations
    R(SpatialOrientation.RIGHT, CLOCKWISE),
    R_2(SpatialOrientation.RIGHT, DOUBLE),
    R_P(SpatialOrientation.RIGHT, COUNTER_CLOCKWISE);

    private final SpatialOrientation orientation;
    private final RotateDirection direction;

    RubiksRotation(SpatialOrientation orientation, RotateDirection direction) {
        this.orientation = orientation;
        this.direction = direction;
    }

    public SpatialOrientation orientation() {
        return this.orientation;
    }

    public RotateDirection direction() {
        return this.direction;
    }

    /**
     * Returns the inverse of the current rotation. The inverse of a clockwise rotation is a
     * counter-clockwise rotation and vice versa. The inverse of a double rotation is itself.
     *
     * @return the inverse of the current rotation
     */
    @Override
    public RubiksRotation inverse() {
        return switch (this) {
            case U -> U_P;
            case U_P -> U;
            case D -> D_P;
            case D_P -> D;
            case F -> F_P;
            case F_P -> F;
            case B -> B_P;
            case B_P -> B;
            case L -> L_P;
            case L_P -> L;
            case R -> R_P;
            case R_P -> R;
            default -> this; // for U_2, D_2, F_2, B_2, L_2, R_2
        };
    }
}
