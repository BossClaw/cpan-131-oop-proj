// Jasmine Sanders

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {

    // GAMES LIST & GETTER
    private List<Game> games;

    // PERSISTENT CLASS DEPENDENCY
    private final DataPersistance _data;

    // DEPENDANCY INJECTION
    public Library(DataPersistance data) {

        // ASSIGN THE DATA DEPENDANCY
        _data = data;

        // INIT GAMES
        this.games = new ArrayList<>();

        // CHECK FOR .CSV AND LOAD IF AVAIL
        if (_data.hasJson()) {
            loadGames();
        }
    }

    // ======================================================
    // DATA RETURN METHODS
    public List<Game> getAllGames() {
        return this.games;
    }

    // ========================================================================
    // ADD GAME 
    public String addNewGame(Scanner scanner) {

        // NOTE - WRAPPER FUNCTIONS LESS CODE, QUICKER TO WRITE, DEBUG, DON'T REPEAT YOURSELF, ETC...
        int id = utils.readInt(scanner, "ID (integer): ");
        String title = utils.readString(scanner, "Title: ");
        int year = utils.readInt(scanner, "Year (4 digit integer): ");
        String platform = utils.readString(scanner, "Platform: ");
        double price = utils.readDouble(scanner, "Price $: ");
        String owned_str = utils.readString(scanner, "Owned (Y/N): ");
        String completion = utils.readString(scanner, "Completion: ");
        int rating = utils.readInt(scanner, "Rating (0-100): ");
        String tag_str = utils.readString(scanner, "Enter comma separated tags: ");

        // PROCESS THE STR VALS, TAG_STR INTO LIST, OWNED_STR INTO BOOLEAN
        var tag_list = new ArrayList<>(List.of(tag_str.trim().split("\\,")));
        var isOwned = "Y".equals(owned_str);

        // CREATE NEW GAME OBJ
        Game newGame = new Game(
                id,
                year,
                title,
                platform,
                price,
                isOwned,
                completion,
                rating,
                tag_list
        );

        // ADD THE NEW GAME
        games.add(newGame);

        // SAVE GAMES TO PERSIST NEW GAME
        saveGames();

        // RETURN THE RESULT
        return " + ADDED GAME[" + newGame.getTitle() + "]";
    }

    // =================================================================
    // UPDATE GAME
    public void editGame(Scanner scanner) {
        // INPUT ID FROM USER
        var getId = true;
        var gameId = -1;

        while (getId) {
            System.out.print("Enter the ID of the game to Edit: ");

            try {
                gameId = Integer.parseInt(scanner.nextLine().trim());
                getId = false;
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID format. Please enter a valid number.");
            }
        }

        // TRY GET GAME BY MATCHING ID
        Game gameToEdit = null;
        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getId() == gameId) {
                gameToEdit = games.get(i);
                break;
            }
        }

        // INSTRUCTIONS
        System.out.println("\nEnter the new values of the Game: ");
        System.out.println("Press enter to keep existing String values.\n ");

        // NOTE - WRAPPER FUNCTIONS LESS CODE, QUICKER TO WRITE, DEBUG, DON'T REPEAT YOURSELF, ETC...        
        var newTitle = utils.readString(scanner, "Title (" + gameToEdit.getTitle() + "): ");
        var newYear = utils.readInt(scanner, "Year (" + gameToEdit.getYear() + "): ");
        var newPlatform = utils.readString(scanner, "Platform (" + gameToEdit.getPlatform() + "): ");
        var newPrice = utils.readDouble(scanner, "Price (" + gameToEdit.getPrice() + "): ");
        var newIsOwned = utils.readString(scanner, "Owned (" + (gameToEdit.getIsOwned() ? "Y" : "N") + "): ");
        var newCompletion = utils.readString(scanner, "Completion (" + gameToEdit.getCompletion() + "): ");
        var newRating = utils.readInt(scanner, "Rating (" + gameToEdit.getRating() + "): ");

        // PROCESS THE STR VALS, TAG_STR INTO LIST, OWNED_STR INTO BOOLEAN
        String newTagStr = utils.readString(scanner, "Comma separated tags " + gameToEdit.getTags() + ": ");

        // HANDLE IF BLANK
        if (!"".equals(newPlatform)) {
            gameToEdit.setPlatform(newPlatform);
        }
        if (newYear != 0) {
            gameToEdit.setYear(newYear);
        }
        if (!"".equals(newTitle)) {
            gameToEdit.setTitle(newTitle);
        }
        if (newPrice != 0.00) {
            gameToEdit.setPrice(newPrice);
        }
        if (!"".equals(newIsOwned)) {
            gameToEdit.setIsOwned("Y".equals(newIsOwned));
        }
        if (!"".equals(newCompletion)) {
            gameToEdit.setCompletion(newCompletion);
        }
        if (newRating > 0) {
            gameToEdit.setRating(newRating);
        }
        if (!"".equals(newTagStr)) {
            var tagList = new ArrayList<>(List.of(newTagStr.trim().split("\\,")));
            gameToEdit.setTags(tagList);
        }

        // SAVE TO DISK USING INJECTED CLASS
        saveGames();
    }

    // =================================================================
    // DELETE
    public void deleteGame(Scanner scanner) {

        // INPUT ID FROM USER
        var getId = true;
        int gameId = -1;

        do {
            System.out.print("Enter the ID of the game to delete: ");

            try {
                gameId = Integer.parseInt(scanner.nextLine().trim());
                getId = false;
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID format. Please enter a valid number.");
            }
        } while (getId);

        // TRY GET GAME 
        Game gameToDelete = null;
        int gameIndex = -1;

        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getId() == gameId) {
                gameToDelete = games.get(i);
                gameIndex = i;
                break;
            }
        }

        // DELETE IF FOUND        
        if (gameToDelete != null) {
            // Show game details before deletion for confirmation
            System.out.println("Found game: " + gameToDelete.getTitle() + " (" + gameToDelete.getYear() + ")");
            System.out.print("Are you sure you want to delete this game? (y/n): ");

            String confirmation = scanner.nextLine().trim().toLowerCase();

            if (confirmation.equals("y") || confirmation.equals("yes")) {
                games.remove(gameIndex);
                System.out.println("Game with ID " + gameId + " has been successfully deleted.");
            } else {
                System.out.println("Deletion cancelled.");
                // DON'T SAVE IF DELETION WAS CANCELLED
                return;
            }
        } else {
            // MESG IF NOT FOUND
            System.out.println("No game found with ID: " + gameId);
            // DON'T SAVE IF NO GAME WAS FOUND & DELETED
            return;
        }

        // SAVE TO DISK USING INJECTED CLASS
        saveGames();
    }

    // ================================================================================
    // LOADING / SAVING
    // DEDICATED FUNCS EASIER TO USE, INTELLISENSE, ALLOWS LOGIC CHANGE IN ONE PLACE
    public void loadGames() {
        // LOAD
        this.games = _data.loadGamesFromJson();
    }

    public void saveGames() {
        _data.saveGamesToJson(this.games);
    }

    // END OF CLASS
    // ========================================================================
    // EXPORT / IMPORT
    public void bulkExportGames() {
        // USE THE dataPersistence EXPORT
        _data.exportToCSV(games);
    }

    public void bulkImportGames() {
        // USE THE dataPersistence IMPORT
        games = _data.importFromCSV();
        _data.saveGamesToJson(games);
    }
}
