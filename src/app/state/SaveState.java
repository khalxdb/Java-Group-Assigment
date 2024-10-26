package app.state;
import app.*;
import app.manager.*;
import app.util.*;
import java.io.IOException;

/**
 * SaveState allows the user to save the current state of the music library and playlists
 * to a specified file location.
 */
public class SaveState implements State {
    private MusicSimulator simulator;
    private SongManager songManager;
    private ConsoleManager console;

    // Constructor
    public SaveState(MusicSimulator simulator) {
        this.simulator = simulator;
        this.songManager = simulator.songManager;
        this.console = simulator.console;
    }

    /**
     * Displays the prompt to enter the file path or name for saving the data.
     */
    @Override
    public void display() {
        console.clearConsole();
        console.showMessage("Saving The Music Library\n");
        console.showMessage("Please type the location/name of the file to save:");
    }

    /**
     * Handles user input to specify the file location for saving. If the input is a valid
     * file name, it attempts to save the data to a CSV file. If 'q' or 'exit' is entered,
     * it returns to the main menu.
     * @param input The user input, either a file path for saving or a command to return to the main menu.
     */
    @Override
    public void handleInput(String input) {
        String filePath = input.trim();
    
        // Handle user request to go back
        if (filePath.equals("q") || filePath.equals("exit")) {
            simulator.clearStateStack();
            simulator.setState(new MainMenuState(simulator));
            return; // Exit without proceeding to save
        }
    
        try { // Catching Exceptions
            songManager.saveToCSV(filePath + ".csv");
            console.showMessage("Successfully saved to " + filePath + ".csv");
        } catch (IOException e) {
            console.showMessage("Error saving the file: " + e.getMessage());// Handle any I/O errors during file writing
        }
        console.waitForEnter();
        // Transition back to the main menu or stay in SaveState as needed
        simulator.setState(new MainMenuState(simulator));
    }
    
}
