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
        currentSongNode = songQueue.head; 
        System.out.println(song.getTitle() + " added to the queue.");
    }

    // Method: Play the current song
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
        if (currentSongNode != null && currentSongNode.next != null) {
            currentSongNode = currentSongNode.next;  // Move to the next song
            System.out.println("Playing next song: " + currentSongNode.song.getTitle());
            return currentSongNode.song;
        } else if (currentSongNode != null && currentSongNode.next == null) {
            // If we're at the tail, stay at the currentSongNode (end of list)
            System.out.println("Reached the end of the playlist.");
            return currentSongNode.song;
        }
    
        System.out.println("No more songs in the playlist or queue.");
        return null;
    }

    // Method: Play the previous song in the playlist/queue
    // TODO: THIS MIGHT NEED TO BE CHANGE TO A CIRCULAR LINKED LIST
    public Song playPreviousSong() {
        if (currentSongNode != null && currentSongNode.prev != null) {
            currentSongNode = currentSongNode.prev;
            System.out.println("Playing previous song: " + currentSongNode.song.getTitle());
            return currentSongNode.song;
        } else if (currentSongNode == null) {
            // If we've reached the end and want to go backward, start from the tail
            currentSongNode = songQueue.tail;
            if (currentSongNode != null) {
                System.out.println("Playing previous song from tail: " + currentSongNode.song.getTitle());
                return currentSongNode.song;
            }
        }

        System.out.println("No previous songs in the playlist or queue.");
        return null;
    }
}
