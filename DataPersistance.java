
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataPersistance {

    // DARRYL LECRAW
    private final String filePath = "video_games.csv";

    public boolean hasCsv() {
        File file = new File(filePath);
        return file.exists() && file.isFile();
    }

    public ArrayList<Game> loadGamesFromCsv() {
        // MAKE A FRESH ARRAY TO RETURN
        ArrayList<Game> return_games_array = new ArrayList<>();

        // ATTEMPT. USE TRY TO CATCH AND GRACEFULLY HANDLE ERRORS
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                // SKIP HEADER
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                // SPLIT BY COMMA CHAR
                String[] parts = line.split(",");

                // ONLY ACCEPT MATCHING
                // NOTE - WE COULD MAKE THIS 'SMART', BUT ONLY IF TIME ALLOWS
                if (parts.length == 5) {
                    // GET THE VALS, CASTING AS NEEDED
                    // id, title, price, isOwned, tags
                    String id = parts[0].trim();
                    String title = parts[1].trim();
                    int year = Integer.getInteger(parts[2].trim());
                    Double price = Double.valueOf(parts[3].trim());
                    Boolean isOwned = "Y".equals(parts[4].trim());
                    String[] tags = parts[5].trim().split(",");
                    var tag_list = new ArrayList<>(List.of(tags));

                    // CREATE THE GAME OBJ
                    Game game = new Game(id, title, year, price, isOwned, tag_list);

                    // ADD TO THE RETURN ARRAY
                    return_games_array.add(game);
                }
            }

        } catch (IOException | NumberFormatException e) {
            // TODO - HANDLE ERROR GRACEFULLY
            e.printStackTrace();
        }

        // RETURN THE ARRAY
        return return_games_array;
    }

    public boolean saveGamesToCsv(List<Game> games) {
        boolean saveSuccess = true;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            // WRITE HEADER
            bw.write("Id,Title,Year,isOwned,Tags,Price");
            bw.newLine();

            // LOOP THROUGH EACH GAME OBJ
            for (Game game : games) {

                // CREATE THE LINE WITH VALS
                String line = String.format("%s,%s,%d,%s,%s,%.2f",
                        game.getId(),
                        game.getTitle(),
                        game.getYear(),
                        game.getIsOwned() ? "Y" : "N",
                        // COLLAPSE TAGS TO STRING
                        String.join("|", game.getTags()),
                        // PRICE
                        game.getPrice());

                bw.write(line);
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
            saveSuccess = false;
        }

        return saveSuccess;
    }
}
