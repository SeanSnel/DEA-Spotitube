package nl.sean.dea.service;

import nl.sean.dea.dto.TracksDTO;

public interface TrackService {
    TracksDTO getAllTracks();

    TracksDTO getAllTracksFromPlaylist(int playlistID);
}
