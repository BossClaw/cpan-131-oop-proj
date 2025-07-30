// Kuo Yu Lu
public class Color {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static String get(String color) {
        switch (color.trim().toLowerCase()) {
            case "black":
                return ANSI_BLACK;
            case "red":
                return ANSI_RED; // warning
            case "green":
                return ANSI_GREEN;
            case "yellow":
                return ANSI_YELLOW; // Info
            case "blue":
                return ANSI_BLUE; // Menu Header
            case "purple":
                return ANSI_PURPLE;
            case "cyan":
                return ANSI_CYAN; // Menu options
            case "white":
                return ANSI_WHITE;
            default:
                return ANSI_RESET;
        }
    }

    // Any Color
    public static String colorize(String color, String text) {
        return get(color) + text + ANSI_RESET;
    }

    // Single color shortcuts
    public static String black(String text) {
        return colorize("black", text);
    }

    public static String red(String text) {
        return colorize("red", text);
    }

    public static String green(String text) {
        return colorize("green", text);
    }

    public static String yellow(String text) {
        return colorize("yellow", text);
    }

    public static String blue(String text) {
        return colorize("blue", text);
    }

    public static String purple(String text) {
        return colorize("purple", text);
    }

    public static String cyan(String text) {
        return colorize("cyan", text);
    }

    public static String white(String text) {
        return colorize("white", text);
    }
}
