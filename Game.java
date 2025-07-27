// CLARANCE LEUNG

import java.util.List;

public class Game {

    private int id;
    private double price;
    private String title;
    private int year;
    private int rating;
    private List<String> tags;
    private String platform;
    private boolean isOwned;
    private String completion;

    public Game(
            int id,
            int year,
            String title,
            String platform,
            double price,
            Boolean isOwned,
            String completion,
            int rating,
            List<String> tags
    ) {

        // System.out.println("[GAME] MAKING A NEW GAME WITH[" + id + "][" + title + "][" + year + "][" + isOwned + "][" + price + "][" + tags + "]");
        this.id = id;
        this.year = year;
        this.title = title;
        this.platform = platform;
        this.price = price;
        this.isOwned = (isOwned != null) ? isOwned : false;
        this.rating = rating;
        this.completion = completion;
        this.tags = tags;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public int getYear() {
        return this.year;
    }

    public String getPlatform() {
        return this.platform;
    }

    public String getCompletion() {
        return this.completion;
    }

    public double getPrice() {
        return this.price;
    }

    public int getRating() {
        return this.rating;
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

    // ===================================================================================
    // MILESTONE 2 
    public boolean updateGameData(String key, int Value) {
        // TODO - MILESTONE 2        
        return true;
    }

    public boolean updateGameData(String key, String Value) {
        // TODO - MILESTONE 2
        return true;
    }
}
