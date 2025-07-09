// EVERYONE

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // SETUP SCANNER

        // CREATE PROJ CLASSES FOR DEPENDENCY INJECTION
        DataPersistance dataPersistance = new DataPersistance();

        // CREATE LIBRARY INSTANCE WITH DEPENDENCY INJECTION
        Library library = new Library(dataPersistance);
        FilterLogic filterLogic = new FilterLogic(library);

        // CREATE MENU AND RUN
        try (
                // CREATE SCANNER FOR USER INPUT
                Scanner scanner = new Scanner(System.in)) {

            // CREATE MENU AND RUN
            Menu menu = new Menu(scanner, library, filterLogic);
            menu.displayMenu();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {

            System.out.println("Thank you for using the library system!");
        }
    }
}
