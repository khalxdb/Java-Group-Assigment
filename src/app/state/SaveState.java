package app.state;
import app.*;
import app.manager.*;
import app.util.*;

import java.io.IOException;

public class SaveState implements State {
    private MusicSimulator simulator;
    private SongManager songManager;
    private ConsoleManager console;

    public SaveState(MusicSimulator simulator) {
        this.simulator = simulator;
        this.songManager = simulator.songManager;
        this.console = simulator.getConsole();
    }

    @Override
    public void display() {
        console.clearConsole();
        console.showMessage("Please type the location/name of the file to save:");
    }

    @Override
    public void handleInput(String input) {
        String filePath = input.trim();
    
        // Handle user request to go back
        if (filePath.equals("q") || filePath.equals("exit")) {
            simulator.clearStateStack();
            simulator.setState(new MainMenuState(simulator));
            return; // Exit without proceeding to save
        }
    
        try {
            songManager.saveToCSV(filePath + ".csv");
            console.showMessage("Successfully saved to " + filePath + ".csv");
        } catch (IOException e) {
            // Handle any I/O errors during file writing
            console.showMessage("Error saving the file: " + e.getMessage());
        }
    
        console.waitForEnter();
    
        // Transition back to the main menu or stay in SaveState as needed
        simulator.setState(new MainMenuState(simulator));
    }
    
}
