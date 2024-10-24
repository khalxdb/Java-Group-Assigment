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
        console.showMessage("Commands: Remove (r <number>), Create (c <name>), Show (s), Play (p), or Exit (q)");
    }

    @Override
    public void handleInput(String input) {
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
                handleCreate(parts);
                break;

            case "s":
            case "show":
                showPlaylistPage();
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

    public void handleRemove(String[] parts) {
        if (parts.length != 2) {
            console.showMessage("\033[31mInvalid command format. Use 'r <number>'.\033[0m");
            return;
        }

        String indexStr = parts[1];
        if (!isNumeric(indexStr)) {
            console.showMessage("\033[31mInvalid input. Please enter a valid number.\033[0m");
            return;
        }

        int idx = Integer.parseInt(indexStr);
        if (idx < 0 || idx >= songManager.playlistManager.listOfPlayLists.size()) {
            console.showMessage("\033[31mInvalid index. Please try again.\033[0m");
            return;
        }

        Playlist removePlaylist = songManager.playlistManager.listOfPlayLists.get(idx);
        songManager.removePlaylist(removePlaylist);
        console.showMessage("Playlist '" + removePlaylist.getName() + "' has been removed.");
        console.waitForEnter();
    }

    public void handleCreate(String[] parts) {
        if (parts.length != 2) {
            console.showMessage("\033[31mInvalid command format. Use 'c <playlistName>'.\033[0m");
            return;
        }

        String playlistName = parts[1];
        songManager.createPlayList(playlistName);
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
