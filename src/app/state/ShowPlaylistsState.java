package app.state;
import java.util.ArrayList;
import app.*;
import app.manager.*;
import app.util.*;
import app.model.*;

/**
 * ShowPlaylistsState displays all available playlists and provides options
 * for users to view or play specific playlists, or return to the main menu.
 */
public class ShowPlaylistsState implements State {
    public MusicSimulator simulator;
    public SongManager songManager;
    public ConsoleManager console;

    // Constructor
    public ShowPlaylistsState(MusicSimulator simulator) {
        this.simulator = simulator;
        this.songManager = simulator.songManager;
        this.console = simulator.console;
    }

    /**
     * Displays the list of all available playlists and provides instructions
     * on available actions, such as viewing or playing a playlist.
     */
    @Override
    public void display() {
        // clear the console() and display all the avialible playlist
        console.clearConsole();
        ArrayList<Playlist> playlists = songManager.getPlaylists();
        console.displayPlaylistList(playlists);
        console.showMessage("\033[33mType:\033[0m");
        console.showMessage("\033[33m'show <number>' to view songs in a playlist ");
        console.showMessage("'play <number>' to play the playlist");
        console.showMessage("'back' or 'q' to return to last page");
        console.showMessage("'exit' to go back to the main menu\033[0m");
    }

    /**
     * Handles user input for playlist management. Accepts commands to show or play
     * a specific playlist, or to return to the main menu.
     *
     * @param input The user input, which can include commands to show or play a playlist.
     */
    @Override
    public void handleInput(String input) {
        /*
         * Expecting the input top be s <number> or p<number> or just exit
         */
        input = input.toLowerCase();
        switch (input) {
            case "back":
            case "q":
                simulator.goBack();
                return;
            case "exit":
                simulator.clearStateStack();
                simulator.setState(new MainMenuState(simulator));
                return;
            default:
                // Handling the playlist selection that the user choose 'show' and 'play'
                PlaylistActionHelper.handlePlaylistAction(input,simulator);
                break;
        }
        display(); // Refresh page
    }
}
