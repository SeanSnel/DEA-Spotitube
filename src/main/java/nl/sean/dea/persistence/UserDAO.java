package nl.sean.dea.persistence;

import nl.sean.dea.dto.TokenDTO;
import nl.sean.dea.dto.UserDTO;

public interface UserDAO {
    UserDTO getUser(String username, String password);

    TokenDTO getUserWithToken(String token);

    TokenDTO registerToken(String user, String token);
}
