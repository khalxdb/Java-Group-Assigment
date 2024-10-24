package app.state;
import java.util.ArrayList;
import app.*;
import app.manager.*;
import app.util.*;
import app.model.*;

public class PlayQueueState implements State {
    private MusicSimulator simulator;
    private SongManager songManager;
    private ConsoleManager console;
    public Playlist curPlaylist;

    public PlayQueueState(MusicSimulator simulator, Playlist playlist) {
        this.simulator = simulator;
        this.songManager = simulator.songManager;
        this.console = simulator.getConsole();
        this.curPlaylist = playlist;
    }

    @Override
    public void display() {
        console.clearConsole();
        if(curPlaylist == null){
            console.showMessage("Now playing: ");
            songManager.printQueue();
            
            console.showMessage("\nAvailable Commands:");
            console.showMessage("\033[34mnext\033[0m n     - to play the next song");
            console.showMessage("\033[34mprev\033[0m p     - to play the previous song");
            console.showMessage("\033[34mshuffle\033[0m s  - shuffle songs");
            console.showMessage("\033[34madd\033[0m a     - to add more songs");
            console.showMessage("\033[34mexit\033[0m q     - to return to the main menu");
        }
        else{
            console.showMessage("\033[32mPlaying: " + curPlaylist.name+"\033[0m");
            songManager.printQueue();

            console.showMessage("\nAvailable Commands:");
            console.showMessage("\033[34mnext\033[0m n     - to play the next song");
            console.showMessage("\033[34mprev\033[0m p     - to play the previous song");
            console.showMessage("\033[34mshuffle\033[0m s  - shuffle songs");
            console.showMessage("\033[34madd\033[0m a     - to add more songs");
            console.showMessage("\033[34mexit\033[0m q     - to return to the main menu");
        }
        
    }

    @Override
    // TODO: Handle queue is empty
    public void handleInput(String input) {
        input = input.toLowerCase().trim();

        switch (input) {
            case "next":
            case "n":
                songManager.playNextSong();
                break;

            case "prev":
            case "p":
                songManager.playPreviousSong();
                break;

            case "shuffle":
            case "s":
                songManager.shuffle();
                songManager.printQueue();
                break;
            case "a":
            case "add":
                simulator.setState(new ShowSongState(simulator));
                break;
            case "q":
                simulator.goBack();
                return;
            case "exit":
                simulator.clearStateStack();
                simulator.setState(new MainMenuState(simulator));
                return;

            default:
                console.showMessage("Invalid option. Please enter 'next', 'prev', 'shuffle', or 'exit'.");
                console.waitForEnter();
                break;
        }

        // After handling the input, display the updated information
        display();
    }
}
