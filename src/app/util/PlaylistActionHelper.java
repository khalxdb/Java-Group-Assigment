package app.util;

import java.util.ArrayList;
import app.*;
import app.model.*;
import app.manager.*;
import app.state.*;

public class PlaylistActionHelper {

    // Method for Handles playlist actions (show or play) across different states
    public static void handlePlaylistAction(String playlistCommand, MusicSimulator simulator) {
        // Extracted the song manager and console from them
        SongManager songManager = simulator.songManager; 
        ConsoleManager console = simulator.console;

        String[] parts = playlistCommand.split(" ");

        if (parts.length != 2) {
            console.showMessage("\033[31mInvalid command format. Please use 'show <number>' or 'play <number>'.\033[0m");
            console.waitForEnter();
            return;
        }

        String action = parts[0];
        String indexStr = parts[1];

        if (!isNumeric(indexStr)) {
            console.showMessage("\033[31mInvalid input. Please enter a valid number.\033[0m");
            console.waitForEnter();
            return;
        }

        int idx = Integer.parseInt(indexStr);
        ArrayList<Playlist> playlists = songManager.getPlaylists();
        if (idx < 0 || idx >= playlists.size()) {
            console.showMessage("\033[31mInvalid index. Please try again.\033[0m");
            console.waitForEnter();
            return;
        }

        Playlist selectedPlaylist = playlists.get(idx);

        switch (action.toLowerCase()) {
            case "show":
            case "s":
                simulator.setState(new ViewPlaylistState(simulator, selectedPlaylist));
                return;
            case "play":
            case "p":
                simulator.setState(new PlayQueueState(simulator, selectedPlaylist));
                return;
            default:
                console.showMessage("\033[31mInvalid command. Please use 'show <number>' or 'play <number>'.\033[0m");
                console.waitForEnter();
        }
    }

    // Helper method to check if a string is numeric
    public static boolean isNumeric(String str) {
        return str.matches("\\d+");
    }
        // // Helper to validate and retrieve song by command
        // public Song getSongFromCommand(String command, String expectedAction) {
        //     String[] parts = command.split(" ");
        //     if (parts.length != 2 || !isNumeric(parts[1])) {
        //         console.showMessage("\033[31mInvalid command format. Use '" + expectedAction + " <number>'.\033[0m");
        //         console.waitForEnter();
        //         return null;
        //     }
    
        //     int songIndex = Integer.parseInt(parts[1]);
        //     ArrayList<Song> listOfSongs = songManager.getListofSong();
        //     if (songIndex < 0 || songIndex >= listOfSongs.size()) {
        //         console.showMessage("\033[31mInvalid index. Please try again.\033[0m");
        //         console.waitForEnter();
        //         return null;
        //     }
        //     return listOfSongs.get(songIndex);
        // }
    
    }
