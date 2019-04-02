package nl.sean.dea.service;

import nl.sean.dea.dto.TrackDTO;
import nl.sean.dea.persistence.TrackDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TrackServiceImplTest {

    @Mock
    TrackDAO trackDAOMock;

    @InjectMocks
    TrackServiceImpl sut;

    private TrackDTO testTrack;

    @BeforeEach
    void setUp() {
        int testLength = 10;
        testTrack = new TrackDTO(1, "test", "test", testLength, "test", 1, "1990-01-01", "test", true);
    }

    @Test
    void getAllTracksSuccess() {
        sut.getAllTracks();

        verify(trackDAOMock).getAllTracks();
    }

    @Test
    void getAllTracksFromPlaylistCalled() {
        sut.getAllTracksFromPlaylist(1);

        verify(trackDAOMock).getAllTracksFromPlaylist(1);
    }

    @Test
    void deleteTrackFromPlaylistCalled() {
        sut.deleteTrackFromPlaylist(1, 1);

        verify(trackDAOMock).deleteTrackFromPlaylist(1, 1);
    }

    @Test
    void addTrackToPlaylistCalled() {
        sut.addTrackToPlaylist(1, testTrack);

        verify(trackDAOMock).addTrackToPlaylist(1, testTrack);
    }

    @Test
    void getAllTracksNotInPlaylistCalled() {
        sut.getAllTracksNotInPlaylist(1);

        verify(trackDAOMock).getAllTracksNotInPlaylist(1);
    }

    @Test
    void emptyConstructorUsedByWebcontainer() {
        TrackService trackService = new TrackServiceImpl();
    }
}