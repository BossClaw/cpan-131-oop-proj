// Clarance Leung

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

    // GET BUT NOT SET
    public int getId() {
        return this.id;
    }

    // GET/SET
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String newVal) {
        this.title = newVal;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int newVal) {
        this.year = newVal;
    }

    public String getPlatform() {
        return this.platform;
    }

    public void setPlatform(String newVal) {
        this.platform = newVal;
    }

    public String getCompletion() {
        return this.completion;
    }

    public void setCompletion(String newVal) {
        this.completion = newVal;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double newVal) {
        this.price = newVal;
    }

    public int getRating() {
        return this.rating;
    }

    public void setRating(int newVal) {
        this.rating = newVal;
    }

    public Boolean getIsOwned() {
        return this.isOwned;
    }

    public void setIsOwned(boolean newVal) {
        this.isOwned = newVal;
    }

    public List<String> getTags() {
        return this.tags;
    }

    public void setTags(List<String> newVal) {
        this.tags = newVal;
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
