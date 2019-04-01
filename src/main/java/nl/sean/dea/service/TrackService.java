package nl.sean.dea.service;

import nl.sean.dea.dto.TrackDTO;
import nl.sean.dea.dto.TracksDTO;

public interface TrackService {
    TracksDTO getAllTracks();

    TracksDTO getAllTracksFromPlaylist(int playlistID);

    TracksDTO deleteTrackFromPlaylist(int trackid, int playlistID);

    TracksDTO addTrackToPlaylist(int playlistID, TrackDTO track);

    TracksDTO getAllTracksNotInPlaylist(int playlistID);
}
