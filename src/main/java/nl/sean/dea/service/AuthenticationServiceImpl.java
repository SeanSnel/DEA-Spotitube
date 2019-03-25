package nl.sean.dea.service;

import nl.sean.dea.dto.TokenDTO;
import nl.sean.dea.dto.UserDTO;
import nl.sean.dea.persistence.UserDAO;

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
        UserDTO foundUser = userDAO.getUser(user.getUser(), user.getPassword());
        if (foundUser != null) {
            return new TokenDTO(foundUser.getUser(), "1234");
        }
        throw new SpotitubeAuthenticationException("invalid user.");
    }
}
