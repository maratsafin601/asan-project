package api.course.api;

import api.course.models.User;
import api.course.utilities.Storage;
import api.course.utilities.UserGenerator;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/asan")
public class AsanResource {

    /**
     * GREETING API.
     */

    // Simple GET endpoint.
    @GET
    public String getGreeting() {
        User user = UserGenerator.generate();

        return "Hello " + user.getFirstName() + "!";
    }

    // Simple POST endpoint.
    @POST
    public String postGreeting(String name) {

        return "Hello " + name + "!";
    }

    /**
     * USER API.
     */

    @GET
    @Path(("/users/0"))
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@QueryParam("id") int id) {

//        return Storage.getUser(0);
        return null;
    }

    @GET
    @Path(("/users"))
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUsers() {

//        return List.of(UserGenerator.generateUser());
//        return Storage.getUsers();
        return null;
    }

    // GET method is used to RETRIEVE the data from a server at the specified resource.

//    @POST
//    @Produces(MediaType.APPLICATION_JSON) // NOTE: New Media-Type
//    @Consumes(MediaType.APPLICATION_JSON)
//    public String createGreetingJson(String username) {
//        return "Hello " + username + "!";
//    }


//    private static final Users users = new Users();

    // --- API V1 -------------------------------------------------------------

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