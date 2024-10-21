import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        
        // TODO : MAKE INTERFACE THAT USE THE SONG MANAGER

        // Create an instance of SongManager
        SongManager songManager = new SongManager();

        songManager = new SongManager();
        
        // Create songs
        Song a = new Song("Frank Sinatra","My Way a ");
        Song b = new Song("Frank Sinatra","Fly me to the Moon b ");
        Song c = new Song("Bruno Mars","That's What I Like c ");
        Song d = new Song("Queen", "Bohemian Rhapsody d ");
        Song e = new Song("The Beatles", "Let it Be e ");
        
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
        songManager.showPlayList("Classic Hits");
        System.out.println();

        // Add another playlist
        songManager.createPlayList("Pop Songs");
        songManager.addSongToPlayList(c, "Pop Songs");
        songManager.addSongToPlayList(e, "Pop Songs");

         // Show songs in 'Pop Songs' playlist
        
        songManager.showPlayList("Pop Songs");
        System.out.println();
 
        // Queue up some songs
        songManager.enqueueSong(a);
        songManager.enqueueSong(b);
        songManager.enqueueSong(c);
 
         // Play the queued songs one by one
        System.out.println("Playing songs from the queue:");
        songManager.playNextSong();                                                                // Plays "My Way"
        songManager.playNextSong();                                                                // Plays "Fly me to the Moon"
        songManager.playNextSong();                                                                // Plays "That's What I Like"
        songManager.playNextSong();                                                                // Queue should be empty now

        System.out.println("\nDoublyLinkedList operations:");

        // // TESTING OUT COMMAND LINE INTERFACE
        // System.out.println("Welcome to the Song Manager!");
        // System.out.println("Commands: play, next, previous, shuffle, exit");
        // Scanner scanner = new Scanner(System.in);

        // while (true) {
        //     clearConsole();
        //     System.out.println("\033[32mWelcome to the Terminal Page!\033[0m"); // Green text
        //     System.out.println("\033[33mType 'exit' to quit.\033[0m"); // Yellow text
        //     System.out.println("\033[34mYour options are:\033[0m"); // Blue text
        //     System.out.println("\033[36m1. Option A\033[0m"); // Cyan text
        //     System.out.println("\033[36m2. Option B\033[0m"); // Cyan text
        //     System.out.print("\033[35mChoose an option: \033[0m"); // Magenta text

        //     String command = scanner.nextLine();

        //     if (command.equalsIgnoreCase("exit")) {
        //         break;
        //     }

        // }
        // System.out.println("\033[31mGoodbye!\033[0m"); // Red text for goodbye
        // scanner.close();
    }

    public static void clearConsole() {
        //print out a sort of new page
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    

}
