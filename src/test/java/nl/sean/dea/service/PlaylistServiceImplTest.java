package nl.sean.dea.service;

import nl.sean.dea.dto.PlaylistDTO;
import nl.sean.dea.dto.PlaylistsDTO;
import nl.sean.dea.dto.TrackDTO;
import nl.sean.dea.dto.TracksDTO;
import nl.sean.dea.persistence.PlaylistDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlaylistServiceImplTest {

    @Mock
    PlaylistDAO playlistDAOMock;

    @InjectMocks
    PlaylistServiceImpl sut;

    private final int testLength = 10;

    private PlaylistDTO testPlaylist;
    private PlaylistsDTO testPlaylistList;

    @BeforeEach
    void setUp() {
        TrackDTO testTrack = new TrackDTO(1, "test", "test", testLength, "test", 1, "1990-01-01", "test", true);
        TracksDTO testTracklist = new TracksDTO(new ArrayList<>(Collections.singletonList(testTrack)));
        testPlaylist = new PlaylistDTO(1, "Testlist", true, testTracklist.getTracks());
        testPlaylistList = new PlaylistsDTO(new ArrayList<>(Collections.singletonList(testPlaylist)), 0);
    }

    @Test
    void getAllPlaylistsCalled() {
        when(playlistDAOMock.getAllPlaylists(anyString())).thenReturn(testPlaylistList);

        PlaylistsDTO actualResult = sut.getAllPlaylists("Sean");

        verify(playlistDAOMock).getAllPlaylists("Sean");
        assertEquals(testLength, actualResult.getLength());
    }

    @Test
    void getPlaylistByIdCalled() {
        when(playlistDAOMock.getPlaylist(anyString(), anyInt())).thenReturn(testPlaylist);

        PlaylistDTO actualResult = sut.getPlaylist("Sean", 1);

        verify(playlistDAOMock).getPlaylist("Sean", 1);

        assertEquals(1, actualResult.getTracks().size());
    }

    @Test
    void deletePlaylistCalled() {
        when(playlistDAOMock.deletePlaylist(anyString(), anyInt())).thenReturn(testPlaylistList);

        PlaylistsDTO actualResult = sut.deletePlaylist("Sean", 1);

        verify(playlistDAOMock).deletePlaylist("Sean", 1);

        assertEquals(1, actualResult.getPlaylists().size());
    }

    @Test
    void addPlaylistCalled() {
        when(playlistDAOMock.addPlaylist(anyString(), any(PlaylistDTO.class))).thenReturn(testPlaylistList);

        PlaylistsDTO actualResult = sut.addPlaylist("Sean", testPlaylist);

        verify(playlistDAOMock).addPlaylist("Sean", testPlaylist);

        assertEquals(1, actualResult.getPlaylists().size());
    }

    @Test
    void editPlaylistCalled() {
        when(playlistDAOMock.editPlaylist(anyString(), anyInt(), any(PlaylistDTO.class))).thenReturn(testPlaylistList);

        PlaylistsDTO actualResult = sut.editPlaylist("Sean", 1, testPlaylist);

        verify(playlistDAOMock).editPlaylist("Sean", 1, testPlaylist);
        assertEquals(1, actualResult.getPlaylists().size());
    }

    @Test
    void emptyConstructorUsedByWebcontainer() {
        PlaylistServiceImpl playlistService = new PlaylistServiceImpl();
    }
}
