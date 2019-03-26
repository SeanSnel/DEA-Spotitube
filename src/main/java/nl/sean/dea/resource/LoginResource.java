package nl.sean.dea.resource;

import nl.sean.dea.dto.UserDTO;
import nl.sean.dea.service.AuthenticationService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("login")
public class LoginResource {

    private AuthenticationService authenticationService;

    public LoginResource() {
    }

    @Inject
    public LoginResource(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response logUserIn(UserDTO user) {
        return Response.ok(authenticationService.login(user)).build();
    }
}
