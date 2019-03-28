package nl.sean.dea.persistence;

import nl.sean.dea.dto.PlaylistDTO;
import nl.sean.dea.dto.PlaylistsDTO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.*;
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
    public PlaylistDTO getPlaylist(String username, int playlistID) {
        PlaylistDTO foundPlaylist = null;
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM playlist WHERE playlist_ID=?")
        ) {
            preparedStatement.setString(1, String.valueOf(playlistID));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                foundPlaylist = new PlaylistDTO(
                        resultSet.getInt("playlist_ID"),
                        resultSet.getString("name"),
                        resultSet.getString("owner").equals(username),
                        trackDAO.getAllTracksFromPlaylist(resultSet.getInt("playlist_ID")).getTracks());
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
                foundPlaylists.add(new PlaylistDTO(
                        resultSet.getInt("playlist_ID"),
                        resultSet.getString("name"),
                        resultSet.getString("owner").equals(username),
                        trackDAO.getAllTracksFromPlaylist(resultSet.getInt("playlist_ID")).getTracks()));
            }
        } catch (SQLException e) {
            throw new SpotitubePersistenceException();
        }
        return new PlaylistsDTO(foundPlaylists, 0);
    }

    @Override
    public PlaylistsDTO deletePlaylist(String username, int playlistID) {
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM playlist WHERE playlist_ID=?")
        ) {
            preparedStatement.setInt(1, playlistID);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new SpotitubePersistenceException();
        }
        return getAllPlaylists(username);
    }

    @Override
    public PlaylistsDTO addPlaylist(String username, PlaylistDTO playlist) {
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement insertPlaylistStatement = connection.prepareStatement(
                        "INSERT INTO playlist(name, owner) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS)
        ) {
            insertPlaylistStatement.setString(1, playlist.getName());
            insertPlaylistStatement.setString(2, username);
            int insertedID = insertPlaylistStatement.executeUpdate();
            if (playlist.getTracks() != null) {
                trackDAO.addTracksToPlaylist(playlist.getTracks(), insertedID);
            }
        } catch (SQLException e) {
            throw new SpotitubePersistenceException();
        }
        return getAllPlaylists(username);
    }

    @Override
    public PlaylistsDTO editPlaylist(String username, int playlistID, PlaylistDTO changedPlaylist) {
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "UPDATE playlist SET name=? WHERE playlist_ID=?")
        ) {
            preparedStatement.setString(1, changedPlaylist.getName());
            preparedStatement.setInt(2, playlistID);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new SpotitubePersistenceException();
        }
        return getAllPlaylists(username);
    }
}
