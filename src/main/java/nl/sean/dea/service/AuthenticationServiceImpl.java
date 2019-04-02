package nl.sean.dea.service;

import nl.sean.dea.dto.TokenDTO;
import nl.sean.dea.dto.UserDTO;
import nl.sean.dea.persistence.UserDAO;
import nl.sean.dea.util.TokenGenerator;
import nl.sean.dea.util.TokenGeneratorImpl;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
public class AuthenticationServiceImpl implements AuthenticationService {

    private UserDAO userDAO;
    private TokenGenerator tokenGenerator;

    public AuthenticationServiceImpl() {
    }

    @Inject
    public AuthenticationServiceImpl(UserDAO userDAO, TokenGenerator tokenGenerator) {
        this.userDAO = userDAO;
        this.tokenGenerator = tokenGenerator;
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
