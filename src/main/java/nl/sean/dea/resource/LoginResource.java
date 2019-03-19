package nl.sean.dea.resource;

import nl.sean.dea.dto.ErrorDTO;
import nl.sean.dea.dto.TokenDTO;
import nl.sean.dea.dto.UserDTO;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("login")
public class LoginResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response logUserIn(UserDTO user) {
        if ("Sean".equals(user.getUser()) && "test".equals(user.getPassword())) {
            return Response.ok(new TokenDTO(user.getUser(), "1234")).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorDTO("Wrong user/password combination.")).build();
        }
    }
}
