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
        }
        else if (input.equals("exit")){
            simulator.clearStateStack();
            simulator.setState(new MainMenuState(simulator));
        }
        else {
            // Handling the playlist selection that the user choose 'show' and 'play'
            PlaylistActionHelper.handlePlaylistAction(input,simulator);
        }
 
        // After handling input, display the state again
        display();
    }
}
