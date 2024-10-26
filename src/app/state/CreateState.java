package app.state;
import java.util.ArrayList;
import app.*;
import app.manager.*;
import app.util.*;
import app.model.*;

/**
 * The CreateState class handles user interactions for creating and removing playlists,
 * as well as navigating to playlist view or play states.
 */
public class CreateState implements State {
    public MusicSimulator simulator;
    public SongManager songManager;
    public ConsoleManager console;

    // Constructor
    public CreateState(MusicSimulator simulator) {
        this.simulator = simulator;
        this.songManager = simulator.songManager;
        this.console = simulator.console;
    }

    /**
     * Displays the list of available playlists along with command options for creating,
     * removing, viewing, or playing playlists.
     */
    @Override
    public void display() {
        console.clearConsole();
        ArrayList<Playlist> playlists = songManager.getPlaylists();
        console.displayPlaylistList(playlists);
        console.showMessage("\033[33mType: Remove (r <number>), Create (c \"<name>\", use \" \"), Show (s), Play (p), or Exit (q) \033[");
    }

     /**
     * Processes user input for creating or removing playlists, showing or playing playlists, 
     * or navigating between menus.
     *
     * @param input The user input command.
     */
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
                //Expecting our input to be <action> <number>
                if (!CommandParserUtil.validCommand(input, simulator.console)){
                    break; // Invalided input
                }
                handleRemove(parts[1]);
                break;

            case "c":
            case "create":
                handleCreate(input);
                break;

            case "s":
            case "show":
            case "p":
            case "play":
                PlaylistActionHelper.handlePlaylistAction(input, simulator);
                break;

            case "q":
            case "exit":
                simulator.goBack();
                break;

            default:
                console.showMessage("\033[31mUnknown command. Please try again.\033[0m");
        }
    }

    /**
     * Handles the removal of a playlist by its index.
     * @param indexPart The command split into parts, where the second part should be the index.
     */
    public void handleRemove(String indexPart) {
        Integer playlistIndex = CommandParserUtil.parseIndexCommand(indexPart, console);
        if (playlistIndex == null) {
            return;
        }

        if (playlistIndex < 0 || playlistIndex >= songManager.playlistLibrary.listOfPlaylists.size()) {
            console.showMessage("\033[31mInvalid index. Please try again.\033[0m");
            console.waitForEnter();
            return;
        }

        Playlist removePlaylist = songManager.getPlaylistAtIndex(playlistIndex);
        songManager.removePlaylist(removePlaylist);
        console.waitForEnter();
    }

    /**
     * Handles the creation of a new playlist using the name provided in the input command.
     *
     * @param input The full input command containing the playlist name in quotes.
     */
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
        String playlistName = input.substring(firstQuoteIndex + 1, lastQuoteIndex).trim();

        songManager.createPlayList(playlistName); // Create the playlist with the full name
        console.waitForEnter();
    }
}
