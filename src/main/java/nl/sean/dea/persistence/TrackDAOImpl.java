package nl.sean.dea.persistence;

import nl.sean.dea.dto.TrackDTO;
import nl.sean.dea.dto.TracksDTO;

import javax.enterprise.inject.Default;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Default
public class TrackDAOImpl implements TrackDAO {
    @Override
    public TracksDTO getAllTracks() {
        List<TrackDTO> foundTracks;
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM track JOIN track_in_playlist tip on track.track_ID = tip.track_ID")
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            foundTracks = getTracksFromResultset(resultSet);
        } catch (SQLException e) {
            throw new SpotitubePersistenceException();
        }
        return new TracksDTO(foundTracks);
    }

    @Override
    public TracksDTO getAllTracksFromPlaylist(int playlistID) {
        List<TrackDTO> foundTracks;
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM track JOIN track_in_playlist tip ON track.track_ID = tip.track_ID WHERE tip.playlist_ID=?")
        ) {
            preparedStatement.setInt(1, playlistID);
            ResultSet resultSet = preparedStatement.executeQuery();
            foundTracks = getTracksFromResultset(resultSet);
        } catch (SQLException e) {
            throw new SpotitubePersistenceException();
        }
        return new TracksDTO(foundTracks);
    }

    @Override
    public void addTracksToPlaylist(List<TrackDTO> tracks, int playlistID) {
        StringBuilder query = new StringBuilder("INSERT INTO track_in_playlist(track_ID, playlist_ID) VALUES");
        for (TrackDTO track : tracks) {
            query.append("(");
            query.append(track.getId());
            query.append(",");
            query.append(playlistID);
            query.append(")");
        }
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        query.toString())
        ) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new SpotitubePersistenceException();
        }
    }

    @Override
    public TracksDTO deleteTrackFromPlaylist(int trackID, int playlistID) {
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM track_in_playlist WHERE playlist_ID=? AND track_ID=?")
        ) {
            preparedStatement.setInt(1, playlistID);
            preparedStatement.setInt(2, trackID);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new SpotitubePersistenceException();
        }
        return getAllTracksFromPlaylist(playlistID);
    }

    @Override
    public TracksDTO addTrackToPlaylist(int playlistID, TrackDTO track) {
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO track_in_playlist(track_ID, playlist_ID, offlineAvailable) VALUES (?,?,?)"
                )) {
            preparedStatement.setInt(1, track.getId());
            preparedStatement.setInt(2, playlistID);
            preparedStatement.setBoolean(3, track.isOfflineAvailable());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new SpotitubePersistenceException();
        }
        return getAllTracksFromPlaylist(playlistID);
    }

    @Override
    public TracksDTO getAllTracksNotInPlaylist(int playlistID) {
        List<TrackDTO> foundTracks;
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT *" +
                                "FROM track " +
                                "INNER JOIN track_in_playlist tip on track.track_ID = tip.track_ID " +
                                "WHERE tip.track_ID NOT IN ( SELECT track_ID " +
                                "                            FROM track_in_playlist " +
                                "                            WHERE playlist_ID = ?);")
        ) {
            preparedStatement.setInt(1, playlistID);
            ResultSet resultSet = preparedStatement.executeQuery();
            foundTracks = getTracksFromResultset(resultSet);
        } catch (SQLException e) {
            throw new SpotitubePersistenceException();
        }
        return new TracksDTO(foundTracks);
    }

    private List<TrackDTO> getTracksFromResultset(ResultSet resultSet) throws SQLException {
        List<TrackDTO> foundTracks = new ArrayList<>();

        while (resultSet.next()) {
            foundTracks.add(new TrackDTO(
                    resultSet.getInt("track.track_ID"),
                    resultSet.getString("track.title"),
                    resultSet.getString("track.performer"),
                    resultSet.getInt("track.duration"),
                    resultSet.getString("track.album"),
                    resultSet.getInt("track.playcount"),
                    resultSet.getString("track.publicationDate"),
                    resultSet.getString("track.description"),
                    resultSet.getBoolean("tip.offlineAvailable")));
        }

        return foundTracks;
    }
}
