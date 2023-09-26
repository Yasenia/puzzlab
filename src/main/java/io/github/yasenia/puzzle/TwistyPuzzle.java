package io.github.yasenia.puzzle;

import java.util.List;

/**
 * Represents a twisty puzzle, defining the fundamental behaviors and properties of a puzzle,
 * focusing solely on the puzzle's baseState and rotation, abstracting away the complexities of transformations or solution strategies.
 *
 * @param <P> the type of the twisty puzzle
 * @param <R> the type of the rotation
 */
public interface TwistyPuzzle<P extends TwistyPuzzle<P, R>, R extends Rotation<P, R>> {

    /**
     * Rotates the puzzle according to the specified rotation.
     *
     * @param rotation the rotation to be applied to the puzzle
     * @return the puzzle in its new baseState after the rotation
     */
    P rotate(R rotation);

    default TwistyPuzzle<P, R> rotate(List<R> rotations) {
        if (rotations.isEmpty()) return this;
        return rotate(rotations.get(0)).rotate(rotations.subList(1, rotations.size()));
    }

    boolean isSolved();
}