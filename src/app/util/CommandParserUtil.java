package app.util;

/*
 * Class for parsing and validating the inputs
 */
public class CommandParserUtil {
    /**
     * Parses a sttring to retrieve the index and validates it
     * @param index the index that we are parsing
     * @param console console instance to print out message
     * @return Integer if it's a valid digit string 
     */
    public static Integer parseIndexCommand(String index, ConsoleManager console) {
        if (!isNumeric(index)) {
            console.showMessage("\033[31mInvalid command format. Use '<action> <number>'.\033[0m");
            console.waitForEnter();
            return null;
        }
        return Integer.parseInt(index);
    }

    /**
     * Checks if the string is numeric
     * @param str string to check
     * @return true if it is false otherwise
     */
    public static boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

    /**
     * Validates command structure of the form "'action' 'number'"
     * @param command the String that is our command or input
     * @param console the console instance to print msg out
     * @return true if it is in the valid format false other wise
     */
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
