package io.github.yasenia.puzzle;

/**
 * Represents a rotation that can be applied to a twisty puzzle, focusing solely on defining the basic properties and behavior of a rotation.
 *
 * @param <P> the type of the twisty puzzle
 * @param <R> the type of the rotation
 */
public interface Rotation<P extends TwistyPuzzle<P, R>, R extends Rotation<P, R>> {

    /**
     * Returns the inverse of the rotation, which when applied, will revert the puzzle back to its previous baseState.
     *
     * @return the inverse of the rotation
     */
    R inverse();
}
