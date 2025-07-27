
import java.util.Random;

public class utils {

    private static final Random rand = new Random();

    public static String getRandomMessage(String[] messages) {
        if (messages == null || messages.length == 0) {
            return "";
        }
        return messages[rand.nextInt(messages.length)];
    }
}
