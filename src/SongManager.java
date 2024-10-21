import java.util.ArrayList;

public class SongManager {
    public SongLibrary songLibrary;
    public PlaylistManager playlistManager;
    public Player player;

    // Constructor
    public SongManager() {
        this.songLibrary = new SongLibrary();
        this.playlistManager = new PlaylistManager();
        this.player = new Player(); 
    }
    
    public void addSongToLibrary(Song song) {
        songLibrary.addSongToLibrary(song);
    }

    // Method to display all songs in the song library
    public void showSongs() {
        songLibrary.showSongs();
    }

    //VK
    public ArrayList<Song> getLibrary() {
        return songLibrary.getLibrary();
    }

    // Method to create a new playlist
    // TODO: Make a false stated for adding playlist with the same name
    public void createPlayList(String name) {
        playlistManager.createPlayList(name);
    }

    // Method to add a song to a specific playlist
    public void addSongToPlayList(Song song, String playlistName) {
        playlistManager.addSongToPlayList(song, playlistName);
    }

    public void setPlaylist(PlayList playlist){
        player.setPlaylist(playlist);
    }

    // Method to enqueue a song for playing
    public void enqueueSong(Song song) {
        player.enqueueSong(song);
    }

    public void clearPlayer(){
        player.clearPlayer();
    }

    // Method to play the next song in the queue
    public Song playCurrentSong(){
        return player.playCurrentSong();
    }

    public Song playNextSong() {
       return player.playNextSong();
    }

    public Song playPreviousSong() {
        return player.playPreviousSong();
     }

    // Method to display all songs in a playlist
    public void showPlayListSong(String playlistName) {
        playlistManager.showPlayListSong(playlistName);
    }
    
    // METHOD: for getting specific song playlist
    public PlayList getPlayList(String s){
        return playlistManager.getPlayList(s);
    }

    // TODO: Show all the PlayList
    public void showPlayList() {
        playlistManager.showPlayList();
    }
    
}