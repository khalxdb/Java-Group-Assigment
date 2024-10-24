package app.test;
import app.manager.*;
import app.model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SongManagerTest {
    public  SongManager songManager;
    public Song a, b, c, d, e;
    public Playlist favorite;

    @BeforeEach
    void setUp() {
        // Initialized the song manager object
        songManager = new SongManager();
        
        // Create songs
        a = new Song("Frank Sinatra","My Way a");
        b = new Song("Frank Sinatra","Fly me to the Moon b");
        c = new Song("Bruno Mars","That's What I Like c");
        d = new Song("Queen", "Bohemian Rhapsody d");
        e = new Song("The Beatles", "Let it Be e");
        
        // Add songs to Song object library
        songManager.addSongToLibrary(a);
        songManager.addSongToLibrary(b);
        songManager.addSongToLibrary(c);
        songManager.addSongToLibrary(d);
        songManager.addSongToLibrary(e);

        // Create playlist and add songs to the playlist
        favorite = new Playlist("favorite");
        favorite.addSong(a);
        favorite.addSong(b);
        favorite.addSong(c);
    }

    @Test
    void testAddSongsToLibrary() {
        // Check if the library contains the correct songs
        assertTrue(songManager.songLibrary.listOfSongs.contains(a));
        assertTrue(songManager.songLibrary.listOfSongs.contains(b));
        assertTrue(songManager.songLibrary.listOfSongs.contains(c));
        assertTrue(songManager.songLibrary.listOfSongs.contains(d));
        assertTrue(songManager.songLibrary.listOfSongs.contains(e));
    }
    
    @Test
    void testAddSameSong() {
        // Check if the library contains the correct songs
        // There should only be one unique set of songs
        assertFalse(songManager.addSongToLibrary(a));
        assertFalse(songManager.addSongToLibrary(b));
        assertFalse(songManager.addSongToLibrary(c));
        assertFalse(songManager.addSongToLibrary(d));
        assertFalse(songManager.addSongToLibrary(e));

    }

    @Test
    void testCreatePlaylistAndAddSongs() {
        // Create playlist
        songManager.createPlayList("Classic Hits");
        songManager.addSongToPlayList(a, "Classic Hits");
        songManager.addSongToPlayList(b, "Classic Hits");
        songManager.addSongToPlayList(d, "Classic Hits");

        // Check that the playlist contains the expected songs
        Playlist classicHits = songManager.playlistManager.findPlaylistbyName("Classic Hits");
        assertNotNull(classicHits);
        assertEquals(3, classicHits.getSongPlayList().size());
        assertTrue(classicHits.songList.contains(a));
        assertTrue(classicHits.songList.contains(b));
        assertTrue(classicHits.songList.contains(d));
    }
    
    @Test
    void testAddSongByNameToPlaylist() {
        // Create playlist using a name
        songManager.createPlayList("Hits Playlist");
    
        // Add songs by their name
        assertTrue(songManager.addSongByNameToPlaylist("My Way a", "Hits Playlist"));
        assertTrue(songManager.addSongByNameToPlaylist("Fly me to the Moon b", "Hits Playlist"));
        assertTrue(songManager.addSongByNameToPlaylist("That's What I Like c", "Hits Playlist"));
    
        // Fetch the playlist and check the songs have been added
        Playlist hitsPlaylist = songManager.playlistManager.findPlaylistbyName("Hits Playlist");
        assertNotNull(hitsPlaylist);
    
        assertEquals(3, hitsPlaylist.songList.size());
        assertTrue(hitsPlaylist.songList.contains(a));
        assertTrue(hitsPlaylist.songList.contains(b));
        assertTrue(hitsPlaylist.songList.contains(c));
    }

    @Test
    void testPlaySongsFromQueue() {
        // Enqueue songs
        songManager.enqueueSong(a);
        songManager.enqueueSong(b);
        songManager.enqueueSong(c);

        // Test playing the next song
        assertEquals(a, songManager.playCurrentSong()); 
        assertEquals(b, songManager.playNextSong()); 
        assertEquals(c, songManager.playNextSong()); 
        assertEquals(a, songManager.playNextSong()); // circular in nature 

        assertEquals(c, songManager.playPreviousSong());
        assertEquals(b,songManager.playPreviousSong());
        
        // clearing the playlist
        songManager.clearPlayer();

        songManager.enqueueSong(d);  
        songManager.enqueueSong(e);  

        assertFalse(songManager.player.songQueue.contains(a));
        assertFalse(songManager.player.songQueue.contains(b));
        assertFalse(songManager.player.songQueue.contains(c));

        assertEquals(d, songManager.playCurrentSong());  
        assertEquals(e, songManager.playNextSong());     

    }

    // @Test
    // void testShowSongsInPlayList() {
    //     // Check playlist has the correct songs
    //     favorite.showSongs();
        
    //     assertEquals(3, favorite.getSongPlayList().size());
    //     assertTrue(favorite.getSongPlayList().contains(a));
    //     assertTrue(favorite.getSongPlayList().contains(b));
    //     assertTrue(favorite.getSongPlayList().contains(c));
    // }

}
