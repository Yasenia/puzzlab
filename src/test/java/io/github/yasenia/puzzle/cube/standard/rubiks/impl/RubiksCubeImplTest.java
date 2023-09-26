package io.github.yasenia.puzzle.cube.standard.rubiks.impl;

import io.github.yasenia.puzzle.cube.standard.geometric.accessor.SpatialAccessor;
import io.github.yasenia.puzzle.cube.standard.geometric.orientation.SpatialOrientation;
import io.github.yasenia.puzzle.cube.standard.rubiks.RubiksCube;
import io.github.yasenia.puzzle.cube.standard.rubiks.RubiksFace;
import io.github.yasenia.puzzle.cube.standard.rubiks.RubiksRotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static io.github.yasenia.puzzle.cube.standard.rubiks.RubiksFacelet.RUBIKS_FACELETS;
import static io.github.yasenia.puzzle.cube.standard.rubiks.RubiksRotation.B;
import static io.github.yasenia.puzzle.cube.standard.rubiks.RubiksRotation.B_2;
import static io.github.yasenia.puzzle.cube.standard.rubiks.RubiksRotation.B_P;
import static io.github.yasenia.puzzle.cube.standard.rubiks.RubiksRotation.D;
import static io.github.yasenia.puzzle.cube.standard.rubiks.RubiksRotation.D_2;
import static io.github.yasenia.puzzle.cube.standard.rubiks.RubiksRotation.D_P;
import static io.github.yasenia.puzzle.cube.standard.rubiks.RubiksRotation.F;
import static io.github.yasenia.puzzle.cube.standard.rubiks.RubiksRotation.F_2;
import static io.github.yasenia.puzzle.cube.standard.rubiks.RubiksRotation.F_P;
import static io.github.yasenia.puzzle.cube.standard.rubiks.RubiksRotation.L;
import static io.github.yasenia.puzzle.cube.standard.rubiks.RubiksRotation.L_2;
import static io.github.yasenia.puzzle.cube.standard.rubiks.RubiksRotation.L_P;
import static io.github.yasenia.puzzle.cube.standard.rubiks.RubiksRotation.R;
import static io.github.yasenia.puzzle.cube.standard.rubiks.RubiksRotation.R_2;
import static io.github.yasenia.puzzle.cube.standard.rubiks.RubiksRotation.R_P;
import static io.github.yasenia.puzzle.cube.standard.rubiks.RubiksRotation.U;
import static io.github.yasenia.puzzle.cube.standard.rubiks.RubiksRotation.U_2;
import static io.github.yasenia.puzzle.cube.standard.rubiks.RubiksRotation.U_P;
import static io.github.yasenia.puzzle.cube.standard.rubiks.RubiksRotation.values;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RubiksCubeImplTest {

    @Test
    void should_be_solved_when_new_cube_is_created() {
        // exercise
        var cube = new RubiksCubeImpl();
        // verify
        assertTrue(cube.isSolved());
    }

    @ParameterizedTest
    @MethodSource("singleRotationStream")
    void should_return_to_solved_state_after_single_rotation_and_its_inverse(RubiksRotation rotation) {
        // setup
        var cube = new RubiksCubeImpl();
        // exercise
        var rotatedCube = cube.rotate(rotation);
        var solvedCube = rotatedCube.rotate(rotation.inverse());
        // verify
        assertFalse(rotatedCube.isSolved());
        assertTrue(solvedCube.isSolved());
    }

    static Stream<Arguments> singleRotationStream() {
        return Stream.of(values()).map(Arguments::of);
    }

    @ParameterizedTest
    @MethodSource("multipleRotationsStream")
    void should_return_to_solved_state_after_multiple_rotations_and_their_inverses(List<RubiksRotation> rotations) {
        // setup
        var cube = new RubiksCubeImpl();
        var reversedRotations = rotations.stream().map(RubiksRotation::inverse).toList().reversed();
        // exercise
        var rotatedCube = cube.rotate(rotations);
        var solvedCube = rotatedCube.rotate(reversedRotations);
        // verify
        assertFalse(rotatedCube.isSolved());
        assertTrue(solvedCube.isSolved());
    }

    static Stream<Arguments> multipleRotationsStream() {
        return Stream.of(
            List.of(L_P, B_2, D_2, F, D_P, F_2, B_P, B, L_2, U_P),
            List.of(D, U_2, R_P, R_2, F_P, R),
            List.of(U, L, L_P, B_2, D_2, F),
            List.of(D_P, F_2, B_P, B, L_2, U_P, D),
            List.of(U_2, R_P, R_2, F_P, R, U, L, L_P, B_2),
            List.of(D_2, F, D_P, F_2, B_P, B, L_2),
            List.of(U_P, D, U_2, R_P, R_2),
            List.of(F_P, R, U, L, L_P, B_2, D_2, F, D_P),
            List.of(F_2, B_P, B, L_2, U_P, D, U_2, R_P),
            List.of(R_2, F_P, R, U, L, L_P, B_2, D_2, F, D_P),
            List.of(F_2, B_P, B, L_2, U_P, D),
            List.of(U_2, R_P, R_2, F_P, R),
            List.of(U, L, L_P, B_2, D_2, F, D_P, F_2),
            List.of(B_P, B, L_2, U_P, D, U_2, R_P, R_2, F_P, R),
            List.of(U, L, L_P, B_2, D_2, F, D_P, F_2),
            List.of(B_P, B, L_2, U_P, D, U_2, R_P, R_2, F_P),
            List.of(R, U, L, L_P, B_2, D_2, F, D_P, F_2),
            List.of(B_P, B, L_2, U_P, D, U_2, R_P),
            List.of(R_2, F_P, R, U, L, L_P, B_2, D_2, F, D_P),
            List.of(F_2, B_P, B, L_2, U_P, D, U_2)
        ).map(Arguments::of);
    }

    @ParameterizedTest
    @MethodSource("rotationsWithExpectedFaces")
    void should_match_expected_faces_after_rotations(List<RubiksRotation> rotations, int cyclesToRestore, SpatialAccessor<RubiksFace> expectedFaces) {
        // setup
        var cube = new RubiksCubeImpl();
        // exercise
        var rotatedCube = cube.rotate(rotations);
        // verify
        assertFalse(rotatedCube.isSolved());
        var faces = rotatedCube.faces();
        for (var spatialOrientation : SpatialOrientation.values()) {
            assertThat(faces.at(spatialOrientation), equalTo(expectedFaces.at(spatialOrientation)));
        }
        // exercise
        RubiksCube loopRotatedCube = cube;
        for (int i = 0; i < cyclesToRestore; i++) loopRotatedCube = loopRotatedCube.rotate(rotations);
        // verify
        assertTrue(loopRotatedCube.isSolved());
    }

    /**
     * Provides test cases for rotations with expected faces and the number of cycles required to restore the cube.
     *
     * @return a stream of arguments, each representing a test case with rotations, expected faces after rotations,
     * and the number of cycles to restore the cube to its original baseState.
     */
    static Stream<Arguments> rotationsWithExpectedFaces() {
        return Stream.of(
            Arguments.of(List.of(R, U, R_P, U_P, R_P, F, R, F_P), 3, new SpatialAccessor<>(
                    new RubiksFace(
                        RUBIKS_FACELETS.up(), RUBIKS_FACELETS.up(), RUBIKS_FACELETS.front(),
                        RUBIKS_FACELETS.up(), RUBIKS_FACELETS.up(), RUBIKS_FACELETS.front(),
                        RUBIKS_FACELETS.back(), RUBIKS_FACELETS.back(), RUBIKS_FACELETS.up()
                    ),
                    new RubiksFace(
                        RUBIKS_FACELETS.down(), RUBIKS_FACELETS.down(), RUBIKS_FACELETS.down(),
                        RUBIKS_FACELETS.down(), RUBIKS_FACELETS.down(), RUBIKS_FACELETS.down(),
                        RUBIKS_FACELETS.down(), RUBIKS_FACELETS.down(), RUBIKS_FACELETS.down()
                    ),
                    new RubiksFace(
                        RUBIKS_FACELETS.back(), RUBIKS_FACELETS.left(), RUBIKS_FACELETS.left(),
                        RUBIKS_FACELETS.left(), RUBIKS_FACELETS.left(), RUBIKS_FACELETS.left(),
                        RUBIKS_FACELETS.left(), RUBIKS_FACELETS.left(), RUBIKS_FACELETS.left()
                    ),
                    new RubiksFace(
                        RUBIKS_FACELETS.right(), RUBIKS_FACELETS.up(), RUBIKS_FACELETS.up(),
                        RUBIKS_FACELETS.right(), RUBIKS_FACELETS.right(), RUBIKS_FACELETS.right(),
                        RUBIKS_FACELETS.right(), RUBIKS_FACELETS.right(), RUBIKS_FACELETS.right()
                    ),
                    new RubiksFace(
                        RUBIKS_FACELETS.up(), RUBIKS_FACELETS.up(), RUBIKS_FACELETS.front(),
                        RUBIKS_FACELETS.front(), RUBIKS_FACELETS.front(), RUBIKS_FACELETS.front(),
                        RUBIKS_FACELETS.front(), RUBIKS_FACELETS.front(), RUBIKS_FACELETS.front()
                    ),
                    new RubiksFace(
                        RUBIKS_FACELETS.left(), RUBIKS_FACELETS.right(), RUBIKS_FACELETS.right(),
                        RUBIKS_FACELETS.back(), RUBIKS_FACELETS.back(), RUBIKS_FACELETS.back(),
                        RUBIKS_FACELETS.back(), RUBIKS_FACELETS.back(), RUBIKS_FACELETS.back())
                )
            )
        );
    }
}