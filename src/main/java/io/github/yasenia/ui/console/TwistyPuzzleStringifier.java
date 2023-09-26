package io.github.yasenia.ui.console;

import io.github.yasenia.puzzle.TwistyPuzzle;

import java.util.AbstractMap.SimpleEntry;
import java.util.stream.Stream;

import static io.github.yasenia.ui.console.ConsoleColor.FAILED;
import static io.github.yasenia.ui.console.ConsoleColor.SUCCESS;
import static java.util.stream.Collectors.joining;

/**
 * Provides an abstract base for implementing stringifies that convert
 * twisty puzzle states to human-readable string representations.
 *
 * @param <P> the type of the twisty puzzle
 */
public abstract class TwistyPuzzleStringifier<P extends TwistyPuzzle<P, ?>> {

    protected final String puzzleName;

    /**
     * Constructs a new {@code TwistyPuzzleStringifier} instance with the provided puzzle name.
     *
     * @param puzzleName the name of the twisty puzzle
     */
    protected TwistyPuzzleStringifier(String puzzleName) {
        this.puzzleName = puzzleName;
    }

    /**
     * Converts the given puzzle state to a string representation. This representation includes
     * the puzzle name, the solved state, and an expanded view of the puzzle state.
     *
     * @param puzzle the twisty puzzle instance to stringify
     * @return a human-readable string representation of the provided twisty puzzle state
     */
    public String stringify(P puzzle) {
        return Stream
            .of(
                new SimpleEntry<>("Puzzle", this.puzzleName),
                new SimpleEntry<>("State", puzzle.isSolved() ? SUCCESS.wrap("Solved") : FAILED.wrap("Unsolved")),
                new SimpleEntry<>("Expanded View", "\n%s".formatted(expandedView(puzzle)))
            )
            .map(property -> "- %s:\t %s".formatted(ConsoleColor.INFO.wrap(property.getKey()), property.getValue()))
            .collect(joining("\n"));
    }

    /**
     * Generates a string representation of the expanded view of the provided puzzle.
     * Subclasses should override this method to provide puzzle-specific expanded views.
     *
     * @param puzzle the twisty puzzle instance whose expanded view is to be generated
     * @return a string representing the expanded view of the puzzle; returns a placeholder string if the expanded view is unsupported
     */
    protected String expandedView(P puzzle) {
        return "Expand view is unsupported";
    }
}
