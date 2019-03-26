package nl.sean.dea.persistence;

import nl.sean.dea.dto.PlaylistDTO;
import nl.sean.dea.dto.PlaylistsDTO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Default
public class PlaylistDAOImpl implements PlaylistDAO {

    private TrackDAO trackDAO;

    public PlaylistDAOImpl() {
    }

    @Inject
    public PlaylistDAOImpl(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    @Override
    public PlaylistDTO getPlaylist(int playlistID) {
        PlaylistDTO foundPlaylist = null;
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM playlist WHERE playlist_ID=?")
        ) {
            preparedStatement.setString(1, String.valueOf(playlistID));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                foundPlaylist = new PlaylistDTO(resultSet.getInt("playlist_ID"), resultSet.getString("name"), resultSet.getBoolean("owner"), trackDAO.getAllTracksFromPlaylist(resultSet.getInt("playlist_ID")).getTracks());
            }
        } catch (SQLException e) {
            throw new SpotitubePersistenceException();
        }
        return foundPlaylist;
    }

    @Override
    public PlaylistsDTO getAllPlaylists(String username) {
        List<PlaylistDTO> foundPlaylists = new ArrayList<>();
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM playlist")
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                foundPlaylists.add(new PlaylistDTO(resultSet.getInt("playlist_ID"), resultSet.getString("name"), resultSet.getBoolean("owner"), trackDAO.getAllTracksFromPlaylist(resultSet.getInt("playlist_ID")).getTracks()));
            }
        } catch (SQLException e) {
            throw new SpotitubePersistenceException();
        }
        return new PlaylistsDTO(foundPlaylists, 0);
    }
}
