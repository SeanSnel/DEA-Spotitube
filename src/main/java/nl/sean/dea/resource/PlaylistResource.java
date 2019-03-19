package nl.sean.dea.resource;

import nl.sean.dea.Playlist;
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
        if(!"1234".equals(token)){
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO("Invalid token.")).build();

        }
        return Response.ok(PlaylistStoreSingleton.getInstance().getPlaylists()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(Playlist playlist) {
        return Response.ok(PlaylistStoreSingleton.getInstance().addPlaylist(playlist)).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removePlaylist(@PathParam("id") int id) {
        return Response.ok(PlaylistStoreSingleton.getInstance().removePlaylist(id)).build();
    }
}
