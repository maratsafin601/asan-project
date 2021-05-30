package api.course.api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/asan")
public class AsanaResource {

    /**
     * GET method is used to RETRIEVE the data from a server at the specified resource.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getGreeting() {
        return "Hello User!";
    }

    /**
     * POST method is used to SEND the data to a server to CREATE a new resource.
     */
    @POST
    @Path("greeting-text")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String createGreetingText(String username) {
        return "Hello " + username + "!"; // NOTE: resource is returned, but not created
    }

//    @POST
//    @Path("greeting-text")
//    @Produces(MediaType.TEXT_PLAIN)
//    @Consumes(MediaType.TEXT_PLAIN)
//    public String createGreetingText(String username) {
//        return "Hello " + username + "!"; // NOTE: resource is returned, but not created
//    }


    //
    @POST
    @Path("greeting-json")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String createGreetingJson(String greeting) {
        return greeting;
    }
}