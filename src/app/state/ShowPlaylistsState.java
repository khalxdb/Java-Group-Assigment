package app.state;
import java.util.ArrayList;
import app.*;
import app.manager.*;
import app.util.*;
import app.model.*;

import java.util.ArrayList;

public class ShowPlaylistsState implements State {
    public MusicSimulator simulator;
    public SongManager songManager;
    public ConsoleManager console;

    public ShowPlaylistsState(MusicSimulator simulator) {
        this.simulator = simulator;
        this.songManager = simulator.songManager;
        this.console = simulator.getConsole();
    }

    @Override
    public void display() {
        console.clearConsole();
        ArrayList<Playlist> playlists = songManager.getPlaylists();
        console.displayPlaylistList(playlists);

        console.showMessage("\033[33mType: 'show <number>' to view songs in a playlist, \n'play <number>' to play the playlist, or 'back' to return to the main menu.\033[0m");
    }

    @Override
    public void handleInput(String input) {

        if (input.equals("back") || input.equals("q")) {
            simulator.goBack();
        }else if (input.equals("exit")){
            simulator.clearStateStack();
            simulator.setState(new MainMenuState(simulator));
        }

        handlePlaylistAction(input);

        // After handling input, display the state again
        display();
    }

    public void handlePlaylistAction(String playlistCommand) {
        String[] parts = playlistCommand.split(" ");

        if (parts.length != 2) {
            console.showMessage("\033[31mInvalid command format. Please use 'show <number>' or 'play <number>'.\033[0m");
            console.waitForEnter();
            return;
        }

        String action = parts[0];
        String indexStr = parts[1];

        if (!isNumeric(indexStr)) {
            console.showMessage("\033[31mInvalid input. Please enter a valid number.\033[0m");
            console.waitForEnter();
            return;
        }

        int idx = Integer.parseInt(indexStr);
        ArrayList<Playlist> playlists = songManager.getPlaylists();
        if (idx < 0 || idx >= playlists.size()) {
            console.showMessage("\033[31mInvalid index. Please try again.\033[0m");
            console.waitForEnter();
            return;
        }

        Playlist selectedPlaylist = playlists.get(idx);

        switch (action.toLowerCase()) {
            case "show":
            case "s":
                // Transition to ViewPlaylistState
                simulator.setState(new ViewPlaylistState(simulator, selectedPlaylist));
                return;
            case "play":
            case "p":
                // Transition to PlayQueueState
                simulator.setState(new PlayQueueState(simulator, selectedPlaylist));
                return;
            default:
                console.showMessage("\033[31mInvalid command. Please use 'show <number>' or 'play <number>'.\033[0m");
                console.waitForEnter();
                return;
        }
    }

    public boolean isNumeric(String str) {
        return str.matches("\\d+");
    }
}
