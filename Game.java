// CLARANCE LEUNG

public class Game {

    private String title;
    private String platform;
    private String genre;
    private int releaseYear;
    private String ownershipStatus;

    public Game(String title, String platform, String genre, int releaseYear, String ownershipStatus) {
        this.title = title;
        this.platform = platform;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.ownershipStatus = ownershipStatus;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getPlatform() {
        return platform;
    }

    public String getGenre() {
        return genre;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getOwnershipStatus() {
        return ownershipStatus;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Platform: " + platform + ", Genre: " + genre
                + ", Release Year: " + releaseYear + ", Ownership: " + ownershipStatus;
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
