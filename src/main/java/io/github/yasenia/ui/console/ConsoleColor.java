package io.github.yasenia.ui.console;

import io.github.yasenia.ui.console.ConsoleColor.ColorCode.BackgroundColor;
import io.github.yasenia.ui.console.ConsoleColor.ColorCode.TextColor;

/**
 * Represents a combination of text color and background color for console output.
 * This record provides several predefined color combinations for common use-cases
 * and a method to wrap text with the appropriate ANSI color codes.
 */
public record ConsoleColor(
    TextColor textColor,
    BackgroundColor backgroundColor
) {

    public static final ConsoleColor INFO = new ConsoleColor(TextColor.BLUE, BackgroundColor.NONE);
    public static final ConsoleColor SUCCESS = new ConsoleColor(TextColor.GREEN, BackgroundColor.NONE);
    public static final ConsoleColor FAILED = new ConsoleColor(TextColor.RED, BackgroundColor.NONE);
    public static final ConsoleColor RED = new ConsoleColor(TextColor.BLACK, BackgroundColor.RED);
    public static final ConsoleColor GREEN = new ConsoleColor(TextColor.BLACK, BackgroundColor.GREEN);
    public static final ConsoleColor BLUE = new ConsoleColor(TextColor.BLACK, BackgroundColor.BLUE);
    public static final ConsoleColor YELLOW = new ConsoleColor(TextColor.BLACK, BackgroundColor.YELLOW);
    public static final ConsoleColor PURPLE = new ConsoleColor(TextColor.BLACK, BackgroundColor.PURPLE);
    public static final ConsoleColor WHITE = new ConsoleColor(TextColor.BLACK, BackgroundColor.WHITE);

    /**
     * Removes ANSI color codes from the given text string.
     * This method is useful for stripping color codes when the colored text needs to be logged or displayed
     * in an environment that does not support ANSI color codes, ensuring that the text is readable
     * without any non-text characters.
     *
     * @param text the colored text string from which ANSI color codes are to be removed.
     * @return a string with ANSI color codes removed.
     */
    public static String decolorize(String text) {
        return text.replaceAll("\u001B\\[\\d+m", "");
    }

    /**
     * Wraps the given text with ANSI color codes corresponding to the textColor and backgroundColor
     * of this ConsoleColor instance.
     *
     * @param text The text to be colored.
     * @return The text wrapped with ANSI color codes.
     */
    public String wrap(String text) {
        return "%s%s%s%s".formatted(textColor.code, backgroundColor.code, text, "\u001B[0m");
    }

    /**
     * Represents the ANSI color code for text or background in the console.
     */
    public sealed interface ColorCode permits TextColor, BackgroundColor {

        /**
         * The ANSI color code string.
         */
        String code();

        /**
         * Represents ANSI color codes for text color in the console.
         */
        enum TextColor implements ColorCode {
            NONE(""),
            BLACK("\u001B[30m"),
            RED("\u001B[31m"),
            GREEN("\u001B[32m"),
            YELLOW("\u001B[33m"),
            BLUE("\u001B[34m"),
            PURPLE("\u001B[35m"),
            CYAN("\u001B[36m"),
            WHITE("\u001B[37m");

            public final String code;

            TextColor(String code) {
                this.code = code;
            }

            @Override
            public String code() {
                return this.code;
            }
        }

        /**
         * Represents ANSI color codes for background color in the console.
         */
        enum BackgroundColor implements ColorCode {
            NONE(""),
            BLACK("\u001B[40m"),
            RED("\u001B[41m"),
            GREEN("\u001B[42m"),
            YELLOW("\u001B[43m"),
            BLUE("\u001B[44m"),
            PURPLE("\u001B[45m"),
            CYAN("\u001B[46m"),
            WHITE("\u001B[47m");

            public final String code;

            BackgroundColor(String code) {
                this.code = code;
            }

            @Override
            public String code() {
                return this.code;
            }
        }
    }
}
