// Kuo Yu Lu

import java.util.List;

public class Print {

    public static final int defaultWidth = 100;
    public static final String defaultPaddingSign = "=";

    // Logo
    public static void logo() {
        String color = "blue";
        // VAULT
        String logo = """
        +--------------------------------------------------------------------------------------------------+
        |                                                                                                  |
        |          ██████   █████  ███    ███ ███████    ██      ██  █████  ██     ██ ██   ████████        |
        |         ██       ██   ██ ████  ████ ██         ██      ██ ██   ██ ██     ██ ██      ██           |
        |         ██   ███ ███████ ██ ████ ██ █████       ██    ██  ███████ ██     ██ ██      ██           |
        |         ██    ██ ██   ██ ██  ██  ██ ██           ██  ██   ██   ██ ██     ██ ██      ██           |
        |          ██████  ██   ██ ██      ██ ███████       ████    ██   ██  ███████  ███████ ██           |
        |                                                                                                  |
        |         ██████     █████ ██         ███████                       ██      ██        ██           |
        |         ██   ██   ██     ██             ██                        ██      ██      ████           |
        |         ██    ██ ██      ██             ██                         ██    ██         ██           |
        |         ██   ██   ██     ██        ██   ██                          ██  ██          ██           |
        |         █████      █████ █████████  ████                             ████    ██  ████████        |
        +--------------------------------------------------------------------------------------------------+
        """;
        System.out.println(Color.colorize(color, logo));

    }

    // Line: Print a centered line with padding
    public static void line(String label, int width, String paddingSign, String color) {
        String centerText = "";

        if (label != null && !label.isEmpty()) {
            centerText = " " + label + " ";
        }

        int paddingWidth = width - centerText.length();
        int left = paddingWidth / 2;
        int right = paddingWidth - left;

        String output = paddingSign.repeat(left) + centerText + paddingSign.repeat(right);

        if (color.equals("")) {
            System.out.println(output);
        } else {
            System.out.println(Color.colorize(color, output));
        }

    }

    public static void line(String label, int width, String paddingSign) {
        line(label, width, paddingSign, "white");
    }

    public static void line(String label, int width) {
        line(label, width, defaultPaddingSign, "white");
    }

    public static void line(String label) {
        line(label, defaultWidth, defaultPaddingSign, "white");
    }

    // Header: Print 3 lines - blank, title, blank
    public static void header(String title, String color) {
        line("", defaultWidth, defaultPaddingSign, color);
        line(title, defaultWidth, " ", color);
        line("", defaultWidth, defaultPaddingSign, color);
    }

    public static void header(String title) {
        line("");
        line(title, defaultWidth, " ", "white");
        line("");
    }

    // List: Print a list of strings with numbered output and header
    public static void gameList(List<Game> games, String title, boolean showCount, String color) {
        // Determine max title length (with "Title: " prefix)
        int maxTitleLength = 0;
        int maxPlatformLength = 0;
        int maxCompletionLength = 0;

        for (Game game : games) {
            if (game.getTitle() != null) { // Prevent NullPointerException
                int titleLength = game.getTitle().length();
                if (titleLength > maxTitleLength) {
                    maxTitleLength = titleLength;
                }

                // BRUTE FORCE ALWAYS UPDATE IS LESS PROC UNDER THE HOOD
                maxPlatformLength = Math.max(maxPlatformLength, game.getPlatform().length());
                maxCompletionLength = Math.max(maxCompletionLength, game.getCompletion().length());
            }
        }

        // REQUIRES AT LEAST 1???
        maxTitleLength = Math.max(1, maxTitleLength);
        maxPlatformLength = Math.max(1, maxPlatformLength);
        maxCompletionLength = Math.max(1, maxCompletionLength);

        // Header
        String headerText = title;
        if (showCount) {
            headerText += " (" + games.size() + ")";
        }
        header(headerText, color);

        if (games.isEmpty()) {
            // No Result
            line("No Result", defaultWidth, " ", "white");
        } else {
            // LOOP THROUGH EVERY GAME
            for (Game game : games) {

                // MAKE THE STRINGS
                String extraString = String.format(
                        "[%4d] %d %-" + maxTitleLength + "s %-" + maxPlatformLength + "s $%7.2f %s %-" + maxCompletionLength + "s %3d Tags: %s",
                        game.getId(),
                        game.getYear(),
                        game.getTitle(),
                        game.getPlatform(),
                        game.getPrice(),
                        game.getIsOwned() ? "Y" : "N",
                        game.getCompletion(),
                        game.getRating(),
                        String.join(", ", game.getTags()));

                // OUTPUT FORMATTED STRING
                System.out.println(extraString);
            }
        }
        // Bottom line
        line("", defaultWidth, "-", color);
    }

    public static void gameList(List<Game> games, String title, boolean showCount) {
        gameList(games, title, showCount, "white");
    }

    public static void gameList(List<Game> games, String title) {
        gameList(games, title, true, "white");
    }
}
