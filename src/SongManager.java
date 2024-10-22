import java.util.ArrayList;
import java.io.*;
import java.util.*;

public class SongManager {
    public SongLibrary songLibrary;
    public PlaylistManager playlistManager;
    public Player player;

    // Constructor
    public SongManager() {
        this.songLibrary = new SongLibrary();
        this.playlistManager = new PlaylistManager();
        this.player = new Player(); 
    }
    
    // Method for adding Song to the Song Library
    public void addSongToLibrary(Song song) {
        songLibrary.addSongToLibrary(song);
    }

    // Method to add a song by searching for its exact name and adding it to a playlist
    public void addSongByNameToPlaylist(String songName, String playlistName) {
        Song foundSong = findSongByName(songName);

        if (foundSong != null) {
            System.out.println("Adding song to playlist: " + foundSong.getTitle() + " by " + foundSong.getArtist());
            playlistManager.addSongToPlayList(foundSong, playlistName);  // Add the song to the playlist
        } else {
            System.out.println("No song found with the name: " + songName);
        }
    }

    // Helper method to find a song by its exact name in the static SongLibrary
    public Song findSongByName(String name) {
        for (Song song : SongLibrary.songLibrary) {  // Access static songLibrary
            if (song.title.equalsIgnoreCase(name)) {  // Case-insensitive exact match
                return song;  // Return the matching song
            }
        }
        return null;  // No match found
    }

    // Method to display all songs in the song library
    public void showSongs() {
        songLibrary.showSongs();
    }

    //VK
    public ArrayList<Song> getLibrary() {
        return songLibrary.getLibrary();
    }

    // Method to create a new playlist
    public void createPlayList(String name) {
        playlistManager.createPlayList(name);
    }

    // Method to add a song to a specific playlist
    public void addSongToPlayList(Song song, String playlistName) {
        playlistManager.addSongToPlayList(song, playlistName);
    }

    public void setPlaylist(PlayList playlist){
        player.setPlaylist(playlist);
    }

    // Method to enqueue a song for playing
    public void enqueueSong(Song song) {
        player.enqueueSong(song);
    }

    public void clearPlayer(){
        player.clearPlayer();
    }

    // Method to play the next song in the queue
    public Song playCurrentSong(){
        return player.playCurrentSong();
    }

    public Song playNextSong() {
       return player.playNextSong();
    }

    public Song playPreviousSong() {
        return player.playPreviousSong();
     }

    // Method to display all songs in a playlist
    public void showPlayListSong(String playlistName) {
        playlistManager.showPlayListSong(playlistName);
    }
    
    // METHOD: for getting specific song playlist
    public PlayList getPlayList(String s){
        return playlistManager.getPlayList(s);
    }

    // TODO: Show all the PlayList
    public void showPlayList() {
        playlistManager.showPlayList();
    }

    public void loadFromCSV(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String headerLine = reader.readLine();
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
    
}