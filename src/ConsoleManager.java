import java.util.Scanner;

public class ConsoleManager {
    public Scanner scanner;

    // Constructor
    public ConsoleManager() {
        scanner = new Scanner(System.in);
    }

    // Method to clear the console
    public void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Method to print the welcome message and commands
    public void printWelcome() {
        clearConsole();
        System.out.println("\033[32m===============================");
        System.out.println("  Welcome to the Song Manager!");
        System.out.println("===============================\033[0m\n");

        System.out.println("Available Commands: \n");
        System.out.println("\033[34mshow\033[0m     - Show songs, playlists, or artists");
        System.out.println("\033[34mplay\033[0m     - Play a playlist or a specific song");
        System.out.println("\033[34mcreate\033[0m   - Create a new playlist");
        System.out.println("\033[34msave\033[0m     - Save the current output");
        System.out.println("\033[34msearch\033[0m   - Search for an artist");
        System.out.println("\033[34mexit\033[0m     - Exit the Song Manager\n");

        System.out.println("\033[33mHint: Use commands like 'play <song>' or 'show playlists' to interact.\033[0m\n");
        System.out.println("\033[1;32mEnjoy managing your music collection!\033[0m");
    }

    // Method to prompt and get user command input
    public String getCommandInput() {
        System.out.print("\n\033[35mCommands: \033[0m");
        return scanner.nextLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    // Method to prompt for input and return user's response
    public void getInput(){
        scanner.nextLine();
    }
    public String promptUser(String promptMessage) {
        System.out.println(promptMessage);
        return scanner.nextLine();
    }

    // Method to wait for user to press Enter
    public void waitForEnter() {
        System.out.println("\033[33mPress Enter to continue...\033[0m");
        scanner.nextLine();
    }

    // Method to close the scanner (when exiting the program)
    public void closeScanner() {
        scanner.close();
    }

    public boolean goBack(String command){
        if (command.equalsIgnoreCase("exit") || command.equalsIgnoreCase("q")) {
            clearConsole();
            showMessage("\033[31mExiting to the main menu...\033[0m");
            return true; 
        }
        return false; 

    }

}
