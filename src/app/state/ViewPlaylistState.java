
package app.state;

import app.*;
import app.manager.*;
import app.util.*;
import app.model.*;

/**
 * The ViewPlaylistState class represents the state where the user can view
 * a specific playlist, play it, add songs to it, or return to the playlist menu.
 */
public class ViewPlaylistState implements State {
    public MusicSimulator simulator;
    public SongManager songManager;
    public ConsoleManager console;
    public Playlist playlist;

    // Constructor
    public ViewPlaylistState(MusicSimulator simulator, Playlist playlist) {
        this.simulator = simulator;
        this.songManager = simulator.songManager;
        this.console = simulator.console;
        this.playlist = playlist;
    }

    /**
     * Displays the playlist contents and available options.
     * Shows the songs in the current playlist and prompts the user with
     * available commands for managing the playlist.
     */
    @Override
    public void display() {
        console.clearConsole();
        console.displayPlaylistSong(playlist);
        console.showMessage("\033[33mType 'back' to return to the playlist menu, 'exit' to exit to the main menu, or 'play' to play the playlist.\033[0m");
        console.showMessage("add | a for adding songs");
    }

    /**
     * Handles user input specific to viewing and managing a playlist.
     * Based on the input, this method allows the user to go back to the playlist
     * menu, play the playlist, add songs to it, or exit to the main menu.
     *
     * @param input The user input command.
     */
    @Override
    public void handleInput(String input) {
        input = input.toLowerCase().trim();

        switch (input) {
            case "back":
            case "q":
                // Return to back
                simulator.goBack();
                break;

            case "exit":
                console.clearConsole();
                console.showMessage("\033[31mExiting to the main menu...\033[0m");
                // Return back to the main menu
                simulator.clearStateStack();
                simulator.setState(new MainMenuState(simulator));
                return;

            case "play":
            case "p":
                simulator.setState(new PlayQueueState(simulator,playlist));
                return;
            case "add":
            case "a":
                simulator.setState(new EditPlaylistState(simulator ,playlist));
                return;

            default:
                console.showMessage("\033[31mInvalid command. Please try again.\033[0m");
                console.waitForEnter();
                break;
        }
        display(); // refresh page
    }
}
