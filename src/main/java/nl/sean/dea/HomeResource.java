package nl.sean.dea;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/")
public class HomeResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getHomePage(){
        return Response.ok("Hello world").build();
    }
}
