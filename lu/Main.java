
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {


        // Create a liberary with all the games
        Library liberary = new Library(games);

        // Create a filter
        GameFilter filter = new GameFilter(liberary);
        filter.menu();
    }
}
