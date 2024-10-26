package app.util;

public class CommandParserUtil {
    // Parses command to retrieve the index and validates it
    public static Integer parseIndexCommand(String index, ConsoleManager console) {
        if (!isNumeric(index)) {
            console.showMessage("\033[31mInvalid command format. Use '<action> <number>'.\033[0m");
            console.waitForEnter();
            return null;
        }
        return Integer.parseInt(index);
    }

    // Checks if the string is numeric
    public static boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

    // Validates command structure of the form "<action> <number>"
    public static boolean validCommand(String command, ConsoleManager console) {
        String[] parts = command.split(" ");
        if (parts.length != 2 || !isNumeric(parts[1])) {
            console.showMessage("\033[31mInvalid command format. Use 'show <number>' or 'play <number>'.\033[0m");
            console.waitForEnter();
            return false;
        }
        return true;
    }
}
