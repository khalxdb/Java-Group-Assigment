package app;
import app.util.*;
import app.manager.*;
import app.model.*;

public class Client {
    public static void main(String[] args) throws Exception {
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

        System.out.println("\nCreating the 'favorite' playlist\n");
        songManager.createPlayList("favorite");

        // add songs by name
        songManager.addSongToPlaylist("My Way", "favorite");
        songManager.addSongToPlaylist("Fly me to the Moon", "favorite");
        
        // make our playlist into an actual variable
        Playlist favorite = songManager.playlistLibrary.findPlaylistByName("favorite");

        // add song by object
        favorite.addSong(a);
        favorite.addSong(b);
        favorite.addSong(c);

        System.out.println("All songs in favorite:\n");
        songManager.showPlayListSong("favorite");
        System.out.println();

        // Create a new playlist and add songs
        songManager.createPlayList("Classic Hits");
        songManager.addSongToPlaylist(a, "Classic Hits");
        songManager.addSongToPlaylist(b, "Classic Hits");
        songManager.addSongToPlaylist(d, "Classic Hits");

        // Show songs in the 'Classic Hits' playlist
        songManager.showPlayListSong("Classic Hits");
        System.out.println();

        // Add another playlist
        songManager.createPlayList("Pop Songs");
        songManager.addSongToPlaylist(c, "Pop Songs");
        songManager.addSongToPlaylist(e, "Pop Songs");

        songManager.showPlayList();;
        System.out.println();
 
        //Queue up some songs
        for (Song song : songManager.songLibrary.listOfSongs){
            songManager.enqueueSong(song);
        }
         // Play the queued songs one by one
        System.out.println("Playing songs from the queue:");

        songManager.saveToCSV("data/output.csv");

        /*=================
         * RUN THE CONSOLE
         * ================ 
         */

        ConsoleManager console = new ConsoleManager();
        
        // boolean runConsole = false;
        // CommandProcessor commandProcessor = new CommandProcessor(console, songManager);

        // THIS is the NEW VERSION
        MusicSimulator simulator = new MusicSimulator(console,songManager);
        simulator.run();

    }
}

 



