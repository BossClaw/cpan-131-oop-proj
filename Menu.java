// Everyone
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
        "Kick Some Shell!",
        "Level Up!",
        "Hit it in the glowy bit!",
        "Save the Kidnapped Royalty!",
        "Arise Tarnished One!",
        "WAKKA WAKKA WAKKA!",};

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
            System.out.println("");

            System.out.println("1. List All Games");
            System.out.println("2. Add New Game");
            System.out.println("3. Edit Game");
            System.out.println("4. Delete Game");
            System.out.println("");

            System.out.println("10. Filter");
            System.out.println("11. Report");
            System.out.println("");

            System.out.println("50. Bulk Export Games to CSV");
            System.out.println("51. Bulk Import Games from CSV");
            System.out.println("");

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
                    break;
                case 3:
                    library.editGame(scanner);
                    break;
                case 4:
                    library.deleteGame(scanner);
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

                // CSV EXPORT/IMPORT
                case 50:
                    library.bulkExportGames();
                    break;
                case 51:
                    library.bulkImportGames();
                    break;

                // SYSTEM
                case 77:
                    System.out.println("Exiting application.\n");

                    // RANDOM EXIT MESG
                    System.out.println(Color.green(utils.getRandomMessage(exitMesgArr)));
                    System.out.println("\n\n");

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

    // ADDS A NEW GAME TO THE LIBRARY BY PROMPTING THE USER FOR DETAILS.
    private void addNewGame() {

        // NOTE - WRAPPER FUNCTIONS LESS CODE, QUICKER TO WRITE, DEBUG, DON'T REPEAT YOURSELF, ETC...
        int id = utils.readInt(scanner, "ID (integer): ");
        String title = utils.readString(scanner, "Title: ");
        int year = utils.readInt(scanner, "Year (4 digit integer): ");
        String platform = utils.readString(scanner, "Platform: ");
        double price = utils.readDouble(scanner, "Price $: ");
        String owned_str = utils.readString(scanner, "Owned (Y/N): ");
        String completion = utils.readString(scanner, "Completion: ");
        int rating = utils.readInt(scanner, "Rating (0-100): ");
        String tag_str = utils.readString(scanner, "Enter comma separated tags: ");

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
