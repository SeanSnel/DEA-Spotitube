package nl.sean.dea.exceptionmapper;

import nl.sean.dea.persistence.SpotitubePersistenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersistenceExceptionMapperTest {

    private PersistenceExceptionMapper sut;

    private SpotitubePersistenceException spotitubePersistenceException;

    @BeforeEach
    void setUp() {
        spotitubePersistenceException = new SpotitubePersistenceException();

        sut = new PersistenceExceptionMapper();
    }

    @Test
    void respondToSpotitubePersistenceExceptionSuccess() {
        Response actualResult = sut.toResponse(spotitubePersistenceException);

        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), actualResult.getStatus());
    }
}