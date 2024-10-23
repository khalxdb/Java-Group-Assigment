
public class Client {
    public static void main(String[] args) throws Exception {
        // TODO : MAKE INTERFACE THAT USE THE SONG MANAGER

        // Create an instance of SongManager
        SongManager songManager = new SongManager();
        
        // // Create songs
        Song a = new Song("Michael Jackson", "Thriller");
        Song b = new Song("Whitney Houston", "I Wanna Dance with Somebody");
        Song c = new Song("Adele", "Someone Like You");
        Song d = new Song("Elton John", "Rocket Man");
        Song e = new Song("Led Zeppelin", "Whole Lotta Love");
        
        // Add songs to the song library
        songManager.addSongToLibrary(a);
        songManager.addSongToLibrary(b);
        songManager.addSongToLibrary(c);
        songManager.addSongToLibrary(d);
        songManager.addSongToLibrary(e);

        String fileName = "data/testing.csv";
        songManager.loadFromCSV(fileName);

        System.out.println("Shows all the current song in the library \n");
        songManager.showSongs();

        // Create playlist and add songs to the playlist method 1
        // PlayList favorite = new PlayList("favorite");

        System.out.println("\nCreating the 'favorite' playlist\n");
        songManager.createPlayList("favorite");
        songManager.showPlayList();

        songManager.addSongByNameToPlaylist("My Way", "favorite");
        songManager.addSongByNameToPlaylist("Fly me to the Moon", "favorite");


        Song i = songManager.songLibrary.findSongByName("My Way");

        
        PlayList favorite = new PlayList("favorite");
        favorite.addSong(a);
        favorite.addSong(b);
        favorite.addSong(c);
        System.out.println("All songs in the library:");
        songManager.showSongs();
        System.out.println();

        // Create a new playlist and add songs
        songManager.createPlayList("Classic Hits");
        songManager.addSongToPlayList(a, "Classic Hits");
        songManager.addSongToPlayList(b, "Classic Hits");
        songManager.addSongToPlayList(d, "Classic Hits");

        // Show songs in the 'Classic Hits' playlist
        songManager.showPlayListSong("Classic Hits");
        System.out.println();

        // Add another playlist
        songManager.createPlayList("Pop Songs");
        songManager.addSongToPlayList(c, "Pop Songs");
        songManager.addSongToPlayList(e, "Pop Songs");

         // Show songs in 'Pop Songs' playlist
        
        songManager.showPlayListSong("favorite");
        System.out.println();
 
        // Queue up some songs
        for (Song song : songManager.songLibrary.listOfSongs){
            songManager.enqueueSong(song);
        }
        
 
         // Play the queued songs one by one
        System.out.println("Playing songs from the queue:");
        songManager.playCurrentSong(); // Plays "My Way"
        songManager.playNextSong(); // Plays "Fly me to the Moon"
        songManager.playNextSong(); // Plays "That's What I Like"
        songManager.playNextSong();// Circular

        boolean runConsole = true;
        ConsoleManager console = new ConsoleManager();

        // TO BE DECIDED
        while (runConsole) {
            console.printWelcome();
            // Get the command input from the user
            String command = console.getCommandInput();

            if (command.equalsIgnoreCase("show") || 
                command.equalsIgnoreCase("show playlists") || 
                command.equalsIgnoreCase("ss")||
                command.equalsIgnoreCase("sl")) {
                    
                showCommand(command, songManager, console);
            }
            
            else if (command.equalsIgnoreCase("play") || 
                     command.equalsIgnoreCase("play playlists") || 
                     command.equalsIgnoreCase("ps")||
                     command.equalsIgnoreCase("pl")) {
                    
                playCommand(command, songManager, console);
            }

            else if (command.equalsIgnoreCase("create") || command.equalsIgnoreCase("c")) {
                createCommand(command, songManager, console);
            }
            else if (command.equalsIgnoreCase("search") || command.equalsIgnoreCase("se")){
                searchCommand(command,songManager,console);
            }
     
            else if (command.equalsIgnoreCase("exit") || command.equalsIgnoreCase("q")) {
                break;
            }
            
            else {
                console.showMessage("Error: Unrecognized command. Please try again.");
                console.waitForEnter(); 
                
            }

            


        }
        System.out.println("\033[31mGoodbye!\033[0m"); // Red Text
        console.closeScanner();
    }

