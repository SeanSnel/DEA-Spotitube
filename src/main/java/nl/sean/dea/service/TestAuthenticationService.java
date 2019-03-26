package nl.sean.dea.service;

import nl.sean.dea.dto.TokenDTO;
import nl.sean.dea.dto.UserDTO;

import javax.enterprise.inject.Alternative;

@Alternative
public class TestAuthenticationService implements AuthenticationService {
    @Override
    public TokenDTO login(UserDTO user) {
        return new TokenDTO(user.getUser(), "1234");
    }

    @Override
    public TokenDTO checkToken(String token) {
        return null;
    }
}
