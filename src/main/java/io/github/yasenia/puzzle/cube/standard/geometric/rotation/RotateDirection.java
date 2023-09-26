package io.github.yasenia.puzzle.cube.standard.geometric.rotation;

/**
 * Enum representing the direction of the rotation. It can be either clockwise,
 * counter-clockwise, or double (180 degrees).
 */
public enum RotateDirection {
    CLOCKWISE,
    COUNTER_CLOCKWISE,
    DOUBLE;

    /**
     * Returns the inverse direction of the current direction.
     * The inverse of CLOCKWISE is COUNTER_CLOCKWISE and vice versa.
     * The inverse of DOUBLE is DOUBLE itself.
     *
     * @return the inverse direction
     */
    public RotateDirection inverse() {
        return switch (this) {
            case CLOCKWISE -> COUNTER_CLOCKWISE;
            case COUNTER_CLOCKWISE -> CLOCKWISE;
            case DOUBLE -> DOUBLE;
        };
    }
}
