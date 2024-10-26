package app.state;

import app.*;
import app.manager.*;
import app.util.*;
import app.model.*;
/**
 * PlayQueueState manages the playback of songs in the queue, allowing the user to play, shuffle,
 * navigate between songs, and add more songs. This state handles user commands to control the
 * queue and displays the current song information.
 */
public class PlayQueueState implements State {
    private MusicSimulator simulator;
    private SongManager songManager;
    private ConsoleManager console;
    public Playlist curPlaylist;

    /**
     * Constructs a PlayQueueState with the given simulator context and a specified playlist.
     * Initializes the playlist in the SongManager if provided.
     * @param simulator The main MusicSimulator context.
     * @param playlist  The playlist to play in the queue, or null to use the default song queue.
     */
    public PlayQueueState(MusicSimulator simulator, Playlist playlist) {
        this.simulator = simulator;
        this.songManager = simulator.songManager;
        this.console = simulator.console;
        this.curPlaylist = playlist;

         // Set playlist and initialize current song node if a playlist is specified
        if (curPlaylist != null) {
            songManager.playPlaylist(curPlaylist);
        }
    }

    /**
     * Displays the current song, the playlist name (if available), and the commands available to
     * the user for queue management.
     */
    @Override
    public void display() {
        console.clearConsole();

        if(curPlaylist == null){ // Queue Without Playlist
            console.showMessage("Now playing: ");
        }
        else{ // Queuing a Playlist
            console.showMessage("\033[32mPlaying: " + curPlaylist.name+"\033[0m");
        }

        songManager.playCurrentSong();
        CircularDoublyLinkedList songQueue = songManager.getSongQueue();
        Node curSongNode = songManager.getCurSongNode();
        console.displayQueue(songQueue,curSongNode);

        console.showMessage("\nAvailable Commands:");
        console.showMessage("\033[34mnext\033[0m n     - to play the next song");
        console.showMessage("\033[34mprev\033[0m p     - to play the previous song");
        console.showMessage("\033[34mshuffle\033[0m s  - shuffle songs");
        console.showMessage("\033[34madd\033[0m a     - to add more songs");
        console.showMessage("\033[34mexit\033[0m q     - to return to the main menu");
    }
    
    /**
     * Processes user input for queue actions such as playing the next or previous song, shuffling,
     * adding songs, or exiting. The appropriate action is performed based on the command.
     * @param input The user command for queue control, such as 'next', 'prev', 'shuffle', or 'exit'.
     */
    @Override
    public void handleInput(String input) {
        input = input.toLowerCase().trim();

        switch (input) {
            case "next":
            case "n":
                songManager.playNextSong();
                return;

            case "prev":
            case "p":
                songManager.playPreviousSong();
                return;

            case "shuffle":
            case "s":
                songManager.shuffle();
                songManager.printQueue();
                return;
            case "a":
            case "add":
                simulator.setState(new ShowSongState(simulator)); // to add more songs
                return;
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
        display(); // refresh states
    }
}
