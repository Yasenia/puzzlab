package io.github.yasenia.puzzle.cube.standard.geometric.orientation;

/**
 * Enumeration representing the two possible orientations in a one-dimensional axis.
 * <p>
 * {@code AxisOrientation} provides a set of predefined constants to represent the orientations in a one-dimensional context, namely:
 * <ul>
 *     <li>{@link #LEFT}</li>
 *     <li>{@link #RIGHT}</li>
 * </ul>
 * </p>
 * This enumeration can be used as a simplified representation of orientation when there are only two possible directions, such as left and right or up and down, depending on the context.
 * <p>
 * Each {@code AxisOrientation} constant provides a method to:
 * <ul>
 *     <li>Get the opposite of the current orientation using {@link #opposite()} method.</li>
 * </ul>
 * </p>
 * <p>
 * <strong>Example Usage:</strong>
 * <pre>{@code
 * AxisOrientation leftOrientation = AxisOrientation.LEFT;
 * AxisOrientation oppositeToLeft = leftOrientation.opposite(); // Returns AxisOrientation.RIGHT
 * }</pre>
 * </p>
 * <p>
 * This enumeration can be useful in various scenarios such as defining binary states, making binary decisions, and representing one-dimensional movements or transformations.
 * </p>
 */
public enum AxisOrientation implements Orientation {
    LEFT, RIGHT;

    /**
     * Returns the opposite of the current AxisOrientation.
     *
     * <p>
     * This method is used to get the opposite orientation of the current orientation.
     * It is useful in scenarios where there is a need to get the reverse direction or orientation.
     * For example, if there is a need to get the opposite side of a line or to find the reverse
     * direction of a movement, this method can be utilized.
     *
     * <p>
     * The opposite orientations are well-defined as follows:
     * <ul>
     *     <li>LEFT's opposite is RIGHT</li>
     *     <li>RIGHT's opposite is LEFT</li>
     * </ul>
     * This provides a clear and concise way to obtain the inverse of any given AxisOrientation.
     *
     * @return the opposite AxisOrientation, representing the inverse direction or orientation of the current AxisOrientation.
     */
    public AxisOrientation opposite() {
        return this == LEFT ? RIGHT : LEFT;
    }
}
