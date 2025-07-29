// EVERYONE

public class Main {

    public static void main(String[] args) {
        // CREATE PROJ CLASSES FOR DEPENDENCY INJECTION
        DataPersistance dataPersistance = new DataPersistance();

        // CREATE LIBRARY INSTANCE WITH DEPENDENCY INJECTION
        Library library = new Library(dataPersistance);
        
        // Create a filter
        GameFilter filter = new GameFilter(library);
        
        // CREATE MENU
        Menu menu = new Menu(library, filter);

        // RUN IT
        Print.logo();
        menu.displayMenu();
    }
}
