import java.util.ArrayList;
public class SongLibrary {
    // TODO: Make a class for song library 
    public ArrayList<Song> songLibrary;

    public SongLibrary(){
        songLibrary = new ArrayList<Song>();
    }

    // TODO: addSongToLibrary
    public void addSongToLibrary(Song song) {
        if ( song == null){
            System.out.println("Can't added a Null Song");;
        }else{songLibrary.add(song);}
    }

    // TODO: added isValidMethod() to check for song validity;
    public boolean isValidSong(Song song){
        return false;
    }

   // Method to display all songs in the song library
    public void showSongs() {
        System.out.println("Songs in the library:");
        for (Song song : songLibrary) {
            System.out.println(song.toString());
        }
    }
    
    public ArrayList<Song> getLibrary() {
        return songLibrary;
    }
}
