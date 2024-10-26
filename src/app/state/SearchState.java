package app.state;

import app.*;
import app.manager.*;
import app.util.*;

/**
 * The SearchState class allows users to search for artists and view
 * their songs. Users can select an artist by entering the corresponding
 * index and can opt to play all songs by that artist.
 */
public class SearchState implements State {
    public MusicSimulator simulator;
    public SongManager songManager;
    public ConsoleManager console;

    // Constructor
    public SearchState(MusicSimulator simulator) {
        this.simulator = simulator;
        this.songManager = simulator.songManager;
        this.console = simulator.console;
    }

     /**
     * Displays the list of available artists to search and prompts the user to select an artist
     * by index or exit the search menu.
     */
    @Override
    public void display() {
        console.clearConsole();
        console.displayArtist(songManager.songLibrary.getArtists());
        console.showMessage("Please enter a number to select an artist, or 'q' to go back.");
    }

    /**
     * Handles user input to select an artist by index, play all songs by the selected artist, or
     * return to the previous menu. If the user inputs an invalid command, an error message is displayed.
     * @param input The user's command input.
     */
    @Override
    public void handleInput(String input) {
        String indexStr = input.trim().toLowerCase();

        if (indexStr.equals("q")) {
            simulator.goBack();
            return;
        } else if (indexStr.equals("exit")) {
            simulator.clearStateStack();
            simulator.setState(new MainMenuState(simulator));
            return;
        }

        // Use CommandParserUtil to parse and validate input as an index
        Integer idx = CommandParserUtil.parseIndexCommand(indexStr, console);
        if (idx == null){
            display();
            return;
        }

        String artist = songManager.getArtistAtIndex(idx);

        // Find all songs by the artist and display them
        CircularDoublyLinkedList artistSongs = songManager.songLibrary.findSongByArtist(artist);
        console.displayQueue(artistSongs, artistSongs.head);

        console.showMessage("\033[33mType 'play' to play the queue, or 'q' to go back, or keep on adding more songs\033[0m");
        handleSubCommand(artistSongs);

        display();  // refresh the page
    }

    /**
     * Handles the subcommands after displaying an artist's songs, allowing the user to play
     * the songs in a queue or return to the previous menu.
     *
     * @param artistSongs The list of songs by the selected artist.
     */
    public void handleSubCommand(CircularDoublyLinkedList artistSongs) {
        String subCommand = console.getCommandInput().trim().toLowerCase();

        if (subCommand.equals("q")) {
            simulator.goBack();
        } else if (subCommand.equals("play") || subCommand.equals("p")) {
            // Sets the player queue with the artist's songs and transitions to play state
            songManager.player.setPlaylist(artistSongs);
            simulator.setState(new PlayQueueState(simulator, null));
        } else {
            console.showMessage("Invalid input. Please try again.");
            console.waitForEnter();
        }
    }
}
