package io.github.yasenia.ui.console.cube.standard;

import org.junit.jupiter.api.Test;

import java.util.List;

import static io.github.yasenia.puzzle.cube.standard.rubiks.RubiksCube.SOLVED_CUBE;
import static io.github.yasenia.puzzle.cube.standard.rubiks.RubiksRotation.R;
import static io.github.yasenia.puzzle.cube.standard.rubiks.RubiksRotation.R_P;
import static io.github.yasenia.puzzle.cube.standard.rubiks.RubiksRotation.U;
import static io.github.yasenia.puzzle.cube.standard.rubiks.RubiksRotation.U_P;
import static io.github.yasenia.ui.console.ConsoleColor.decolorize;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class RubiksCubeStringifierTest {

    @Test
    void should_generate_expanded_view_of_solved_cube() {
        // setup
        var stringifier = new RubiksCubeStringifier();
        // exercise
        var result = stringifier.stringify(SOLVED_CUBE);
        // verify
        assertThat(decolorize(result), equalTo("""
            - Puzzle:\t Rubik's Cube(3 x 3 x 3)
            - State:\t Solved
            - Expanded View:\t\s
                              ┏━━━━━┳━━━━━┳━━━━━┓
                              ┃  U  ┃  U  ┃  U  ┃
                              ┣━━━━━╋━━━━━╋━━━━━┫
                              ┃  U  ┃  U  ┃  U  ┃
                              ┣━━━━━╋━━━━━╋━━━━━┫
                              ┃  U  ┃  U  ┃  U  ┃
            ┏━━━━━┳━━━━━┳━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━┳━━━━━┳━━━━━┳━━━━━┳━━━━━┳━━━━━┓
            ┃  L  ┃  L  ┃  L  ┃  F  ┃  F  ┃  F  ┃  R  ┃  R  ┃  R  ┃  B  ┃  B  ┃  B  ┃
            ┣━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━┫
            ┃  L  ┃  L  ┃  L  ┃  F  ┃  F  ┃  F  ┃  R  ┃  R  ┃  R  ┃  B  ┃  B  ┃  B  ┃
            ┣━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━┫
            ┃  L  ┃  L  ┃  L  ┃  F  ┃  F  ┃  F  ┃  R  ┃  R  ┃  R  ┃  B  ┃  B  ┃  B  ┃
            ┗━━━━━┻━━━━━┻━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━┻━━━━━┻━━━━━┻━━━━━┻━━━━━┻━━━━━┛
                              ┃  D  ┃  D  ┃  D  ┃
                              ┣━━━━━╋━━━━━╋━━━━━┫
                              ┃  D  ┃  D  ┃  D  ┃
                              ┣━━━━━╋━━━━━╋━━━━━┫
                              ┃  D  ┃  D  ┃  D  ┃
                              ┗━━━━━┻━━━━━┻━━━━━┛
            """));
    }

    @Test
    void should_generate_expanded_view_of_shuffled_cube() {
        // setup
        var stringifier = new RubiksCubeStringifier();
        // exercise
        var result = stringifier.stringify(SOLVED_CUBE.rotate(List.of(R, U, R_P, U_P)));
        // verify
        assertThat(decolorize(result), equalTo("""
            - Puzzle:\t Rubik's Cube(3 x 3 x 3)
            - State:\t Unsolved
            - Expanded View:\t\s
                              ┏━━━━━┳━━━━━┳━━━━━┓
                              ┃  U  ┃  U  ┃  L  ┃
                              ┣━━━━━╋━━━━━╋━━━━━┫
                              ┃  U  ┃  U  ┃  F  ┃
                              ┣━━━━━╋━━━━━╋━━━━━┫
                              ┃  U  ┃  U  ┃  F  ┃
            ┏━━━━━┳━━━━━┳━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━┳━━━━━┳━━━━━┳━━━━━┳━━━━━┳━━━━━┓
            ┃  B  ┃  L  ┃  L  ┃  F  ┃  F  ┃  D  ┃  R  ┃  R  ┃  U  ┃  B  ┃  R  ┃  R  ┃
            ┣━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━┫
            ┃  L  ┃  L  ┃  L  ┃  F  ┃  F  ┃  U  ┃  B  ┃  R  ┃  R  ┃  B  ┃  B  ┃  B  ┃
            ┣━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━┫
            ┃  L  ┃  L  ┃  L  ┃  F  ┃  F  ┃  F  ┃  U  ┃  R  ┃  R  ┃  B  ┃  B  ┃  B  ┃
            ┗━━━━━┻━━━━━┻━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━┻━━━━━┻━━━━━┻━━━━━┻━━━━━┻━━━━━┛
                              ┃  D  ┃  D  ┃  R  ┃
                              ┣━━━━━╋━━━━━╋━━━━━┫
                              ┃  D  ┃  D  ┃  D  ┃
                              ┣━━━━━╋━━━━━╋━━━━━┫
                              ┃  D  ┃  D  ┃  D  ┃
                              ┗━━━━━┻━━━━━┻━━━━━┛
            """));
    }
}