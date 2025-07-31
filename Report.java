// Kuo Yu Lu

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Report {

  private Library library;
  private final String color = "green";
  private final String innerColor = "blue";

  public Report(Library liberary) {
    this.library = liberary;
  }

  public void print(Scanner scan) {
    Print.header("Game Report", color);

    // Get all the games
    List<Game> games = new ArrayList<>(library.getAllGames());

    // ------------- Statistics -------------------
    System.out.println(Color.green("STATISTICS"));

    // Count
    int totalGameCount = games.size();
    int totalOwnedGameCount = games.stream().filter(g -> g.getIsOwned() == true).collect(Collectors.toList()).size();
    int gameCompleted = games.stream().filter(g -> "100%".equals(g.getCompletion())).collect(Collectors.toList())
        .size();

    // Total Game count
    printLine(String.format("%-18s", "Total Games"), String.valueOf(totalGameCount));

    // Owned Game count
    printLine(String.format("%-18s", "Owned Games"),
        valueOverTotalString(totalOwnedGameCount, totalGameCount));

    // Game Completed
    printLine(String.format("%-18s", "Completed Game"),
        valueOverTotalString(gameCompleted, totalGameCount));

    System.out.println("");

    // ------------------ Price ---------------------
    System.out.println(Color.green("PRICE"));

    // Total Money spent
    int totalCost = 0;
    Game mostExpensiveGame = null;
    Game cheapestGame = null;

    for (Game game : games) {
      totalCost += game.getPrice();
      // find most expensive
      if (mostExpensiveGame == null) {
        mostExpensiveGame = game;
      } else if (game.getPrice() > mostExpensiveGame.getPrice()) {
        mostExpensiveGame = game;
      }

      // find cheapest
      if (cheapestGame == null) {
        cheapestGame = game;
      } else if (game.getPrice() < cheapestGame.getPrice()) {
        cheapestGame = game;
      }
    }
    ;
    printLine(String.format("%-18s", "Total Cost"), "$" + totalCost);

    // Average money spent on game
    int averageCost = totalCost / games.size();
    printLine(String.format("%-18s", "Average Cost"), "$" + averageCost);

    // Priciest game
    printLine(String.format("%-18s", "Priciest Game"),
        mostExpensiveGame.getTitle() + " $" + mostExpensiveGame.getPrice());

    // Cheapest game
    printLine(String.format("%-18s", "Cheapest Game"),
        cheapestGame.getTitle() + " $" + cheapestGame.getPrice());

    System.out.println("");

    // ----------------- Platform ---------------
    System.out.println(Color.green("Platform"));

    // Get all platform
    Map<String, Integer> allPlatform = new HashMap<>();
    for (Game game : games) {
      allPlatform.put(game.getPlatform(), allPlatform.getOrDefault(game.getPlatform(), 0) + 1);
    }

    // Print platform count
    printLine(String.format("%-18s", "Total Unique"), String.valueOf(allPlatform.size()));

    // Sort and print all platform
    allPlatform.entrySet().stream()
        .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())) // sort by value descending
        .forEach(entry -> System.out.println(String.format("%-17s %d", entry.getKey().trim(), entry.getValue())));

    System.out.println("");

    // ----------------- Tags ---------------
    System.out.println(Color.green("TAGS"));

    // Get all tags
    Map<String, Integer> allTags = new HashMap<>();
    for (Game game : games) {
      for (String tag : game.getTags()) {
        // Add to count (or default to 0) then + 1
        allTags.put(tag, allTags.getOrDefault(tag, 0) + 1);
      }
    }

    // print all tags + count
    printLine(String.format("%-18s", "Total Unique"), String.valueOf(allTags.size()));

    // Sort and Print out all tags
    allTags.entrySet().stream()
        .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())) // sort by value descending
        .forEach(entry -> System.out.println(String.format("%-17s %d", entry.getKey().trim(), entry.getValue())));

    System.out.println("");

    // ------------------ Ask user to Exit
    System.out.print(Color.colorize(color, "> (0 to exit): "));
    String input = scan.nextLine();
    if (input.equals("0")) {
      return;
    }
  }

  public void printLine(String title, String value, String color) {
    System.out.println(title + Color.colorize(color, value));
  }

  public void printLine(String title, String value) {
    String color = this.innerColor;
    System.out.println(title + Color.colorize(color, value));
  }

  // Return a string of value / totalGame
  public String valueOverTotalString(int value, int total) {
    return value + " / " + total;
  }

}
