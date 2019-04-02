package nl.sean.dea.exceptionmapper;

import nl.sean.dea.dto.ErrorDTO;
import nl.sean.dea.service.SpotitubeTokenException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TokenExceptionMapperTest {

    private TokenExceptionMapper sut;

    private SpotitubeTokenException spotitubeTokenException;

    @BeforeEach
    void setUp() {
        sut = new TokenExceptionMapper();
        spotitubeTokenException = new SpotitubeTokenException("Invalid token.");
    }

    @Test
    void respondToInvalidTokenExceptionSuccess() {
        Response actualResult = sut.toResponse(spotitubeTokenException);

        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), actualResult.getStatus());

        ErrorDTO errorDTO = (ErrorDTO) actualResult.getEntity();
        assertEquals("Invalid token.", errorDTO.getMessage());
    }
}