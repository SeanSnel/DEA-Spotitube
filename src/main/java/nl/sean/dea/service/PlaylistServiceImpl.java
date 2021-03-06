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

    @Override
    public PlaylistsDTO deletePlaylist(String username, int playlistID) {
        PlaylistsDTO playlistsDTO = playlistDAO.deletePlaylist(username, playlistID);
        playlistsDTO.setLength(calculateTotalDuration(playlistsDTO.getPlaylists()));
        return playlistsDTO;
    }

    @Override
    public PlaylistsDTO addPlaylist(String username, PlaylistDTO playlist) {
        PlaylistsDTO playlistsDTO = playlistDAO.addPlaylist(username, playlist);
        playlistsDTO.setLength(calculateTotalDuration(playlistsDTO.getPlaylists()));
        return playlistsDTO;
    }

    @Override
    public PlaylistsDTO editPlaylist(String username, int playlistID, PlaylistDTO changedPlaylist) {
        PlaylistsDTO playlistsDTO = playlistDAO.editPlaylist(username, playlistID, changedPlaylist);
        playlistsDTO.setLength(calculateTotalDuration(playlistsDTO.getPlaylists()));
        return playlistsDTO;
    }
}
