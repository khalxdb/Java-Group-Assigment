package app.state;
import app.*;
import app.manager.*;
import app.util.*;

public class MainMenuState implements State{
    public MusicSimulator simulator; 
    public ConsoleManager console;
    public SongManager songManager;

    public MainMenuState(MusicSimulator simulator) {
        this.simulator = simulator;
        this.console = simulator.console;
        this.songManager = simulator.songManager;
    }

    @Override
    public void display() {
        // Print out the welcome message and relevant details
       console.printWelcome();
    }

    @Override
    public void handleInput(String input){
        /*
         * The main menu have 5 main different states
         * show: show songs and playlists
         * play: play songs and playlists
         * queue: play the current queue
         * create: create a playlists
         * search: search for song by artist
         * save: save our current output
         * exit: exit out of the program
         */
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
                simulator.setState( new ShowSongState(simulator, null));
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
                simulator.setState( new ShowSongState(simulator, null));
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
                console.waitForEnter(); // for showing message
                break;
            }
    }
    
    // Handle selection for checking out songs or plays
    public void handlePlaySelection(){
        String showCommand = console.promptUser("Would you like to see all the songs <s> or playlists <pl>?");
        showCommand = showCommand.toLowerCase();
        switch(showCommand){
            case "songs":
            case "s":
                simulator.setState( new ShowSongState(simulator,null));
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
