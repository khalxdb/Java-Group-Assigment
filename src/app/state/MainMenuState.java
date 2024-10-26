package app.state;
import app.*;
import app.manager.*;
import app.util.*;

/**
 * MainMenuState handles the main menu interaction of the MusicSimulator application. 
 * This class displays the welcome screen and processes user input to navigate to different 
 * states of the application, such as showing songs, playing playlists, creating playlists, 
 * searching by artist, saving data, or exiting.
 */
public class MainMenuState implements State{
    public MusicSimulator simulator; 
    public ConsoleManager console;
    public SongManager songManager;

    // Constructor
    public MainMenuState(MusicSimulator simulator) {
        this.simulator = simulator;
        this.console = simulator.console;
        this.songManager = simulator.songManager;
    }

    /**
     * Displays the main menu welcome message and available commands.
     */
    @Override
    public void display() {
        // Print out the welcome message and relevant details
       console.printWelcome();
    }

    /**
     * Handles user input at the main menu, routing commands to appropriate states or actions. 
     * Recognized commands include showing songs/playlists, playing songs/playlists, managing 
     * the queue, creating playlists, searching for songs, saving data, and exiting.
     *
     * @param input The user's input command.
     */
    @Override
    public void handleInput(String input){
        input = input.toLowerCase(); // Make the input not case sensitive
        switch (input) {
            case "show":
                handlePlaySelection();
                break;
            case "show playlists":
            case "sl":
                simulator.setState( new ShowPlaylistsState(simulator));
                break;
            case "show songs":
            case "ss":
                simulator.setState( new ShowSongState(simulator));
                break;
            
            case "play":
            case "p":
                handlePlaySelection();
                break;
            case "play playlists":
            case "pl" :
                simulator.setState( new ShowPlaylistsState(simulator));
                break;
            case "ps":
                simulator.setState( new ShowSongState(simulator));
                break;
            case "play queue":
            case "play q":
            case "pq": 
            case "queue": 
                simulator.setState(new PlayQueueState(simulator,null));
                break;
    
            case "search":
            case "se":
                simulator.setState(new SearchState(simulator));
                break;

            case "create":
            case "c":
                simulator.setState(new CreateState(simulator));
                break;
            
            case "save":
                simulator.setState(new SaveState(simulator));
                break;

            case "exit":
            case "q":
                simulator.clearStateStack();
                console.showMessage("Exiting Program");
                System.exit(0);
                break;
            default:
                // Error none of the message
                console.showMessage("\033[31mError: Unrecognized command. Please try again.\033");
                console.waitForEnter(); // For showing message
                break;
            }
    }
    
    /**
     * Displays options for viewing either the list of songs or playlists. If the user selects
     * an option, the state transitions to the corresponding view.
     */
    public void handlePlaySelection(){
        String showCommand = console.promptUser("Would you like to see all the songs <s> or playlists <pl>?");
        showCommand = showCommand.toLowerCase();
        switch(showCommand){
            case "songs":
            case "s":
                simulator.setState( new ShowSongState(simulator));
                return;

            case "playlists":
            case "pl":
                simulator.setState( new ShowPlaylistsState(simulator));
                return;
            
            default:
                console.showMessage("\033[31mInvalid option. Please enter 'songs' or 'playlists'.\033");
                console.waitForEnter();
                return;
        }
    }
}
