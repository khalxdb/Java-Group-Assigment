package app.model;

public class Song {
    //A song can contains an artist and a title
    public String artist;
    public String title;
    
    // Constructor
    public Song(String artist, String title ){
        this.artist = artist;
        this.title = title;
    }

    // Converted Song Object into String format, artist , title
    public String toString(){
        return  artist + " by " + title;
    }

    /**
     * Parsed a String in "artist , title" formatted to Song Object;
     * @param songString String to get the song from
     * @return Song Object;
     */
    public Song fromString(String songString){
        /*
         * we parsed the string split it by "," to different 
         * part of the songs then turn each parts 
         * into a songs object and return it.
         */
        String[] parts = songString.split(",");
        String artist = parts[0];
        String songName = parts[1];
        Song newSong = new Song(artist, songName);
        return newSong;
    }
}