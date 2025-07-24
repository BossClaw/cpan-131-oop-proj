// EVERYONE

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // CREATE PROJ CLASSES FOR DEPENDENCY INJECTION
        DataPersistance dataPersistance = new DataPersistance();

        // CREATE LIBRARY INSTANCE WITH DEPENDENCY INJECTION
        Library library = new Library(dataPersistance);
        FilterLogic filterLogic = new FilterLogic(library);

        // CREATE SCANNER FOR USER INPUT
        Scanner scanner = new Scanner(System.in);

        // CREATE MENU
        Menu menu = new Menu(scanner, library, filterLogic);

        // RUN IT
        menu.displayMenu();

        // DISPOSE SCANNER
        scanner.close();;
    }
}
