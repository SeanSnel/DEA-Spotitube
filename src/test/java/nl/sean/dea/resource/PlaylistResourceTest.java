//package nl.sean.dea.resource;
//
//import nl.sean.dea.dto.ErrorDTO;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import javax.ws.rs.core.Response;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//class PlaylistResourceTest {
//    private PlaylistResource sut;
//    private final String VALID_TOKEN = "1234";
//    private final String INVALID_TOKEN = "12345";
//
//    @BeforeEach
//    void setUp() {
//        sut = new PlaylistResource();
//    }
//
//    @Test
//    void allPlaylistsSuccess() {
//        Response actualResult = sut.getAllPlaylists(VALID_TOKEN);
//        assertEquals(Response.Status.OK.getStatusCode(), actualResult.getStatus());
//    }
//
//    @Test
//    void allPlaylistsFail() {
//        Response actualResult = sut.getAllPlaylists(INVALID_TOKEN);
//        ErrorDTO errorDTO = (ErrorDTO) actualResult.getEntity();
//
//        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), actualResult.getStatus());
//        assertEquals("Invalid token.", errorDTO.getMessage());
//    }
//
//    @Test
//    void getTracksFromPlaylistSuccess() {
//        Response actualResult = sut.getTracksFromPlaylist(1, VALID_TOKEN);
//        assertEquals(Response.Status.OK.getStatusCode(), actualResult.getStatus());
//    }
//
//    @Test
//    void getTracksFromPlaylistFail() {
//        Response actualResult = sut.getTracksFromPlaylist(1, INVALID_TOKEN);
//        ErrorDTO errorDTO = (ErrorDTO) actualResult.getEntity();
//
//        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), actualResult.getStatus());
//        assertEquals("Invalid token.", errorDTO.getMessage());
//    }
//}