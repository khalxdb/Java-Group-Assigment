package app.util;

import java.io.Console;
import java.util.ArrayList;

import app.MusicSimulator;
import app.manager.SongManager;
import app.model.Playlist;
import app.model.Song;

public class SongAndPlaylistActionHelper {

    public static void handlePlaylistAction(String input, MusicSimulator simulator) {
        // Extracted the song manager and console from them
        SongManager songManager = simulator.songManager; 
        ConsoleManager console = simulator.console;
        // Split the input into different part
        String[] parts = input.split(" ");
        if (!validInput(parts)){
            console.showMessage("\033[31mInvalid command format.\033[0m");
            console.waitForEnter();
        }
        String action = parts[0];
        String indexStr = parts[1];

    }

    public static boolean validSongIndex(ArrayList<Song> listOfSongs ){
        return (songIdx < 0 || songIdx >= listOfSongs.size());
    }
    public static boolean validPlaylistIndex(ArrayList<Playlist> playlists , int playlistIdx){
        return playlistIdx < 0 || playlistIdx >= playlists.size();
    }

    public static boolean validInput(String[] parts){
        /*
         * A valid input is one that is not more thant 2 and the second part is numeric
         * 'p 1' is valid, 'p s' is not.
         */
        return parts.length != 2 || !isNumeric(parts[1]);
    }
    // Check for Numeric
    public static boolean isNumeric(String str) {
        return str.matches("\\d+");
    }
}
