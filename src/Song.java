public class Song {
    // a song contains an artist and a title
    String artist;
    String title;
    
    // constructor
    public Song(String artist, String title ){
        this.artist = artist;
        this.title = title;
    }

    public String getTitle(){
        return "'" + title + "'";
    }

    public String getArtist(){
        return "'" + artist + "'";
    }

    public String toString(){
        return "'" + artist + " - " + title +"'";
    }
    
}