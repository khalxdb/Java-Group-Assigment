package app.manager;
import java.io.*;
import app.model.*;

/**
 * DataManager class handles loading and saving song and playlist data from/to CSV files.
 */
public class DataManager {
    public SongLibrary songLibrary;
    public PlaylistLibrary playlistLibrary;
    
    // Constructor
    public DataManager(SongLibrary songLibrary, PlaylistLibrary playlistLibrary) {
        this.songLibrary = songLibrary;
        this.playlistLibrary = playlistLibrary;
    }

    /**
     * Loads song data from a CSV file and adds it to the song library,using a buffer reader
     * @param fileName The name of the CSV file to load.
     * @throws IOException if there is an issue with reading the file, such as an invalid path or file format.
     */
    public void loadFromCSV(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        reader.readLine();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            String artist = parts[0];
            String  songName = parts[1];
            
            // Create the New Song Object
            Song loadSong = new Song(artist, songName);
            songLibrary.addSongToLibrary(loadSong);
        } 
        reader.close();
    }

    /**
     * Save playlist and song data into a CSV file, using a buffer reader
     * @param fileName The name /path of the CSV file to save.
     * @throws IOException if there is an issue with saving the file.
     */
    public void saveToCSV(String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        try {            
            writer.write("Playlist Name,Song Title,Artist");// Write headers
            writer.newLine();  
    
            /*
             * Loop through the listOfPlaylists
             * if the playlist is empty we say there are no songs and no artist
             * then for each playlist we traverse it and printout the playlist name, title, artist format 
             */
            for (Playlist playlist : playlistLibrary.listOfPlaylists) {
                Node currentNode = playlist.songList.head;
                
                // Handle empty playlist (no songs in the playlist)
                if (currentNode == null) {
                    writer.write(playlist.name + ",No songs,No artist");
                    writer.newLine();
                    continue;  // Move to the next playlist
                }
    
                // Handle playlists with songs
                do {
                    Song song = currentNode.song;
                    writer.write(playlist.name + "," + song.title + "," + song.artist);
                    writer.newLine();  // Move to the next line for the next song
                    currentNode = currentNode.next;
                } 
                while (currentNode != playlist.songList.head);  // Circular list, stop at the head
            }
        } finally {
            writer.close();// Ensure the writer is always closed, even if an exception occurs
        }
    }
}
