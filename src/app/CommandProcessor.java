package app;
import java.io.IOException;
import java.util.ArrayList;
import app.model.*;
import app.manager.*;
import app.util.*;

public class CommandProcessor {
    public ConsoleManager console;
    public SongManager songManager;
    // The role of this class is to handle all the commands

    public CommandProcessor(ConsoleManager console, SongManager songManager) {
        this.console = console;
        this.songManager = songManager;
    }

    /**
     * Boolean function for processing different Command
     * @param command
     * @return true if we still keep running the program
     * other false meaning we exit the program
     */
    public boolean processCommand(String command) {
        switch (command) {
            // Any Command with show in them, run the show command.
            case "show":
            case "show playlists":
            case "show songs":
            case "ss":
            case "sl":
                showCommand(command, songManager, console);
                break;
            
            case "play":
            case "play playlists":
            case "ps":
            case "pl" :
            case "play queue":
            case "play q":
            case "p q":  
                playCommand(command, songManager, console);
                break;
            case "search":
            case "se":
                searchCommand(command, songManager, console);
                break;

            case "create":
            case "c":
                createCommand(command, songManager, console);
                break;
            
            case "save":
                saveCommand(command, songManager, console);

            case "exit":
            case "q":
                return false;
            default:
                console.showMessage("Error: Unrecognized command. Please try again.");
                console.waitForEnter();
            }
        return true;
    }
       public static void showCommand(String command, SongManager songManager, ConsoleManager console) {
        command = command.toLowerCase().trim();;
        switch(command){
            case "show":
                handlePlaySelection(songManager, console);
                return;

            case "show playlists":
            case "sl":
                showPlaylistPage(songManager,console);
                return;

            case "show songs":
            case "ss":
                showSongPage(songManager,console);
                return;
            default:
                console.showMessage("\033[31mInvalid command. Please try again.\033[0m");
                return;
        }
    }

    public static void handlePlaySelection(SongManager songManager, ConsoleManager console){
        String showCommand = console.promptUser("Would you like to see all the songs <s> or playlists <pl>?");
        showCommand = showCommand.toLowerCase();
        switch(showCommand){
            case "songs":
            case "s":
                showSongPage(songManager,console);
                return;

            case "playlists":
            case "pl":
                showPlaylistPage(songManager,console);
                return;

            default:
                console.showMessage("Invalid option. Please enter 'songs' or 'playlists'.");
                console.waitForEnter();
                return;
        }
    }



    public static void showSongPage(SongManager songManager, ConsoleManager console) {
        while (true) {
            console.clearConsole();
            ArrayList<Song> listOfsongs = songManager.songLibrary.listOfSongs;
            console.displaySongList(listOfsongs);

            songManager.player.songQueue.printList();
    
            // Get input
            String showSongCommand = console.getCommandInput().toLowerCase();
            // single word command like exit or q
            if (showSongCommand.equals("q") || showSongCommand.equals("exit")) {
                return; // Exit the loop
            } 
            // Handle 'play q' to play from the queue
            // Check for 'play q' to play from the queue
            else if (showSongCommand.equals("p q")) {
                if (handlePlayQueue(songManager, console)) {
                    return;
                }
                continue;
            }

            String[] parts = showSongCommand.split(" ");

            if (parts.length == 1) {
                console.showMessage("\033[31mInvalid command format. Please use 'show <number>' or 'play <number>'.\033[0m");
                console.waitForEnter();
                continue; // Go back to prompt
            }
            
            // Handle two-word commands  'play 1'
            if (parts.length == 2) {
                String action = parts[0];
                String indexStr = parts[1];
                
                if (!isNumeric(indexStr)) {
                    console.showMessage("\033[31mInvalid input. Please enter a valid number.\033[0m");
                    console.waitForEnter();
                    continue;
                }
            
                int idx = Integer.parseInt(indexStr);
    
                // Validate index against the song list size, not the playlist size
                if (idx < 0 || idx >= songManager.songLibrary.listOfSongs.size()) {
                    console.showMessage("\033[31mInvalid index. Please try again.\033[0m");
                    console.waitForEnter();
                    continue;
                }
    
                if (action.equals("play") || action.equals("p")) {
                    Song curSong = songManager.songLibrary.listOfSongs.get(idx);
                    songManager.enqueueSong(curSong);
                    console.showMessage("Song '" + curSong.title + "' has been added to the queue.");
                    console.waitForEnter();
                } else {
                    console.showMessage("\033[31mUnknown command. Use 'show' or 'play'.\033[0m");
                    console.waitForEnter();
                    continue; // Loop back for another input
                }
            }else {
                // If more than two parts were entered, it's invalid
                console.showMessage("\033[31mInvalid command format. Please use 'play <number>' or 'play q'.\033[0m");
                console.waitForEnter();
            }
        }
    }

