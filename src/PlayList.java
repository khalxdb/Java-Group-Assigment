public class PlayList {
    String name; 
    DoublyLinkedList songList;
    
    public PlayList(String name){
        this.name = name;
        this.songList = new DoublyLinkedList();
    }

    // Method to add a song to the playlist
    public void addSong(Song song) {
        if (songList.contains(song)) {
            System.out.println("The song '" + song.getTitle() + "' is already in the playlist.");
        } else {
            songList.addNode(song);
            System.out.println("Added '" + song.getTitle() + "' to the playlist '" + name + "'.");
        }
    }

    // Method to display all songs in the playlist
    public void showSongs() {
        if (songList.isEmpty()) {
            System.out.println("The playlist '" + name + "' is empty.");
        } else {
            System.out.println("Songs in playlist '" + name + "':");
            songList.printList(); // Assuming printList displays all songs in the list
        }
    }

    public String getName(){
        return name;
    }

    public DoublyLinkedList getSongPlayList(){
        return songList;
    }

    public int size(){
        return songList.size();
    }
}