package app.state;
import java.util.ArrayList;
import app.*;
import app.manager.*;
import app.util.*;
import app.model.*;

import java.util.ArrayList;

public class ShowSongState implements State {
    public MusicSimulator simulator;
    public SongManager songManager;
    public ConsoleManager console;

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
        songManager.printQueue();

        console.showMessage("\033[33mType: 'play <number>' to play the song, or 'play q' to play the queue, or 'back' | 'q' to return to the main menu.\033[0m");
    }

    @Override
    public void handleInput(String input) {
        switch (input.toLowerCase()) {
            case "q":
                simulator.goBack();
                break;
                
            case "exit":// go back to main menu
                simulator.clearStateStack();
                simulator.setState(new MainMenuState(simulator));
                break;
            case "play q":
                simulator.setState(new PlayQueueState(simulator, null)); // play the queue without a playlist
                break;
            default:
                hadnleShowSong(input);
                break;
        }
        display();
    }

    public void hadnleShowSong(String showSongCommand) {
        String[] parts = showSongCommand.split(" ");

        // Validate command format
        if (parts.length != 2) {
            console.showMessage("\033[31mInvalid command format. Please use 'play <number>' or 'play q'.\033[0m");
            console.waitForEnter();
            return;
        }

        String action = parts[0].toLowerCase();
        String indexStr = parts[1];

        // Check if the second part is a valid number
        if (!isNumeric(indexStr)) {
            console.showMessage("\033[31mInvalid input. Please enter a valid number.\033[0m");
            console.waitForEnter();
            return;
        }

        int idx = Integer.parseInt(indexStr);

        // Validate index against the song list size
        if (idx < 0 || idx >= songManager.songLibrary.listOfSongs.size()) {
            console.showMessage("\033[31mInvalid index. Please try again.\033[0m");
            console.waitForEnter();
            return;
        }

        // Handle the play command
        if (action.equals("play") || action.equals("p")) {
            Song curSong = songManager.songLibrary.listOfSongs.get(idx);
            songManager.enqueueSong(curSong);
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
