import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SongManagerTest {

    public  SongManager songManager;
    public Song a, b, c, d, e;
    public PlayList favorite;

    @BeforeEach
    void setUp() {
        songManager = new SongManager();
        
        // Create songs
        a = new Song("Frank Sinatra","My Way a ");
        b = new Song("Frank Sinatra","Fly me to the Moon b ");
        c = new Song("Bruno Mars","That's What I Like c ");
        d = new Song("Queen", "Bohemian Rhapsody d ");
        e = new Song("The Beatles", "Let it Be e ");
        
        // Add songs to song library
        songManager.addSongToLibrary(a);
        songManager.addSongToLibrary(b);
        songManager.addSongToLibrary(c);
        songManager.addSongToLibrary(d);
        songManager.addSongToLibrary(e);

        // Create playlist and add songs to the playlist
        favorite = new PlayList("favorite");
        favorite.addSong(a);
        favorite.addSong(b);
        favorite.addSong(c);
    }

    @Test
    void testAddSongsToLibrary() {
        // Check if the library contains the correct songs
        assertTrue(songManager.getLibrary().contains(a));
        assertTrue(songManager.getLibrary().contains(b));
        assertTrue(songManager.getLibrary().contains(c));
        assertTrue(songManager.getLibrary().contains(d));
        assertTrue(songManager.getLibrary().contains(e));
    }

    @Test
    void testCreatePlaylistAndAddSongs() {
        // Create playlist
        songManager.createPlayList("Classic Hits");
        songManager.addSongToPlayList(a, "Classic Hits");
        songManager.addSongToPlayList(b, "Classic Hits");
        songManager.addSongToPlayList(d, "Classic Hits");

        // Check that the playlist contains the expected songs
        PlayList classicHits = songManager.getPlayList("Classic Hits");
        assertNotNull(classicHits);
        assertEquals(3, classicHits.getSongPlayList().size());
        assertTrue(classicHits.getSongPlayList().contains(a));
        assertTrue(classicHits.getSongPlayList().contains(b));
        assertTrue(classicHits.getSongPlayList().contains(d));
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
        assertEquals(c, songManager.playNextSong()); // Queue should be empty now print out message:Reached the end of the playlist 

        // Test playing the previous song
        assertEquals(b, songManager.playPreviousSong()); 
        assertEquals(a, songManager.playPreviousSong()); 
        assertNull(songManager.playPreviousSong()); // No more previous songs, should return null
    }

    @Test
    void testShowSongsInPlayList() {
        // Check playlist has the correct songs
        favorite.showSong();
        
        songManager.setPlaylist(favorite);
        assertEquals(3, favorite.getSongPlayList().size());
        assertTrue(favorite.getSongPlayList().contains(a));
        assertTrue(favorite.getSongPlayList().contains(b));
        assertTrue(favorite.getSongPlayList().contains(c));
    }

}
