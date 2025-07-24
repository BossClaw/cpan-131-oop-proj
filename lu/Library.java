import java.util.List;

class Library {
  private List<Game> games;

  public Library(List<Game> games) {
    this.games = games;
  }

  public void add(Game game) {
    this.games.add(game);
  }

  public void removeById(String id) {

    boolean isRemoved = this.games.removeIf(game -> game.getId().equals(id));

    if (!isRemoved) {
      System.err.println("Cannot find game with id : " + id + "to remove.");
    }
  }

  public List<Game> getAllGames() {
    return this.games;
  }

  public void printAllGames() {
    int count = 1;

    for (Game game : this.games) {
      System.out.printf("%-3d %s\n", count, game.getTitle());
      count++;
    }
  }

}