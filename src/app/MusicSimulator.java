package app;
import java.util.Stack;
import app.util.*;
import app.manager.*;
import app.state.*;

/*
 * This music simulator is our context class to which we will change it's states depending on the input. 
 */
public class MusicSimulator {
    public State currentState;
    public Stack<State> stateStack; // stack for keeping history of page
    public ConsoleManager console; 
    public SongManager songManager;
    
    // Constructor
    public MusicSimulator(ConsoleManager console, SongManager songManager){
        this.console = console;
        this.songManager  = songManager;
        this.stateStack = new Stack<>();
        this.currentState = new MainMenuState(this); // our default states is the main menu one
    }

    // Setting states, every times we set a state we keep track of a history with a stack
    public void setState(State newState) {
        if (currentState != null) {
            stateStack.push(currentState);  // Save current state to the stack
        }
        this.currentState = newState;
    }
    
    // going back to the previous page by poping the stack
    public void goBack() {
        if (!stateStack.isEmpty()) {
            this.currentState = stateStack.pop();  // Restore the previous state
        } else {
            console.showMessage("No previous state available, staying in the current state.");
        }
    }

    // clear the stack
    public void clearStateStack() {
        stateStack.clear();
    }

    /*
     * When we run the simulator, we do the following
     * loop to stay in the current states or page
     * display the state or page
     * get input for the state or page and handle the input
     */
    public void run(){
        while(true){
            // Activated the display method in every states class
            currentState.display();
            // Trim the input for spaces only
            String input = console.getCommandInput().trim();
            // Activated Handling Input in every state class
            currentState.handleInput(input);
        }
    }
}
