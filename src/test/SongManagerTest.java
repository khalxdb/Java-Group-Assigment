package test;
import app.manager.*;
import app.model.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class SongManagerTest {
    public  SongManager songManager;
    public Song a, b, c, d, e;
    public Playlist favorite;

    @Before
    public void setUp() {
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
    public void testAddSongsToLibrary() {
        // Check if the library contains the correct songs
        assertTrue(songManager.songLibrary.listOfSongs.contains(a));
        assertTrue(songManager.songLibrary.listOfSongs.contains(b));
        assertTrue(songManager.songLibrary.listOfSongs.contains(c));
        assertTrue(songManager.songLibrary.listOfSongs.contains(d));
        assertTrue(songManager.songLibrary.listOfSongs.contains(e));

        // Check if the library contains the correct songs
        // There should only be one unique set of songs
        assertFalse(songManager.addSongToLibrary(a));
        assertFalse(songManager.addSongToLibrary(b));
        assertFalse(songManager.addSongToLibrary(c));
        
        assertFalse(songManager.addSongToLibrary(null));
    }
        
    @Test
    public void testCreatePlaylistAndAddSongs() {
        // Create playlist
        songManager.createPlayList("Classic Hits");
        songManager.addSongToPlaylist(a, "Classic Hits");
        songManager.addSongToPlaylist(b, "Classic Hits");
        songManager.addSongToPlaylist(d, "Classic Hits");

        // Check that the playlist contains the expected songs
        Playlist classicHits = songManager.playlistLibrary.findPlaylistByName("Classic Hits");
        assertNotNull(classicHits);
        assertEquals(3, classicHits.size());
        assertTrue(classicHits.songList.contains(a));
        assertTrue(classicHits.songList.contains(b));
        assertTrue(classicHits.songList.contains(d));
    }
    
    @Test
    public void testAddSongByNameToPlaylist() {
        // Create playlist using a name
        songManager.createPlayList("Hits Playlist");
    
        // Add songs by their name
        assertTrue(songManager.addSongToPlaylist("My Way a", "Hits Playlist"));
        assertTrue(songManager.addSongToPlaylist("Fly me to the Moon b", "Hits Playlist"));
        assertTrue(songManager.addSongToPlaylist("That's What I Like c", "Hits Playlist"));
    
        // Fetch the playlist and check the songs have been added
        Playlist hitsPlaylist = songManager.playlistLibrary.findPlaylistByName("Hits Playlist");
        assertNotNull(hitsPlaylist);
    
        assertEquals(3, hitsPlaylist.songList.size());
        assertTrue(hitsPlaylist.songList.contains(a));
        assertTrue(hitsPlaylist.songList.contains(b));
        assertTrue(hitsPlaylist.songList.contains(c));
    }

    @Test
    public void testPlaySongsFromQueue() {
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

    @Test
    public void testAddPlaylist(){
        Playlist pa = new Playlist("a");
        Playlist pb = new Playlist("b");
        Playlist pc = null;
        Playlist pd = new Playlist(null);

        // sucessfully added the songs
        assertTrue(songManager.addPlaylist(pa));
        assertTrue(songManager.addPlaylist(pb));

        // can't added the same songs
        assertFalse(songManager.addPlaylist(pa));
        assertFalse(songManager.addPlaylist(pb));
        
        // Can't added invalid songs
        assertFalse(songManager.addPlaylist(pc));
        assertFalse(songManager.addPlaylist(pd));
    }

    @Test
    public void testRemovePlaylist(){
        Playlist pa = new Playlist("a");
        songManager.addPlaylist(pa);

        songManager.createPlayList("1");
        songManager.createPlayList("2");
        songManager.createPlayList("3");

        assertTrue(songManager.removePlaylist(pa));
        assertTrue(songManager.removePlaylist("1"));
        assertTrue(songManager.removePlaylist("2"));
        assertTrue(songManager.removePlaylist("3"));

        assertFalse(songManager.removePlaylist("1")); // already remove
        assertFalse(songManager.removePlaylist("")); // empty name
        assertFalse(songManager.removePlaylist(null));
    }
}
