package api.course.api.v1.rest;

import api.course.api.v1.impl.UserService;
import api.course.api.v1.models.Error;
import api.course.api.v1.models.User;
import api.course.utilities.constant.Constants;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;
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
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/asan" + Constants.UriFragment.API_V1 + Constants.Endpoint.USERS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

  private static final String USER_NOT_FOUND = "user not found: id=";
  private static final Logger logger = Logger.getLogger(UserResource.class.getName());

  @Inject private UserService userService;

  @POST
  @Path("/")
  public Response createUser(@Valid @NotNull User user) {
    logger.info("createUser endpoint hit: user=" + user);
    User createdUser = userService.createUser(user);
    return Response.status(Response.Status.CREATED).entity(createdUser).build();
  }

  @GET
  @Path(Constants.UriFragment.ID)
  public Response getUser(@PathParam(Constants.PathParam.ID) String id) {
    logger.info("getUser endpoint hit: id=" + id);
    User foundUser = userService.getUser(id);
    if (foundUser != null) {
      return Response.status(Response.Status.OK).entity(foundUser).build();
    }
    logger.info(USER_NOT_FOUND + id);
    return Response.status(Response.Status.NOT_FOUND)
        .entity(new Error(Response.Status.NOT_FOUND, USER_NOT_FOUND + id))
        .build();
  }

  @GET
  @Path("/")
  public Response listUsers(@QueryParam(Constants.QueryParam.SORT) String sort) {
    logger.info("listUsers endpoint hit");
    List<User> userList = userService.listUsers(sort);
    return Response.status(Response.Status.OK).entity(userList).build();
  }

  @PUT
  @Path(Constants.UriFragment.ID)
  public Response updateUser(
      @PathParam(Constants.PathParam.ID) String id, @Valid @NotNull User user) {
    logger.info("updateUser endpoint hit: id=" + id + ", user=" + user);
    User updatedUser = userService.updateUser(id, user);
    if (updatedUser != null) {
      return Response.status(Response.Status.OK).entity(user).build();
    }
    logger.info(USER_NOT_FOUND + id);
    return Response.status(Response.Status.NOT_FOUND)
        .entity(new Error(Response.Status.NOT_FOUND, USER_NOT_FOUND + id))
        .build();
  }

  @DELETE
  @Path(Constants.UriFragment.ID)
  public Response deleteUser(@PathParam(Constants.PathParam.ID) String id) {
    logger.info("deleteUser endpoint hit: id=" + id);
    if (userService.deleteUser(id)) {
      return Response.status(Response.Status.NO_CONTENT).build();
    }
    logger.info(USER_NOT_FOUND + id);
    return Response.status(Response.Status.NOT_FOUND)
        .entity(new Error(Response.Status.NOT_FOUND, USER_NOT_FOUND + id))
        .build();
  }
}
