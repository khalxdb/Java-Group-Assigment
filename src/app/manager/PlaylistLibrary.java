package app.manager;

import java.util.ArrayList;
import app.model.*;
import app.util.CircularDoublyLinkedList;

/**
 * The PlaylistLibrary class manages a collection of playlists, providing methods to 
 * add, remove, find, and modify playlists and their associated songs.
 */
public class PlaylistLibrary {
    public ArrayList<Playlist> listOfPlaylists;

    //Constructor to initialize the playlist library.
    public PlaylistLibrary() {
        this.listOfPlaylists = new ArrayList<Playlist>();
    }

    /**
     * Creates a new playlist with the given name if it doesn't already exist.
     * @param name The name of the playlist.
     * @return true if the playlist was successfully created, false otherwise.
     */
    public boolean createPlaylist(String name) {
        if (playlistExists(name)) {
            return false;  // Playlist with the same name already exists
        }
        listOfPlaylists.add(new Playlist(name));
        return true;
    }

    /**
     * Adds an existing playlist object to the library if a playlist with the same 
     * name does not already exist.
     * @param playlist The playlist to add.
     * @return true if the playlist was added successfully, false if it already exists or is null.
     */
    public boolean addPlaylist(Playlist playlist) {
        if (playlist == null || playlistExists(playlist.name)) {
            return false;  // Cannot add a null playlist or a duplicate
        }
        listOfPlaylists.add(playlist);
        return true;
    }

    /**
     * Removes a playlist by its name.
     * @param name The name of the playlist to remove.
     * @return true if the playlist was removed, false if it was not found.
     */
    public boolean removePlaylist(String name) {
        Playlist playlistToRemove = findPlaylistByName(name);
        if (playlistToRemove == null) {
            return false;  // Playlist does not exist
        }
        listOfPlaylists.remove(playlistToRemove);
        return true;
    }

    /**
     * Removes a playlist object from the library.
     * @param playlist The playlist to remove.
     * @return true if the playlist was successfully removed, false if it was not found.
     */
    public boolean removePlaylist(Playlist playlist) {
        if (playlist == null || !listOfPlaylists.remove(playlist)) {
            return false;  // Cannot remove a null playlist or a non-existent playlist
        }
        return true;
    }

    /**
     * Checks if a playlist with the specified name exists in the library.
     * @param playlistName The name of the playlist.
     * @return true if a playlist with the given name exists, false otherwise.
     */
    public boolean playlistExists(String playlistName) {
        return findPlaylistByName(playlistName) != null;
    }

    /**
     * Finds a specific playlist by name with case sensitivity.
     * @param playlistName The name of the playlist to find.
     * @return The Playlist object if found, otherwise null.
     */
    public Playlist findPlaylistByName(String playlistName) {
        for (Playlist playlist : listOfPlaylists) {
            if (playlist.name.equals(playlistName)) {  // Case-sensitive check
                return playlist;
            }
        }
        return null;  // Playlist not found
    }

    /**
     * Retrieves the songs in a specified playlist.
     * @param playlistName The name of the playlist.
     * @return CircularDoublyLinkedList containing the songs in the playlist, or null if not found.
     */
    public CircularDoublyLinkedList getSongPlayList(String playlistName) {
        Playlist playlist = findPlaylistByName(playlistName);
        if (playlist != null) {
            return playlist.songList;
        }
        return null;
    }

    /**
     * Adds a song to a specified playlist by playlist name.
     * @param song The song to add.
     * @param playlistName The name of the playlist.
     * @return true if the song was added, false if the playlist was not found.
     */
    public boolean addSongToPlaylist(Song song, String playlistName) {
        Playlist playlist = findPlaylistByName(playlistName);
        if (playlist == null) {
            return false;  // Playlist does not exist
        }
        playlist.addSong(song);
        return true;
    }

    /**
     * Retrieves the names of all available playlists.
     * @return ArrayList of playlist names.
     */
    public ArrayList<String> getPlaylistNames() {
        ArrayList<String> playlistNames = new ArrayList<>();
        for (Playlist playlist : listOfPlaylists) {
            playlistNames.add(playlist.name);
        }
        return playlistNames;
    }
}
