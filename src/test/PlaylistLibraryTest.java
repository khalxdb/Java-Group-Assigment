package test;

import app.manager.PlaylistLibrary;
import app.model.Playlist;
import app.model.Song;

import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PlaylistLibraryTest {
    public PlaylistLibrary playlistLibrary;
    public Playlist playlistA;
    public Playlist playlistB;

    @Before
    public void setUp() {
        playlistLibrary = new PlaylistLibrary();
        playlistA = new Playlist("Favorites");
        playlistB = new Playlist("Workout");
        playlistLibrary.addPlaylist(playlistA);
        playlistLibrary.addPlaylist(playlistB);
    }

    @Test
    public void testCreatePlaylist() {
        // Create a new unique playlist
        assertTrue(playlistLibrary.createPlaylist("Chill Vibes"));
        assertNotNull(playlistLibrary.findPlaylistByName("Chill Vibes"));

        // Create a duplicate playlist
        assertFalse(playlistLibrary.createPlaylist("Favorites"));

        // Edge cases
        assertFalse(playlistLibrary.createPlaylist(""));   // Empty name
        assertFalse(playlistLibrary.createPlaylist(null)); // Null name
    }

    @Test
    public void testAddPlaylist() {
        Playlist playlistC = new Playlist("Party Hits");

        // Add a new unique playlist
        assertTrue(playlistLibrary.addPlaylist(playlistC));
        assertTrue(playlistLibrary.listOfPlaylists.contains(playlistC));

        // Attempt to add a duplicate playlist by name
        Playlist duplicatePlaylist = new Playlist("Favorites");
        assertFalse(playlistLibrary.addPlaylist(duplicatePlaylist));

        // Edge cases
        assertFalse(playlistLibrary.addPlaylist(null));  // Null playlist
        assertFalse(playlistLibrary.addPlaylist(new Playlist(null))); // Null name
    }

    @Test
    public void testRemovePlaylistByName() {
        // Remove an existing playlist by name
        assertTrue(playlistLibrary.removePlaylist("Favorites"));
        assertNull(playlistLibrary.findPlaylistByName("Favorites"));

        // Attempt to remove a non-existing playlist by name
        assertFalse(playlistLibrary.removePlaylist("NonExistent"));

        // Edge cases
        assertFalse(playlistLibrary.removePlaylist(null));  // Null name
        assertFalse(playlistLibrary.removePlaylist(""));    // Empty name
    }

    @Test
    public void testRemovePlaylistByObject() {
        // Successfully remove an existing playlist by object
        assertTrue(playlistLibrary.removePlaylist(playlistB));
        assertNull(playlistLibrary.findPlaylistByName("Workout"));

        // Remove a playlist that isn't in the library
        Playlist nonExistingPlaylist = new Playlist("Relax");
        assertFalse(playlistLibrary.removePlaylist(nonExistingPlaylist));

        // Edge cases
        assertFalse(playlistLibrary.removePlaylist(null));  // Null playlist
    }

    @Test
    public void testPlaylistExists() {
        // Check for existing playlists
        assertTrue(playlistLibrary.playlistExists("Favorites"));
        assertTrue(playlistLibrary.playlistExists("Workout"));

        // Check for non-existing playlist
        assertFalse(playlistLibrary.playlistExists("NonExistent"));

        // Edge cases
        assertFalse(playlistLibrary.playlistExists(null));   // Null name
        assertFalse(playlistLibrary.playlistExists(""));     // Empty name
    }

    @Test
    public void testFindPlaylistByName() {
        // Find existing playlists by name
        assertEquals(playlistA, playlistLibrary.findPlaylistByName("Favorites"));
        assertEquals(playlistB, playlistLibrary.findPlaylistByName("Workout"));

        // Attempt to find a non-existing playlist
        assertNull(playlistLibrary.findPlaylistByName("NonExistent"));

        // Edge cases
        assertNull(playlistLibrary.findPlaylistByName(null)); // Null name
        assertNull(playlistLibrary.findPlaylistByName(""));   // Empty name
    }

    @Test
    public void testAddSongToPlaylist() {
        Song song = new Song("Artist", "Test Song");

        // Add song to an existing playlist
        assertTrue(playlistLibrary.addSongToPlaylist(song, "Favorites"));
        assertTrue(playlistA.songList.contains(song));

        // Attempt to add song to a non-existent playlist
        assertFalse(playlistLibrary.addSongToPlaylist(song, "NonExistent"));

        // Edge cases
        assertFalse(playlistLibrary.addSongToPlaylist(song, null));  // Null playlist name
        assertFalse(playlistLibrary.addSongToPlaylist(null, "Favorites")); // Null song
    }

    @Test
    public void testGetPlaylistNames() {
        // Retrieve playlist names
        ArrayList<String> playlistNames = playlistLibrary.getPlaylistNames();
        assertEquals(2, playlistNames.size());
        assertTrue(playlistNames.contains("Favorites"));
        assertTrue(playlistNames.contains("Workout"));
    }
}
