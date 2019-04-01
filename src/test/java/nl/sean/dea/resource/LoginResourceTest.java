package nl.sean.dea.resource;

import nl.sean.dea.dto.TokenDTO;
import nl.sean.dea.dto.UserDTO;
import nl.sean.dea.service.AuthenticationService;
import nl.sean.dea.service.SpotitubeAuthenticationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginResourceTest {

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private LoginResource sut;

    @Test
    void loginSuccess() {
        UserDTO user = new UserDTO("Sean", "test");
        when(authenticationService.login(user)).thenReturn(new TokenDTO("Sean", "1234"));

        Response actualResult = sut.loginUser(user);

        assertEquals(Response.Status.OK.getStatusCode(), actualResult.getStatus());
        TokenDTO tokenDTO = (TokenDTO) actualResult.getEntity();
        assertEquals("Sean", tokenDTO.getUser());
        assertEquals("1234", tokenDTO.getToken());
    }

    @Test
    void loginFail() {
        when(authenticationService.login(any(UserDTO.class))).thenThrow(new SpotitubeAuthenticationException("Invalid user."));

        SpotitubeAuthenticationException spotitubeAuthenticationException = assertThrows(SpotitubeAuthenticationException.class, () -> {
            Response actualResult = sut.loginUser(new UserDTO("Sean", "wrongPassword"));
        });

        assertEquals("Invalid user.", spotitubeAuthenticationException.getMessage());
    }

    @Test
    void emptyConstructorUsedByWebcontainer() {
        LoginResource loginResource = new LoginResource();
    }
}
