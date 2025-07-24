// EVERYONE

import java.util.Scanner;

public class Menu {

    // HANDLES ALL THE USER INPUT AND MENU LOGIC
    // DEPENDENCY INJECTION CLASSES
    private final Library library;
    private final FilterLogic filter;

    // USED BY MENU METHODS
    private Scanner scanner;

    // CONSTRUCTOR REQUIRING CLASS DEPENDENCIES
    public Menu(Library _library, FilterLogic _filter) {
        this.library = _library;
        this.filter = _filter;
    }

    public void displayMenu() {

        // MAKE THE SCANNER                
        scanner = new Scanner(System.in);

        // DEFAULT CHOICE
        int choice;

        // MAIN MENU LOOP UNTIL USER CHOOSES TO EXIT
        do {
            // CLEAR SCREEN
            //clearScreen();

            // DISPLAY MENU OPTIONS
            System.out.println("\n--- Video Game Library Menu ---");
            System.out.println("1. Add New Game");
            System.out.println("2. Add Game (Shorthand)");
            System.out.println("3. Edit Game (MILESTONE 2)");
            System.out.println("4. Delete Game (MILESTONE 2)");

            System.out.println("10. View All Games");
            System.out.println("11. Report");

            System.out.println("50. Bulk Import Games from CSV (MILESTONE 2)");

            System.out.println("77. Exit");
            System.out.println("999. SCREW UP");

            // PROMPT USER FOR CHOICE AS INT
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            // CONSUME NEWLINE CHARACTER
            scanner.nextLine();

            // HANDLE USER CHOICE
            switch (choice) {
                // GAME CUD
                case 1:
                    addNewGame();
                    break;
                case 2:
                    addGameShorthand();
                    break;
                case 3:
                    // MILESTONE 2 editGameShorthand();
                    break;

                // GAME RETRIEVAL/FILTER/REPORTS
                case 10:
                    // TBD - BEST WAY TO DO THIS
                    // BLANK FILTER LISTS ALL
                    filter.listGamesByFilter("");
                    promptEnterKey();
                    break;
                case 11:
                    // USER INPUT FILTER
                    // EG:  genre:Action?,Dating Sim? platform:PC, title:*shock, year: > 1980,  sort:title, year,  ASC
                    //filter.listGamesByFilter(userFilter);
                    promptEnterKey();
                    break;
                case 12:
                    // WHOLE REPORT
                    // filter.listGamesByFilter(userFilter);
                    promptEnterKey();
                    break;

                // CSV IMPORT/EXPORT
                case 50:
                    bulkImportGames();
                    break;

                // SYSTEM
                case 77:
                    System.out.println("Exiting application. Goodbye!\n\n");
                    break;

                //  SCREW UP
                case 999:
                    System.out.println("You chose to screw up! This is not a valid option.");
                    System.out.println("Please choose a valid option from the menu.");
                    throw new UnsupportedOperationException(
                            "This is a placeholder for a future feature or error handling."
                    );

                // DEFAULT
                default:
                    System.out.println("Invalid choice. Please try again.");
                    promptEnterKey();
            }
        } while (choice != 77);

        // END OF MENU PROCESSING
        // DISPOSE SCANNER
        scanner.close();
    }

    /**
     * Adds a new game to the library by prompting the user for details.
     */
    private void addNewGame() {
        System.out.print("Enter game title: ");
        String title = scanner.nextLine();
        System.out.println("GOT TITLE[" + title + "]\n\n");

        System.out.print("Enter release year: ");
        int releaseYear = scanner.nextInt();
        scanner.nextLine(); // CONSUME NEWLINE AFTER ALL INTS
        System.out.println("GOT YEAR[" + releaseYear + "]\n\n");

        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();
        System.out.println("GOT GENRE[" + genre + "]\n\n");

        System.out.print("Enter platform (PC, PlayStation, Xbox, Switch): ");
        String platform = scanner.nextLine();
        System.out.println("GOT PLATFORM[" + platform + "]\n\n");

        System.out.print("Enter ownership status ((O)wned, (W)ishlisted): ");
        String ownership = scanner.nextLine();
        System.out.println("GOT OWNERSHIP[" + ownership + "]\n\n");

        // PASS THE INPUT VARS TO LIBRARY ADDGAME METHOD
        library.addGame(
                title,
                platform,
                genre,
                releaseYear,
                ownership);

        // PROMPT USER TO PRESS ENTER TO RETURN TO MAIN MENU
        promptEnterKey();
    }

    /**
     * Bulk imports games from a CSV file.
     */
    private void bulkImportGames() {
        System.out.print("Enter the path to the CSV file for bulk import: ");
        String csvFilePath = scanner.nextLine();
        // library.bulkImportFromCsv(csvFilePath);
        promptEnterKey();
    }

