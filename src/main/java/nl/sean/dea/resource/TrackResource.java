package nl.sean.dea.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("tracks")
public class TrackResource {

    @GET
    public Response getAllTracks(){
        return Response.ok().build();
    }
}
