package nl.sean.dea;

import nl.sean.dea.dto.PlaylistsDTO;

import java.util.ArrayList;
import java.util.List;

public class PlaylistStoreSingleton {
    private List<Playlist> playlists;
    private int length;
    private static PlaylistStoreSingleton instance;

    private PlaylistStoreSingleton() {
        playlists = new ArrayList<>();
        playlists.add(new Playlist(getMaxPlaylistIndex() + 1, "Rocklist", false));
        playlists.add(new Playlist(getMaxPlaylistIndex() + 1, "Houselist", true));
        playlists.add(new Playlist(getMaxPlaylistIndex() + 1, "Dancelist", false));
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

    public PlaylistsDTO addPlaylist(Playlist playlist) {
        int newID = getMaxPlaylistIndex() + 1;
        playlist.setId(newID);

        playlists.add(playlist);
        return new PlaylistsDTO(playlists, length);
    }

    public PlaylistsDTO removePlaylist(int playlistID) {
        for (Playlist playlist : playlists) {
            if (playlist.getId() == playlistID) {
                playlists.remove(playlist);
                return new PlaylistsDTO(playlists, length);
            }
        }
        return null;
    }

    private int getMaxPlaylistIndex() {
        int max = 0;
        for (Playlist playlist : playlists) {
            if (playlist.getId() > max) {
                max = playlist.getId();
            }
        }
        return max;
    }

}
