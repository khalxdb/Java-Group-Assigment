package app.util;
import app.model.*;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The ConsoleManager class handles user input and output operations in the console.
 * It manages displaying messages, reading commands, and formatting outputs for songs,
 * playlists, artists, and queue.
 */
public class ConsoleManager {
    public Scanner scanner;

    // Constructor
    public ConsoleManager() {
        scanner = new Scanner(System.in);
    }

    /**
     * Clears the console screen by printing multiple blank lines and resetting the cursor.
     */
    public void clearConsole() {
        for(int i = 0; i < 40; i++){
            System.out.println();
        }
        
        System.out.print("\033[H\033[2J"); // resetting cursor
        System.out.flush();
    }

    /**
     * Displays a welcome message and available commands for users.
     */
    public void printWelcome() {
        clearConsole();//\033[1;32m
        //\033[0m\n
        System.out.println("\033[1;32m==========================================\033[0m");
        System.out.println("\033[1;32m           Welcome to Fake Spotify!       \033[0m");
        System.out.println("\033[1;32m==========================================\033[0m");

        System.out.println("\033[1;36mAvailable Commands:\033[0m\n");

        System.out.println("\033[34mshow\033[0m       - Show songs (\033[34mss\033[0m) or playlists (\033[34msl\033[0m)");
        System.out.println("\033[34mplay\033[0m       - Play songs (\033[34mps\033[0m) or playlists (\033[34mpl\033[0m)");
        System.out.println("\033[34mplay queue\033[0m - Play the queue (\033[34mpq\033[0m) or (\033[34mqueue\033[0m)");
        System.out.println("\033[34mcreate\033[0m     - Create a new playlist (\033[34mc\033[0m)");
        System.out.println("\033[34msave\033[0m       - Save your music library to a file");
        System.out.println("\033[34msearch\033[0m     - Search for an artist (\033[34mse\033[0m)");
        System.out.println("\033[34mexit\033[0m       - Exit the Song Manager (\033[34mq\033[0m)\n");

        System.out.println("\033[1;32mEnjoy managing your music collection!\033[0m");
    }


    /**
     * Prompts the user for input and returns the command entered.
     * 
     * @return The user command as a String.
     */
    public String getCommandInput() {
        System.out.print("\n\033[35mCommands: \033[0m");
        return scanner.nextLine();
    }

    /**
     * Displays a message in the console.
     *
     * @param message The message to be displayed.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prompts the user to press Enter to continue. use for displaying stopping the while loop to display message
     */
    public void waitForEnter() {
        System.out.println("\033[33mPress Enter to continue...\033[0m");
        scanner.nextLine();
    }

    /**
     * Prompts the user with a messages and get input
     * @param promptMessage String a message to prompt the user
     * @return the input string that the user inputted
     */
    public String promptUser(String promptMessage) {
        System.out.println(promptMessage);
        return getCommandInput();
    }

    /**
     * Closes the scanner used for reading input.
     */
    public void closeScanner() {
        scanner.close();
    }

    /**
     * Displays a list of songs in a formatted table.
     * @param listOfSongs The list of songs to display an ArrayList of Song.
     */
    public void displaySongList(ArrayList<Song> listOfSongs) {
        System.out.println("\033[1;34m==========================================\033[0m");
        System.out.println("\033[1;34m                Available Songs           \033[0m");
        System.out.println("\033[1;34m==========================================\033[0m");

        // Print header row with fixed-width columns
        // left align 5, left align 30 , left align 20
        System.out.printf("\033[1;34m%-5s %-30s %-20s\033[0m\n", "Index", "Title", "Artist");
        System.out.println("\033[1;34m------------------------------------------\033[0m");

        // Print each song with formatted columns
        for (int i = 0; i < listOfSongs.size(); i++) {
            System.out.printf("%-5d %-30s %-20s\n", i, listOfSongs.get(i).title, listOfSongs.get(i).artist);
        }
        System.out.println("\033[1;34m==========================================\033[0m");
    }

