package nl.sean.dea.exceptionmapper;

import nl.sean.dea.dto.ErrorDTO;
import nl.sean.dea.service.SpotitubeAuthenticationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthenticationExceptionMapperTest {

    private AuthenticationExceptionMapper sut;

    private SpotitubeAuthenticationException spotitubeAuthenticationException;

    @BeforeEach
    void setUp() {
        sut = new AuthenticationExceptionMapper();
        spotitubeAuthenticationException = new SpotitubeAuthenticationException("Invalid user.");
    }

    @Test
    void respondToSpotitubeAuthenticationExceptionSuccess() {
        Response actualResult = sut.toResponse(spotitubeAuthenticationException);

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), actualResult.getStatus());

        ErrorDTO errorDTO = (ErrorDTO) actualResult.getEntity();
        assertEquals("Invalid user.", errorDTO.getMessage());
    }
}