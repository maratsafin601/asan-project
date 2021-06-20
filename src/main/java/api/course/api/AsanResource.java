package api.course.api;

import api.course.models.User;
import api.course.models.Users;
import org.jboss.logging.annotations.Param;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/asan")
public class AsanResource {

    private static final Users users = new Users();

    // --- API V0 -------------------------------------------------------------

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
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String createGreetingText(String username) {
        return "Hello " + username + "!"; // NOTE: resource is returned, but not created
    }

//    @POST
//    @Produces(MediaType.APPLICATION_JSON) // NOTE: New Media-Type
//    @Consumes(MediaType.APPLICATION_JSON)
//    public String createGreetingJson(String username) {
//        return "Hello " + username + "!";
//    }



    // --- API V1 -------------------------------------------------------------

    @GET
    @Path(("/users"))                                                       // FIXME
    @Produces(MediaType.APPLICATION_JSON)
    public Users getUsers() {
        users.getUsers().add(new User("Bob SMith"));
        "".substring(1);
        return users;
    }

    /**
     * POST method is used to SEND the data to a server to CREATE a new resource.
     */
    @POST
    @Path(("/api.v1"))
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String createGreetingText_v1(String username) {
        return "Hello " + username + "!"; // NOTE: resource is returned, but not created
    }

    @POST
    @Path("greeting-text")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String createGreetingText1(String username) {
        return "Hello " + username + "!"; // NOTE: resource is returned, but not created
    }


    //

}