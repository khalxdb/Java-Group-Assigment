package app.state;

import app.*;
import app.manager.*;
import app.util.*;
import app.model.*;

/**
 * The EditPlaylistState class allows users to add or remove songs from a specified playlist.
 * It also provides options to play the playlist or navigate back to the previous menu.
 */
public class EditPlaylistState implements State {
    public MusicSimulator simulator;
    public SongManager songManager;
    public ConsoleManager console;
    public Playlist playlist;

    /**
     * Constructor to initialize the EditPlaylistState with the specified playlist.
     * @param simulator The main application context for managing the current state.
     * @param playlist The playlist to be edited.
     */
    public EditPlaylistState(MusicSimulator simulator, Playlist playlist) {
        this.simulator = simulator;
        this.songManager = simulator.songManager;
        this.console = simulator.console;
        this.playlist = playlist;
    }

     /**
     * Displays the available songs, the selected playlist's contents, and the command options
     * for adding or removing songs from the playlist.
     */
    @Override
    public void display() {
        console.clearConsole();
        console.displaySongList(songManager.songLibrary.listOfSongs);
        console.displayPlaylistSong(playlist);

        // Display commands specific to editing the playlist
        console.showMessage("\033[33mAdding songs to playlist: " + playlist.name + "\033[0m");
        console.showMessage("\033[33m'add | a <number>'  - Add song to playlist.\033[0m");
        console.showMessage("\033[33m'remove | r | rm <playlist's number>'  - remove song from playlist.\033[0m");
        console.showMessage("\033[33m'play' or 'p'   - Play the playlist.\033[0m");
        console.showMessage("\033[33m'back' or 'q'   - Return to previous menu.\033[0m");
        console.showMessage("\033[33m'exit'          - Return to main menu.\033[0m");
    }

    /**
     * Processes user input for editing the playlist by adding or removing songs,
     * or navigating between menus.
     *
     * @param input The user input command.
     */
    @Override
    public void handleInput(String input) {
        switch (input.toLowerCase()) {
            case "q":
            case "back":
                simulator.goBack();
                break;

            case "exit":
                simulator.clearStateStack();
                simulator.setState(new MainMenuState(simulator));
                break;
            case "p":
            case "play":
                simulator.setState(new PlayQueueState(simulator, playlist)); // playing the song as queue

            default:
                //Expecting our input to be <action> <number>
                if (!CommandParserUtil.validCommand(input, simulator.console)){
                    return; // Invalided input
                }

                String[] parts = input.split(" ");
                String action = parts[0].toLowerCase();
                String indexPart = parts[1]; // Extract the index part directly

                if (action.equals("add") || action.equals("a")) {
                    handleAddToPlaylist(indexPart);
                    return;
                } 
                else if (action.equals("remove") || action.equals("r")||action.equals("rm")){
                    handleRemoveToPlaylist(indexPart);
                    return;
                }
                else {
                    console.showMessage("\033[31mInvalid command. Use 'add <number>' to add song to playlist.\033[0m");
                    console.waitForEnter();
                }
                break;
        }
        display(); // refresh page
    }

    /**
     * Adds a song to the playlist based on the specified index.
     * @param indexPart The index of the song to add.
     */
    public void handleAddToPlaylist(String indexPart) {
        Integer songIdx = CommandParserUtil.parseIndexCommand(indexPart, simulator.console);
        if (songIdx == null) {
            return;
        }
        Song songToAdd = songManager.getSongAtIndex(songIdx);
        if (playlist.addSong(songToAdd)) {
            console.showMessage("\033[32mSucessfully added " + songToAdd.title + " to " + playlist.name+ "\033[0m");
        }else{
            console.showMessage("\033[31mInvalid command.\033[0m");
        }
        console.waitForEnter();
    }

    /**
     * Removes a song from the playlist based on the specified index.
     * @param indexPart The index of the song to remove.
     */
    public void handleRemoveToPlaylist(String indexPart) {
        Integer plSongIdx = CommandParserUtil.parseIndexCommand(indexPart, simulator.console);
        if (plSongIdx == null) {
            return;
        }
        
        Song songToAdd = playlist.getSongAtIndex(plSongIdx); // get the songs in the playlist
        if (playlist.removeSong(songToAdd)) {
            console.showMessage("\033[32mSucessfully remove " + songToAdd.title + " to " + playlist.name+ "\033[0m");
        }else{
            console.showMessage("\033[31mInvalid command.\033[0m");
        }
        console.waitForEnter();
    }
}
