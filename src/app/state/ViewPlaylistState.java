
package app.state;

import app.*;
import app.manager.*;
import app.util.*;
import app.model.*;

public class ViewPlaylistState implements State {
    public MusicSimulator simulator;
    public SongManager songManager;
    public ConsoleManager console;
    public Playlist playlist;

    public ViewPlaylistState(MusicSimulator simulator, Playlist playlist) {
        this.simulator = simulator;
        this.songManager = simulator.songManager;
        this.console = simulator.getConsole();
        this.playlist = playlist;
    }

    @Override
    public void display() {
        console.clearConsole();
        console.displayPlaylistSong(playlist);
        console.showMessage("\033[33mType 'back' to return to the playlist menu, 'exit' to exit to the main menu, or 'play' to play the playlist.\033[0m");
    }

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
                simulator.setState(new PlayQueueState(simulator,playlist));
                return;

            default:
                console.showMessage("\033[31mInvalid command. Please try again.\033[0m");
                console.waitForEnter();
                break;
        }

        // After handling input, display the state again
        display();
    }
}
