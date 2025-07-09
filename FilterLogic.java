// KUO YU LU

import java.util.ArrayList;
import java.util.List;

public class FilterLogic {

    // DEPENDENCIES
    public Library library;

    // DEPENDENCY INJECTION
    public FilterLogic(Library library) {
        this.library = library;
    }

    public void listGamesByFilter(String string) {

        // CREATE FILTER ARRAY
        List<Game> filteredGames = new ArrayList<>();

        // EARLY CHECK FOR NO FITLER
        if (string == null || string.isEmpty()) {
            System.out.println("No filter criteria provided. Listing all games.");
            filteredGames.addAll(library.GetGames());
        } else {
            // PROCESS FILTER LOGIC
            for (Game game : library.GetGames()) {
                // TODO - ADD FILTER LOGIC HERE
                // EG: if (game.getGenre().equalsIgnoreCase(string)) {
                // EG: if (game.getTitle().contains(string)) {

                // FOR NOW, JUST ADD ALL GAMES
                filteredGames.add(game);
            }
        }

        // OUTPUT THE FILTERED GAMES
        System.out.println("\n--- Filtered Game Library ---");
        if (filteredGames.isEmpty()) {
            System.out.println("No games match the filter criteria.");
        } else {
            for (int i = 0; i < filteredGames.size(); i++) {
                System.out.println((i + 1) + ". " + filteredGames.get(i));
            }
        }
    }

    // public void retriveGames() {
    //     // RETURN EARLY
    //     if (games.isEmpty()) {
    //         System.out.println("Your game library is empty.");
    //         return;
    //     }
    //     // JASMINE
    //     // TAKE APART IN CRITERIA filterCriteria TO FILTER GAMES
    //     List<Game> filteredGames = new ArrayList<>();
    //     // var filteredGames;
    //     // filteredGames.addAll(games);
    //     // filterCLass.filterLIbrrary(games, "FJDFJDKFDFL");
    //     /// FILTER LOGIC
    //     /// FILTER LOGIC
    //     /// FILTER LOGIC
    //     /// FILTER LOGIC
    //     /// FILTER LOGIC
    //     /// FILTER LOGIC
    //     // PREPARE THE OUTPUT
    //     System.out.println("\n--- Your Game Library ---");
    //     for (int i = 0; i < filteredGames.size(); i++) {
    //         System.out.println((i + 1) + ". " + filteredGames.get(i));
    //     }
    //     System.out.println("-------------------------");
    // }
    // // =================================================================
    // // INDEXES
    // // EG: index["Action"].add(game)
    // // EG: index["1994"].add(game)
    // // COUNT FOR THE REPORT
    // // EG: index["1994"].count()
    // Map<String, List<Game>> indexes = new HashMap<>();
    // List<String> TagList = new HashMap<>();
    // public void rebuildIndexes() {
    //     // TBD - CHOOSE THE EASIEST THING TO IMPLEMENT
    //     // TBD - INDEXES VIA DICTIONARY OR PROPERTIES
    //     // PRE LOOP TO COLLECT THE DYNAMIC STUFF
    //     // CLEAR
    //     indexes.clear();
    //     // LOOP THROUGH GAMES AND BUILD TAGS
    //     for (Game game : games) {
    //         tagHashMap.add(game);
    //     }
    //     // LOOP THROUGH GAMES AND BUILD INDEXES
    //     for (Game game : games) {
    //         // TODO - CREATE A NEW KEY IF NONE EXIST
    //         if (!indexes.containsKey(game.getGenre())) {
    //             indexes[game.getGenre()] = new ArrayList<Game>();
    //         }
    //         // ADD TO EXISTING KEY
    //         // indexes[game.getGenre()].add(game); //
    //         // indexes[game.getPlatform()].add(game);
    //     }
    //     //
    // }
}
