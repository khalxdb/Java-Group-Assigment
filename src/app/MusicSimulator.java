package app;
import java.util.Stack;
import app.util.*;
import app.manager.*;
import app.state.*;

public class MusicSimulator {
    // The music simulator is our context to which we will change it's states
    public State currentState;
    public Stack<State> stateStack;
    public ConsoleManager console; 
    public SongManager songManager;
    
    // Constructor
    public MusicSimulator(ConsoleManager console, SongManager songManager){
        this.console = console;
        this.songManager  = songManager;
        this.stateStack = new Stack<>();
        this.currentState = new MainMenuState(this);
    }

    // Method for setting states
    public void setState(State newState) {
        if (currentState != null) {
            stateStack.push(currentState);  // Save current state to the stack
        }
        this.currentState = newState;
    }
    
    // Method for going back
    public void goBack() {
        if (!stateStack.isEmpty()) {
            this.currentState = stateStack.pop();  // Restore the previous state
        } else {
            console.showMessage("No previous state available. Staying in the current state.");
        }
    }
    
    public void clearStateStack() {
        stateStack.clear();
    }

    public ConsoleManager getConsole(){
        return this.console;
    }


    // Running the simulator
    public void run(){
        while(true){
            // Activated the display method in every states class
            currentState.display();
            String input = console.getCommandInput().toLowerCase().trim();
            // Activated Handling Input in every state class
            currentState.handleInput(input);
        }
    }
}
