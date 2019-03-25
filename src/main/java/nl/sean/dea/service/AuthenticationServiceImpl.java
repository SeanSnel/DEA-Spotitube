package nl.sean.dea.service;

import nl.sean.dea.dto.TokenDTO;
import nl.sean.dea.dto.UserDTO;
import nl.sean.dea.persistence.UserDAO;
import nl.sean.dea.util.TokenGenerator;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
public class AuthenticationServiceImpl implements AuthenticationService {

    private UserDAO userDAO;
    private TokenGenerator tokenGenerator = new TokenGenerator();

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
            String token = tokenGenerator.generateToken();
            return userDAO.registerToken(user.getUser(), token);
        }
        throw new SpotitubeAuthenticationException("Invalid user.");
    }

    @Override
    public TokenDTO checkToken(String token) {
        TokenDTO foundUser = userDAO.getUserWithToken(token);
        if (foundUser != null) {
            return foundUser;
        }
        throw new SpotitubeTokenException("Invalid token.");
    }
}
