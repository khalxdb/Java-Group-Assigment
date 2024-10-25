package app.manager;
import java.io.*;
import app.model.*;

public class DataManager {
    public SongLibrary songLibrary;
    public PlaylistLibrary playlistLibrary;

    public DataManager(SongLibrary songLibrary, PlaylistLibrary playlistLibrary) {
        this.songLibrary = songLibrary;
        this.playlistLibrary = playlistLibrary;
    }

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

    public void saveToCSV(String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        try {
            // Write headers
            writer.write("Playlist Name,Song Title,Artist");
            writer.newLine();  
    
            // Loop through each playlist
            for (Playlist playlist : playlistLibrary.listOfPlayLists) {
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
    
                    // Handle null song cases
                    if (song != null) {
                        writer.write(playlist.name + "," + song.title + "," + song.artist);
                    } else {
                        writer.write(playlist.name + ",Unknown song,Unknown artist");
                    }
    
                    writer.newLine();  // Move to the next line for the next song
                    currentNode = currentNode.next;
                } while (currentNode != playlist.songList.head);  // Circular list, stop at the head
            }
        } finally {
            // Ensure the writer is always closed, even if an exception occurs
            writer.close();
        }
    }
}
