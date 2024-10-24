package app.model;
import app.util.*;
public class Playlist {
    public String name; 
    public CircularDoublyLinkedList songList;
    
    public Playlist(String name){
        this.name = name;
        this.songList = new CircularDoublyLinkedList();
    }

    // Method to add a song to the playlist
    public boolean addSong(Song song) {
        // check if song is already in playlists
        if (songList.contains(song)) {
            System.out.println("The song " + song.title + " is already in the playlist.");
            return true;
        
        } else {// a new unique songs added it to the songlist
            songList.addNode(song);
            System.out.println("Added " + song.title + " to the playlist " + this.getName() + ".");
            return false;
        }
    }

    // TODO: Remove Song Method

    // Method to display all songs in the playlist
    public void showSongs() {
        if (songList.head == null) {
            System.out.println("No songs in the playlist.");
            return;
        }

        Node current = songList.head;
        System.out.println("Songs in playlist '" + name + "':");
        int countIdx = 0;
        do {
            System.out.println(countIdx + ". " + current.song.title + " by " + current.song.artist);
            current = current.next;
            countIdx++;
        }while (current != songList.head);
        System.out.println();
    }


    public String getName(){
        return "'" + name + "'";
    }

    public CircularDoublyLinkedList getSongPlayList(){
        return songList;
    }

    public int size(){
        return songList.size();
    }
}