package nl.sean.dea.persistence;

import nl.sean.dea.ConnectionFactory;
import nl.sean.dea.dto.PlaylistDTO;
import nl.sean.dea.dto.PlaylistsDTO;

import javax.enterprise.inject.Default;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Default
public class PlaylistDAOImpl implements PlaylistDAO {

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
                foundPlaylist = new PlaylistDTO(resultSet.getInt("playlist_ID"), resultSet.getString("name"), resultSet.getBoolean("owner"), null);
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return foundPlaylist;
    }

    @Override
    public PlaylistsDTO getAllPlaylists() {
        return null;
    }
}
