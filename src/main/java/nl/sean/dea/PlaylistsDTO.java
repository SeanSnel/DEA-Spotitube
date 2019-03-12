package nl.sean.dea;

import java.util.List;

public class PlaylistsDTO {
    private List<Playlist> playlists;
    private int length;

    public PlaylistsDTO(List<Playlist> playlists, int length) {
        this.playlists = playlists;
        this.length = length;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public int getLength() {
        return length;
    }
}