    public static void showPlaylistPage(SongManager songManager, ConsoleManager console) {
        // another loop for showing all the songs
        while (true) {
            console.clearConsole();
            ArrayList<Playlist> listsOfPlaylists = songManager.playlistManager.listOfPlayLists;
            console.displayPlaylistList(listsOfPlaylists);

            console.showMessage("\033[33mType: 'show <number>' to view songs in a playlist, \n'play <number>' to play the playlist, or 'exit' to return to the main menu.\033[0m");
            
            // Get the Command for the playlist
            String playlistCommand = console.getCommandInput().toLowerCase().trim();

            // if we go back exit break out of the loops
            if (playlistCommand.equals("q")|| playlistCommand.equals("exit") ){
                return; // return directly back to the main menu
            }
            // go back directly to the main menu
            // Handle the playlist action and continue looping until valid input is received
            boolean shouldExit = handlePlaylistAction(playlistCommand, songManager, console);

            if (shouldExit) {
                return;  // Exit to main menu
            }
        }
    }


    public static boolean handlePlaylistAction(String playlistCommand, SongManager songManager, ConsoleManager console) {
        // Get the command and split it into 2 parts
        String[] parts = playlistCommand.split(" ");
        if (parts.length != 2) {
            console.showMessage("\033[31mInvalid command format. Please use 'show <number>' or 'play <number>'.\033[0m");
            console.waitForEnter();
            return false;
        }
    
        String action = parts[0];
        String indexStr = parts[1];
        
        if (!isNumeric(indexStr)) {
            console.showMessage("\033[31mInvalid input. Please enter a valid number.\033[0m");
            return false;
        }
    
        int idx = Integer.parseInt(indexStr);
        if (idx < 0 || idx >= songManager.playlistManager.listOfPlayLists.size()) {
            console.showMessage("\033[31mInvalid index. Please try again.\033[0m");
            return false;
        }
    
        Playlist curPlaylist = songManager.playlistManager.listOfPlayLists.get(idx);
        
        if (action.equalsIgnoreCase("show") || action.equalsIgnoreCase("s")) {
            // we looped here because we wanted the player to constantly be able to see the playlists songs,
            return showPlaylistSongsLoop(curPlaylist, songManager, console);
            
        } else if (action.equalsIgnoreCase("play") || action.equalsIgnoreCase("p")) {
            // if play then we play put the playlists in queue and playit.
            return playSelectedPlaylist(curPlaylist, songManager, console);
            
        } else {
            console.showMessage("\033[31mInvalid command. Please use 'show <number>' or 'play <number>'.\033[0m");
            showPlaylistPage( songManager,  console);
            return false;
        }
    }

    public static boolean  showPlaylistSongsLoop(Playlist curPlaylist, SongManager songManager, ConsoleManager console) {
        
        while (true) {
            console.clearConsole();
            console.displayPlaylistSong(curPlaylist);
            console.showMessage("\033[33mType 'back' to return to the playlist menu, 'exit' to exit to the main menu, or 'next' to play the playlist.\033[0m");
            
            String innerCommand = console.getCommandInput().toLowerCase(); // Normalize input
    
            switch (innerCommand) {
                case "back":
                case "q":
                    console.clearConsole();
                     // Return to the playlist selection
                     return false;
                    
                case "exit":
                    console.clearConsole();
                    console.showMessage("\033[31mExiting to the main menu...\033[0m");
                    return true; // Exit to the main menu
                    
                case "next":
                case "play":
                    playSelectedPlaylist(curPlaylist, songManager, console);
                    return false;
    
                default:
                    console.showMessage("\033[31mInvalid command. Please try again.\033[0m");
                    break;
            }
        }
      
    }

    public static boolean playSelectedPlaylist(Playlist curPlaylist, SongManager songManager, ConsoleManager console) {
        console.showMessage("\033[32mPlaying the selected playlist...\033[0m");
        songManager.playPlaylist(curPlaylist);
        return handlePlayQueue(songManager, console);
    }
    
    public static boolean isNumeric(String str) {
        return str.matches("\\d+");  // Returns true if the string contains only digits
    }
    
    public static void playCommand(String command, SongManager songManager, ConsoleManager console) {
        // Convert the command to lowercase to handle case-insensitivity
        command = command.toLowerCase();
    
        switch (command) {
            case "play":
            case "p":
                // check if the players is choosing song or playlist, we are going to the same page in these scenario.
                handlePlaySelection(songManager, console);
                break;
    
            case "play playlists":
            case "pl":
                showPlaylistPage(songManager, console);
                break;
    
            case "play songs":
            case "ps":
                showSongPage(songManager, console);
                break;

            case "play queue":
            case "play q":
            case "p q":
                handlePlayQueue(songManager, console);
                break;
    
            default:
                console.showMessage("Invalid play command.");
                break;
        }
    }
    

