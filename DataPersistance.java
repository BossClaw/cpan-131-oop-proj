// Darryl LeCraw

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class DataPersistance {

    private final String jsonFilePath = "video_games.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

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

    // ====================================================================================================
    // IMPORT / EXPORT
    private final String csvFilePath = "video_games.csv";

    public List<Game> importFromCSV() {
        // READS GAMES FROM CSV AT csvFilePath
        // ADDS NEW GAMES or REPLACES BASED ON ID

        List<Game> importedGames = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                // SKIP HEADER LINE
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                // PARSE CSV LINE
                String[] values = parseCSVLine(line);

                // Ensure we have all required fields
                if (values.length >= 9) {
                    try {
                        int id = Integer.parseInt(values[0].trim());
                        String platform = values[1].trim();
                        int year = Integer.parseInt(values[2].trim());
                        String title = values[3].trim();
                        double price = Double.parseDouble(values[4].trim());
                        boolean isOwned = Boolean.parseBoolean(values[5].trim());
                        String completion = values[6].trim();
                        int rating = Integer.parseInt(values[7].trim());

                        // PARSE TAGS (ASSUMING THEY'RE SEMICOLON-SEPARATED)
                        List<String> tags = new ArrayList<>();
                        if (!values[5].trim().isEmpty()) {
                            String[] tagArray = values[8].trim().split(";");
                            tags = Arrays.asList(tagArray);
                        }

                        // CREATE NEW GAME
                        Game newGame = new Game(id, year, title, platform, price, isOwned, completion, rating, tags);

                        // ADD TO LIST
                        importedGames.add(newGame);
                        System.out.println("[PERSIST] ADDED new game with ID: " + id);

                    } catch (NumberFormatException e) {
                        System.err.println("[PERSIST] ERROR PARSING LINE(" + line + ") " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }

        // RETURN
        return importedGames;
    }

    public void exportToCSV(List<Game> games) {
        // MILESTONE 2 - EXPORT GAMES TO CSV AT csvFilePath

        try (PrintWriter writer = new PrintWriter(new FileWriter(csvFilePath))) {
            // WRITE CSV HEADER            
            writer.println("id,platform,year,title,price,isOwned,completion,rating,tags");

            // Write each game as a CSV row
            for (Game game : games) {
                StringBuilder csvLine = new StringBuilder();

                // Add each field, handling commas and quotes properly
                csvLine.append(game.getId()).append(",");
                csvLine.append(escapeCSVField(game.getPlatform())).append(",");
                csvLine.append(game.getYear()).append(",");
                csvLine.append(escapeCSVField(game.getTitle())).append(",");
                csvLine.append(game.getPrice()).append(",");
                csvLine.append(game.getIsOwned()).append(",");
                csvLine.append(escapeCSVField(game.getCompletion())).append(",");
                csvLine.append(game.getRating()).append(",");

                // HANDLE TAGS LIST (JOIN WITH SEMICOLONS TO AVOID COMMA CONFLICTS)
                String tagsString = "";
                if (game.getTags() != null && !game.getTags().isEmpty()) {
                    tagsString = String.join(";", game.getTags());
                }
                csvLine.append(escapeCSVField(tagsString));

                // WRITE THE WHOLE LINE
                writer.println(csvLine.toString());
            }

            System.out.println("[PERSIST] Exported " + games.size() + " games to CSV successfully");

        } catch (IOException e) {
            System.err.println("Error writing CSV file: " + e.getMessage());
        }
    }

    // HELPER METHOD TO PROPERLY ESCAPE CSV FIELDS THAT CONTAIN COMMAS, QUOTES, OR NEWLINES
    private String escapeCSVField(String field) {
        if (field == null) {
            return "";
        }

        // IF FIELD CONTAINS COMMA, QUOTE, OR NEWLINE, WRAP IN QUOTES AND ESCAPE INTERNAL QUOTES
        if (field.contains(",") || field.contains("\"") || field.contains("\n")) {
            return "\"" + field.replace("\"", "\"\"") + "\"";
        }

        return field;
    }

    // HELPER METHOD TO PARSE CSV LINE HANDLING QUOTED FIELDS PROPERLY
    private String[] parseCSVLine(String line) {
        List<String> fields = new ArrayList<>();
        StringBuilder currentField = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '"') {
                if (inQuotes && i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    // Escaped quote
                    currentField.append('"');
                    i++; // Skip next quote
                } else {
                    // Toggle quote state
                    inQuotes = !inQuotes;
                }
            } else if (c == ',' && !inQuotes) {
                // Field separator
                fields.add(currentField.toString());
                currentField = new StringBuilder();
            } else {
                currentField.append(c);
            }
        }

        // Add the last field
        fields.add(currentField.toString());

        return fields.toArray(new String[0]);
    }
}