    /**
     * Adds a game using shorthand input format. The format is:
     * Title,Platform,Genre,ReleaseYear,OwnershipStatus
     */
    private void addGameShorthand() {
        System.out.println(
                "Enter game details in shorthand (Title,Platform,Genre,ReleaseYear,OwnershipStatus):"
        );
        String shorthandInput = scanner.nextLine();
        String[] parts = shorthandInput.split(",");
        if (parts.length == 5) {
            try {
                // ASSIGN PARTS OF STRING TO VARIABLES
                String shTitle = parts[0].trim();
                String shPlatform = parts[1].trim();
                String shGenre = parts[2].trim();
                int shReleaseYear = Integer.parseInt(parts[3].trim());
                String shOwnershipStatus = parts[4].trim();

                // CALL LIBRARY ADDGAME METHOD WITH SHORTHAND INPUT
                library.addGame(shTitle, shPlatform, shGenre, shReleaseYear, shOwnershipStatus);
            } catch (NumberFormatException e) {
                System.err.println(
                        "Invalid release year format. Please enter a number."
                );
            } catch (Exception e) {
                System.err.println("Error parsing shorthand input: " + e.getMessage());
            }
        } else {
            System.err.println(
                    "Invalid shorthand format. Please provide 5 comma-separated values."
            );
        }
        promptEnterKey();
    }

    /**
     * Prompts the user to press Enter to return to the main menu.
     */
    private void promptEnterKey() {
        System.out.println("Press Enter to return to main menu...");
        scanner.nextLine();
    }

    /**
     * Clears the console screen based on the operating system.
     */
    public static void clearScreen() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            // HANDLE EXCEPTIONS
        }
    }
}
// TODO - ENSURE ALL OPTIONS BELOW ARE IMPLEMENTED IN THE MENU ABOVE
//         // CREATE FILTER CLASS
//         // CREATE LIBRARY CLASS
//         // FAIL EARLY, FIND BUG TO FIX ASAP
//         Library library = new Library(perp);
//         int choice;
//         do {
//             System.out.println("\n--- Video Game Library Menu ---");
//             System.out.println("1. Add New Game"); // CREATE
//             // CRUD
//             System.out.println("2. View All Games");
//             System.out.println("33. Filter Games");
//             System.out.println("2. View Single Game"); // REVIEW
//             // VIEW -> EDIT -> CHOOSE KEY -> GET VALUE ->
//             System.out.println("77. Edit"); // UPDATE
//             System.out.println("4. Delete"); // DELETE
//             System.out.println("12. Show Report");
//             System.out.println("10. Import/Export");
//             System.out.println("99. Exit");
//             System.out.print("Enter your choice: ");
//             choice = scanner.nextInt();
//             scanner.nextLine(); // Consume newline
//             switch (choice) {
//                 case 1:
//                     System.out.print("Enter game title: ");
//                     String title = scanner.nextLine();
//                     System.out.print("Enter platform (PC, PlayStation, Xbox, Switch): ");
//                     String platform = scanner.nextLine();
//                     System.out.print("Enter genre: ");
//                     String genre = scanner.nextLine();
//                     System.out.print("Enter release year: ");
//                     int releaseYear = scanner.nextInt();
//                     System.out.print("Is this favorite game? (Y/N): ");
//                     String isFavoriteStr = scanner.nextLine();
//                     boolean isFavorite = isFavoriteStr == "Y";
//                     scanner.nextLine(); // Consume newline
//                     System.out.print("Enter ownership status (owned, wishlisted): ");
//                     String ownershipStatus = scanner.nextLine();
//                     // WITH ALL THE DATA
//                     Game newGame = new Game(title, platform, genre, releaseYear, ownershipStatus);
//                     library.addGame(newGame);
//                     break;
//                 case 2:
//                     var filterStr = "";
//                     library.getAllGames();
//                     break;
//                 case 3:
//                     System.out.println("Exiting application. Goodbye!");
//                     break;
//                 case 77:
//                     // V2DO - SUB MENU TO GO FROM SINGLE GAME RESULT TO MENU
//                     System.out.println("Enter game id to edit ( -1 for cancel )");
//                     // VIEW -> EDIT -> CHOOSE KEY -> GET VALUE ->
//                     Game gameToEdit = Library.getGameByID(id);
//                     // GET KEY
//                     var key = "GAME";
//                     var value = "Bio Shock";
//                     // GET VALUE
//                     var success = gameToEdit.updateGameData(key, value);
//                     if (success) {
//                         perp.saveGamesToCsv();
//                     }
//                     break;
//                 case 33:
//                     System.out.print("Enter your filter: ");
//                     String filterString = scanner.nextLine();
//                     System.out.println(filter.runFilter(library.getAllGames(), filterString));
//                     break;
//                 case 12:
//                     System.out.print(reporter.generateReport(library.getAllGames()));
//                     break;
//                 default:
//                     System.out.println("Invalid choice. Please try again.");
//             }
//             // LOOP UNTIL USER CHOOSES EXIT
//         } while (choice != 3);
//         // CLOSE THE SCANNER
//         scanner.close();
//     }
// }
