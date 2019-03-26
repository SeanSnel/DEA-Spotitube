package nl.sean.dea.resource;

import nl.sean.dea.dto.TokenDTO;
import nl.sean.dea.service.AuthenticationService;
import nl.sean.dea.service.PlaylistService;
import nl.sean.dea.service.TrackService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistResource {

    private PlaylistService playlistService;
    private TrackService trackService;
    private AuthenticationService authenticationService;

    public PlaylistResource() {
    }

    @Inject
    public PlaylistResource(PlaylistService playlistService, TrackService trackService, AuthenticationService authenticationService) {
        this.playlistService = playlistService;
        this.trackService = trackService;
        this.authenticationService = authenticationService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylists(@QueryParam("token") String token) {
        TokenDTO user = authenticationService.checkToken(token);
        return Response.ok(playlistService.getAllPlaylists()).build();
    }

    @GET
    @Path("{id}/tracks")
    public Response getTracksFromPlaylist(@PathParam("id") int playlistID, @QueryParam("token") String token) {
        authenticationService.checkToken(token);
        return Response.ok(trackService.getAllTracksFromPlaylist(playlistID)).build();
    }
}
