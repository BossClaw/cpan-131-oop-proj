import java.util.List;

public class Game {
  private String id;
  private String title;
  private double price;
  private List<String> tags;
  private boolean isOwned;

  public Game(String id, String title, double price, List<String> tags, Boolean isOwned) {
    this.id = id;
    this.title = title;
    this.price = price;
    this.tags = tags;
    this.isOwned = (isOwned != null) ? isOwned : false;
  }

  public String getId() {
    return this.id;
  }

  public String getTitle() {
    return this.title;
  }

  public double getPrice() {
    return this.price;
  }

  public List<String> getTags() {
    return this.tags;
  }

  public Boolean getIsOwned() {
    return this.isOwned;
  }
}
