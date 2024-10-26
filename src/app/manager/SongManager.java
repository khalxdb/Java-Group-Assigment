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
     * Method for adding Song to the Song Library
     * @param song song to be added
     * @return true if successfully added false otherwise;
     */
    public boolean addSongToLibrary(Song song) {
        if (songLibrary.addSongToLibrary(song)){
            System.out.println("Added " + song.title + " to the Library");
            return true;
        }
        else{
            System.out.println("Cannot added a null songs");
            return false;
        }
    }

    /**
     * Add an a playlist object to the playlist library
     * @param playlist playlist to be added
     * @return true if successfully added false otherwise;
     */
    public boolean addPlaylist(Playlist playlist){
        if (!playlistLibrary.addPlaylist(playlist)){
            System.out.println("Playlist cannot be added: it may already exist or is invalid.");
            return false;
        }
        else {
            System.out.println("Playlist '" + playlist.name + "' has been successfully added.");
            return true;
        }
    }

    /**
     * Create a new playlist given a string
     * @param name name of teh playlist 
     * @return true if we successfully create a playlist false otherwise
     */
    public boolean createPlayList(String name) {
        if (!playlistLibrary.createPlaylist(name)) {
            System.out.println("Playlist with name '" + name + "' already exists.");
            return false;
        }
        return true;
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
        } 
        else {
            System.out.println("Playlist '" + playlistName + "' not found.");
        }
        return addedSuccessfully;
    }

    /**
     * Removes a playlist from the playlist library by name or Playlist object.
     * @param input Either the name of the playlist (String) or the Playlist object itself.
     * @return true if the playlist was successfully removed, false otherwise.
     */
    public boolean removePlaylist(Object input) {
        boolean result = playlistLibrary.removePlaylist(input); // Delegate to PlaylistLibrary

        // Inform the user about the success or failure of the operation
        if (result) {
            System.out.println("Playlist removed.");
        } else {
            System.out.println("Playlist not found or cannot be removed.");
        }
        
        return result; // Return the result for further handling if needed
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


    /**
     * Put a songs into queue
     * @param song The song object for putting into queue
     */
    public void enqueueSong(Song song) {
        if (song == null){ // invalid songs
            System.out.println("Can't enqueue a null songs");
        }
        
        if (player.songQueue.contains(song)){
            System.out.println(song.title + " is already in the queue.");
        }else {
            player.enqueueSong(song);  // Add the song to the queue
            System.out.println(song.title + " has been added to the queue.");
        }
    }

    /**
     * Plays the current song in the queue.
     * @return The Song object representing the current song being played.
     */
    public Song playCurrentSong() {
        Song currentSong = player.playCurrentSong();
        if (currentSong != null) {
            System.out.println("Now playing: " + currentSong.title + " by " + currentSong.artist);
        } else {
            System.out.println("No song is currently playing.");
        }
        return currentSong;
    }

    /**
     * Play the next song in the queue.
     * @return The Song object representing the next song being played.
     */
    public Song playNextSong() {
        Song nextSong = player.playNextSong();
        if (nextSong != null) {
            System.out.println("Playing next song: " + nextSong.title + " by " + nextSong.artist);
        } else {
            System.out.println("No next song is available to play.");
        }
        return nextSong;
    }

    /**
     * Goes back to and plays the previous song in the queue.
     * @return The Song object representing the previous song being played.
     */
    public Song playPreviousSong() {
        Song previousSong = player.playPreviousSong();
        if (previousSong != null) {
            System.out.println("Playing previous song: " + previousSong.title + " by " + previousSong.artist);
        } else {
            System.out.println("No previous song is available to play.");
        }
        return previousSong;
    }

    /**
     * Clears the player queue, removing all songs from the current play queue.
     */
    public void clearPlayer() {
        player.clearPlayer();
        System.out.println("Player queue has been cleared.");
    }

    /**
     * Shuffles the songs in the player queue.
     */
    public void shuffle() {
        player.shuffle();
        System.out.println("Player queue has been shuffled.");
    }

    /**
     * Method to display all songs in the song library
     */
    public void showSongs() {
        System.out.println("Songs in the library:");
        for (int i = 0 ; i < songLibrary.listOfSongs.size();i++){
            Song song = songLibrary.listOfSongs.get(i);
            System.out.println(i  + " " + song.toString());
        }
    }

    /**
     * Method to display all artists in the song library
     */
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

    /**
     * show all the available playlist in the playlist library;
     */
    public void showPlayList() {
        ArrayList<String> playlistName = playlistLibrary.getPlaylistNames();
        if (playlistName == null){
            System.err.println("There is no playlist");
        }
        for (int i = 0 ; i < playlistName.size(); i++){
            System.out.println(i + " " + playlistName.get(i));
        }
    }

    /**
     * Displays all songs in a specified playlist.
     * @param playlistName The String Playlist name for showing all the songs 
     */
    public void showPlayListSong(String playlistName) {
        Playlist playlist = playlistLibrary.findPlaylistByName(playlistName);
        if (playlist != null) {
            playlist.showSongs();
        } else {
            System.out.println("Playlist not found.");
        }
    }

    /**
     * Loads song and playlist data from a specified CSV file.
     * Displays a success message upon successful load or an error message if loading fails.
     * 
     * @param fileName The name of the CSV file to load data from.
     * @throws IOException if there is an issue with reading the file, such as an invalid path or file format.
     */
    public void loadFromCSV(String fileName) throws IOException {
        try {
            dataManager.loadFromCSV(fileName);
            System.out.println("Data successfully loaded from " + fileName);
        } catch (IOException e) {
            System.err.println("Error: Could not load data from file '" + fileName + "'. Please check the file path and format.");
            throw e; // Continue throwing for further handling if needed
        }
    }

    /**
     * Saves the current song and playlist data to a specified CSV file.
     * Displays a success message upon successful save or an error message if saving fails.
     * 
     * @param fileName The name of the CSV file to save data to.
     * @throws IOException if there is an issue with writing to the file, such as lack of write permissions.
     */
    public void saveToCSV(String fileName) throws IOException {
        try {
            dataManager.saveToCSV(fileName);
            System.out.println("Data successfully saved to " + fileName);
        } catch (IOException e) {
            System.err.println("Error: Could not save data to file '" + fileName + "'. Please check if the file is accessible.");
            throw e; // Re-throw to allow higher-level handling
        }
    }

    /**
     * Get a songs from the song library at specific index within the ArrayList of Song
     * @param n the index 
     * @return the songs at that index, otherwise return null;
     */
    public Song getSongAtIndex(int n) {
        if (n >= 0 && n < songLibrary.listOfSongs.size()) {
            return songLibrary.listOfSongs.get(n);
        } 
        else {
            System.out.println("Index out of range.");
            return null;
        }
    }

    /**
     * Get the Artist at specific index within the SongLibrary
     * @param n the index
     * @return String the artist name, otherwise null
     */
    public String getArtistAtIndex(int n){
        // Validate index within the artist list bounds
        if (n >= 0 && n < songLibrary.getArtists().size()) {
            return songLibrary.getArtists().get(n);
        }
        else{
            System.out.println("Index out of range.");
            return null;
        }
    }

    /**
     * Get a songs from the PlaylistLibrary at specific index within the ArrayList of Playlist
     * @param n the index 
     * @return the playlist at that index, otherwise return null;
     */
    public Playlist getPlaylistAtIndex(int n){
        if (n >= 0 && n < playlistLibrary.listOfPlaylists.size()) {
            return playlistLibrary.listOfPlaylists.get(n);
        } 
        else {
            System.out.println("Index out of range.");
            return null;
        }
    }

    // Getting the playlists in the playlistlibrary
    public ArrayList<Playlist> getPlaylists() {
        return playlistLibrary.listOfPlaylists;
    }

    // Getting the songlists in the songlibrary
    public ArrayList<Song> getListofSong() {
        return songLibrary.listOfSongs;
    }

    // print the current queue
    public void printQueue() {
        player.songQueue.printList();
    }

    // Method for getting song queue
    public CircularDoublyLinkedList getSongQueue(){
        return player.songQueue;
    }

    // Method for getting current Song Node
    public Node getCurSongNode() {
        return player.currentSongNode;
    }
}

