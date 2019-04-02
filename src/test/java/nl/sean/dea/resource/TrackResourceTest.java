package nl.sean.dea.resource;

import nl.sean.dea.dto.TokenDTO;
import nl.sean.dea.service.AuthenticationService;
import nl.sean.dea.service.TrackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrackResourceTest {

    private final String VALID_TOKEN = "1234";
    @Mock
    TrackService trackServiceMock;
    @Mock
    AuthenticationService authenticationServiceMock;
    @InjectMocks
    TrackResource sut;
    private TokenDTO testToken;

    @BeforeEach
    void setUp() {
        testToken = new TokenDTO("Sean", VALID_TOKEN);
    }

    @Test
    void getAllTracksSuccess() {
        when(authenticationServiceMock.checkToken(any())).thenReturn(testToken);

        Response actualResult = sut.getAllTracksNotInPlaylist(VALID_TOKEN, 1);

        verify(authenticationServiceMock).checkToken(VALID_TOKEN);
        assertEquals(Response.Status.OK.getStatusCode(), actualResult.getStatus());
        verify(trackServiceMock).getAllTracksNotInPlaylist(1);
    }

    @Test
    void emptyConstructorUsedByWebcontainer() {
        TrackResource resource = new TrackResource();
    }
}