package nl.sean.dea.resource;

import nl.sean.dea.dto.*;
import nl.sean.dea.service.AuthenticationService;
import nl.sean.dea.service.PlaylistService;
import nl.sean.dea.service.TrackService;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlaylistResourceTest {

    @Mock
    PlaylistService playlistServiceMock;

    @Mock
    AuthenticationService authenticationServiceMock;

    @Mock
    TrackService trackServiceMock;

    @InjectMocks
    private PlaylistResource sut;

    private final String VALID_TOKEN = "1234";
    private final TrackDTO testTrack = new TrackDTO(1, "test", "test", 1, "test", 1, "1990-01-01", "test", true);
    private final TracksDTO testTracklist = new TracksDTO(new ArrayList<>(Collections.singletonList(testTrack)));
    private final PlaylistDTO testPlaylist = new PlaylistDTO(1, "Testlist", true, testTracklist.getTracks());
    private final int testLength = 10;
    private final PlaylistsDTO testPlaylistsDTO = new PlaylistsDTO(new ArrayList<>(Collections.singletonList(testPlaylist)), testLength);
    private final TokenDTO testToken = new TokenDTO("Sean", VALID_TOKEN);

    @Test
    void getAllPlaylistsSuccess() {
        when(authenticationServiceMock.checkToken(any())).thenReturn(testToken);
        when(playlistServiceMock.getAllPlaylists(any())).thenReturn(testPlaylistsDTO);

        Response actualResult = sut.getAllPlaylists(VALID_TOKEN);
        verify(authenticationServiceMock).checkToken(VALID_TOKEN);
        assertEquals(Response.Status.OK.getStatusCode(), actualResult.getStatus());

        PlaylistsDTO actualPlaylistsDTO = (PlaylistsDTO) actualResult.getEntity();
        assertEquals(testPlaylist, actualPlaylistsDTO.getPlaylists().get(0));
    }

    @Test
    void getTracksFromPlaylistSuccess() {
        when(trackServiceMock.getAllTracksFromPlaylist(anyInt())).thenReturn(testTracklist);

        Response actualResult = sut.getTracksFromPlaylist(1, VALID_TOKEN);
        verify(authenticationServiceMock).checkToken(VALID_TOKEN);

        assertEquals(Response.Status.OK.getStatusCode(), actualResult.getStatus());

        TracksDTO actualTracksDTO = (TracksDTO) actualResult.getEntity();
        assertEquals(testTracklist, actualTracksDTO);
    }

    @Test
    void deletePlaylistSuccess() {
        when(authenticationServiceMock.checkToken(any())).thenReturn(testToken);

        Response actualResult = sut.deletePlaylist(1, VALID_TOKEN);
        verify(authenticationServiceMock).checkToken(VALID_TOKEN);
        assertEquals(Response.Status.OK.getStatusCode(), actualResult.getStatus());

        verify(playlistServiceMock).deletePlaylist("Sean", 1);
    }

    @Test
    void addPlaylistSuccess() {
        when(authenticationServiceMock.checkToken(any())).thenReturn(testToken);

        Response actualResult = sut.addPlaylist(testPlaylist, VALID_TOKEN);
        verify(authenticationServiceMock).checkToken(VALID_TOKEN);
        assertEquals(Response.Status.CREATED.getStatusCode(), actualResult.getStatus());

        verify(playlistServiceMock).addPlaylist("Sean", testPlaylist);
    }

    @Test
    void emptyConstuctorUsedByWebcontainer() {
        PlaylistResource playlistResource = new PlaylistResource();
    }
}
