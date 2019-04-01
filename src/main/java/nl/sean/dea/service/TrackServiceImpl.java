package nl.sean.dea.service;

import nl.sean.dea.dto.TrackDTO;
import nl.sean.dea.dto.TracksDTO;
import nl.sean.dea.persistence.TrackDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
public class TrackServiceImpl implements TrackService {
    private TrackDAO trackDAO;

    public TrackServiceImpl() {
    }

    @Inject
    public TrackServiceImpl(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    @Override
    public TracksDTO getAllTracks() {
        return trackDAO.getAllTracks();
    }

    @Override
    public TracksDTO getAllTracksFromPlaylist(int playlistID) {
        return trackDAO.getAllTracksFromPlaylist(playlistID);
    }

    @Override
    public TracksDTO deleteTrackFromPlaylist(int trackid, int playlistID) {
        return trackDAO.deleteTrackFromPlaylist(trackid, playlistID);
    }

    @Override
    public TracksDTO addTrackToPlaylist(int playlistID, TrackDTO track) {
        return trackDAO.addTrackToPlaylist(playlistID, track);
    }

    @Override
    public TracksDTO getAllTracksNotInPlaylist(int playlistID) {
        return trackDAO.getAllTracksNotInPlaylist(playlistID);
    }
}
