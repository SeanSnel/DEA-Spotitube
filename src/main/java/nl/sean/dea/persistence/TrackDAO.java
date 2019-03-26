package nl.sean.dea.persistence;

import nl.sean.dea.dto.TracksDTO;

public interface TrackDAO {
    TracksDTO getAllTracks();

    TracksDTO getAllTracksFromPlaylist(int playlistID);
}
