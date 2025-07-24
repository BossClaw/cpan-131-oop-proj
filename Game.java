// CLARANCE LEUNG

import java.util.List;

public class Game {

    private String id;
    private String title;
    private int year;
    private boolean isOwned;
    private List<String> tags;
    private double price;

    public Game(String id, String title, int year, Boolean isOwned, List<String> tags, double price) {

        // System.out.println("[GAME] MAKING A NEW GAME WITH[" + id + "][" + title + "][" + year + "][" + isOwned + "][" + price + "][" + tags + "]");
        this.id = id;
        this.title = title;
        this.year = year;
        this.isOwned = (isOwned != null) ? isOwned : false;
        this.tags = tags;
        this.price = price;
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public int getYear() {
        return this.year;
    }

    public double getPrice() {
        return this.price;
    }

    public Boolean getIsOwned() {
        return this.isOwned;
    }

    public List<String> getTags() {
        return this.tags;
    }

    // ===================================================================================
    // PROB USELESS
    @Override
    public String toString() {
        return "Title: " + title + ", Price: " + price + ", IsOwned: " + isOwned
                + ", Tags: " + tags.toString();
    }

    public boolean updateGameData(String key, int Value) {
        // TODO - MILESTONE 2        
        return true;
    }

    public boolean updateGameData(String key, String Value) {
        // TODO - MILESTONE 2
        return true;
    }
}
