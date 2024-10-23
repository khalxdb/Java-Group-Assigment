import java.util.Scanner;

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

        songManager.showSongs();

        // Create playlist and add songs to the playlist method 1
        // PlayList favorite = new PlayList("favorite");
        songManager.createPlayList("favorite");
        songManager.addSongByNameToPlaylist("My Way", "favorite");
        songManager.addSongByNameToPlaylist("Fly me to the Moon", "favorite");
        // Song a = songManager.findSongByName("My Way");

        songManager.showPlayList();
        
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
        
        songManager.showPlayListSong("Pop Songs");
        System.out.println();
 
        // Queue up some songs
        for (Song song : songManager.songLibrary.listOfSongs){
            songManager.enqueueSong(song);
        }
        
 
        //  // Play the queued songs one by one
        // System.out.println("Playing songs from the queue:");
        // songManager.playCurrentSong(); // Plays "My Way"
        // songManager.playNextSong(); // Plays "Fly me to the Moon"
        // songManager.playNextSong(); // Plays "That's What I Like"
        // songManager.playNextSong();// Circular

        boolean runConsole = true;
        ConsoleManager console = new ConsoleManager();
        console.clearConsole();

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
                console.waitForEnter(); 
                console.clearConsole();
            }

            if (command.equalsIgnoreCase("play")||
                command.equalsIgnoreCase("p")) {
                handlePlayQueue(songManager, console);
            }

            if (command.equalsIgnoreCase("create")) {
                console.clearConsole();
                System.out.println("What would you like to name your playlists as?");
                String playlistName = console.scanner.nextLine();
                songManager.createPlayList(playlistName);
                songManager.showPlayListSong(playlistName);
                console.getInput();  
            }

            

            if (command.equalsIgnoreCase("exit") || command.equalsIgnoreCase("q")) {
                break;
            }

        }
        System.out.println("\033[31mGoodbye!\033[0m"); // Red Text
        console.closeScanner();
    }

    // New Method to Handle Show Commands
    public static void showCommand(String command, SongManager songManager, ConsoleManager console) {
        if (command.equalsIgnoreCase("show")) {
            String showCommand = console.promptUser("Would you like to see all the songs or playlists?");
            
            if (showCommand.equalsIgnoreCase("songs")) {
                showSongCommand(songManager,console);

            } else if (showCommand.equalsIgnoreCase("playlists")) {
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

    public static void showPlaylistCommand(SongManager songManager, ConsoleManager console){
        console.clearConsole();
        console.showMessage("Displaying all playlists...");
        songManager.showPlayList();
    }

    public static void handlePlaySong(SongManager songManager, ConsoleManager console)

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
}


