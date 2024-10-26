package app.state;

import app.*;
import app.manager.*;
import app.util.*;


public class SearchState implements State {
    public MusicSimulator simulator;
    public SongManager songManager;
    public ConsoleManager console;

    public SearchState(MusicSimulator simulator) {
        this.simulator = simulator;
        this.songManager = simulator.songManager;
        this.console = simulator.console;
    }

    @Override
    public void display() {
        console.clearConsole();
        console.displayArtist(songManager.songLibrary.getArtists());
        console.showMessage("Please enter a number to select an artist, or 'q' to go back.");
    }

    @Override
    public void handleInput(String input) {
        String indexStr = input.trim().toLowerCase();

        // Handle user commands to exit or go back
        if (indexStr.equals("q")) {
            simulator.goBack();
            return;
        } else if (indexStr.equals("exit")) {
            simulator.clearStateStack();
            simulator.setState(new MainMenuState(simulator));
            return;
        }

        // Check if input is numeric
        if (!isNumeric(indexStr)) {
            console.showMessage("Invalid input. Please enter a valid number or 'q' to go back.");
            console.waitForEnter();
            display();
            return;
        }

        // Parse the index and get the artist
        int idx = Integer.parseInt(indexStr);

        // Validate index within the artist list bounds
        if (idx < 0 || idx >= songManager.songLibrary.listOfArtist.size()) {
            console.showMessage("Invalid index. Please try again.");
            console.waitForEnter();
            display();
            return;
        }

        String artist = songManager.songLibrary.listOfArtist.get(idx);

        // Find all songs by the artist and display them
        CircularDoublyLinkedList artistSongs = songManager.songLibrary.findSongByArtist(artist);
        console.displayQueue(artistSongs, artistSongs.head);
        console.showMessage("Type 'play' to play the queue, or 'q' to go back, or keep on adding more songs");

        // Handle subcommand input
        String subCommand = console.getCommandInput().trim().toLowerCase();

        if (subCommand.equals("q")) {
            simulator.goBack();
            return;
        } else if (subCommand.equals("play") || subCommand.equals("p")) {
            // Set the current queue to the list of artist and play them in the play queue states.
            songManager.player.setPlaylist(artistSongs);
            simulator.setState(new PlayQueueState(simulator, null));
        } else {
            console.showMessage("Invalid input. Please try again.");
            console.waitForEnter();
        } 
        display();  // Redisplay the options after subcommand
    }

    // Helper function to check if a string is numeric
    public boolean isNumeric(String str) {
        return str.matches("\\d+");
    }
}
