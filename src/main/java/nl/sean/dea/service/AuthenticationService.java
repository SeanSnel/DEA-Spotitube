package nl.sean.dea.service;

import nl.sean.dea.dto.TokenDTO;
import nl.sean.dea.dto.UserDTO;

public interface AuthenticationService {
    TokenDTO login(UserDTO user);
}
