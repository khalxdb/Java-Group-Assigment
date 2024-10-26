package app.state;
/**
 * The State interface defines the contract for different states within the application.
 * Each state should implement methods to display the state-specific information and handle
 * user input to navigate or perform actions.
 */
public interface State {
    /**
     * Displays the content and options specific to the current state.
     * This method is invoked to present the state’s visual elements and messages to the user.
     */
    void display();

    /**
     * Handles the user input, performing actions relevant to the current state.
     * Based on the input, the state may transition to a different state or perform actions.
     *
     * @param input The user input.
     */
    void handleInput(String input);
}