    // New Method to Handle Show Commands
    public static void showCommand(String command, SongManager songManager, ConsoleManager console) {
        if (command.equalsIgnoreCase("show")) {
            String showCommand = console.promptUser("Would you like to see all the songs <s> or playlists <pl>?");
            
            if (showCommand.equalsIgnoreCase("songs")|| showCommand.equalsIgnoreCase("s")) {
                showSongCommand(songManager,console);

            } else if (showCommand.equalsIgnoreCase("playlists") || showCommand.equalsIgnoreCase("pl")) {
                showPlaylistCommand(songManager,console);
            } else {
                console.showMessage("Invalid option.");
            }
        } else if (command.equalsIgnoreCase("show playlists") || command.equalsIgnoreCase("sl")) {
            showPlaylistCommand(songManager,console);
        } else if(command.equalsIgnoreCase("show songs") || command.equalsIgnoreCase("ss")){
            showSongCommand(songManager,console);
        } 
    }

    public static void showSongCommand(SongManager songManager, ConsoleManager console){
        console.clearConsole();
        console.showMessage("Displaying all songs...");
        songManager.showSongs();
    }

    public static void showPlaylistCommand(SongManager songManager, ConsoleManager console) {
        // another loop for showing all the songs
        while (true) {
            console.clearConsole();
            console.showMessage("\033[34mDisplaying all playlists...\033[0m");
            songManager.showPlayList();
    
            console.showMessage("\033[33mType: 'show <number>' to view songs in a playlist, \n'play <number>' to play the playlist, or 'exit' to return to the main menu.\033[0m");
            
            // Get the Command for the playlist
            String playlistCommand = console.getCommandInput();

            // if we found exit break out of the loops
            if (handleExitCommand(playlistCommand, console)) break;
    
            handlePlaylistAction(playlistCommand, songManager, console);
        }
    }

    public static boolean handleExitCommand(String command, ConsoleManager console) {
        if (command.equalsIgnoreCase("exit") || command.equalsIgnoreCase("q")) {
            console.clearConsole();
            console.showMessage("\033[31mExiting to the main menu...\033[0m");
            // Exit the outer loop
            return true; 
        }
        // Continue the outer loop
        return false; 
    }

    public static void handlePlaylistAction(String playlistCommand, SongManager songManager, ConsoleManager console) {
        // Get the command and split it into 2 parts
        String[] parts = playlistCommand.split(" ");
        if (parts.length != 2) {
            console.showMessage("\033[31mInvalid command format. Please use 'show <number>' or 'play <number>'.\033[0m");
            return;
        }
    
        String action = parts[0];
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
    
        PlayList curPlaylist = songManager.playlistManager.listOfPlayLists.get(idx);
        if (action.equalsIgnoreCase("show") || action.equalsIgnoreCase("s")) {
            // we looped here because we wanted the player to constantly be able to see the playlists songs,
            showPlaylistSongsLoop(curPlaylist, songManager, console);
        } else if (action.equalsIgnoreCase("play") || action.equalsIgnoreCase("p")) {
            // if play then we play put the playlists in queue and playit.
            playSelectedPlaylist(curPlaylist, songManager, console);
        } else {
            console.showMessage("\033[31mInvalid command. Please use 'show <number>' or 'play <number>'.\033[0m");
        }
    }

    public static void showPlaylistSongsLoop(PlayList curPlaylist, SongManager songManager, ConsoleManager console) {
        while (true) {
            console.clearConsole();
            console.showMessage("\033[36mShowing songs in the selected playlist:\033[0m");
            songManager.showPlayListSong(curPlaylist);
            
            console.showMessage("\033[33mType 'back' to return to the playlist menu, 'exit' to exit to the main menu, or 'next' to play the playlist.\033[0m");
            String innerCommand = console.getCommandInput();
    
            if (innerCommand.equalsIgnoreCase("back") || innerCommand.equalsIgnoreCase("q")) {
                console.clearConsole();
                break; // Return to the playlist selection
            } else if (innerCommand.equalsIgnoreCase("exit")) {
                console.clearConsole();
                console.showMessage("\033[31mExiting to the main menu...\033[0m");
                return; // Exit both loops and return to the main menu
            } else if (innerCommand.equalsIgnoreCase("next") || innerCommand.equalsIgnoreCase("play")) {
                playSelectedPlaylist(curPlaylist, songManager, console);
            } else {
                console.showMessage("\033[31mInvalid command. Please try again.\033[0m");
            }
        }
    }
    public static void playSelectedPlaylist(PlayList curPlaylist, SongManager songManager, ConsoleManager console) {
        console.showMessage("\033[32mPlaying the selected playlist...\033[0m");
        songManager.playPlaylist(curPlaylist);
        handlePlayQueue(songManager, console);
    }
    
