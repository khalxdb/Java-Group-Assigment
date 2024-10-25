package app.state;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import app.*;
import app.manager.*;
import app.util.*;
import app.model.*;

public class ShowSongState implements State {
    private MusicSimulator simulator;
    private SongManager songManager;
    private ConsoleManager console;

    public ShowSongState(MusicSimulator simulator) {
        this.simulator = simulator;
        this.songManager = simulator.songManager;
        this.console = simulator.getConsole();
    }

    @Override
    public void display() {
        console.clearConsole();
        ArrayList<Song> listOfSongs = songManager.songLibrary.listOfSongs;
        console.displaySongList(listOfSongs);

        // General commands for library management
        console.showMessage("\033[33mCommands:\033[0m");
        console.showMessage("\033[33m'play <number>'                   - Add song to queue.\033[0m");
        console.showMessage("\033[33m'play' or 'p'                     - Play the queue.\033[0m");
        console.showMessage("\033[33m'add \"<title>\" by \"<artist>\"'     - Add new song to library.\033[0m");
        console.showMessage("\033[33m'back' or 'q'                     - Go Back.\033[0m");
        console.showMessage("\033[33m'exit'                            - Return to main menu.\033[0m");
        console.showMessage("\nCurrent Queue:");

        // Display the queue and current song
        CircularDoublyLinkedList songQueue = songManager.player.songQueue;
        Node curSongNode = songManager.player.currentSongNode;
        console.displayQueue(songQueue, curSongNode);
    }

    @Override
    public void handleInput(String input) {
        switch (input.toLowerCase()) {
            case "q":
            case "back":
                simulator.goBack();
                break;

            case "exit": // Return to main menu
                simulator.clearStateStack();
                simulator.setState(new MainMenuState(simulator));
                break;

            case "play":
            case "p":
                simulator.setState(new PlayQueueState(simulator, null)); // Play the queue without a playlist
                break;

            default:
                // Case where we have more than one input
                if (input.startsWith("add") || input.startsWith("a")) {
                    handleAddSong(input); // Add to song library
                } 
                else if (input.startsWith("play") || input.startsWith("p")){
                    handlePlaySong(input); // Enqueue song to play
                }
                console.showMessage("\033[31mInvalid Input \033[0m");
                console.waitForEnter();
                break;
        }
        display();
    }

    // Method to handle adding a new song to the song library
    public void handleAddSong(String addCommand) {
        // Get the pattern for adding songs add or a follow by " " "by" ""
        Pattern pattern = Pattern.compile("(add|a)\\s+\"([^\"]+)\"\\s+by\\s+\"([^\"]+)\"", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(addCommand);

        if (matcher.matches()) {
            String title = matcher.group(2); 
            String artist = matcher.group(3);

            Song newSong = new Song(title, artist);
            songManager.addSongToLibrary(newSong);
            console.showMessage("\033[32mSong '" + title + "' by '" + artist + "' has been added to the library.\033[0m");
        } else {
            console.showMessage("\033[31mInvalid format. Use 'add \"<song title>\" by \"<artist>\"'.\033[0m");
        }
        console.waitForEnter();
    }

    // Method to handle showing songs
    public void handlePlaySong(String showSongCommand) {
        Song songToPlay = getSongFromCommand(showSongCommand, "play");
        if (songToPlay == null) return;

        songManager.enqueueSong(songToPlay);
        console.showMessage("\033[32mSong '" + songToPlay.title + "' added to queue.\033[0m");
        console.waitForEnter();
    }

    // Helper to get song by command index
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

    // Check for Numeric
    public boolean isNumeric(String str) {
        return str.matches("\\d+");
    }
}
