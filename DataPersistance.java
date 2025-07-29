
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class DataPersistance {

    private final String csvFilePath = "video_games.csv";
    private final String jsonFilePath = "video_games.json";
    private final Gson gson = new Gson();

    public boolean hasJson() {
        File file = new File(jsonFilePath);
        return file.exists() && file.isFile();
    }

    public ArrayList<Game> loadGamesFromJson() {
        // MAKE THE RETURN ARRAY
        ArrayList<Game> ret_games_arr_list = new ArrayList<>();

        // LOOP AROUND THE READER
        try (Reader reader = new FileReader(jsonFilePath)) {
            ret_games_arr_list = gson.fromJson(reader, new TypeToken<ArrayList<Game>>() {
            }.getType());
        } catch (IOException e) {
            System.err.println("Error reading JSON: " + e.getMessage());
        }

        // DEBUG OUTPUT ON LOAD
        // for (Game game : ret_games_arr_list) {
        //     System.out.println("[PERSIST]   + LOADED [" + game.getId() + "][" + game.getYear() + "][" + game.getTitle() + "]");
        // }
        return ret_games_arr_list;
    }

    public void saveGamesToJson(List<Game> games) {
        try (Writer writer = new FileWriter(jsonFilePath)) {
            gson.toJson(games, writer);
        } catch (IOException e) {
            System.err.println("Error writing JSON: " + e.getMessage());
        }
    }

    public ArrayList<Game> importFromCSV() {
        ArrayList<Game> games = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] parts = line.split(",", -1);
                if (parts.length >= 8) {
                    int id = Integer.parseInt(parts[0]);
                    String title = parts[1].replaceAll("^\"|\"$", "");
                    int year = Integer.parseInt(parts[2]);
                    double rating = Double.parseDouble(parts[3]);
                    List<String> tags = Arrays.asList(parts[4].replaceAll("^\"|\"$", "").split(";"));
                    String recommended = parts[5].replaceAll("\"", "");
                    String platform = parts[6].replaceAll("\"", "");
                    String completion = parts[7].replaceAll("\"", "");

                    //games.add(new Game(id, title, year, rating, tags, recommended, platform, completion));
                    
                }
            }
        } catch (IOException e) {
            System.err.println("Error importing CSV: " + e.getMessage());
        }
        return games;
    }

    public void exportToCSV(List<Game> games) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(csvFilePath))) {
            pw.println("ID,Title,ReleaseYear,Rating,Tags,Recommended,Platform,Completion");

            for (Game g : games) {
                String tags = String.join(";", g.getTags());
                pw.printf("%d,\"%s\",%d,%.1f,\"%s\",\"%s\",\"%s\",\"%s\"%n",
                        g.getId(), g.getTitle(), g.getYear(), g.getRating(),
                        tags, g.getRating(), g.getPlatform(), g.getCompletion());
            }
        } catch (IOException e) {
            System.err.println("Error exporting CSV: " + e.getMessage());
        }
    }
}
