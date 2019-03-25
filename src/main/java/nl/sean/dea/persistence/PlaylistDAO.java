package nl.sean.dea.persistence;

import nl.sean.dea.dto.PlaylistDTO;
import nl.sean.dea.dto.PlaylistsDTO;

public interface PlaylistDAO {

    PlaylistDTO getPlaylist(int playlistID);
    PlaylistsDTO getAllPlaylists();
}
