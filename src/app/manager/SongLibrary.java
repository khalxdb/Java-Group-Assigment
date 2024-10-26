package app.manager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Collections;
import app.model.*;
import app.util.*;

/**
 * The SongLibrary class manages the collection of songs in the library,
 * supporting operations like adding songs, retrieving unique artists, and
 * searching by song title or artist.
 */
public class SongLibrary {
    public ArrayList<Song> listOfSongs; 
    private ArrayList<String> listOfArtist; // should only be accessible through a method as we need to update it

    // Constructor
    public SongLibrary(){
        listOfSongs = new ArrayList<Song>();
        listOfArtist = new ArrayList<>();
    }

    /**
     * Adds a Song object to the library if it doesn't already exist.
     * 
     * @param song The Song object to be added.
     * @return true if the song was added successfully, false otherwise.
    */
    public boolean addSongToLibrary(Song song) {
        if (song == null || listOfSongs.contains(song)){
            return false;
        }
        listOfSongs.add(song);
        updateArtistList(); // Update artist list after adding a song
        return true;
    }

    /**
     * Adds a song to the library by its title if the song is found.
     * 
     * @param songName The title of the song to add.
     * @return true if the song was added successfully, false if not found.
     */
    public boolean addSongToLibrary(String songName) {
        Song foundSong = findSongByName(songName);
        if (foundSong == null) {
            return false;
        }
        listOfSongs.add(foundSong);
        updateArtistList();
        return true;
    }
    
     /**
     * Finds a song by its exact title in the library.
     * 
     * @param songName The title of the song to search for.
     * @return The Song if found, otherwise null.
     */
    public Song findSongByName(String songName) {
        if (songName == null || songName.isEmpty()) {
            return null; 
        }

        for (Song song : this.listOfSongs) {
            if (song.title.equals(songName)) {
                return song;
            }
        }
        return null;  // No match found
    }

    /**
     * Retrieves a sorted list of unique artist names in the library.
     * 
     * @return A sorted ArrayList of unique artist names.
     */
    public ArrayList<String> getArtists() {
        updateArtistList();
        return new ArrayList<>(listOfArtist); 
    }

    /**
     * Finds songs by a specific artist and returns them in a circular doubly linked list.
     * 
     * @param artistName The artist name to search for.
     * @return A CircularDoublyLinkedList of songs by the specified artist, or an empty list if none found.
     */
    public CircularDoublyLinkedList findSongByArtist(String artistName) {
        CircularDoublyLinkedList artistSongs = new CircularDoublyLinkedList();
        if (artistName == null || artistName.isEmpty()) {
            return artistSongs; 
        }

        for (Song song : listOfSongs) {
            if (song.artist.equalsIgnoreCase(artistName)) {
                artistSongs.addNode(song);
            }
        }
        return artistSongs;
    }

    /**
     * Updates the list of unique artists based on the current songs in the library.
     */
    private void updateArtistList() {
        Set<String> artistSet = new HashSet<>();
        for (Song song : listOfSongs) {
            artistSet.add(song.artist);
        }
        listOfArtist.clear(); // clear the current one for update.
        listOfArtist.addAll(artistSet);
        Collections.sort(listOfArtist);
    }

    /**
     * Returns all songs in the library.
     * @return An ArrayList of all songs.
     */
    public ArrayList<Song> getAllSongs() {
        return new ArrayList<>(listOfSongs); // Return a copy for encapsulation
    }
}
