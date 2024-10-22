import java.util.ArrayList;
public class SongLibrary {
    // The Song Library Class Roles is for handling all the possible song
    public static ArrayList<Song> songLibrary;

    // Constructor
    public SongLibrary(){
        songLibrary = new ArrayList<Song>();
    }

    // addSong to Library
    public boolean addSongToLibrary(Song song) {
        if ( song == null){
            System.out.println("Can't added a Null Song");
            return false;
        }else if(findSongByName(song.title) != null){
            System.out.println("Can't added a Null Song");
            return false;
        }
        songLibrary.add(song);
        System.out.println("Added " + song.title + "to the Library");
        return true;
    }

    // TODO: added isValidMethod() to check for song validity;
    public boolean isValidSong(Song song){
        return false;
    }
        // Method to find a song by its exact name in the library
    public Song findSongByName(String name) {
        for (Song song : songLibrary) {
            if (song.title.equalsIgnoreCase(name)) {
                return song;  // Return the matching song
            }
        }
        System.out.println("No Song Found");
        return null;  // No match found
    }

   // method to display all songs in the song library
    public void showSongs() {
        System.out.println("Songs in the library:");
        for (Song song : songLibrary) {
            System.out.println('-'+song.toString());
        }
    }

    public Song findSongByArtist(String artist){
        for (Song song: songLibrary){
            if (song.getArtist().equalsIgnoreCase(artist)){
                return song;
            }
        }
        System.out.println("No Song Found or Incorrect Artist Name");
        return null;
    }
    
}
