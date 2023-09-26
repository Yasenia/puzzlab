package io.github.yasenia.ui.console.cube.standard;

import io.github.yasenia.puzzle.cube.standard.geometric.accessor.SpatialAccessor;
import io.github.yasenia.puzzle.cube.standard.rubiks.RubiksCube;
import io.github.yasenia.puzzle.cube.standard.rubiks.RubiksFacelet;
import io.github.yasenia.ui.console.ConsoleColor;
import io.github.yasenia.ui.console.TwistyPuzzleStringifier;

import java.util.stream.Collector;
import java.util.stream.Stream;

import static io.github.yasenia.ui.console.ConsoleColor.BLUE;
import static io.github.yasenia.ui.console.ConsoleColor.GREEN;
import static io.github.yasenia.ui.console.ConsoleColor.PURPLE;
import static io.github.yasenia.ui.console.ConsoleColor.RED;
import static io.github.yasenia.ui.console.ConsoleColor.WHITE;
import static io.github.yasenia.ui.console.ConsoleColor.YELLOW;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.mapping;

/**
 * A specialized {@link TwistyPuzzleStringifier} that converts the states of a {@link RubiksCube} to
 * human-readable string representations. It provides a detailed, colored representation of the Rubik's Cube,
 * showing each face with colors and characters to visualize the state of the cube effectively.
 *
 * <p>
 * It uses {@link SpatialAccessor} to access color and facelet codes efficiently for different orientations
 * and utilizes Java streams and collectors for building string representations of the cube's rows.
 *
 * <p>
 * The {@code expandedView} method is overridden to provide a customized view that visually represents each
 * face of the Rubik's Cube, allowing users to understand the cube's state at a glance.
 */
public class RubiksCubeStringifier extends TwistyPuzzleStringifier<RubiksCube> {

    private static final SpatialAccessor<ConsoleColor> STANDARD_COLOR_SYSTEM = new SpatialAccessor<>(YELLOW, WHITE, BLUE, GREEN, RED, PURPLE);
    private static final SpatialAccessor<String> FACELET_CODES = new SpatialAccessor<>("U", "D", "L", "R", "F", "B");

    private final Collector<RubiksFacelet, ?, String> toFaceletsRow;

    /**
     * Constructs a default {@link RubiksCubeStringifier} with a standard color system.
     */
    public RubiksCubeStringifier() {
        this(STANDARD_COLOR_SYSTEM);
    }

    /**
     * Constructs a {@link RubiksCubeStringifier} with the given color system.
     *
     * @param colorSystem a {@link SpatialAccessor} providing colors for different orientations.
     */
    protected RubiksCubeStringifier(SpatialAccessor<ConsoleColor> colorSystem) {
        super("Rubik's Cube(3 x 3 x 3)");
        this.toFaceletsRow = mapping(
            facelet -> colorSystem.at(facelet.originalOrientation()).wrap(" %s ".formatted(FACELET_CODES.at(facelet.originalOrientation()))),
            joining(" ┃ ", "┃ ", " ┃\n")
        );
    }

    /**
     * Generates a string representation of the expanded view of the provided Rubik's Cube.
     * It visually represents each face of the cube using characters and colors.
     *
     * @param cube the Rubik's Cube instance whose expanded view is to be generated
     * @return a string representing the expanded view of the Rubik's Cube
     */
    @Override
    protected String expandedView(RubiksCube cube) {
        var up = cube.faces().up();
        var left = cube.faces().left();
        var front = cube.faces().front();
        var right = cube.faces().right();
        var back = cube.faces().back();
        var down = cube.faces().down();
        //noinspection StringBufferReplaceableByString
        return new StringBuilder()
            .append("                  ┏━━━━━┳━━━━━┳━━━━━┓\n")
            .append("                  ").append(up.rows().first().toStream().collect(toFaceletsRow))
            .append("                  ┣━━━━━╋━━━━━╋━━━━━┫\n")
            .append("                  ").append(up.rows().second().toStream().collect(toFaceletsRow))
            .append("                  ┣━━━━━╋━━━━━╋━━━━━┫\n")
            .append("                  ").append(up.rows().third().toStream().collect(toFaceletsRow))
            .append("┏━━━━━┳━━━━━┳━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━┳━━━━━┳━━━━━┳━━━━━┳━━━━━┳━━━━━┓\n")
            .append(Stream.of(left, front, right, back).flatMap(face -> face.rows().first().toStream()).collect(toFaceletsRow))
            .append("┣━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━┫\n")
            .append(Stream.of(left, front, right, back).flatMap(face -> face.rows().second().toStream()).collect(toFaceletsRow))
            .append("┣━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━┫\n")
            .append(Stream.of(left, front, right, back).flatMap(face -> face.rows().third().toStream()).collect(toFaceletsRow))
            .append("┗━━━━━┻━━━━━┻━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━┻━━━━━┻━━━━━┻━━━━━┻━━━━━┻━━━━━┛\n")
            .append("                  ").append(down.rows().first().toStream().collect(toFaceletsRow))
            .append("                  ┣━━━━━╋━━━━━╋━━━━━┫\n")
            .append("                  ").append(down.rows().second().toStream().collect(toFaceletsRow))
            .append("                  ┣━━━━━╋━━━━━╋━━━━━┫\n")
            .append("                  ").append(down.rows().third().toStream().collect(toFaceletsRow))
            .append("                  ┗━━━━━┻━━━━━┻━━━━━┛\n")
            .toString();
    }
}
