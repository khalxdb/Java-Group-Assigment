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
     * @param song
     * @return true when we sucessfully add songs, false otherwise
     */
    public boolean addSong(Song song) {
        if (songList.contains(song)|| song == null) {
            return false; // song already exist or it's null;
        } 
        else {// new songs, add it to playlist
            songList.addNode(song);
            return true;
        }
    }

    // TODO: Remove song form playlist METHOD

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
}