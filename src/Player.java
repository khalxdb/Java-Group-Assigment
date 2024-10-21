public class Player {
    // The doubly linked list will now hold the current playlist songs
    public DoublyLinkedList songQueue;  // This acts as both the queue and the playlist
    public Node currentSongNode;  // To keep track of the current song

    // Constructor
    public Player() {
        songQueue = new DoublyLinkedList();
        currentSongNode = null;
    }

    // Method: Set the current playlist by converting it into a doubly linked list
    public void setPlaylist(PlayList playlist) {
         // Reset the song queue
        songQueue = playlist.songList; 
        currentSongNode = songQueue.head; 
        System.out.println("Playlist set to: " + playlist.getName());
    }

    // Method: Add a single song to the queue (ad-hoc song, not part of the playlist)
    public void enqueueSong(Song song) {
        songQueue.addNode(song);
        if (currentSongNode == null) {
            currentSongNode = songQueue.head;
        }
        System.out.println(song.getTitle() + " added to the queue.");
    }

    public void clearPlayer() {
        // Clear the playlist and reset the current song node
        songQueue = new DoublyLinkedList();  // Reset the songQueue to an empty list
        currentSongNode = null;  // Reset the current song pointer
        System.out.println("Player has been cleared.");
    }
    
    // Method: Play the current song
    // TODO : Added a Preview Features
    public Song playCurrentSong() {
        if (currentSongNode != null) {
            System.out.println("Playing current song: " + currentSongNode.song.getTitle());
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
    // TODO: THIS MIGHT NEED TO BE CHANGE TO A CIRCULAR LINKED LIST
    public Song playPreviousSong() {
        if (currentSongNode != null) {
            // Move to the previous song, if we're at the head, we circle back to the tail
            currentSongNode = currentSongNode.prev;  // Automatically circles back to tail due to circular nature
            System.out.println("Playing previous song: " + currentSongNode.song.getTitle());
            return currentSongNode.song;
        }
    
        System.out.println("No songs available to play.");
        return null;
    }
    
}
