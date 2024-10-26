package app.model;
import app.util.*;
public class Playlist {
    public String name; 
    public CircularDoublyLinkedList songList;
    
    // Constructor
    public Playlist(String name){
        this.name = name;
        this.songList = new CircularDoublyLinkedList();
    }

    /**
     * Method for Adding song to playlist
     * @param song The song object to add to the playlist
     * @return true when we sucessfully add songs, false otherwise
     */
    public boolean addSong(Song song) {
        if (songList.contains(song) || song == null) {
            return false; // song already exist or it's null;
        } 
        else {// new songs, add it to playlist
            songList.addNode(song);
            return true;
        }
    }

    /**
     * Removes a song from the playlist.
     * @param song The song to be removed from the playlist.
     * @return true if the song was removed successfully, false if it was not found.
     */
    public boolean removeSong(Song song) {
        return songList.removeNode(song);
    }

    // Method for showing all the Songs in the playlists
    public void showSongs() {
        if (songList.head == null) { // check if playlists is empty
            System.out.println("No songs in the playlist.");
            return;
        }

        // Traverse the song and each times print out the song index, name and artist.
        Node curNode = songList.head;
        System.out.println("Songs in playlist '" + name + "':");
        int countIdx = 0;
        do {
            System.out.println(countIdx + ". " + curNode.song.title + " by " + curNode.song.artist);
            curNode = curNode.next;
            countIdx++;
        }
        while (curNode != songList.head); // reach our head again break;
        System.out.println();
    }

    // Method for returning the size of the playlists
    public int size(){
        return songList.size();
    }

    /**
     * Retrieves the song at the specified index in the playlist.
     *
     * @param index The index of the song to retrieve.
     * @return The song at the specified index, or null if the index is invalid.
     */
    public Song getSongAtIndex(int index) {
        if (songList == null) {
            return null; // Return null if the song list is not initialized
        }
        
        // Call the get method from CircularDoublyLinkedList to retrieve the song at the specified index
        return songList.get(index);
    }
}