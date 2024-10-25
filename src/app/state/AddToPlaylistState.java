package app.state;

import java.util.ArrayList;
import app.*;
import app.manager.*;
import app.util.*;
import app.model.*;

public class AddToPlaylistState implements State {
    private MusicSimulator simulator;
    private SongManager songManager;
    private ConsoleManager console;
    private Playlist playlist;

    // Constructor for adding songs to a specific playlist
    public AddToPlaylistState(MusicSimulator simulator, Playlist playlist) {
        this.simulator = simulator;
        this.songManager = simulator.songManager;
        this.console = simulator.console;
        this.playlist = playlist;
    }

    @Override
    public void display() {
        console.clearConsole();
        console.displaySongList(songManager.songLibrary.listOfSongs);
        console.displayPlaylistSong(playlist);

        // Display commands specific to adding to playlist
        console.showMessage("\033[33mAdding songs to playlist: " + playlist.name + "\033[0m");
        console.showMessage("\033[33m'add <number>'  - Add song to playlist.\033[0m");
        console.showMessage("\033[33m'play' or 'p'   - Play the playlist.\033[0m");
        console.showMessage("\033[33m'back' or 'q'   - Return to previous menu.\033[0m");
        console.showMessage("\033[33m'exit'          - Return to main menu.\033[0m");
    }

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
                simulator.setState(new PlayQueueState(simulator, playlist));

            default:
                if (input.startsWith("add") || input.startsWith("a")) {
                    handleAddToPlaylist(input);
                } else {
                    console.showMessage("\033[31mInvalid command. Use 'add <number>' to add song to playlist.\033[0m");
                    console.waitForEnter();
                }
                break;
        }
        display();
    }

    // Method to handle adding an existing song to the playlist
    public void handleAddToPlaylist(String addCommand) {
        Song songToAdd = getSongFromCommand(addCommand, "add");
        if (songToAdd == null) return;
        playlist.addSong(songToAdd);
        console.waitForEnter();
    }

    // Helper to validate and retrieve song by command
    public Song getSongFromCommand(String command, String expectedAction) {
        String[] parts = command.split(" ");
        if (parts.length != 2 || !isNumeric(parts[1])) {
            console.showMessage("\033[31mInvalid command format. Use '" + expectedAction + " <number>'.\033[0m");
            console.waitForEnter();
            return null;
        }

        int songIndex = Integer.parseInt(parts[1]);
        ArrayList<Song> listOfSongs = songManager.getListofSong();
        if (songIndex < 0 || songIndex >= listOfSongs.size()) {
            console.showMessage("\033[31mInvalid index. Please try again.\033[0m");
            console.waitForEnter();
            return null;
        }
        return listOfSongs.get(songIndex);
    }

    // check for numeric
    public boolean isNumeric(String str) {
        return str.matches("\\d+");
    }
}
