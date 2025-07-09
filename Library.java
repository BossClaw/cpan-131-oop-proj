// JASMIE SANDERS

import java.util.ArrayList;
import java.util.List;

public class Library {

    // DEPENDENCIES
    private final DataPersistance _data;

    // GAMES LIST & GETTER
    private List<Game> games;

    public List<Game> GetGames() {
        return games;
    }

    // DEPENDANCY INJECTION
    public Library(DataPersistance data) {

        // ASSIGN THE DATA DEPENDANCY
        _data = data;

        // INIT GAMES
        this.games = new ArrayList<>();

        // CHECK FOR .CSV AND LOAD IF AVAIL
        if (_data.hasCsv()) {
            this.games = _data.loadGamesFromCsv();
        }
    }

    // ========================================================================
    // CREATE
    public void addGame(String title, String platform, String genre, int releaseYear, String ownership) {

        // V2DO - OWNERSHIP SHORTFORM (O)wned, (W)ishlisted
        // TODO - EVAL WHY CODE SUGGEST USES REVERSE AND EQUALS FOR STRING COMPARISON
        var ownershipFull = ownership;
        if ("O".equals(ownershipFull.toUpperCase())) {
            ownershipFull = "Owned";
        }
        if ("W".equals(ownershipFull.toUpperCase())) {
            ownershipFull = "Wishlisted";
        }

        // CREATE NEW GAME OBJ
        Game newGame = new Game(
                title,
                platform,
                genre,
                releaseYear,
                ownershipFull
        );

        // ADD NEWGAME TO LIBRARY LIST IN MEMORY
        this.games.add(newGame);

        // SAVE THE CHANGED LIBRARY LIST TO DISK
        _data.saveGamesToCsv();

        // OUTPUT TO CONSOLE
        System.out.println("Game \"" + newGame.getTitle() + "\" added to the library.");
    }

    // =================================================================
    // UPDATE GAME
    public void updateGame(Game updateGame) {
        // MILESTONE 2

        // FIND THE EXISTING GAME ID
        // UPDATE
        // SAVE TO DISK
        _data.saveGamesToCsv();

        // System.out.println("Game \"" + game.getTitle() + "\" updated the library.");
    }

    // =================================================================
    // DELETE
    public void deleteGame(int gameId) {
        // MILESTONE 2 

        // FIND THE EXISTING GAME ID
        // var game = games.find
        // DELETE
        // SAVE TO DISK
        _data.saveGamesToCsv();

        // System.out.println("Game \"" + game.getTitle() + "\" delete from library.");
    }    
}
