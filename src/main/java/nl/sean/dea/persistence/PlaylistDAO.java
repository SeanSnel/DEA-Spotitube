package nl.sean.dea.persistence;

import nl.sean.dea.dto.PlaylistDTO;
import nl.sean.dea.dto.PlaylistsDTO;

public interface PlaylistDAO {
    PlaylistDTO getPlaylist(String username, int playlistID);

    PlaylistsDTO getAllPlaylists(String username);

    PlaylistsDTO deletePlaylist(String username, int playlistID);

    PlaylistsDTO addPlaylist(String username, PlaylistDTO playlist);

    PlaylistsDTO editPlaylist(String username, int playlistID, PlaylistDTO changedPlaylist);
}
