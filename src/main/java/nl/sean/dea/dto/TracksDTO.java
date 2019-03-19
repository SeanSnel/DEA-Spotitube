package nl.sean.dea.dto;

import nl.sean.dea.Track;

import java.util.List;

public class TracksDTO {
    private List<Track> tracks;

    public TracksDTO(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
