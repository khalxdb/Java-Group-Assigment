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

    /**
     * Setting states, every times we set a state we keep track of a history with a stack
     * @param newState The new States of our context the music simulator
     */
    public void setState(State newState) {
        if (currentState != null) {
            stateStack.push(currentState);  // Save current state to the stack
        }
        this.currentState = newState;
    }
    
    /**
     * Go back to the previous page by checking and popping the stack
     */
    public void goBack() {
        if (!stateStack.isEmpty()) {
            this.currentState = stateStack.pop();  // Restore the previous state
        } else {
            console.showMessage("No previous state available, staying in the current state.");
        }
    }

    /**
     * Clear the State Stack
     */
    public void clearStateStack() {
        stateStack.clear();
    }

    /**
     * When we run the simulator, we do the following , loop to stay in the current states or page
     * display the state or page, get input for the state or page and handle the input
     */
    public void run(){
        while(true){
            currentState.display();
            String input = console.getCommandInput().trim(); // Trim the input to ignored white spaces
            currentState.handleInput(input);
        }
    }
}