    public static boolean isNumeric(String str) {
        return str.matches("\\d+");  // Returns true if the string contains only digits
    }
    
    public static void playCommand(String command, SongManager songManager, ConsoleManager console) {
        if (command.equalsIgnoreCase("play")||command.equalsIgnoreCase("p")) {
            String showCommand = console.promptUser("Would you like play selected songs or playlists?");
            
            if (showCommand.equalsIgnoreCase("songs")) {
                showSongCommand(songManager,console);

            } else if (showCommand.equalsIgnoreCase("playlists")) {
                showPlaylistCommand(songManager,console);

            } else {
                console.showMessage("Invalid option.");
            }

        } else if (command.equalsIgnoreCase("play playlists") || command.equalsIgnoreCase("pl")) {
            showPlaylistCommand(songManager,console);
        } else if(command.equalsIgnoreCase("play songs") || command.equalsIgnoreCase("ps")){
            showSongCommand(songManager,console);
        } 
    }



    // Handling Play Queue
    public static void handlePlayQueue( SongManager songManager, ConsoleManager console){
        console.clearConsole();
        System.out.println("Now playing: \n");
        songManager.playCurrentSong();

        // Loop to continuously handle next, prev, and exit
        System.out.println("\nAvailable Commands: \n");
        console.showMessage("\033[34mnext\033[0m n     - to play the next song");
        console.showMessage("\033[34mprev\033[0m p     - to play the previous song");
        console.showMessage("\033[34mexit\033[0m q     - to return to the main menu");
        while (true) {  

            String playCommand = console.getCommandInput();
            
            if (playCommand.equalsIgnoreCase("next")||
                playCommand.equalsIgnoreCase("n")) {
                songManager.playNextSong();
            } else if (playCommand.equalsIgnoreCase("prev")||
                playCommand.equalsIgnoreCase("p")) {
                songManager.playPreviousSong();
            } else if (playCommand.equalsIgnoreCase("exit")||
                playCommand.equalsIgnoreCase("q")) {
                console.clearConsole();
                System.out.println("Exiting play mode. Returning to the main menu.");
                break;  // Exit the loop and return to the main menu
            } else {
                console.clearConsole();
                System.out.println("Invalid option. Please enter 'next', 'prev', or 'exit'.");
            }
        }
     
    }

    public static void createCommand(String command, SongManager songManager, ConsoleManager console){
        while (true) {
            console.clearConsole();
            songManager.showPlayList();
            System.out.println("You can remove r or crete c playlist");
            System.out.println("s p to go to showing the playlists?");
            String createCommand = console.getCommandInput();
            
            String[] parts = createCommand.split(" ");

            if (parts.length != 2) {
                console.showMessage("\033[31mInvalid command format. Please use 'show <number>' or 'play <number>'.\033[0m");
                return;
            }
            String action = parts[0];
            
            if (action.equalsIgnoreCase("r") || action.equalsIgnoreCase("remove") ){
                String indexStr = parts[1];
                if (!isNumeric(indexStr)) {
                    console.showMessage("\033[31mInvalid input. Please enter a valid number.\033[0m");
                    return;
                }
                int idx = Integer.parseInt(indexStr);

                if (idx < 0 || idx >= songManager.playlistManager.listOfPlayLists.size()) {
                    console.showMessage("\033[31mInvalid index. Please try again.\033[0m");
                }
                PlayList removePlaylist = songManager.playlistManager.listOfPlayLists.get(idx);
                songManager.removePlaylist(removePlaylist);
                console.waitForEnter();
            
            }else if(action.equalsIgnoreCase("c")|| action.equalsIgnoreCase("create")){
                // we know that the user wanted to create a playlist now
                // Error message that show that it's been created as well 
                String playlistsName = parts[1];
                songManager.createPlayList(playlistsName);   
                console.waitForEnter();

            }else if (createCommand.equalsIgnoreCase("exit")|| createCommand.equalsIgnoreCase("q")){
                return;
            }else if (createCommand.equalsIgnoreCase("show") || 
                    createCommand.equalsIgnoreCase("play")||
                createCommand.equalsIgnoreCase("s")||
                createCommand.contentEquals("p")){
                showPlaylistCommand(songManager,console);
                
            }
            console.showMessage("Error All Wrong Input");
            
        }

    }

    public static void searchCommand(String command, SongManager songManager, ConsoleManager console){

    }
 


}


