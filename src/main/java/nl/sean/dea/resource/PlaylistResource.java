package nl.sean.dea.resource;

import nl.sean.dea.PlaylistStoreSingleton;
import nl.sean.dea.dto.ErrorDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylists(@QueryParam("token") String token) {
        if (!"1234".equals(token)) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO("Invalid token.")).build();
        }
        return Response.ok(PlaylistStoreSingleton.getInstance().getPlaylists()).build();
    }

    @GET
    @Path("{id}/tracks")
    public Response getTracksFromPlaylist(@PathParam("id") int playlistID, @QueryParam("token") String token) {
        if (!"1234".equals(token)) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO("Invalid token.")).build();
        }
        return Response.ok(PlaylistStoreSingleton.getInstance().getTracksFromPlaylist(playlistID)).build();
    }
}
