package app.state;
import java.util.ArrayList;
import app.*;
import app.manager.*;
import app.util.*;
import app.model.*;


public class ShowPlaylistsState implements State {
    public MusicSimulator simulator;
    public SongManager songManager;
    public ConsoleManager console;

    public ShowPlaylistsState(MusicSimulator simulator) {
        this.simulator = simulator;
        this.songManager = simulator.songManager;
        this.console = simulator.console;
    }

    @Override
    public void display() {
        // clear the console() and display all the avialible playlist
        console.clearConsole();
        ArrayList<Playlist> playlists = songManager.getPlaylists();
        console.displayPlaylistList(playlists);
        console.showMessage("\033[33mType: 'show <number>' to view songs in a playlist, \n'play <number>' to play the playlist, or 'back' to return to the main menu.\033[0m");
    }

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
        // After handling input, display the state again
        display();
    }
}
