package nl.sean.dea.service;

import nl.sean.dea.dao.UserDAO;
import nl.sean.dea.dto.TokenDTO;
import nl.sean.dea.dto.UserDTO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
public class AuthenticationServiceImpl implements AuthenticationService {

    private UserDAO userDAO;

    public AuthenticationServiceImpl() {
    }

    @Inject
    public AuthenticationServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public TokenDTO login(UserDTO user) {
        return new TokenDTO(user.getUser(), "1234");
    }
}
