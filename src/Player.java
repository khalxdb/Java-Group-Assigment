public class Player {
    public CircularDoublyLinkedList songQueue;  // the songQueue for playing our songs
    public Node currentSongNode;  // keeping track of the current song node

    // Constructor
    public Player() {
        songQueue = new CircularDoublyLinkedList();
        currentSongNode = null;
    }

    // Method: Set the current playlist by converting it into a doubly linked list
    public void setPlaylist(PlayList playlist) {
         // Reset the song queue
        songQueue = playlist.songList; 
        currentSongNode = songQueue.head; 
        System.out.println("Playlist set to: " + playlist.getName());
    }

    // Method: Add song to the queue
    public void enqueueSong(Song song) {
        songQueue.addNode(song);
        if (currentSongNode == null) {
            currentSongNode = songQueue.head;
        }
        System.out.println(song.getTitle() + " added to the queue.");
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
            System.out.println("Playing next song: " + currentSongNode.song.getTitle());
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
            System.out.println("Playing previous song: " + currentSongNode.song.getTitle());
            return currentSongNode.song;
        }
    
        System.out.println("No songs available to play.");
        return null;
    }

    // TODO: Shuffle method
    
}
