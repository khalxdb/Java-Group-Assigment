package app.util;
import java.util.ArrayList;
import java.util.Collections;
import app.model.*;

public class Player {
    public CircularDoublyLinkedList songQueue;  // the songQueue for playing our songs
    public Node currentSongNode;  // keeping track of the current song node

    // Constructor
    public Player() {
        songQueue = new CircularDoublyLinkedList();
        currentSongNode = null;
    }

    // Method: Set the current playlist by converting it into a doubly linked list
    public void setPlaylist(Playlist playlist) {
         // Reset the song queue
        clearPlayer();
        this.songQueue = playlist.songList; 
        this.currentSongNode = songQueue.head; 
        System.out.println("Playlist set to: " + playlist.getName());
    }

    // Method: Add song to the queue
    public void enqueueSong(Song song) {
        songQueue.addNode(song);
        if (currentSongNode == null) {
            currentSongNode = songQueue.head;
        }
        System.out.println(song.title + " added to the queue.");
    }

    public void clearPlayer() {
        // Clear the playlist and reset the current song node
        songQueue = new CircularDoublyLinkedList();  // Reset the songQueue to an empty list
        currentSongNode = null;  // Reset the current song pointer
        System.out.println("Player has been cleared.");
    }
    
    // Method: Play the current song
    // TODO : Added a Preview Features
    public Song playCurrentSong() {
        if (currentSongNode != null) {
            System.out.println("Playing current song: " + currentSongNode.song.title + " by " + currentSongNode.song.artist);
            return currentSongNode.song;
        }
        System.out.println("No current song to play.");
        return null;
    }

    // Method: Play the next song in the playlist/queue
    public Song playNextSong() {
        if (currentSongNode != null) {
            // Move to the next song, if we're at the tail, we circle back to the head
            currentSongNode = currentSongNode.next;  // Automatically circles back to head due to circular nature
            System.out.println("Playing next song: " + currentSongNode.song.title);
            return currentSongNode.song;
        }
    
        System.out.println("No songs available to play.");
        return null;
    }
    
    // Method: Play the previous song in the playlist/queue
    public Song playPreviousSong() {
        // Automatically comeback to the same song because of circular linked list
        if (currentSongNode != null) {
            currentSongNode = currentSongNode.prev; 
            System.out.println("Playing previous song: " + currentSongNode.song.title);
            return currentSongNode.song;
        }
        System.out.println("No songs available to play.");
        return null;
    }

    // Method: Shuffle method
    public void shuffle(){
        if (songQueue.head == null){
            System.out.println("No song to shuffle");
            return;
        }

        // Extract song into an arraylist and shuffle it
        ArrayList<Song> songList = new ArrayList<Song>();
        Node current = songQueue.head;
        do{
            songList.add(current.song);
            current = current.next;
        }while(current != songQueue.head);

        Collections.shuffle(songList);

        songQueue = new CircularDoublyLinkedList();

        for (Song song : songList) {
            songQueue.addNode(song);
        }

        currentSongNode = songQueue.head;
        System.out.println("Songs have been shuffled.");

    }
    
}
