import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
public class testing {
    // TODO: WRITE OUT TEST CASE
    // Test adding songs
    SongManager testManager = new SongManager();
    @Test
    public void testAddingSong(){
        Song song1 = new Song(null, null);
        Song song2 = new Song("Vanda" , null);
        Song song3 = new Song(null, "It's time to Rise");

        assertFalse(isValidSong(song1));
        assertFalse(isValidSong(song2));
        assertFalse(isValidSong(song3));
    }

    // make a playlist with the same name? 
    @Test
    public void testPlaylist() {
        String a = "hi";
        String b = "Hi";
        
        assertEquals(true, testManager.createPlayList(a), "Failed to create playlist with name: " + a);
        assertEquals(false, testManager.createPlayList(a), "Should not create a duplicate playlist with name: " + a);
        
    }
}
