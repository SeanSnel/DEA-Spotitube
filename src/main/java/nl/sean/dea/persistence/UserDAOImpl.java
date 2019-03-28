package nl.sean.dea.persistence;

import nl.sean.dea.dto.TokenDTO;
import nl.sean.dea.dto.UserDTO;

import javax.enterprise.inject.Default;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Default
public class UserDAOImpl implements UserDAO {

    @Override
    public UserDTO getUser(String username, String password) {
        UserDTO foundUser = null;
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM account WHERE username=? AND password=?")
        ) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                foundUser = new UserDTO(username, password);
            }
        } catch (SQLException e) {
            throw new SpotitubePersistenceException();
        }
        return foundUser;
    }

    @Override
    public TokenDTO getUserWithToken(String token) {
        TokenDTO foundUser = null;
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM user_token WHERE token=?")
        ) {
            preparedStatement.setString(1, token);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                foundUser = new TokenDTO(resultSet.getString("username"), token);
            }
        } catch (SQLException e) {
            throw new SpotitubePersistenceException();
        }
        return foundUser;
    }

    @Override
    public TokenDTO registerToken(String username, String token) {
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM user_token WHERE username=?")
        ) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return updateExistingTokenUser(username, token);
            } else {
                return insertTokenForNewUser(username, token);
            }
        } catch (SQLException e) {
            throw new SpotitubePersistenceException();
        }
    }

    private TokenDTO updateExistingTokenUser(String username, String token) {
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "UPDATE user_token SET token=? WHERE username=?")
        ) {
            preparedStatement.setString(1, token);
            preparedStatement.setString(2, username);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new SpotitubePersistenceException();
        }
        return new TokenDTO(username,token);
    }

    private TokenDTO insertTokenForNewUser(String username, String token) {
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO user_token(username, token) VALUES (?,?)")
        ) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, token);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new SpotitubePersistenceException();
        }
        return new TokenDTO(username, token);
    }
}
