package nl.sean.dea.service;

import nl.sean.dea.dto.PlaylistDTO;
import nl.sean.dea.dto.PlaylistsDTO;
import nl.sean.dea.persistence.PlaylistDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.List;

@Default
public class PlaylistServiceImpl implements PlaylistService {

    private PlaylistDAO playlistDAO;

    public PlaylistServiceImpl() {
    }

    @Inject
    public PlaylistServiceImpl(PlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }

    @Override
    public PlaylistsDTO getAllPlaylists(String username) {
        PlaylistsDTO playlistsDTO = playlistDAO.getAllPlaylists(username);
        playlistsDTO.setLength(calculateTotalDuration(playlistsDTO.getPlaylists()));
        return playlistsDTO;
    }

    private int calculateTotalDuration(List<PlaylistDTO> playlists) {
        int duration = 0;
        for (PlaylistDTO playlist : playlists) {
            duration += playlist.getDuration();
        }
        return duration;
    }

    @Override
    public PlaylistDTO getPlaylist(String username, int playlistID) {
        return playlistDAO.getPlaylist(username, playlistID);
    }
}
