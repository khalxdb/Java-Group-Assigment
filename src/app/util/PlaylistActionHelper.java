package app.util;

import app.*;
import app.model.*;
import app.manager.*;
import app.state.*;

public class PlaylistActionHelper {
    /**
     * Parses and handles the playlist command, routing to the appropriate action
     * either show or play a playlist based on user input.
     * @param playlistCommand The command input by the user in the form of 'action' 'number'.
     * @param simulator The main MusicSimulator context to access song manager and console.
     */
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

     /**
     * Handles the action to show a playlist by retrieving it at the specified index
     * and transitioning to the ViewPlaylistState if the playlist exists.
     *
     * @param indexPart The index of the playlist to be displayed, as a string.
     * @param simulator The main MusicSimulator context to access the song manager.
     */
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
            simulator.console.waitForEnter(); // Display error if found
        }
    }

    /**
     * Handles the action to play a playlist by retrieving it at the specified index
     * and transitioning to the PlayQueueState if the playlist exists.
     *
     * @param indexPart The index of the playlist to be played, as a string.
     * @param simulator The main MusicSimulator context to access the song manager.
     */
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
            simulator.console.waitForEnter(); // Display error if found
        }
    }

}
