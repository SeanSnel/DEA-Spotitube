package nl.sean.dea.dao;

import nl.sean.dea.dto.UserDTO;

public interface UserDAO {
    UserDTO getUser(String username, String password);
}