    // Handling Play Queue
    public static boolean handlePlayQueue( SongManager songManager, ConsoleManager console){
        console.clearConsole();
        System.out.println("Now playing: \n");
        songManager.player.songQueue.printList();
        songManager.playCurrentSong();

        // Loop to continuously handle next, prev, and exit
        System.out.println("\nAvailable Commands: \n");
        console.showMessage("\033[34mnext\033[0m n     - to play the next song");
        console.showMessage("\033[34mprev\033[0m p     - to play the previous song");
        console.showMessage("\033[34mshuffle\033[0m s  - shuffle songs");
        console.showMessage("\033[34mexit\033[0m q     - to return to the main menu");
        
        boolean queue = true;

        while (queue) {
            String playCommand = console.getCommandInput().toLowerCase(); // Convert input to lowercase for easier comparison
        
            switch (playCommand) {
                case "next":
                case "n":
                    songManager.playNextSong();
                    break;
        
                case "prev":
                case "p":
                    songManager.playPreviousSong();
                    break;
                case "shuffle":
                case "s":
                    songManager.shuffle();
                    songManager.player.songQueue.printList();
                    break;
        
                case "q":
                    console.clearConsole();
                    System.out.println("Exiting to main menu.");
                    return false;// Exit the loop and return to the main menu
                case "exit":
                    console.clearConsole();
                    console.showMessage("Exit to another page");
                    return true;

                default:
                    console.clearConsole();
                    System.out.println("Invalid option. Please enter 'next', 'prev', or 'exit'.");
                    break;
            }
        }
        return false;
     
    }

    // TODO: make a way to added songs
    public static void createCommand(String command, SongManager songManager, ConsoleManager console){
        while (true) {
            console.clearConsole();
            songManager.showPlayList();
            console.showMessage("Commands: Remove (r <number>), Create (c <name>), Show (s) or Play (p), Exit (q)");

            String createCommand = console.getCommandInput();
            String[] parts = createCommand.split(" ");

            if (parts.length < 1) {
                console.showMessage("\033[31mInvalid command. Please enter a valid command.\033[0m");
                continue;
            }

            String action = parts[0].toLowerCase(); // Normalize the command for comparison

        switch (action) {
            case "r":
            case "remove":
                handleRemove(parts, songManager, console);
                break;

            case "c":
            case "create":
                handleCreate(parts, songManager, console);
                break;

            case "s":
            case "show":
            case "p":
            case "play":
                showPlaylistPage(songManager, console);
                break;

            case "q":
            case "exit":
                return; // Exit the loop and method

            default:
                console.showMessage("\033[31mUnknown command. Please try again.\033[0m");
                continue;
        }
    }
}

    public static void handleRemove(String[] parts, SongManager songManager, ConsoleManager console) {
        if (parts.length != 2) {
            console.showMessage("\033[31mInvalid command format. Use 'r <number>'.\033[0m");
            return;
        }
        String indexStr = parts[1];
        if (!isNumeric(indexStr)) {
            console.showMessage("\033[31mInvalid input. Please enter a valid number.\033[0m");
            return;
        }
        int idx = Integer.parseInt(indexStr);
        if (idx < 0 || idx >= songManager.playlistManager.listOfPlayLists.size()) {
            console.showMessage("\033[31mInvalid index. Please try again.\033[0m");
            return;
        }
        Playlist removePlaylist = songManager.playlistManager.listOfPlayLists.get(idx);
        songManager.removePlaylist(removePlaylist);
        console.showMessage("Playlist '" + removePlaylist.getName() + "' has been removed.");
        console.waitForEnter();
    }

    public static void handleCreate(String[] parts, SongManager songManager, ConsoleManager console) {
        if (parts.length != 2) {
            console.showMessage("\033[31mInvalid command format. Use 'c <playlistName>'.\033[0m");
            return;
        }
        String playlistsName = parts[1];
        songManager.createPlayList(playlistsName);
        console.showMessage("Playlist '" + playlistsName + "' has been created.");
        console.waitForEnter();
    }

    public static void searchCommand(String command, SongManager songManager, ConsoleManager console){
        while (true){
            // display a list of artist;
            songManager.songLibrary.showArtist();
            console.showMessage("Please Enter a Number");
            String indexStr = console.getCommandInput();

            if ( indexStr.equals("q") || indexStr.equals("exit")){
                return;
            }

            if(!isNumeric(indexStr)){
                console.showMessage("Please Enter a Number");
                console.waitForEnter();
                continue;
            }

            int idx =  Integer.parseInt(indexStr);

            String artist = songManager.songLibrary.listOfArtist.get(idx);

            songManager.player.songQueue = songManager.songLibrary.findSongByArtist(artist);
            // print all the aviable song by that artist
            songManager.player.songQueue.printList();
            
            String subCommand = console.getCommandInput().toLowerCase();
            if ( subCommand.equals("q") || subCommand.equals("exit")){
                return;
            }else if ( subCommand.equals("p") || subCommand.equals("play")){
                handlePlayQueue(songManager, console);
            }else{
                console.showMessage("Error input");
                console.waitForEnter();
            }

        }
    }

    public static void saveCommand(String command, SongManager songManager, ConsoleManager console){
        console.showMessage("Please type the locations/ name of the file");
        String filePath = console.getCommandInput();
        try {
            songManager.saveToCSV(filePath + ".csv");
            console.showMessage("Successfully saved to " + filePath + ".csv");
        } catch (IOException e) {
            // Handle any I/O errors during file writing
            console.showMessage("Error saving the file: " + e.getMessage());
        }
    }

}
