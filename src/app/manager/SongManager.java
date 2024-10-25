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
        this.dataManager = new DataManager(this.songLibrary,this.playlistManager);
    }

    // Method: For playing a playlist
    public void playPlaylist(String playlistName) {
        Playlist playlist = playlistManager.findPlaylistbyName(playlistName);
        if (playlist != null) {
            player.setPlaylist(playlist);
            player.playCurrentSong();
        } 
        else {
            System.out.println("Playlist not found.");
        }
    }

    // Method: For playing a playlist
    public void playPlaylist(Playlist playlist) {
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


    // Method to add a song by searching for its exact name and adding it to a playlist
    public boolean addSongByNameToPlaylist(String songName, String playlistName) {
        // Make sure that it exist in the first places
        Song foundSong = songLibrary.findSongByName(songName);
        if (foundSong != null) {
            // Add the song to the playlist
            playlistManager.addSongToPlayList(foundSong, playlistName);
            return true;  
        } 
        else {
            System.out.println("No song found with the name: " + songName);
            return false;
        }
    }

    // Method to display all songs in the song library
    public void showSongs() {
        songLibrary.showSongs();
    }

    public void showArtist(){
        songLibrary.showArtist();
    }

    // Creating Playlists
    public void createPlayList(String name) {
        playlistManager.createPlaylist(name);
    }
    public void createPlayList(Playlist playlist) {
        playlistManager.createPlaylist(playlist);
    }
    
    //Removing Playlist
    public void removePlaylist(String name){
        playlistManager.removePlaylist(name);
    }
    public void removePlaylist(Playlist playlist){
        playlistManager.removePlaylist(playlist);
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
    public Song playCurrentSong(){
        return player.playCurrentSong();
    }

    public Song playNextSong() {
       return player.playNextSong();
    }

    public Song playPreviousSong() {
        return player.playPreviousSong();
    }

    public void clearPlayer(){
        player.clearPlayer();
    }

    public void shuffle(){
        player.shuffle();
    }

    // Method to display all songs in a playlist
    public void showPlayListSong(String playlistName) {
        playlistManager.findPlaylistbyName(playlistName).showSongs();
    }
    public void showPlayListSong(Playlist playlistName) {
        playlistName.showSongs();;
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

    public ArrayList<Playlist> getPlaylists(){
        return playlistManager.listOfPlayLists;
    }
    
    public ArrayList<Song> getListofSong(){
        return songLibrary.listOfSongs;
    }
    // Method getting to songs from the song library at particular index
    public Song getSongAtIndex(int n){
        return songLibrary.listOfSongs.get(n);
    }

    public void printQueue (){
        player.songQueue.printList();
    }
    
}



