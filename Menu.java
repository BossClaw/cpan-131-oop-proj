// Everyone
import java.util.Scanner;

public class Menu {

    // HANDLES ALL THE USER INPUT AND MENU LOGIC w/DEPENDENCY INJECTION CLASSES
    private final Library library;
    private final GameFilter filter;
    private final Report report;

    // USED BY MENU METHODS
    private Scanner scanner;

    // CONSTRUCTOR REQUIRING OBJECT DEPENDENCIES
    public Menu(Library _library, GameFilter _filter, Report _report) {
        this.library = _library;
        this.filter = _filter;
        this.report = _report;
    }

    public void displayMenu() {

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
                // GAME CRUD  (Create Retrieve Update Delete)
                case 1:
                    // LIST GAMES
                    Print.gameList(library.getAllGames(), "All Games", true, "blue");
                    break;

                case 2:
                    library.addNewGame(scanner);
                    break;

                case 3:
                    library.editGame(scanner);
                    break;

                case 4:
                    library.deleteGame(scanner);
                    break;

                // GAME FILTER/REPORT
                case 10:
                    filter.menu(scanner);
                    break;
                case 11:
                    report.print(scanner);
                    break;

                // CSV EXPORT/IMPORT
                case 50:
                    library.bulkExportGames();
                    break;

                case 51:
                    library.bulkImportGames();
                    break;

                // QUIT
                case 77:
                    System.out.println("Exiting application.\n");

                    // RANDOM EXIT MESG
                    System.out.println(Color.green(utils.getRandomMessage(exitMesgArr)));
                    System.out.println("\n\n");
                    break;
                
                // DEFAULT
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 77);

        // END OF MENU PROCESSING
        // DISPOSE SCANNER
        scanner.close();
    }

    // ============================================================
    // RANDOM EXIT MESG 
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

    public String[] getExitMesgArr() {
        return exitMesgArr;
    }
}
