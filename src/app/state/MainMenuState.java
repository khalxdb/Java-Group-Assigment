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
        simulator.getConsole().printWelcome();
    }

    @Override
    public void handleInput(String input){
        switch (input) {
            // // Any Command with show in them, run the show command.
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
            case "p q":  
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
                System.exit(0);
                break;
            default:
                simulator.getConsole().showMessage("Error: Unrecognized command. Please try again.");
                simulator.getConsole().waitForEnter();
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
                console.showMessage("Invalid option. Please enter 'songs' or 'playlists'.");
                console.waitForEnter();
                return;
        }
    }

}
