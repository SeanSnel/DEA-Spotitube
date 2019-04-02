package nl.sean.dea.service;

import nl.sean.dea.dto.TokenDTO;
import nl.sean.dea.dto.UserDTO;
import nl.sean.dea.persistence.UserDAO;
import nl.sean.dea.util.TokenGeneratorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceImplTest {

    @Mock
    UserDAO userDAOMock;

    @Mock
    TokenGeneratorImpl tokenGeneratorStub;

    @InjectMocks
    AuthenticationServiceImpl sut;

    private final String VALID_TOKEN = "1234";
    private final String INVALID_TOKEN = "1";
    private UserDTO testUser;
    private TokenDTO testToken;

    @BeforeEach
    void setUp() {
        testUser = new UserDTO("Sean", "mypassword");
        testToken = new TokenDTO("Sean", VALID_TOKEN);
    }

    @Test
    void loginSuccess() {
        when(userDAOMock.getUser(any(), any())).thenReturn(testUser);
        when(tokenGeneratorStub.generateToken()).thenReturn(VALID_TOKEN);
        when(userDAOMock.registerToken(any(), any())).thenReturn(testToken);

        TokenDTO actualResult = sut.login(testUser);

        verify(userDAOMock).getUser("Sean", "mypassword");
        verify(tokenGeneratorStub).generateToken();
        verify(userDAOMock).registerToken("Sean", "1234");

        assertEquals("Sean", actualResult.getUser());
        assertEquals("1234", actualResult.getToken());
    }

    @Test
    void loginFail() {
        when(userDAOMock.getUser(any(), any())).thenReturn(null);

        SpotitubeAuthenticationException actualResult = assertThrows(SpotitubeAuthenticationException.class, () -> sut.login(testUser));

        assertEquals("Invalid user.", actualResult.getMessage());
    }

    @Test
    void checkTokenSuccess() {
        when(userDAOMock.getUserWithToken(any())).thenReturn(testToken);

        TokenDTO actualResult = sut.checkToken(VALID_TOKEN);

        verify(userDAOMock).getUserWithToken(VALID_TOKEN);
        assertEquals("Sean", actualResult.getUser());
        assertEquals("1234", actualResult.getToken());
    }

    @Test
    void checkTokenFail() {
        when(userDAOMock.getUserWithToken(any())).thenReturn(null);

        SpotitubeTokenException actualResult = assertThrows(SpotitubeTokenException.class, () -> sut.checkToken(INVALID_TOKEN));

        assertEquals("Invalid token.", actualResult.getMessage());
    }

    @Test
    void emptyConstructorUsedByWebcontainer() {
        AuthenticationService authenticationService = new AuthenticationServiceImpl();
    }
}