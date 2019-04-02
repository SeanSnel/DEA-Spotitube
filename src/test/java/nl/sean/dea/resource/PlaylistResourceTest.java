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

    @Mock
    PlaylistService playlistServiceMock;

    @Mock
    AuthenticationService authenticationServiceMock;

    @Mock
    TrackService trackServiceMock;

    @InjectMocks
    private PlaylistResource sut;

    private final String VALID_TOKEN = "1234";
    private PlaylistDTO testPlaylist;
    private TrackDTO testTrack;

    @BeforeEach
    void setUp() {
        testTrack = new TrackDTO();
        final String test = "test";
        testTrack.setId(1);
        testTrack.setTitle(test);
        testTrack.setPerformer(test);
        testTrack.setDuration(1);
        testTrack.setAlbum(test);
        testTrack.setPlaycount(1);
        testTrack.setPublicationDate("1990-01-01");
        testTrack.setDescription(test);
        testTrack.setOfflineAvailable(true);

        TracksDTO testTracklist = new TracksDTO(new ArrayList<>(Collections.singletonList(testTrack)));
        testPlaylist = new PlaylistDTO();
        testPlaylist.setId(1);
        testPlaylist.setName("Testlist");
        testPlaylist.setOwner(true);
        testPlaylist.setTracks(testTracklist.getTracks());
        TokenDTO testToken = new TokenDTO();
        testToken.setToken(VALID_TOKEN);
        testToken.setUser("Sean");

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

    @Test
    void deleteTrackFromPlaylistSuccess() {
        Response actualresult = sut.deleteTrackFromPlaylist(1, 1, VALID_TOKEN);

        verify(authenticationServiceMock).checkToken(VALID_TOKEN);
        assertEquals(Response.Status.OK.getStatusCode(), actualresult.getStatus());
        verify(trackServiceMock).deleteTrackFromPlaylist(1, 1);
    }

    @Test
    void addTrackToPlaylistSuccess() {
        Response actualResult = sut.addTrackToPlaylist(1, VALID_TOKEN, testTrack);

        verify(authenticationServiceMock).checkToken(VALID_TOKEN);
        assertEquals(Response.Status.CREATED.getStatusCode(), actualResult.getStatus());
        verify(trackServiceMock).addTrackToPlaylist(1, testTrack);
    }
}
