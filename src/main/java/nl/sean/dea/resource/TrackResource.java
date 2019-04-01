package nl.sean.dea.resource;

import nl.sean.dea.service.AuthenticationService;
import nl.sean.dea.service.TrackService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("tracks")
public class TrackResource {

    private TrackService trackService;
    private AuthenticationService authenticationService;

    public TrackResource() {
    }

    @Inject
    public TrackResource(TrackService trackService, AuthenticationService authenticationService) {
        this.trackService = trackService;
        this.authenticationService = authenticationService;
    }

    @GET
    public Response getAllTracksNotInPlaylist(@QueryParam("token") String token, @QueryParam("forPlaylist") int playlistID) {
        authenticationService.checkToken(token);
        return Response.ok(trackService.getAllTracksNotInPlaylist(playlistID)).build();
    }
}
