package api.course;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class AsanResource {

    @GET // GET method is used to retrieve data from a resource
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello!";
    }

    @POST
    @Path("greeting-text")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String greetingText(String greeting) { // ex: "Hello John!"
        return greeting;
    }

    @POST
    @Path("greeting-json")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String greetingJson(String greeting) {
        return greeting;
    }
}