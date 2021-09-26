package api.course.api.v1.rest;

import api.course.api.v1.impl.UserService;
import api.course.api.v1.models.User;
import api.course.utilities.Constants;
import api.course.utilities.Storage;
import api.course.utilities.UserGenerator;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/asan" + Constants.UriFragment.API_V1 + Constants.Endpoint.USERS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

  private static final Logger logger = Logger.getLogger(UserResource.class.getName());
  @Inject private UserService userService;

  @POST
  public User createUser(@Valid @NotNull User user) {
    logger.info("createUser endpoint hit: user=" + user);
    User newUser = userService.createUser(user);
    return newUser;
  }

  @GET
  @Path(Constants.UriFragment.ID)
  public User getUser(@PathParam(Constants.PathParam.ID) Integer id) {
    logger.info("getUser endpoint hit: id=" + id);
    User foundUser = userService.getUser(id);
    return foundUser;
  }

  @GET
  public List<User> listUsers() {
    logger.info("listUsers endpoint hit");
    return userService.listUsers();
  }

  @PUT
  @Path(Constants.UriFragment.ID)
  public User updateUser(@PathParam(Constants.PathParam.ID) Integer id, @Valid @NotNull User user) {
    logger.info("updateUser endpoint hit: id=" + id + ", user=" + user);
    userService.updateUser(id, user);
    return user;
  }

  @DELETE
  @Path(Constants.UriFragment.ID)
  public void deleteUser(@PathParam("id") Integer id) {
    logger.info("deleteUser endpoint hit: id=" + id);
    userService.deleteUser(id);
  }
}
