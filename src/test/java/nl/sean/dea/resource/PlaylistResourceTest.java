package nl.sean.dea.resource;

import nl.sean.dea.dto.PlaylistDTO;
import nl.sean.dea.dto.TokenDTO;
import nl.sean.dea.dto.TrackDTO;
import nl.sean.dea.dto.TracksDTO;
import nl.sean.dea.service.AuthenticationService;
import nl.sean.dea.service.PlaylistService;
import nl.sean.dea.service.TrackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlaylistResourceTest {

    private final String VALID_TOKEN = "1234";
    @Mock
    PlaylistService playlistServiceMock;
    @Mock
    AuthenticationService authenticationServiceMock;
    @Mock
    TrackService trackServiceMock;
    @InjectMocks
    private PlaylistResource sut;
    private PlaylistDTO testPlaylist;

    @BeforeEach
    void setUp() {
        TrackDTO testTrack = new TrackDTO(1, "test", "test", 1, "test", 1, "1990-01-01", "test", true);
        TracksDTO testTracklist = new TracksDTO(new ArrayList<>(Collections.singletonList(testTrack)));
        testPlaylist = new PlaylistDTO(1, "Testlist", true, testTracklist.getTracks());
        TokenDTO testToken = new TokenDTO("Sean", VALID_TOKEN);

        when(authenticationServiceMock.checkToken(any())).thenReturn(testToken);
    }

    @Test
    void getAllPlaylistsSuccess() {
        Response actualResult = sut.getAllPlaylists(VALID_TOKEN);

        verify(authenticationServiceMock).checkToken(VALID_TOKEN);
        assertEquals(Response.Status.OK.getStatusCode(), actualResult.getStatus());
        verify(playlistServiceMock).getAllPlaylists("Sean");
    }

    @Test
    void getTracksFromPlaylistSuccess() {
        Response actualResult = sut.getTracksFromPlaylist(1, VALID_TOKEN);

        verify(authenticationServiceMock).checkToken(VALID_TOKEN);
        assertEquals(Response.Status.OK.getStatusCode(), actualResult.getStatus());
        verify(trackServiceMock).getAllTracksFromPlaylist(1);
    }

    @Test
    void deletePlaylistSuccess() {
        Response actualResult = sut.deletePlaylist(1, VALID_TOKEN);

        verify(authenticationServiceMock).checkToken(VALID_TOKEN);
        assertEquals(Response.Status.OK.getStatusCode(), actualResult.getStatus());
        verify(playlistServiceMock).deletePlaylist("Sean", 1);
    }

    @Test
    void addPlaylistSuccess() {
        Response actualResult = sut.addPlaylist(testPlaylist, VALID_TOKEN);

        verify(authenticationServiceMock).checkToken(VALID_TOKEN);
        assertEquals(Response.Status.CREATED.getStatusCode(), actualResult.getStatus());
        verify(playlistServiceMock).addPlaylist("Sean", testPlaylist);
    }

    @Test
    void editPlaylistSuccess() {
        Response actualResult = sut.editPlaylist(1, testPlaylist, VALID_TOKEN);

        verify(authenticationServiceMock).checkToken(VALID_TOKEN);
        assertEquals(Response.Status.OK.getStatusCode(), actualResult.getStatus());
        verify(playlistServiceMock).editPlaylist("Sean", 1, testPlaylist);
    }

}
