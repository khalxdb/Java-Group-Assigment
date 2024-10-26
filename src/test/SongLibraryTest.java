package test;

import app.manager.SongLibrary;
import app.model.Song;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class SongLibraryTest {
    private SongLibrary songLibrary;

    @Before
    public void setUp() {
        songLibrary = new SongLibrary();
    }

    @Test
    public void testAddNewSongUpdatesArtistList() {
        // Initial artist list should be empty
        assertTrue("Initial artist list should be empty", songLibrary.getArtists().isEmpty());

        // Add a song by a new artist
        Song newSong = new Song("artist a", "a" );
        songLibrary.addSongToLibrary(newSong);

        // Get the updated artist list
        ArrayList<String> updatedArtists = songLibrary.getArtists();

        // Check the updated artisti list it should now only contain one artist.
        assertTrue(updatedArtists.contains("artist a"));
        assertEquals(1, updatedArtists.size());

        // Add another song by the same artist, which should not add a new artist to the list
        Song anotherSongSameArtist = new Song("artist a", "b");
        songLibrary.addSongToLibrary(anotherSongSameArtist);

        // Check that the artist list still contains only one unique artist
        updatedArtists = songLibrary.getArtists();
        assertEquals(1, updatedArtists.size());

        // Add a song by a different artist
        Song newArtistSong = new Song("artist b", "c");
        songLibrary.addSongToLibrary(newArtistSong);

        // Get the final updated artist list
        updatedArtists = songLibrary.getArtists();

        // Check that the artist list contains both artists
        assertTrue(updatedArtists.contains("artist b"));
        assertTrue(updatedArtists.contains("artist a"));
        assertEquals(2, updatedArtists.size());
    }
}
