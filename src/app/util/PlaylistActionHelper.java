package app.util;

import app.*;
import app.model.*;
import app.manager.*;
import app.state.*;

public class PlaylistActionHelper {

    public static void handlePlaylistAction(String playlistCommand, MusicSimulator simulator) {
        //Expecting our input to be <action> <number>
        if (!CommandParserUtil.validCommand(playlistCommand, simulator.console)){
            return; // Invalided input
        }

        String[] parts = playlistCommand.split(" ");
        String action = parts[0].toLowerCase();
        String indexPart = parts[1]; // Extract the index part directly
        
        switch (action) {
            case "show":
            case "s":
                handleShowPlaylist(indexPart, simulator);
                break;
            case "play":
            case "p":
                handlePlayPlaylist(indexPart, simulator);
                break;
            default:
                simulator.console.showMessage("\033[31mInvalid command. Use 'show <number>' or 'play <number>'.\033[0m");
                simulator.console.waitForEnter();
                break;
        }
    }

    public static void handleShowPlaylist(String indexPart, MusicSimulator simulator){
        Integer playlistIndex = CommandParserUtil.parseIndexCommand(indexPart, simulator.console);
        if (playlistIndex == null) {
            return;
        }

        SongManager songManager = simulator.songManager;
        Playlist selectedPlaylist = songManager.getPlaylistAtIndex(playlistIndex);
        if (selectedPlaylist != null){
            simulator.setState(new ViewPlaylistState(simulator, selectedPlaylist));
        }
        else{
            simulator.console.waitForEnter();// print out the error
        }
    }

    // Method to handle playing a playlist
    public static void handlePlayPlaylist(String indexPart, MusicSimulator simulator) {
        Integer playlistIndex = CommandParserUtil.parseIndexCommand(indexPart, simulator.console);
        if (playlistIndex == null) {
            return;
        }
        SongManager songManager = simulator.songManager;
        Playlist selectedPlaylist = songManager.getPlaylistAtIndex(playlistIndex);
        if (selectedPlaylist != null) {
            simulator.setState(new PlayQueueState(simulator, selectedPlaylist));
        }
        else{
            simulator.console.waitForEnter();// print out the error
        }
    }

}
