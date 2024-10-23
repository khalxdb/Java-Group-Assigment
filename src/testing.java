import static org.junit.jupiter.api.Assertions.assertTrue;

public class testing {
    public static void main(String[] args) throws Exception {
        // Initialized the song manager object
        SongManager songManager = new SongManager();
        
        // Create songs
        Song a = new Song("Frank Sinatra","My Way a");
        Song b = new Song("Frank Sinatra","Fly me to the Moon b ");
        Song c = new Song("Bruno Mars","That's What I Like c ");
        Song d = new Song("Queen", "Bohemian Rhapsody d ");
        Song e = new Song("The Beatles", "Let it Be e ");
        
        // Add songs to Song object library
        songManager.addSongToLibrary(a);
        songManager.addSongToLibrary(b);
        songManager.addSongToLibrary(c);
        songManager.addSongToLibrary(d);
        songManager.addSongToLibrary(e);

        // Create playlist and add songs to the playlist
        PlayList favorite = new PlayList("favorite");
        favorite.addSong(a);
        favorite.addSong(b);
        favorite.addSong(c);

        // Create playlist using a name
        songManager.createPlayList("Hits Playlist");
        songManager.showSongs();
    
        // Add songs by their name
        songManager.addSongByNameToPlaylist("My Way a", "Hits Playlist");

    }
}