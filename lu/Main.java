import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {

    List<Game> games = new ArrayList<>();

    games.add(new Game("g01", "Zelda", 59.99, new ArrayList<>(List.of("Adventure", "RPG")), true));
    games.add(new Game("g02", "Mario Kart", 49.99, new ArrayList<>(List.of("Racing", "Multiplayer")), false));
    games
        .add(new Game("g03", "Minecraft", 29.99, new ArrayList<>(List.of("Sandbox", "Creative", "Multiplayer")), true));
    games.add(new Game("g04", "Elden Ring", 69.99, new ArrayList<>(List.of("Soulslike", "RPG", "Fantasy")), false));
    games.add(new Game("g05", "Animal Crossing", 59.99, new ArrayList<>(List.of("Simulation", "Relaxing")), true));
    games.add(new Game("g06", "Overwatch", 39.99, new ArrayList<>(List.of("Shooter", "Multiplayer")), false));
    games.add(new Game("g07", "The Witcher 3", 39.99, new ArrayList<>(List.of("RPG", "Fantasy", "Adventure")), true));
    games.add(new Game("g08", "Stardew Valley", 14.99, new ArrayList<>(List.of("Farming", "Indie", "Relaxing")), true));
    games.add(
        new Game("g09", "Call of Duty", 69.99, new ArrayList<>(List.of("Shooter", "Action", "Multiplayer")), false));
    games.add(new Game("g10", "Hollow Knight", 15.99, new ArrayList<>(List.of("Metroidvania", "Indie")), true));

    // Create a liberary with all the games
    Library liberary = new Library(games);

    // Create a filter
    GameFilter filter = new GameFilter(liberary);
    filter.menu();
  }
}
