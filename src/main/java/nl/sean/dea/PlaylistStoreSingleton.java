package nl.sean.dea;

import java.util.ArrayList;
import java.util.List;

public class PlaylistStoreSingleton {
    private List<Playlist> playlists;
    private static PlaylistStoreSingleton instance;

    private PlaylistStoreSingleton(){
        this.playlists = new ArrayList<>();
    }

    public static PlaylistStoreSingleton getInstance(){
        if(instance == null){
           instance = new PlaylistStoreSingleton();
        }
        return instance;
    }

    public void addPlaylist(Playlist playlist){
        playlists.add(playlist);
    }

    public void removePlaylist(int playlistID){
        for(Playlist playlist : playlists){
            if(playlist.getId() == playlistID){
                playlists.remove(playlist);
            }
        }
    }

    
}
