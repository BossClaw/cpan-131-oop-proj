// EVERYONE
public class Main {

    public static void main(String[] args) {
        // CREATE PROJ CLASSES FOR DEPENDENCY INJECTION
        DataPersistance dataPersistance = new DataPersistance();

        // CREATE LIBRARY INSTANCE WITH DEPENDENCY INJECTION
        Library library = new Library(dataPersistance);
        

        // CREATE MENU
        Menu menu = new Menu(library, filterLogic);

        // RUN IT
        menu.displayMenu();

    }
}
