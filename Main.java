// Everyone

public class Main {

    public static void main(String[] args) {
        // CREATE PROJ CLASSES FOR DEPENDENCY INJECTION
        DataPersistance dataPersistance = new DataPersistance();

        // CREATE LIBRARY INSTANCE WITH DEPENDENCY INJECTION
        Library library = new Library(dataPersistance);

        // Create a filter
        GameFilter filter = new GameFilter(library);

        // Create a report
        Report report = new Report(library);

        // CREATE MENU
        Menu menu = new Menu(library, filter, report);

        // RUN IT
        Print.logo();
        menu.displayMenu();
    }
}