    /**
     * Displays songs in a specified playlist with formatted columns.
     * @param playlist The playlist whose songs will be displayed.
     */
    public void displayPlaylistSong(Playlist playlist){
        System.out.println("\033[1;33m==========================================\033[0m");
        System.out.println("\033[1;33m       Selected Playlist: \033[0m" + "\033[34m" + playlist.name + "\033[0m");
        System.out.println("\033[1;33m==========================================\033[0m");

        // Check if the playlist is empty
        if (playlist.songList.head == null) {
            System.out.println("No songs in the playlist.");
            return;
        }

        // Print the header row with fixed-width columns
        System.out.printf("\033[1;33m%-5s %-30s %-20s\033[0m\n", "Index", "Title", "Artist");
        System.out.println("\033[1;33m------------------------------------------\033[0m");

        // Iterate through the circular linked list and print each song in formatted columns
        Node current = playlist.songList.head;
        int countIdx = 0;
        do {
            // printout each songs in a columsn format
            System.out.printf("%-5d %-30s %-20s\n", countIdx, current.song.title, current.song.artist);
            current = current.next;
            countIdx++;
        } 
        while (current != playlist.songList.head);

        System.out.println("\033[1;33m==========================================\033[0m\n");
    }

    /**
     * Displays a list of playlists with their indices and names.
     * @param playlists The list of playlists to display.
     */
    public void displayPlaylistList(ArrayList<Playlist> playlists) {
        System.out.println("\033[1;34m==========================================\033[0m");
        System.out.println("\033[1;34m            Available Playlists               \033[0m");
        System.out.println("\033[1;34m==========================================\033[0m");
    
        // Print the header row with fixed-width columns
        System.out.printf("\033[1;34m%-5s %-30s\033[0m\n", "Idx", "Name");
        System.out.println("\033[34m------------------------------------------\033[0m");
    
        // Print each playlist with formatted columns
        for (int i = 0; i < playlists.size(); i++) {
            System.out.printf("%-5d %-30s\n", i, playlists.get(i).name);
        }

        System.out.println("\033[34m==========================================\033[0m\n");
    }

    /**
     * Displays a list of artists with their indices and names.
     * @param artists The list of artists to display.
     */
    public void displayArtist(ArrayList<String> artists) {
        System.out.println("\033[1;36m==========================================\033[0m");
        System.out.println("\033[1;36m          Artists in the Library              \033[0m");
        System.out.println("\033[1;36m==========================================\033[0m\n");
    
        if (artists.isEmpty()) {
            System.out.println("\033[31mNo artists found in the library.\033[0m");
            return;
        }
    
        // Print the header row with fixed-width columns
        System.out.printf("\033[1;36m%-5s %-30s\033[0m\n", "Index", "Artist Name");
        System.out.println("\033[1;36m------------------------------------------\033[0m");
    
        // Print each artist with formatted columns
        for (int i = 0; i < artists.size(); i++) {
            System.out.printf("\033[1;33m%-5d %-30s\033[0m\n", i, artists.get(i));
        }
    
        System.out.println("\033[1;36m==========================================\033[0m\n");
    }

    /**
     * Displays the current song queue, highlighting the currently playing song.
     * @param songQueue The queue of songs.
     * @param currentSongNode The node of the currently playing song.
     */
    public void displayQueue(CircularDoublyLinkedList songQueue, Node currentSongNode) {
        if (songQueue.head == null) { // Check if the queue is empty
            System.out.println("Queue is empty.");
            return;
        }

        Node curNode = songQueue.head; 
        System.out.println("\033[1;34mQueue:\033[0m");
    
        do {
            if (curNode == currentSongNode) { // Highlight the current song node in the queue
                System.out.print("\033[1;32m[" + curNode.song.title + "]\033[0m"); // Highlight in green
            } else {
                System.out.print(curNode.song.title);
            }
    
            // Print an arrow if there are more nodes in the queue
            curNode = curNode.next;
            if (curNode != songQueue.head) {
                System.out.print(" -> ");
            }
        } 
        while (curNode != songQueue.head); // Stop after a full circle back to the head
        
        System.out.println();
    }
}
