package nl.sean.dea;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/login")
public class LoginResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(){
        return Response.ok().build();
    }
}
