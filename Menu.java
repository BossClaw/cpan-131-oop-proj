// EVERYONE

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    // HANDLES ALL THE USER INPUT AND MENU LOGIC w/DEPENDENCY INJECTION CLASSES
    private final Library library;
    private final GameFilter filter;
    private final Report report;

    // USED BY MENU METHODS
    private Scanner scanner;

    // CONSTRUCTOR REQUIRING CLASS DEPENDENCIES
    public Menu(Library _library, GameFilter _filter, Report _report) {
        this.library = _library;
        this.filter = _filter;
        this.report = _report;
    }

    private final String[] exitMesgArr = {
            "Game On!",
            "Now You're Playing with power!",
            "Boop Boop Beep Beep!",
            "Kick Shell!",
            "Level Up!",
            "Hit it in the glowy bit!"
    };

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

            // SPACE
            System.out.println("");

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
                    // editGame();
                    break;
                case 4:
                    // MILESTONE 2
                    // deleteGame();
                    break;

                // GAME FILTER/REPORT
                case 10:
                    // LU - FILTER SUBMENU
                    filter.menu(scanner);
                    break;
                case 11:
                    // LU - REPORT SUBMENU MILESTONE 2
                    report.print(scanner);
                    break;

                // CSV IMPORT/EXPORT
                case 50:
                    // CLAW - IMPORT MILESTONE 2
                    // library.bulkImportGames();
                    break;
                case 51:
                    // CLAW - EXPORT MILESTONE 2
                    // library.bulkExportGames();
                    break;

                // SYSTEM
                case 77:
                    System.out.println("Exiting application.\n");

                    System.out.println(Color.green(utils.getRandomMessage(exitMesgArr)));

                    // TODO - RANDOM GOODBYE MESG
                    break;

                // MAKE AN ERROR
                case 999:
                    System.out.println("You chose to make error! This is not a valid option.");
                    System.out.println("Please choose a valid option from the menu.");
                    throw new UnsupportedOperationException(
                            "This is a placeholder for a future feature or error handling.");

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
        // System.out.println("INPUT ID[" + retVal + "]\n\n");
        return retVal;
    }

    private double readDouble(String mesg) {
        System.out.print(mesg);
        double retVal = scanner.nextDouble();
        scanner.nextLine(); // CONSUME NEWLINE AFTER ALL NUMBERS
        // System.out.println("INPUT DOUBLE[" + retVal + "]\n\n");
        return retVal;
    }

    private String readString(String mesg) {
        System.out.print(mesg);
        String retVal = scanner.nextLine();
        // System.out.println("INPUT STRING[" + retVal + "]\n\n");
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
        var tag_list = new ArrayList<>(List.of(tag_str.trim().split("\\,")));
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
                tag_list);

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

    public String[] getExitMesgArr() {
        return exitMesgArr;
    }
}
