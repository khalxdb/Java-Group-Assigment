public class PlayList {
    String name; 
    DoublyLinkedList songList;
    
    public PlayList(String name){
        this.name = name;
        this.songList = new DoublyLinkedList();
    }

    public void addSong(Song song){
        songList.addNode(song);
    }

    public void showSong(){
        songList.printList();
    }
}