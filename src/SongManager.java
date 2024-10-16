import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class SongManager {
    public ArrayList<PlayList> playLists;
    public ArrayList<Song> songLibrary;
    public Queue<Song> songQueue;                                                           //We would be using Queue to manage songs in sequence
    // Constructor
    public SongManager() {
        playLists = new ArrayList<>();   
        songLibrary = new ArrayList<>();
        songQueue = new LinkedList<>(); 
    }

    // TODO: addSong
    // TODO: create Playlist
    // TODO: addSongToPlayList
    // TODO: showPlaylist
    //TODO showSongs
 
    // Method to add a song to the library
    public void addSong(Song song) {
        songLibrary.add(song);
    }

    // Method to create a new playlist
    public void createPlayList(String name) {
        PlayList playList = new PlayList(name);
        playLists.add(playList);
    }

    // Method to add a song to a specific playlist
    public void addSongToPlayList(Song song, String playlistName) {
        for (PlayList playList : playLists) {
            if (playList.getName().equals(playlistName)) {
                playList.addSong(song);
                return;
            }
        }
        System.out.println("Playlist not found");
    }

    // Method to enqueue a song for playing
    public void enqueueSong(Song song) {
        songQueue.add(song);
    }

    // Method to play the next song in the queue
    public void playNextSong() {
        Song nextSong = songQueue.poll();
        if (nextSong != null) {
            System.out.println("Playing: " + nextSong.toString());
        } else {
            System.out.println("No more songs in the queue.");
        }
    }

    // Method to display all songs in a playlist
    public void showPlayList(String playlistName) {
        for (PlayList playList : playLists) {
            if (playList.getName().equals(playlistName)) {
                System.out.println("Songs in playlist: " + playlistName);
                playList.showSong();
                return;
            }
        }
        System.out.println("Playlist not found");
    }

    // Method to display all songs in the song library
    public void showSongs() {
        System.out.println("Songs in the library:");
        for (Song song : songLibrary) {
            System.out.println(song.toString());
        }
    }
}