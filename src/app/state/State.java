package app.state;

public interface State {
    /**
     * Handle user input and perform actions specific to the current state.
     *
     * @param input The user input.
     */
    void handleInput(String input);

    void display();
}
