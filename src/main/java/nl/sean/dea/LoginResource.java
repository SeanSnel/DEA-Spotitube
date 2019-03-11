package nl.sean.dea;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(User user) {
        if ("Sean".equals(user.getUsername()) && "test".equals(user.getPassword())) {
            return Response.ok(new Login(user.getUsername(), "1234")).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
