package nl.sean.dea.resource;

import nl.sean.dea.service.TrackService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("tracks")
public class TrackResource {

    private TrackService trackService;

    public TrackResource() {
    }

    @Inject
    public TrackResource(TrackService trackService) {
        this.trackService = trackService;
    }

    @GET
    public Response getAllTracks() {
        return Response.ok(trackService.getAllTracks()).build();
    }
}
