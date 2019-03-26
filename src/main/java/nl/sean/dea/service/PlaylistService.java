package nl.sean.dea.service;

import nl.sean.dea.dto.PlaylistDTO;
import nl.sean.dea.dto.PlaylistsDTO;

public interface PlaylistService {
    PlaylistsDTO getAllPlaylists(String username);

    PlaylistDTO getPlaylist(String username, int playlistID);

    PlaylistsDTO deletePlaylist(String username, int playlistID);

    PlaylistsDTO addPlaylist(String username, PlaylistDTO playlist);
}
