package app.state;
import java.util.ArrayList;
import app.*;
import app.manager.*;
import app.util.*;
import app.model.*;

public class CreateState implements State {
    public MusicSimulator simulator;
    public SongManager songManager;
    public ConsoleManager console;

    public CreateState(MusicSimulator simulator) {
        this.simulator = simulator;
        this.songManager = simulator.songManager;
        this.console = simulator.getConsole();
    }

    @Override
    public void display() {
        console.clearConsole();
        ArrayList<Playlist> playlists = songManager.getPlaylists();
        console.displayPlaylistList(playlists);
        console.showMessage("Commands: Remove (r <number>), Create (c \"<name>\", use \"\"), Show (s), Play (p), or Exit (q)");
    }

    @Override
    public void handleInput(String input) {
        // Take in 2 inputs, one for command like create and remove
        String[] parts = input.split(" ");
        
        if (parts.length < 1) {
            console.showMessage("\033[31mInvalid command. Please enter a valid command.\033[0m");
            return;
        }

        String action = parts[0].toLowerCase(); // Normalize the command for comparison

        switch (action) {
            case "r":
            case "remove":
                handleRemove(parts);
                break;

            case "c":
            case "create":
                handleCreate(input);
                break;

            case "s":
            case "show":
                // Handle Selecting Songs 
                PlaylistActionHelper.handlePlaylistAction(input, simulator);
                break;

            case "p":
            case "play":
                simulator.setState(new PlayQueueState(simulator, null)); // Transition to play state
                break;

            case "q":
            case "exit":
                simulator.goBack(); // Go back to the previous state
                break;

            default:
                console.showMessage("\033[31mUnknown command. Please try again.\033[0m");
        }
    }

    // Function for removing playlists 
    public void handleRemove(String[] parts) {
        if (parts.length != 2) {
            console.showMessage("\033[31mInvalid command format. Use 'r <number>'.\033[0m");
            console.waitForEnter();
            return;
        }

        String indexStr = parts[1];
        if (!isNumeric(indexStr)) {
            console.showMessage("\033[31mInvalid input. Please enter a valid number.\033[0m");
            console.waitForEnter();
            return;
        }

        int idx = Integer.parseInt(indexStr);
        if (idx < 0 || idx >= songManager.playlistLibrary.listOfPlaylists.size()) {
            console.showMessage("\033[31mInvalid index. Please try again.\033[0m");
            console.waitForEnter();
            return;
        }

        Playlist removePlaylist = songManager.getPlaylistAtIndex(idx);
        songManager.removePlaylist(removePlaylist);
        console.waitForEnter();
    }

    // Function for handling creating playlists
    public void handleCreate(String input) {
        /*
        * Use regular expression to check for both 'c' or 'create', followed by space(s),
        * and then the playlist name inside double quotes.
        * It also makes the check case-insensitive.
        */
        if (!input.toLowerCase().matches("(c|create)\\s+\"[^\"]+\"")) {
            console.showMessage("\033[31mInvalid command format. Use 'c \"<playlistName>\"' or 'create \"<playlistName>\"'.\033[0m");
            console.waitForEnter();
            return;
        }

        // Extract the playlist name from the quotes
        int firstQuoteIndex = input.indexOf("\"");
        int lastQuoteIndex = input.lastIndexOf("\"");

        // Get the playlist name between the quotes
        String playlistName = input.substring(firstQuoteIndex + 1, lastQuoteIndex).trim();

        // Create the playlist with the full name
        songManager.createPlayList(playlistName);
        // console.showMessage("Playlist '" + playlistName + "' created successfully!");
        console.waitForEnter();
    }

    public void showPlaylistPage() {
        console.clearConsole();
        songManager.showPlayList();
        console.waitForEnter();
    }

    // Helper method to check if a string is numeric
    public boolean isNumeric(String str) {
        return str.matches("\\d+");
    }
}
