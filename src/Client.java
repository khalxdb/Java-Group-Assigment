import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        // TODO : MAKE INTERFACE THAT USE THE SONG MANAGER

        // Create an instance of SongManager
        SongManager songManager = new SongManager();

        songManager = new SongManager();
        
        // Create songs
        Song a = new Song("Frank Sinatra","My Way");
        Song b = new Song("Frank Sinatra","Fly me to the Moon");
        Song c = new Song("Bruno Mars","That's What I Like");
        Song d = new Song("Queen", "Bohemian Rhapsody");
        Song e = new Song("The Beatles", "Let it Be");
        
        // Add songs to the song library
        songManager.addSongToLibrary(a);
        songManager.addSongToLibrary(b);
        songManager.addSongToLibrary(c);
        songManager.addSongToLibrary(d);
        songManager.addSongToLibrary(e);

        // Create playlist and add songs to the playlist method 1
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
        songManager.enqueueSong(a);
        songManager.enqueueSong(b);
        songManager.enqueueSong(c);
 
         // Play the queued songs one by one
        System.out.println("Playing songs from the queue:");
        songManager.playCurrentSong(); // Plays "My Way"
        songManager.playNextSong(); // Plays "Fly me to the Moon"
        songManager.playNextSong(); // Plays "That's What I Like"
        songManager.playNextSong();// Circular

        boolean runConsole = true;
       
        Scanner scanner = new Scanner(System.in);

        // TO BE DECIDED
        while (true&& runConsole) {
            clearConsole();
             // TESTING OUT COMMAND LINE INTERFACE
            System.out.println("\033[32mWelcome to the Song Manager!\033[0m");
            System.out.println("Commands: show, play, create, save, exit");

            String command = scanner.nextLine();

            // TODO: Write if statments for different interactions : for all the different command
            if (command.equalsIgnoreCase("show")) {
                System.out.println("Would you like to see all the songs or playlists?");
                System.out.println("Type 'songs' to view all songs or 'playlists' to view all playlists.");

                // Get the user's response for songs or playlists
                String showCommand = scanner.nextLine(); 

                if (showCommand.equalsIgnoreCase("songs")) {
                    clearConsole();
                    System.out.println("Displaying all songs...");
                    songManager.showSongs();
                } else if (showCommand.equalsIgnoreCase("playlists")) {
                    clearConsole();
                    System.out.println("Displaying all playlists...");
                    songManager.showPlayList();
                } else {
                    clearConsole();
                    System.out.println("Invalid option. Returning to main menu.");
                }
                // After displaying the information, prompt to return to the main menu
                System.out.println("\033[33mPress Enter to return to the main menu...\033[0m");
                // Wait for the user to press Enter before returning
                scanner.nextLine();  
                clearConsole();
            }

            if (command.equalsIgnoreCase("show playlists")){
                clearConsole();
                System.out.println("Displaying all songs...");
                songManager.showSongs();
                scanner.nextLine();  
                clearConsole();
            }

            if (command.equalsIgnoreCase("show songs")){
                clearConsole();
                System.out.println("Displaying all songs...");
                songManager.showSongs();
                scanner.nextLine();  
                clearConsole();
            }

            if (command.equalsIgnoreCase("create")) {
                clearConsole();
                System.out.println("What would you like to name your playlists as?");
                String playlistName = scanner.nextLine();
                songManager.createPlayList(playlistName);
                songManager.showPlayListSong(playlistName);
                scanner.nextLine();  
            }

            if (command.equalsIgnoreCase("play")) {
                clearConsole();
                System.out.println("Now Playing: ");
                songManager.playCurrentSong();

                // Loop to continuously handle next, prev, and exit
                while (true) {  
                    System.out.println("\nEnter 'next' to play the next song, 'prev' to play the previous song, or 'exit' to return to the main menu.");
                    String playCommand = scanner.nextLine();
                    if (playCommand.equalsIgnoreCase("next")) {
                        songManager.playNextSong();
                    } else if (playCommand.equalsIgnoreCase("prev")) {
                        songManager.playPreviousSong();
                    } else if (playCommand.equalsIgnoreCase("exit")) {
                        clearConsole();
                        System.out.println("Exiting play mode. Returning to the main menu.");
                        break;  // Exit the loop and return to the main menu
                    } else {
                        clearConsole();
                        System.out.println("Invalid option. Please enter 'next', 'prev', or 'exit'.");
                    }
                }
            }

            if (command.equalsIgnoreCase("exit")) {
                break;
            }

        }
        System.out.println("\033[31mGoodbye!\033[0m"); // Red Text
        scanner.close();
    }

    public static void clearConsole() {
        //print out a sort of new page
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    

}
