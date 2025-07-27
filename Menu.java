// EVERYONE

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    // HANDLES ALL THE USER INPUT AND MENU LOGIC
    // DEPENDENCY INJECTION CLASSES
    private final Library library;
    private final GameFilter filter;

    // USED BY MENU METHODS
    private Scanner scanner;

    // CONSTRUCTOR REQUIRING CLASS DEPENDENCIES
    public Menu(Library _library, GameFilter _filter) {
        this.library = _library;
        this.filter = _filter;
    }

    public void displayMenu() {

        // CLEAR SCREEN
        // clearScreen();
        // MAKE THE SCANNER                
        scanner = new Scanner(System.in);

        // DEFAULT CHOICE
        int choice;

        // MAIN MENU LOOP UNTIL USER CHOOSES TO EXIT
        do {

            // DISPLAY MENU OPTIONS
            Print.header("Video Game Library Menu", "yellow");

            System.out.println("1. List All Games");
            System.out.println("2. Add New Game");
            System.out.println("3. Edit Game (MILESTONE 2)");
            System.out.println("4. Delete Game (MILESTONE 2)");

            System.out.println("10. Filter");
            System.out.println("11. Report (MILESTONE 2)");

            System.out.println("50. Bulk Import Games from CSV (MILESTONE 2)");
            System.out.println("51. Bulk Export Games to CSV (MILESTONE 2)");

            System.out.println("77. Exit");

            // PROMPT USER FOR CHOICE AS INT
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            // CONSUME NEWLINE CHARACTER
            scanner.nextLine();

            // HANDLE USER CHOICE
            switch (choice) {
                // GAME CUD
                case 1:
                    // LIST GAMES
                    Print.gameList(library.getAllGames(), "All Games", true, "blue");
                    break;
                case 2:
                    addNewGame();
                    // MILESTONE 2 editGameShorthand();
                    break;
                case 3:
                    // MILESTONE 2
                    //editGame();
                    break;
                case 4:
                    // MILESTONE 2
                    //deleteGame();
                    break;

                // GAME FILTER/REPORT
                case 10:
                    // LU - FILTER SUBMENU
                    filter.menu(scanner);
                    break;
                case 11:
                    // LU - REPORT SUBMENU MILESTONE 2
                    // report.menu(scanner);                    
                    break;

                // CSV IMPORT/EXPORT
                case 50:
                    // CLAW - IMPORT MILESTONE 2
                    //library.bulkImportGames();
                    break;
                case 51:
                    // CLAW - EXPORT MILESTONE 2
                    //library.bulkExportGames();
                    break;

                // SYSTEM
                case 77:
                    System.out.println("Exiting application. Game on!\n\n");
                    // TODO - RANDOM GOODBYE MESG
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

    private int readInt(String mesg) {
        System.out.print(mesg);
        int retVal = scanner.nextInt();
        scanner.nextLine(); // CONSUME NEWLINE AFTER ALL INTS
        System.out.println("INPUT ID[" + retVal + "]\n\n");
        return retVal;
    }

    private double readDouble(String mesg) {
        System.out.print(mesg);
        double retVal = scanner.nextDouble();
        scanner.nextLine(); // CONSUME NEWLINE AFTER ALL NUMBERS
        System.out.println("INPUT DOUBLE[" + retVal + "]\n\n");
        return retVal;
    }

    private String readString(String mesg) {
        System.out.print(mesg);
        String retVal = scanner.nextLine();
        System.out.println("INPUT STRING[" + retVal + "]\n\n");
        return retVal;
    }

    /**
     * Adds a new game to the library by prompting the user for details.
     */
    private void addNewGame() {

        // WIP - WRAPPER FUNCTIONS EG: id = readInt("Enter Game Id")
        // LESS CODE, QUICKER TO WRITE, DEBUG, DON'T REPEAT YOURSELF, ETC...
        int id = readInt("Enter ID (integer):");
        String title = readString("Enter game title: ");
        int year = readInt("Enter YEAR (4 digit integer):");
        String platform = readString("Enter Platform: ");
        double price = readDouble("Price $: ");
        String owned_str = readString("Owned (Y/N): ");
        String completion = readString("Completion: ");
        int rating = readInt("Rating (0-100): ");
        String tag_str = readString("Enter comma separated tags: ");

        // PROCESS THE STR VALS, TAG_STR INTO LIST, OWNED_STR INTO BOOLEAN
        var tag_list = new ArrayList<>(List.of(tag_str.trim().split("\\|")));
        var isOwned = "Y".equals(owned_str);

        // PASS THE INPUT VARS TO LIBRARY ADDGAME METHOD
        String addResult = library.addGame(
                id,
                year,
                title,
                platform,
                price,
                isOwned,
                completion,
                rating,
                tag_list
        );

        // PRINT RESULT
        System.out.println(addResult);
    }

    /**
     * Bulk imports games from a CSV file.
     */
    private void bulkImportGames() {
        // MILESTONE 2
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
                // library.addGame(shTitle, shPlatform, shGenre, shReleaseYear, shOwnershipStatus);
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
