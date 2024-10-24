package app.manager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Collections;
import app.model.*;
import app.util.*;

public class SongLibrary {
    // The Song Library Class Roles is for handling all the possible song
    public ArrayList<Song> listOfSongs;
    public ArrayList<String> listOfArtist;

    // Constructor
    public SongLibrary(){
        listOfSongs = new ArrayList<Song>();
        listOfArtist = new ArrayList<>();
    }

    // addSong to Library
    public boolean addSongToLibrary(Song song) {
        if (song == null){
            System.out.println("Can't added a Null Song");
            return false;

        }else if(listOfSongs.contains(song)){
            System.out.println("Song Already Exist");
            return false;
        }
        listOfSongs.add(song);
        System.out.println("Added " + song.title + "to the Library");
        return true;
    }

    // adding song by string name instead
    public boolean addSongToLibrary(String songName) {
        if (songName == null){
            System.out.println("Can't added a Null");
            return false;

        }else if(findSongByName(songName) == null){
            System.out.println("Song is not found");
            return false;
        }
        Song foundSong = findSongByName(songName);
        listOfSongs.add(foundSong);
        System.out.println("Added " + foundSong.title + "to the Library");
        return true;
    }
    
    // Method to find a song by its exact name in the library
    // Method: Find a song by its exact name in the library (returns null if not found)
    public Song findSongByName(String songName) {
        if (songName == null || songName.isEmpty()) {
            return null; 
        }

        for (Song song : this.listOfSongs) {
            if (song.title.equalsIgnoreCase(songName)) {
                return song;
            }
        }
        return null;  // No match found
    }

    public void getArtist() {
        // Use a Set to store unique artists
        Set<String> artistSet = new HashSet<>();  
        
        for (Song song : listOfSongs) {
            // Add each artist to the Set , duplicates are automatically handled
            artistSet.add(song.artist);  
        }
        
        // Clear the existing listOfArtist and add all unique artists from the Set
        listOfArtist.clear();
        listOfArtist.addAll(artistSet);
        Collections.sort(listOfArtist);
    }

    // Method: Find a song by artist (returns null if not found)
    public CircularDoublyLinkedList findSongByArtist(String artist) {
        CircularDoublyLinkedList artistSong = new CircularDoublyLinkedList();
        if (artist == null || artist.isEmpty()) {
            return null; 
        }

        for (Song song : listOfSongs) {
            if (song.artist.equalsIgnoreCase(artist)) {
                artistSong.addNode(song);
            }
        }
        return artistSong;
    }

   // method to display all songs in the song library
    public void showSongs() {
        System.out.println("Songs in the library:");
        for (int i = 0 ; i < listOfSongs.size();i++){
            Song song = listOfSongs.get(i);
            System.out.println(i  + " " + song.toString());
        }
    }

    public void showArtist() {
        getArtist();  // Ensure the artist list is updated
        System.out.println("Artists in the library:");

        if (listOfArtist.isEmpty()) {
            System.out.println("No artists found in the library.");
            return;
        }
        
        for (int i = 0; i < listOfArtist.size(); i++) {
            System.out.println(i + ". " + listOfArtist.get(i)); 
        }
    }

    
}
