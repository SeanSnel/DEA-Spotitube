package nl.sean.dea;

import nl.sean.dea.dto.PlaylistDTO;
import nl.sean.dea.dto.PlaylistsDTO;
import nl.sean.dea.dto.TrackDTO;
import nl.sean.dea.dto.TracksDTO;

import java.util.ArrayList;
import java.util.List;

public class PlaylistStoreSingleton {
    private static PlaylistStoreSingleton instance;
    private List<PlaylistDTO> playlists;
    private int length;

    private PlaylistStoreSingleton() {
        playlists = new ArrayList<>();
        playlists.add(new PlaylistDTO(getMaxPlaylistIndex() + 1, "Rocklist", false, generateRockTracklist()));
        playlists.add(new PlaylistDTO(getMaxPlaylistIndex() + 1, "Houselist", true, generateHouseTracklist()));
        playlists.add(new PlaylistDTO(getMaxPlaylistIndex() + 1, "Classiclist", false, generateClassicTracklist()));
        length = 10000;
    }

    public static PlaylistStoreSingleton getInstance() {
        if (instance == null) {
            instance = new PlaylistStoreSingleton();
        }
        return instance;
    }

    public PlaylistsDTO getPlaylists() {
        return new PlaylistsDTO(playlists, length);
    }

    public TracksDTO getTracksFromPlaylist(int playlistID) {
        for (PlaylistDTO playlist : playlists) {
            if (playlist.getId() == playlistID) {
                return new TracksDTO(playlist.getTracks());
            }
        }
        return null;
    }

    public PlaylistsDTO addPlaylist(PlaylistDTO playlist) {
        int newID = getMaxPlaylistIndex() + 1;
        playlist.setId(newID);

        playlists.add(playlist);
        return new PlaylistsDTO(playlists, length);
    }

    public PlaylistsDTO removePlaylist(int playlistID) {
        for (PlaylistDTO playlist : playlists) {
            if (playlist.getId() == playlistID) {
                playlists.remove(playlist);
                return new PlaylistsDTO(playlists, length);
            }
        }
        return null;
    }

    private int getMaxPlaylistIndex() {
        int max = 0;
        for (PlaylistDTO playlist : playlists) {
            if (playlist.getId() > max) {
                max = playlist.getId();
            }
        }
        return max;
    }

    private ArrayList<TrackDTO> generateRockTracklist() {
        ArrayList<TrackDTO> trackList = new ArrayList<>();
        trackList.add(new TrackDTO(1, "Come out swinging", "The Offspring", 201, "Conspiracy Of One", 231, "01-01-1994", null, true));
        trackList.add(new TrackDTO(2, "All along", "The Offspring", 231, "Conspiracy Of One", 296, "01-01-1994", null, true));
        return trackList;
    }

    private ArrayList<TrackDTO> generateHouseTracklist() {
        ArrayList<TrackDTO> trackList = new ArrayList<>();
        trackList.add(new TrackDTO(3, "Neverland", "KSHMR", 264, "Neverland", 2587, "03-08-2018", null, true));
        trackList.add(new TrackDTO(4, "Memories", "KSHMR", 234, "Memories", 3654, "20-08-2018", "Radio Edit", true));
        return trackList;
    }

    private ArrayList<TrackDTO> generateClassicTracklist() {
        ArrayList<TrackDTO> trackList = new ArrayList<>();
        trackList.add(new TrackDTO(5, "I Giorini", "Ludovico Einaudi", 411, "Islands", 245, "03-08-2011", null, false));
        trackList.add(new TrackDTO(6, "Passagio", "Ludovico Einaudi", 445, "Islands", 345, "03-08-2011", null, false));
        return trackList;
    }

}
