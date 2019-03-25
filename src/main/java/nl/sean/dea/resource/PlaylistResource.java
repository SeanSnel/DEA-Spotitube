package nl.sean.dea.resource;

import nl.sean.dea.dto.ErrorDTO;
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

    public PlaylistResource() {
    }

    @Inject
    public PlaylistResource(PlaylistService playlistService, TrackService trackService) {
        this.playlistService = playlistService;
        this.trackService = trackService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylists(@QueryParam("token") String token) {
        if (!"1234".equals(token)) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO("Invalid token.")).build();
        }
        return Response.ok(playlistService.getAllPlaylists()).build();
    }

    @GET
    @Path("{id}/tracks")
    public Response getTracksFromPlaylist(@PathParam("id") int playlistID, @QueryParam("token") String token) {
        if (!"1234".equals(token)) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO("Invalid token.")).build();
        }
        return Response.ok(trackService.getAllTracksFromPlaylist(playlistID)).build();
    }
}
