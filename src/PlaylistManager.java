import java.util.ArrayList;

public class PlaylistManager {
    public static ArrayList<PlayList> listOfPlayLists;

    // Constructor
    public PlaylistManager() {
        listOfPlayLists = new ArrayList<>();
    }

    // METHOD: Create a playlist by name
    public boolean createPlaylist(String name) {
        if (playlistExists(name)) {
            System.out.println("Playlist with name '" + name + "' already exists.");
            return false;
        } else {
            PlayList newPlaylist = new PlayList(name);
            listOfPlayLists.add(newPlaylist);
            System.out.println("Playlist '" + name + "' has been created.");
            return true;
        }
    }

    // METHOD: Adding a playlist using a playlist object
    public boolean createPlaylist(PlayList playlist) {
        if (playlist == null) {
            System.out.println("Cannot add a null playlist.");
            return false;
        }

        if (playlistExists(playlist.getName())) {
            System.out.println("Playlist with name '" + playlist.getName() + "' already exists.");
            return false;
        } else {
            listOfPlayLists.add(playlist);
            System.out.println("Playlist '" + playlist.getName() + "' has been added.");
            return true;
        }
    }

    // METHOD: Remove a playlist by name
    public boolean removePlaylist(String name) {
        if (!playlistExists(name)) {
            System.out.println("Playlist '" + name + "' does not exist.");
            return false;
        }

        PlayList playlistToRemove = getPlayList(name);
        listOfPlayLists.remove(playlistToRemove);
        System.out.println("Playlist '" + name + "' has been removed.");
        return true;
    }
    
    // METHOD : Remove a playlist object
    public boolean removePlaylist(PlayList playlist) {
        if (playlist == null) {
            System.out.println("Cannot remove a null playlist.");
            return false;
        }

        if (playlistExists(playlist.getName())) {
            listOfPlayLists.remove(playlist);
        } 
        System.out.println("Playlist '" + playlist.getName() + "' does not exist.");
        return false;
    }
    

    // Helper Function: Check if a playlist exists
    public boolean playlistExists(String playlistName) {
        return getPlayList(playlistName) != null;
    }

    // Helper Function: Check if playlist name is already taken
    public boolean isPlaylistNameTaken(String playlistName) {
        return getPlayList(playlistName) != null;
    }

    // METHOD: Add a song to a playlist by name
    public boolean addSongToPlayList(Song song, String playlistName) {
        PlayList playlist = getPlayList(playlistName);
        if (playlist != null) {
            playlist.addSong(song);
            System.out.println("Song '" + song.getTitle() + "' added to playlist '" + playlistName + "'.");
            return false;
        } else {
            System.out.println("Playlist '" + playlistName + "' not found.");
            return true;
        }
    }

    //Helper: Method for getting specific playlist
    public PlayList getPlayList(String playlistName) {
        for ( PlayList playlist : listOfPlayLists){
            if (playlist.getName().equals(playlistName)) {  
                return playlist;  // Return the matching playlist
            }
        }
        // Not Found
        System.out.println("Playlist with name '" + playlistName + "' not found.");
        return null; 
    }

    // METHOD: Display songs in a specific playlist
    public void showPlayListSong(String playlistName) {
        PlayList playlist = getPlayList(playlistName);
        if (playlist != null) {
            System.out.println("Songs in playlist '" + playlistName + "':");
            playlist.showSong();
        } else {
            System.out.println("Playlist '" + playlistName + "' not found.");
        }
    }

    // METHOD: GET All the song in the specific playlist
    public DoublyLinkedList getSongPlayList(String playlistName) {
        PlayList playlist = getPlayList(playlistName);
        if (playlist != null) {
            return playlist.songList;
        }
        System.out.println("Playlist '" + playlistName + "' not found.");
        return null;
    }

    // Display all the avialible PlayList
    public void showPlayList() {
        if (listOfPlayLists.isEmpty()) {
            System.out.println("No playlists available.");
        } else {
            System.out.println("Available playlists:");
            for (PlayList playlist : listOfPlayLists) {
                System.out.println("- " + playlist.getName());
            }
        }
    }

}
