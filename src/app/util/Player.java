package app.util;
import java.util.ArrayList;
import java.util.Collections;
import app.model.*;

/**
 * The Player class manages the queue of songs in a playlist, allowing operations 
 * like setting a playlist, enqueuing songs, playing songs, and shuffling.
 */
public class Player {
    public CircularDoublyLinkedList songQueue;  // Queue of Song for playback
    public Node currentSongNode;  // keeping track of the current song node

    // Constructor
    public Player() {
        songQueue = new CircularDoublyLinkedList();
        currentSongNode = null;
    }

    /**
     * Sets the current playlist for the player, replacing any existing queue.
     * @param playlist The playlist to be set for playback.
     */
    public void setPlaylist(Playlist playlist) {
        clearPlayer(); // Clear the queue
        this.songQueue = playlist.songList; 
        this.currentSongNode = songQueue.head; // start at the head of the playlists
    }

    // Another Method Overloading
    public void setPlaylist(CircularDoublyLinkedList queue) {
        clearPlayer(); // Clear the queue
        this.songQueue = queue; 
        this.currentSongNode = songQueue.head; // start at the head of the playlists
    }


    //Clears the song queue and resets the current song pointer.
    public void clearPlayer() {
        songQueue = new CircularDoublyLinkedList();   // Empty song queue
        currentSongNode = null;  // Reset pointer
    }
    
    /**
     * Enqueues a single song at the end of the song queue.
     * @param song The song to be enqueued.
     */
    public void enqueueSong(Song song) {
        if (currentSongNode == null) {
            currentSongNode = songQueue.head;  // Set current node if it's the first song
        }
        songQueue.addNode(song);
    }

    /**
     * Plays the current song in the queue.
     * @return The current Song if available, otherwise null.
     */
    public Song playCurrentSong() {
        if (currentSongNode != null) {
            return currentSongNode.song;
        }
        return null;
    }

    /**
     * Advances to and plays the next song in the queue.
     * @return The next Song in the queue if available, otherwise null.
     */
    public Song playNextSong() {
        if (currentSongNode != null) {
            currentSongNode = currentSongNode.next;  // Automatically circles back to head due to circular nature
            return currentSongNode.song;
        }
        return null;
    }
    
    /**
     * Moves back to and plays the previous song in the queue.
     * @return The previous Song in the queue if available, otherwise null.
     */
    public Song playPreviousSong() {
        if (currentSongNode != null) {
            currentSongNode = currentSongNode.prev; // Automatically comeback to the same song because of circular linked list
            return currentSongNode.song;
        }
        return null;
    }

    /**
     * Shuffles the current song queue, randomizing the order of songs.
     * This can be achieve by turning the SongList into a ArrayList 
     * Use the Shuffle Method and then turn it back into a CircularDoublyLinkedList
     */
    public void shuffle(){
        if (songQueue.head == null){
            return;// No songs to shuffle
        }

         // Collect all songs into a ArrayList, shuffle, and re-add them to the queue
        ArrayList<Song> songList = new ArrayList<Song>();
        Node current = songQueue.head;
        do{
            songList.add(current.song);
            current = current.next;
        }
        while(current != songQueue.head); // reach the head again stop

        Collections.shuffle(songList);// Shuffle the songs

        // Clear the queue and re-add songs in shuffled order
        songQueue = new CircularDoublyLinkedList();
        for (Song song : songList) {
            songQueue.addNode(song);
        }
        currentSongNode = songQueue.head;
    }

    /**
    * Checks if the song queue is empty.
    * @return true if the song queue is empty, false otherwise.
    */
    public boolean isQueueEmpty() {
        return songQueue.head == null;
    }
    
}
