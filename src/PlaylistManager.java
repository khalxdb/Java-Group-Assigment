import java.util.ArrayList;

public class PlaylistManager {
    public ArrayList<PlayList> listOfPlayList;
    // TODO : Make a new playlist manager class

    // constructor
    public PlaylistManager() {
        listOfPlayList = new ArrayList<>();
    }

    // METHOD: createPlayList
    public boolean createPlayList(String name) {
        PlayList playList = new PlayList(name);
        listOfPlayList.add(playList);
        return true;
    }

    // METHOD : addSongToPlayList
    public void addSongToPlayList(Song song, String playlistName) {
        for (PlayList playList : listOfPlayList) {
            if (playList.getName().equals(playlistName)) {
                playList.addSong(song);
                return;
            }
        }
        System.out.println("Playlist not found");
    }

    // METHOD : show song in playlist
    public void showPlayListSong(String playlistName) {
        for (PlayList playList : listOfPlayList) {
            if (playList.getName().equals(playlistName)) {
                System.out.println("Songs in playlist: " + playlistName);
                playList.showSong();
                return;
            }
        }
        System.out.println("Playlist not found");
    }

    //METHOD: Method for getting specific song playlist
    public PlayList getPlayList(String playlistName) {
        for ( PlayList playlist : listOfPlayList){
            if (playlist.getName().equals(playlistName)) {  
                return playlist;  // Return the matching playlist
            }
        }
        // throw an exception that the playlist is not found
        throw new IllegalArgumentException("Playlist with name '" + playlistName + "' not found."); 
    }

    // METHOD: GET All the song in the specific playlist
    public DoublyLinkedList getSongPlayList(String playlistName){
        PlayList currentPlaylist = this.getPlayList(playlistName);
        return currentPlaylist.getSongPlayList();
    }

    public void showPlayList(){
        for (PlayList playlist : listOfPlayList){
            System.out.println('-'+ playlist.getName());
        }   
    }

}
