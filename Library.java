
import java.util.ArrayList;
import java.util.List;

public class Library {

    // PERSISTENT CLASS DEPENDENCY
    private final DataPersistance _data;

    // GAMES LIST & GETTER
    private List<Game> games;

    // DEPENDANCY INJECTION
    public Library(DataPersistance data) {

        // ASSIGN THE DATA DEPENDANCY
        _data = data;

        // INIT GAMES
        this.games = new ArrayList<>();

        // CHECK FOR .CSV AND LOAD IF AVAIL
        if (_data.hasJson()) {
            loadGamesFromDisc();
        }
    }

    // MILESTONE 2 - LU'S CODE WIP
    public void removeById(String id) {

        // boolean isRemoved = this.games.removeIf(game -> game.getId().equals(id));
        // if (!isRemoved) {
        //     System.err.println("Cannot find game with id : " + id + "to remove.");
        // }
    }

    public List<Game> GetGames() {
        // LU
        return games;
    }

    public List<Game> getAllGames() {
        // LU? 
        return this.games;
    }

    public void printAllGames() {
        // LU
        int count = 1;

        for (Game game : this.games) {
            System.out.printf("%-3d %s\n", count, game.getTitle());
            count++;
        }
    }

    // ========================================================================
    // EXPORT / IMPORT

    public void bulkExportGames() {
        // USE THE dataPersistenceClass EXPORT
        _data.exportToCSV(games);
    }

    public void bulkImportGames() {
        // USE THE dataPersistenceClass IMPORT
        games = _data.importFromCSV();
    }

    // ========================================================================
    // CREATE METHODS, PARAMS & CLONE VIA OVERRIDE
    public String addGame(
            int id,
            int year,
            String title,
            String platform,
            Double price,
            boolean isOwned,
            String completion,
            int rating,
            List<String> tags
    ) {
        // CREATE NEW GAME OBJ AND ADD        
        Game newGame = new Game(
                id,
                year,
                title,
                platform,
                price,
                isOwned,
                completion,
                rating,
                tags
        );

        return addGame(newGame);
    }

    public String addGame(Game newGame) {

        // TODO - TRY / CATCH TO ERROR GRACEFULLY
        // ADD NEWGAME TO LIBRARY LIST IN MEMORY
        this.games.add(newGame);

        // SAVE TO DISK USING INJECTED CLASS
        save_games_to_disc();

        // RETURN MESG
        return "Game \"" + newGame.getTitle() + "\" added to the library.";
    }

    // =================================================================
    // UPDATE GAME
    public void updateGame(Game updateGame) {
        // MILESTONE 2

        // FIND THE EXISTING GAME ID
        // UPDATE
        // SAVE TO DISK USING INJECTED CLASS
        save_games_to_disc();

        // System.out.println("Game \"" + game.getTitle() + "\" updated the library.");
    }

    // =================================================================
    // DELETE
    public void deleteGame(int gameId) {
        // MILESTONE 2 

        // FIND THE EXISTING GAME ID
        // var game = games.find
        // DELETE
        // SAVE TO DISK USING INJECTED CLASS
        save_games_to_disc();

        // System.out.println("Game \"" + game.getTitle() + "\" delete from library.");
    }

    // ================================================================================
    // LOADING / SAVING
    // DEDICATED FUNCS EASIER TO USE, INTELLISENSE, ALLOWS LOGIC CHANGE IN ONE PLACE
    public void loadGamesFromDisc() {
        this.games = _data.loadGamesFromJson();

        // DEBUG - DUMP LIST OF GAMES
        System.out.println("[LIBARY] LOADED GAMES (" + this.games.size() + ")");

        // ALL OF THEM
        //for (Game game : this.games) {
        //System.out.println("  " + game);
        //}        
    }

    public void save_games_to_disc() {
        _data.saveGamesToJson(this.games);
    }

    // END OF CLASS
}
