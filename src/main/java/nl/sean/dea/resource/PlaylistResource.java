package nl.sean.dea.resource;

import nl.sean.dea.dto.PlaylistDTO;
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
        TokenDTO userToken = authenticationService.checkToken(token);
        return Response.ok(playlistService.getAllPlaylists(userToken.getUser())).build();
    }

    @GET
    @Path("{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksFromPlaylist(@PathParam("id") int playlistID, @QueryParam("token") String token) {
        authenticationService.checkToken(token);
        return Response.ok(trackService.getAllTracksFromPlaylist(playlistID)).build();
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@PathParam("id") int playlistID, @QueryParam("token") String token){
        TokenDTO userToken = authenticationService.checkToken(token);
        return Response.ok(playlistService.deletePlaylist(userToken.getUser(), playlistID)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(PlaylistDTO playlist, @QueryParam("token") String token){
        TokenDTO userToken = authenticationService.checkToken(token);
        return Response.status(Response.Status.CREATED).entity(playlistService.addPlaylist(userToken.getUser(), playlist)).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPlaylist(@PathParam("id") int playlistID, PlaylistDTO changedPlaylist, @QueryParam("token") String token){
        TokenDTO userToken = authenticationService.checkToken(token);
        return Response.ok(playlistService.editPlaylist(userToken.getUser(), playlistID, changedPlaylist)).build();
    }
}
