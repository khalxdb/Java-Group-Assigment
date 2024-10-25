package app.manager;
import java.io.IOException;
import java.util.ArrayList;
import app.util.*;
import app.model.*;

/**
 * The SongManager class serves as a primary interface for interacting with the song library, playlists, 
 * and player functionalities, as well as managing presentation-related tasks.
 */
public class SongManager {
    public SongLibrary songLibrary;
    public PlaylistLibrary playlistLibrary;
    public Player player;
    public DataManager dataManager;

    // Constructor
    public SongManager() {
        this.songLibrary = new SongLibrary();
        this.playlistLibrary = new PlaylistLibrary();
        this.player = new Player();
        this.dataManager = new DataManager(this.songLibrary, this.playlistLibrary);
    }

    /**
     * Method to play a playlist by name or by playlist object.
     * 
     * @param input Playlist name (String) or Playlist object
     */
    public void playPlaylist(Object input) {
        Playlist playlist = null; // initialized a Playlist

        if (input instanceof String){
            String playlistName = (String) input; // Cast the input into a playlistName (String);
            playlist = playlistLibrary.findPlaylistByName(playlistName); // find the playlist
        }else if ( input instanceof Playlist){
            playlist = (Playlist) input;
        }

        if (playlist != null){
            // Set the playlist in the player and play the first song
            player.setPlaylist(playlist);
            player.playCurrentSong();
        } 
        else {
            System.out.println("Playlist not found.");
        }
    }

    // Method for adding Song to the Song Library
    public boolean addSongToLibrary(Song song) {
        if (songLibrary.addSongToLibrary(song)){
            System.out.println("Added " + song.title + " to the Library");
            return true;
        }else{
            System.out.println("Cannot added a null songs");
            return false;
        }
    }

    // Method for adding playlist
    public void addPlaylist(Playlist playlist){
        if (!playlistLibrary.addPlaylist(playlist)){
            System.out.println("Playlist cannot be added: it may already exist or is invalid.");
        }
        else {
            System.out.println("Playlist '" + playlist.name + "' has been successfully added.");
        }
    }

    /**
     * Adds a song to a specified playlist by song name.
     * @param songName The name of the song to add.
     * @param playlistName The name of the playlist to which the song will be added.
     * @return true if the song is added successfully; false otherwise.
     */
    public boolean addSongToPlaylist(String songName, String playlistName) {
        Song foundSong = songLibrary.findSongByName(songName);
        if (foundSong == null) {  // Song name was not found
            System.out.println("No song found with the name: " + songName);
            return false;
        }
        return addSongToPlaylist(foundSong, playlistName);  // Call the overloaded method with the Song object
    }

    /**
     * Adds a song to a specified playlist by Song object.
     * @param song The Song object to add.
     * @param playlistName The name of the playlist to which the song will be added.
     * @return true if the song is added successfully; false otherwise.
     */
    public boolean addSongToPlaylist(Song song, String playlistName) {
        if (!songLibrary.listOfSongs.contains(song)) {
            System.out.println("The song is not found in the song library.");
            return false;
        }

        boolean addedSuccessfully = playlistLibrary.addSongToPlaylist(song, playlistName);
        if (addedSuccessfully) {
            System.out.println("Song '" + song.title + "' added to playlist '" + playlistName + "'.");
        } else {
            System.out.println("Playlist '" + playlistName + "' not found.");
        }
        return addedSuccessfully;
    }

    // Method to display all songs in the song library
    public void showSongs() {
        System.out.println("Songs in the library:");
        for (int i = 0 ; i < songLibrary.listOfSongs.size();i++){
            Song song = songLibrary.listOfSongs.get(i);
            System.out.println(i  + " " + song.toString());
        }
    }

    // Method to display all song by artist in the song library
    public void showArtist() {
        ArrayList<String> artists = songLibrary.getArtists();  // Ensure the artist list is updated
        System.out.println("Artists in the library:");

        if (artists.isEmpty()) {
            System.out.println("No artists found in the library.");
            return;
        }

        for (int i = 0; i < artists.size(); i++) {
            System.out.println(i + ". " + artists.get(i)); 
        }
    }
    
    // Creating Playlists, we create a playlist by name
    public void createPlayList(String name) {
        if (!playlistLibrary.createPlaylist(name)) {
            System.out.println("Playlist with name '" + name + "' already exists.");
        }
    }

    // Overloading removeplaylists by name and by object
    public void removePlaylist(Object input){
        boolean result = false; // flag for results
        if (input instanceof String){
            String name = (String) input;
            playlistLibrary.removePlaylist(name);
        }
        else{
            Playlist playlist = (Playlist) input;
            playlistLibrary.removePlaylist(playlist);
        }

        if (result){
            System.out.println("Playlist removed.");
        }
        else{
            System.out.println("Playlist not found or cannot be removed.");
        }
    }


    // Method to enqueue a song for playing
    public void enqueueSong(Song song) {
        if (song == null){
            System.out.println("Can't enqueue a null songs");
        }
        player.enqueueSong(song);
        System.out.println(song.title + " Has been put into queue");
    }

    // Method to play the next song in the queue
    public Song playCurrentSong() {
        return player.playCurrentSong();
    }

    // Play the next songs in the queue
    public Song playNextSong() {
        return player.playNextSong();
    }

    // Play the previous songs in the queue
    public Song playPreviousSong() {
        return player.playPreviousSong();
    }

    // clear the player
    public void clearPlayer() {
        player.clearPlayer();
    }

    // shuffle the songs
    public void shuffle() {
        player.shuffle();
    }

    // Displays all songs in a specified playlist.
    public void showPlayListSong(String playlistName) {
        Playlist playlist = playlistLibrary.findPlaylistByName(playlistName);
        if (playlist != null) {
            playlist.showSongs();
        } else {
            System.out.println("Playlist not found.");
        }
    }

    // show songs in playlists
    public void showPlayList() {
        ArrayList<String> playlistName = playlistLibrary.getPlaylistNames();
        if (playlistName == null){
            System.err.println("There is no playlist");
        }
        for (int i = 0 ; i < playlistName.size(); i++){
            System.out.println(i + " " + playlistName.get(i));
        }
    }

    // Load Data form CSV
    public void loadFromCSV(String fileName) throws IOException {
        dataManager.loadFromCSV(fileName);
    }

    // Save Data to CSV
    public void saveToCSV(String fileName) throws IOException {
        dataManager.saveToCSV(fileName);
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlistLibrary.listOfPlaylists;
    }

    public ArrayList<Song> getListofSong() {
        return songLibrary.listOfSongs;
    }

    // Get a songs from the song library at specific index
    public Song getSongAtIndex(int n) {
        if (n >= 0 && n < songLibrary.listOfSongs.size()) {
            return songLibrary.listOfSongs.get(n);
        } else {
            System.out.println("Index out of range.");
            return null;
        }
    }

    // Get a songs from teh playlistlibrary at specific index
    public Playlist getPlaylistAtIndex(int n){
        if (n >= 0 && n < songLibrary.listOfSongs.size()) {
            return playlistLibrary.listOfPlaylists.get(n);
        } else {
            System.out.println("Index out of range.");
            return null;
        }
    }

    // print the current queue
    public void printQueue() {
        player.songQueue.printList();
    }
}
