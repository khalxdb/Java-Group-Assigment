public class PlayList {
    String name; 
    CircularDoublyLinkedList songList;
    
    public PlayList(String name){
        this.name = name;
        this.songList = new CircularDoublyLinkedList();
    }

    // Method to add a song to the playlist
    public boolean addSong(Song song) {
        // check if song is already in playlists
        if (songList.contains(song)) {
            System.out.println("The song " + song.getTitle() + " is already in the playlist.");
            return true;
        
        } else {// a new unique songs added it to the songlist
            songList.addNode(song);
            System.out.println("Added " + song.getTitle() + " to the playlist " + this.getName() + ".");
            return false;
        }
    }

    // Method to display all songs in the playlist
    public void showSongs() {
        if (songList.isEmpty()) {
            System.out.println("No songs in the playlist.");
            return;
        }

        Node current = songList.head;
        System.out.println("Songs in playlist '" + name + "':");
        int count = 1;
        do {
            System.out.println(count + ". " + current.song.getTitle() + " by " + current.song.getArtist());
            current = current.next;
            count++;
        }while (current != null);

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