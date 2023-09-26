package io.github.yasenia.puzzle.cube.standard.rubiks;

import io.github.yasenia.puzzle.cube.standard.Cube;
import io.github.yasenia.puzzle.cube.standard.geometric.orientation.SpatialOrientation;
import io.github.yasenia.puzzle.cube.standard.rubiks.impl.RubiksCubeImpl;

import java.util.List;
import java.util.stream.Stream;

/**
 * Represents a Rubik's Cube, extending the OddCube interface. It is parameterized by the RubiksRotation
 * and RubiksFace, specifying the rotation operations and the face structure of the cube.
 */
public interface RubiksCube extends Cube.Odd<RubiksCube, RubiksRotation, RubiksFacelet, RubiksFace> {

    RubiksCube SOLVED_CUBE = new RubiksCubeImpl();

    /**
     * Rotates the Rubik's Cube using a list of RubiksRotation operations.
     * This method recursively applies each rotation in the list to the cube.
     * If the list of rotations is empty, it returns the cube itself.
     *
     * @param rotations the list of RubiksRotation operations to be applied
     * @return the Rubik's Cube after applying all the rotations
     */
    @Override
    default RubiksCube rotate(List<RubiksRotation> rotations) {
        if (rotations.isEmpty()) return this;
        return rotate(rotations.get(0)).rotate(rotations.subList(1, rotations.size()));
    }

    /**
     * Checks if the Rubik's Cube is solved.
     * A Rubik's Cube is considered solved if all its faces are solved.
     * A face is considered solved if all its facelets have the same color as the center facelet.
     *
     * @return true if the Rubik's Cube is solved, false otherwise
     */
    @Override
    default boolean isSolved() {
        return Stream.of(SpatialOrientation.values()).allMatch(orientation -> faces().at(orientation).isSolved());
    }
}
