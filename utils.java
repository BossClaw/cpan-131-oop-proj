// Darryl LeCraw

import java.util.Random;
import java.util.Scanner;

public class utils {

    private static final Random rand = new Random();

    public static String getRandomMessage(String[] messages) {
        if (messages == null || messages.length == 0) {
            return "";
        }
        return messages[rand.nextInt(messages.length)];
    }

    public static int readInt(Scanner scanner, String mesg) {
        System.out.print(mesg);
        int retVal = scanner.nextInt();
        scanner.nextLine();
        return retVal;
    }

    public static double readDouble(Scanner scanner, String mesg) {
        System.out.print(mesg);
        double retVal = scanner.nextDouble();
        scanner.nextLine(); // CONSUME NEWLINE AFTER ALL NUMBERS        
        return retVal;
    }

    public static String readString(Scanner scanner, String mesg) {
        System.out.print(mesg);
        String retVal = scanner.nextLine();
        return retVal;
    }

}
