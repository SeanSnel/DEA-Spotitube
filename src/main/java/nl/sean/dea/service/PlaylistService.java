package nl.sean.dea.service;

import nl.sean.dea.dto.PlaylistDTO;
import nl.sean.dea.dto.PlaylistsDTO;

public interface PlaylistService {
    PlaylistsDTO getAllPlaylists();

    PlaylistDTO getPlaylist(int playlistID);
}
