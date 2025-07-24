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
    for (Game game : games) {
      if (game.getTitle() != null) { // Prevent NullPointerException
        int titleLength = game.getTitle().length();
        if (titleLength > maxTitleLength) {
          maxTitleLength = titleLength;
        }
      }
    }

    // Header
    String headerText = title;
    if (showCount) {
      headerText += " (" + games.size() + ")";
    }
    header(headerText, color);

    if (games.size() == 0) {
      // No Result
      line("No Result", defaultWidth, " ", "white");
    } else {
      // Has Results
      String format = "%-3s %-4s %-" + maxTitleLength + "s  %s%n";

      for (Game game : games) {
        String titleStr = game.getTitle();
        String extraString = String.format(
            "%d  $%.2f  isOwned: %s  Tags: %s",
            game.getYear(),
            game.getPrice(),
            game.getIsOwned() ? "T" : "F",
            String.join(", ", game.getTags()));

        System.out.printf(format, "-", game.getId(), titleStr, extraString);
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