// Kuo Yu Lu

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class GameFilter {

    private Library library;
    private final String color = "CYAN";
    private final String innerColor = "blue";
    private final String[] options = {
        "Find By Title",
        "Find By Tags",
        "Find By Owned",
        "Find By Price",
        "Sort",
        "Exit"
    };

    public GameFilter(Library library) {
        this.library = library;
    }

    // Print main menu
    public void menu(Scanner scan) {
        Print.header("Game Filter Menu", color);

        for (int i = 0; i < options.length; i++) {
            int index = i + 1;
            String formatted;
            if (options[i].equals("Exit")) {
                formatted = String.format("%-3s %s\n", "0.", options[i]);
            } else {
                formatted = String.format("%-3s %s\n", index + ".", options[i]);
            }
            String formattedWithColor = Color.colorize(innerColor, formatted);
            System.out.print(formattedWithColor);
        }

        Print.line("", Print.defaultWidth, Print.defaultPaddingSign, color);
        menuInput(scan);
    }

    // Get menu input
    public void menuInput(Scanner scan) {
        boolean isContinue = true;

        while (true) {
            // Print menu options in line
            String displayOptions = "";
            int optionIndex = 1;
            for (String option : this.options) {
                if (option.equals("Exit")) {
                    displayOptions += "(0)" + option;
                    continue;
                }
                displayOptions += "(" + optionIndex + ")" + option + "  ";
                optionIndex++;
            }

            System.out.println(displayOptions);
            System.out.print(Color.colorize(innerColor, "> [Game Filter] Enter filter option (0 to exit): "));

            // Handle invalid input
            if (!scan.hasNextInt()) {
                System.out.println(Color.red("❌ Please enter a valid option"));
                scan.nextLine(); // consume invalid input
                continue;
            }

            // Run selected option
            int option = scan.nextInt();

            switch (option) {
                case 1:
                    findByTitle(scan);
                    break;
                case 2:
                    findByTag(scan);
                    break;
                case 3:
                    findByOwnGame(scan);
                    break;
                case 4:
                    findByPrice(scan);
                    break;
                case 5:
                    sort(scan);
                    break;
                case 0:
                    System.out.println("Exit Game Filter Menu");
                    isContinue = false;
                    break;
                default:
                    System.out.println(Color.red("❌ Not a valid option, please try again"));
                    break;
            }

            if (!isContinue) {
                return;
            }
        }
    }

    // Find by title
    public void findByTitle(Scanner scan) {
        // Ask user
        System.out.print(Color.colorize(innerColor, "> [Find By Title] Enter game title (0 to exit): "));
        scan.nextLine(); // consume newline
        String input = scan.nextLine();

        if (input.equals("0")) {
            return;
        }

        // Find games
        List<Game> games = new ArrayList<>(library.getAllGames());
        List<Game> filteredGames = games.stream()
                .filter(game -> game.getTitle().toLowerCase().contains(input.toLowerCase()))
                .collect(Collectors.toList());

        // sort by id (ascending)
        sortHelper(filteredGames, "id", "a");

        // Show game list
        Print.gameList(filteredGames, "Results", true);
    }

    // Find by tags
    public void findByTag(Scanner scan) {
        List<Game> games = new ArrayList<>(library.getAllGames());

        // Get all tags
        // V2DO - GRAB THE TAGS IN THE LIBRARY CLASS ON EVERY LOAD/WRITE
        Set<String> displayTagsSet = new HashSet<>();
        for (Game game : games) {
            displayTagsSet.addAll(game.getTags());
        }

        // Print all tags nicely
        System.out.println(Color.black("All Tags:"));
        int wrapCount = 8;
        int count = 0;
        for (String tag : displayTagsSet) {
            System.out.print(Color.black(tag + "  "));
            count++;
            if (count % wrapCount == 0) {
                System.out.println();
            }
        }
        System.out.println(); // line break

        // Ask user
        System.out.print(Color.colorize(innerColor, "> [Find By Tags] Enter tags (comma-separated, 0 to exit): "));
        scan.nextLine(); // consume newline
        String input = scan.nextLine();
        if (input.equals("0")) {
            return;
        }

        // Process search tags
        List<String> tags = Arrays.asList(input.split(","));
        List<String> searchTags = tags.stream().map(t -> t.toLowerCase().trim()).collect(Collectors.toList());

        // Find games with matching tags
        List<Game> filteredGames = new ArrayList<>();
        for (Game game : games) {
            for (String tag : game.getTags()) {
                if (searchTags.contains(tag.toLowerCase())) {
                    if (!filteredGames.contains(game)) {
                        filteredGames.add(game);
                    }
                }
            }
        }

        // sort by id (ascending)
        sortHelper(filteredGames, "id", "a");

        // Show game list
        Print.gameList(filteredGames, "Results", true);
    }

    // Find by owned
    public void findByOwnGame(Scanner scan) {
        List<Game> games = new ArrayList<>(library.getAllGames());

        // Ask user
        System.out.print(Color.colorize(innerColor, "> [Find By Owned] Enter T / F (0 to exit): "));
        scan.nextLine(); // consume newline
        String input = scan.nextLine();
        if (input.equals("0")) {
            return;
        }

        // Convert to boolean
        boolean isOwned = input.trim().toLowerCase().equals("t");

        // Find games
        List<Game> filteredGames = games.stream()
                .filter(g -> g.getIsOwned() == isOwned)
                .collect(Collectors.toList());

        // sort by id (ascending)
        sortHelper(filteredGames, "id", "a");

        // Show game list
        Print.gameList(filteredGames, "Results", true);
    }

    // Find by price
    public void findByPrice(Scanner scan) {
        List<Game> games = new ArrayList<>(library.getAllGames());

        // Ask for operator
        System.out
                .print(Color.colorize(innerColor, "> [Find By Price] Enter comparison operator ( > , < , = or 0 to exit): "));
        scan.nextLine(); // consume newline
        String operatorInput = scan.nextLine().trim();
        if (operatorInput.equals("0")) {
            return;
        }

        // Check operator
        List<String> operatorChecks = List.of("=", ">", "<");
        if (!operatorChecks.contains(operatorInput)) {
            System.err.println(Color.red("❌ Invalid operator input: " + operatorInput));
            return;
        }

        // Ask for price
        System.out.print(Color.colorize(innerColor, "> [Find By Price] Enter price: "));
        double priceInput = scan.nextDouble();

        // Find games
        List<Game> filteredGames = new ArrayList<>();
        switch (operatorInput) {
            case ">":
                filteredGames = games.stream().filter(game -> game.getPrice() > priceInput).collect(Collectors.toList());
                break;
            case "<":
                filteredGames = games.stream().filter(game -> game.getPrice() < priceInput).collect(Collectors.toList());
                break;
            case "=":
                filteredGames = games.stream().filter(game -> game.getPrice() == priceInput).collect(Collectors.toList());
                break;
            default:
                System.err.println(Color.red("❌ Invalid operator input: " + operatorInput));
                return;
        }

        // sort by price (ascending)
        sortHelper(filteredGames, "price", "a");

        // Show game list
        Print.gameList(filteredGames, "Results", true);
    }

    // Sort By
    public void sort(Scanner scan) {
        List<Game> games = new ArrayList<>(library.getAllGames());

        // Ask input sort by option
        System.out
                .print(Color.colorize(innerColor, "> [Sort] Enter sort field (id, title, price, year, owned or 0 to exit): "));
        scan.nextLine(); // consume newline
        String sortBy = scan.nextLine().trim();

        // check for exit
        if (sortBy.equals("0")) {
            return;
        }

        // check for sortBy
        List<String> sortByOptions = List.of("id", "title", "price", "year", "owned");
        if (!sortByOptions.contains(sortBy.toLowerCase())) {
            System.out.println(Color.red("❌ Please enter a valid sort by field (id, title, price, year, owned)"));
            return;
        }

        // Ask input order Ascending (default) / Decending
        System.out.print(Color.colorize(innerColor, "> [Sort] Enter sort order (a) Ascending / (d) Decending : "));
        String order = scan.nextLine().trim().toLowerCase();

        if (!order.equals("a") && !order.equals("d")) {
            order = "a"; // default to ascending
        }

        // Sort the games
        sortHelper(games, sortBy, order);

        // Show game list
        Print.gameList(games, "Results", true);
    }

    // --------------------- Helper funciton ---------------------
    // Sort and mutate the list
    public void sortHelper(List<Game> games, String sortBy, String order) {

        if (!order.equals("d") && !order.equals("a")) {
            order = "a"; // default to ascending order
        }
        String sortByString = sortBy + "-" + order;

        switch (sortByString) {
            case "id-a":
                games.sort(Comparator.comparing(Game::getId));
                break;
            case "id-d":
                games.sort(Comparator.comparing(Game::getId).reversed());
                break;
            case "title-a":
                games.sort(Comparator.comparing(Game::getTitle));
                break;
            case "title-d":
                games.sort(Comparator.comparing(Game::getTitle).reversed());
                break;
            case "price-a":
                games.sort(Comparator.comparing(Game::getPrice));
                break;
            case "price-d":
                games.sort(Comparator.comparing(Game::getPrice).reversed());
                break;
            case "year-a":
                games.sort(Comparator.comparing(Game::getYear));
                break;
            case "year-d":
                games.sort(Comparator.comparing(Game::getYear).reversed());
                break;
            case "owned-a":
                games.sort(Comparator.comparing(Game::getIsOwned));
                break;
            case "owned-d":
                games.sort(Comparator.comparing(Game::getIsOwned).reversed());
                break;
            default:
                System.out.println(Color.red("❌ Please enter a valid sort by field [id, title, price, owned]"));
                return;
        }
    }
}
