package app.manager;
import java.io.IOException;
import java.util.ArrayList;
import app.util.*;
import app.model.*;


public class SongManager {
    public SongLibrary songLibrary;
    public PlaylistManager playlistManager;
    public Player player;
    public DataManager dataManager;

    // Constructor
    public SongManager() {
        this.songLibrary = new SongLibrary();
        this.playlistManager = new PlaylistManager();
        this.player = new Player();
        this.dataManager = new DataManager(this.songLibrary, this.playlistManager);
    }

    // Method: For playing a playlist
    public void playPlaylist(String playlistName) {
        Playlist playlist = playlistManager.findPlaylistbyName(playlistName);
        if (playlist != null) {
            player.setPlaylist(playlist);
            player.playCurrentSong();
        } else {
            System.out.println("Playlist not found.");
        }
    }

    // Method for adding Song to the Song Library
    public boolean addSongToLibrary(Song song) {
        return songLibrary.addSongToLibrary(song);
    }

    // Method for adding playlist
    public void addPlaylist(Playlist playlist){
        playlistManager.addPlaylist(playlist);
    }

    // Method to add a song by searching for its exact name and adding it to a playlist
    public boolean addSongByNameToPlaylist(String songName, String playlistName) {
        Song foundSong = songLibrary.findSongByName(songName);
        if (foundSong != null) {
            playlistManager.addSongToPlayList(foundSong, playlistName);
            return true;
        } else {
            System.out.println("No song found with the name: " + songName);
            return false;
        }
    }

    // Method to display all songs in the song library
    public void showSongs() {
        songLibrary.showSongs();
    }

    // Method for showing all the artist
    public void showArtist() {
        songLibrary.showArtist();
    }
    
    // Creating Playlists, we create a playlist by name
    public void createPlayList(String name) {
        playlistManager.createPlaylist(name);
    }

    // Overloading removeplaylists by name and by object
    public void removePlaylist(Object input){
        if (input instanceof String){
            String name = (String) input;
            playlistManager.removePlaylist(name);
        }
        else{
            Playlist playlist = (Playlist) input;
            playlistManager.removePlaylist(playlist);
        }
    }

    // Method to add a song to a specific playlist
    public void addSongToPlayList(Song song, String playlistName) {
        playlistManager.addSongToPlayList(song, playlistName);
        
    }

    // Method to enqueue a song for playing
    public void enqueueSong(Song song) {
        player.enqueueSong(song);
    }

    // Method to play the next song in the queue
    public Song playCurrentSong() {
        return player.playCurrentSong();
    }

    public Song playNextSong() {
        return player.playNextSong();
    }

    public Song playPreviousSong() {
        return player.playPreviousSong();
    }

    public void clearPlayer() {
        player.clearPlayer();
    }

    public void shuffle() {
        player.shuffle();
    }

    // Method to display all songs in a playlist
    public void showPlayListSong(String playlistName) {
        Playlist playlist = playlistManager.findPlaylistbyName(playlistName);
        if (playlist != null) {
            playlist.showSongs();
        } else {
            System.out.println("Playlist not found.");
        }
    }

    public void showPlayList() {
        playlistManager.showPlayList();
    }

    public void loadFromCSV(String fileName) throws IOException {
        dataManager.loadFromCSV(fileName);
    }

    public void saveToCSV(String fileName) throws IOException {
        dataManager.saveToCSV(fileName);
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlistManager.listOfPlayLists;
    }

    public ArrayList<Song> getListofSong() {
        return songLibrary.listOfSongs;
    }

    // Method getting to songs from the song library at particular index
    public Song getSongAtIndex(int n) {
        if (n >= 0 && n < songLibrary.listOfSongs.size()) {
            return songLibrary.listOfSongs.get(n);
        } else {
            System.out.println("Index out of range.");
            return null;
        }
    }

    public Playlist getPlaylistAtIndex(int n){
        if (n >= 0 && n < songLibrary.listOfSongs.size()) {
            return playlistManager.listOfPlayLists.get(n);
        } else {
            System.out.println("Index out of range.");
            return null;
        }
    }

    public void printQueue() {
        player.songQueue.printList();
    }
}
