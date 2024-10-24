package app.model;
public class Node {
    public Song song;
    public Node next;
    public Node prev;
    
    // Constructor take a song class object
    public Node(Song song){
        this.song = song;
        this.next = null;
        this.prev = null;
    }
}
