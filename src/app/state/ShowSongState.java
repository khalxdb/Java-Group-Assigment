package app.state;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import app.*;
import app.manager.*;
import app.util.*;
import app.model.*;

public class ShowSongState implements State {
    public MusicSimulator simulator;
    public SongManager songManager;
    public ConsoleManager console;
    private Playlist playlist; // Playlist to add songs to, if specified

    // Constructor
    public ShowSongState(MusicSimulator simulator, Playlist playlist) {
        this.simulator = simulator;
        this.songManager = simulator.songManager;
        this.console = simulator.console;
        this.playlist = playlist; // Set the playlist (null if managing general song library)
    }

    @Override
    public void display() {
        console.clearConsole();
        
        ArrayList<Song> listOfSongs = songManager.songLibrary.listOfSongs;
        console.displaySongList(listOfSongs);
        
        // Display different messages based on whether we're in a playlist context
        if (playlist == null) {
            // Normal show playlists
            console.showMessage("\033[33mCommands:\033[0m");
            console.showMessage("\033[33m'play <number>' - Add song to queue.\033[0m");
            console.showMessage("\033[33m'play' or 'p' - Play the queue.\033[0m");
            console.showMessage("\033[33m'add \"<song title>\" by \"<artist>\"' - Add new song to library.\033[0m");
            console.showMessage("\nCurrent Queue:");
            songManager.printQueue();
        } else {
            // Showing playlists songs
            System.out.println();
            console.showMessage("\033[33m'add <number>' - Add song to playlist.\033[0m");
            console.showMessage("\033[33m'play <number>' - Add song to queue.\033[0m");
            console.showMessage("\033[33m'play' or 'p' - Play the queue.\033[0m");
            console.showMessage("\033[33m'back' or 'q' - Return to main menu.\033[0m");
            System.out.println();
            playlist.showSongs();
        }

        
    }

    @Override
    public void handleInput(String input) {
        switch (input.toLowerCase()) {
            case "q":
            case "back":
                simulator.goBack();
                break;

            case "exit": // Go back to main menu
                simulator.clearStateStack();
                simulator.setState(new MainMenuState(simulator));
                break;

            case "play":
            case "p":
                simulator.setState(new PlayQueueState(simulator, null)); // Play the queue without a playlist
                break;

            default:
                if (input.startsWith("add") || input.startsWith("a")) {
                    if (playlist == null) {
                        handleAddSong(input); // Add to song library if no playlist specified
                    } else {
                        handleAddToPlaylist(input); // Add song to the specified playlist
                    }
                } else {
                    handleShowSong(input);
                }
                break;
        }
        display();
    }

    // Method to handle adding a new song to the song library
    public void handleAddSong(String addCommand) {
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

    // Method to handle adding an existing song to the playlist
    public void handleAddToPlaylist(String addCommand) {
        String[] parts = addCommand.split(" ");

        if (parts.length != 2 || !isNumeric(parts[1])) {
            console.showMessage("\033[31mInvalid command format. Please use 'add <number>'.\033[0m");
            console.waitForEnter();
            return;
        }

        int songIndex = Integer.parseInt(parts[1]);

        ArrayList<Song> listOfSongs = songManager.songLibrary.listOfSongs;
        if (songIndex < 0 || songIndex >= listOfSongs.size()) {
            console.showMessage("\033[31mInvalid index. Please try again.\033[0m");
            console.waitForEnter();
            return;
        }

        Song songToAdd = listOfSongs.get(songIndex);
        if (playlist.addSong(songToAdd)){
            console.showMessage("\033[32mSong '" + songToAdd.title + "' added to playlist '" + playlist.getName() + "'.\033[0m");
        }
        else{
            console.waitForEnter();
        };
        
        
    }

    // Method to handle showing and adding songs to the queue
    public void handleShowSong(String showSongCommand) {
        String[] parts = showSongCommand.split(" ");

        if (parts.length != 2) {
            console.showMessage("\033[31mInvalid command format. Please use 'play <number>' or 'play'.\033[0m");
            console.waitForEnter();
            return;
        }

        String action = parts[0].toLowerCase();
        String indexStr = parts[1];

        if (!isNumeric(indexStr)) {
            console.showMessage("\033[31mInvalid input. Please enter a valid number.\033[0m");
            console.waitForEnter();
            return;
        }

        int idx = Integer.parseInt(indexStr);

        ArrayList<Song> listOfSongs = songManager.songLibrary.listOfSongs;
        if (idx < 0 || idx >= listOfSongs.size()) {
            console.showMessage("\033[31mInvalid index. Please try again.\033[0m");
            console.waitForEnter();
            return;
        }

        if (action.equals("play") || action.equals("p")) {
            Song curSong = listOfSongs.get(idx);
            songManager.enqueueSong(curSong);
            console.showMessage("\033[32mSong '" + curSong.title + "' added to queue.\033[0m");
            console.waitForEnter();
        } else {
            console.showMessage("\033[31mUnknown command. Use 'play'.\033[0m");
            console.waitForEnter();
        }
    }

    public boolean isNumeric(String str) {
        return str.matches("\\d+");
    }
}
