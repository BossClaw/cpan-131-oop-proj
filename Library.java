// JASMIE SANDERS

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
        if (_data.hasCsv()) {
            loadGamesFromDisc();
        }
    }

    // LU'S CODE
    public void removeById(String id) {
// LU
        boolean isRemoved = this.games.removeIf(game -> game.getId().equals(id));

        if (!isRemoved) {
            System.err.println("Cannot find game with id : " + id + "to remove.");
        }
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
    // CREATE
    // OVERRIDE!!!!
    public String addGame(String id, String title, int year, boolean owned, List<String> tags, Double price) {
        // CREATE NEW GAME OBJ AND ADD        
        Game newGame = new Game(
                id,
                title,
                year,
                owned,
                tags,
                price
        );

        return addGame(newGame);
    }

    public String addGame(Game newGame) {

        // TODO - TRY / CATCH TO ERROR GRACEFULLY
        // ADD NEWGAME TO LIBRARY LIST IN MEMORY
        this.games.add(newGame);

        // SAVE TO DISK USING INJECTED CLASS
        _data.saveGamesToCsv(this.games);

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
        _data.saveGamesToCsv(this.games);

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
        this.games = _data.loadGamesFromCsv();

        // DEBUG - DUMP LIST OF GAMES
        System.out.println("[LIBARY] LOADED GAMES (" + this.games.size() + ")");

        // ALL OF THEM
        //for (Game game : this.games) {
        //System.out.println("  " + game);
        //}        
    }

    public void save_games_to_disc() {
        _data.saveGamesToCsv(this.games);
    }

    // END OF CLASS
}
