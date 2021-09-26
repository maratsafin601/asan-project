package api.course.api.v1.rest;

import api.course.api.v1.impl.UserService;
import api.course.api.v1.models.Error;
import api.course.api.v1.models.User;
import api.course.utilities.Constants;
import api.course.utilities.UserGenerator;
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
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/asan" + Constants.UriFragment.API_V1)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

  private static final Logger logger = Logger.getLogger(UserResource.class.getName());
  @Inject private UserService userService;

  @POST
  @Path(Constants.Endpoint.USERS)
  public Response createUser(@Valid @NotNull User user) {
    logger.info("createUser endpoint hit: user=" + user);
    User newUser = userService.createUser(user);
    return Response.status(Response.Status.CREATED).entity(newUser).build();
  }

  @GET
  @Path(Constants.Endpoint.USERS + Constants.UriFragment.ID)
  public Response getUser(@PathParam(Constants.PathParam.ID) Integer id) {
    logger.info("getUser endpoint hit: id=" + id);
    Response response = userService.checkId(id);
    if (response != null) {
      return response;
    }
    User foundUser = userService.getUser(id);
    return Response.status(Response.Status.OK).entity(foundUser).build();
  }

  @GET
  @Path(Constants.Endpoint.USERS)
  public List<User> listUsers() {
    logger.info("listUsers endpoint hit");
    return userService.listUsers();
  }

  @PUT
  @Path(Constants.Endpoint.USERS + Constants.UriFragment.ID)
  public Response updateUser(
      @PathParam(Constants.PathParam.ID) Integer id, @Valid @NotNull User user) {
    logger.info("updateUser endpoint hit: id=" + id + ", user=" + user);
    Response response = userService.checkId(id);
    if (response != null) {
      return response;
    }
    userService.updateUser(id, user);
    return Response.status(Response.Status.OK).entity(user).build();
  }

  @DELETE
  @Path(Constants.Endpoint.USERS + Constants.UriFragment.ID)
  public Response deleteUser(@PathParam(Constants.PathParam.ID) Integer id) {
    logger.info("deleteUser endpoint hit: id=" + id);
    Response response = userService.checkId(id);
    if (response != null) {
      return response;
    }
    userService.deleteUser(id);
    return Response.status(Response.Status.NO_CONTENT).build();
  }

  /** BUG1 - WRONG RESPONSE STATUS CODE */
  private static final String URI_FRAGMENT_BUG1 = "/bug1";

  @POST
  @Path(URI_FRAGMENT_BUG1)
  public Response createUser_Bug1(User user) {
    logger.info(URI_FRAGMENT_BUG1 + " createUser endpoint hit: user=" + user);
    return Response.status(Response.Status.FOUND).entity(user).build();
  }

  @GET
  @Path(URI_FRAGMENT_BUG1 + Constants.UriFragment.ID)
  public Response getUser_Bug1(@PathParam(Constants.PathParam.ID) Integer id) {
    logger.info(URI_FRAGMENT_BUG1 + " getUser endpoint hit: id=" + id);
    return Response.status(Response.Status.CREATED).entity(userService.getUser(id)).build();
  }

  @PUT
  @Path(URI_FRAGMENT_BUG1 + Constants.UriFragment.ID)
  public Response updateUser_Bug1(@PathParam(Constants.PathParam.ID) Integer id, User user) {
    logger.info(URI_FRAGMENT_BUG1 + " updateUser endpoint hit: id=" + id + ", user=" + user);
    return Response.status(Response.Status.ACCEPTED).entity(user).build();
  }

  @DELETE
  @Path(URI_FRAGMENT_BUG1 + Constants.UriFragment.ID)
  public Response deleteUser_Bug1(@PathParam(Constants.PathParam.ID) Integer id) {
    logger.info(URI_FRAGMENT_BUG1 + " deleteUser endpoint hit: id=" + id);
    return Response.status(Response.Status.NOT_FOUND).build();
  }

  /** BUG2 - WRONG RESPONSE BODY */
  private static final String URI_FRAGMENT_BUG2 = "/bug2";

  @POST
  @Path(URI_FRAGMENT_BUG2)
  public Response createUser_bug2(User user) {
    logger.info(URI_FRAGMENT_BUG2 + " createUser endpoint hit: user=" + user);
    return Response.status(Response.Status.CREATED).entity(UserGenerator.generate()).build();
  }

  @GET
  @Path(URI_FRAGMENT_BUG2 + Constants.UriFragment.ID)
  public Response getUser_bug2(@PathParam(Constants.PathParam.ID) Integer id) {
    logger.info(URI_FRAGMENT_BUG2 + " getUser endpoint hit: id=" + id);
    User foundUser = userService.getUser(id);
    User wrongUser = UserGenerator.generate();
    foundUser.setAddress(wrongUser.getAddress());
    return Response.status(Response.Status.OK).entity(foundUser).build();
  }

  @PUT
  @Path(URI_FRAGMENT_BUG2 + Constants.UriFragment.ID)
  public Response updateUser_bug2(@PathParam(Constants.PathParam.ID) Integer id, User user) {
    logger.info(URI_FRAGMENT_BUG2 + " updateUser endpoint hit: id=" + id + ", user=" + user);
    String firstName = user.getFirstName();
    user.setFirstName(user.getLastName());
    user.setLastName(firstName);
    return Response.status(Response.Status.OK).entity(user).build();
  }

  @DELETE
  @Path(URI_FRAGMENT_BUG2 + Constants.UriFragment.ID)
  public Response deleteUser_bug2(@PathParam(Constants.PathParam.ID) Integer id) {
    logger.info(URI_FRAGMENT_BUG2 + " deleteUser endpoint hit: id=" + id);
    return Response.status(Response.Status.OK)
        .entity(new Error(Response.Status.NO_CONTENT, Constants.Error.MUST_NOT_BE_EMPTY))
        .build();
  }

  /** BUG3 - FUNCTIONAL TESTING */
  private static final String URI_FRAGMENT_BUG3 = "/bug3";

  @POST
  @Path(URI_FRAGMENT_BUG3)
  public Response createUser_bug3(User user) {
    logger.info(URI_FRAGMENT_BUG3 + " createUser endpoint hit: user=" + user);
    return Response.status(Response.Status.CREATED).entity(user).build();
  }

  @GET
  @Path(URI_FRAGMENT_BUG3 + Constants.UriFragment.ID)
  public Response getUser_bug3(@PathParam(Constants.PathParam.ID) Integer id) {
    logger.info(URI_FRAGMENT_BUG3 + " getUser endpoint hit: id=" + id);
    User generatedUser = UserGenerator.generate();
    userService.createUser(generatedUser);
    return Response.status(Response.Status.OK).entity(generatedUser).build();
  }

  @PUT
  @Path(URI_FRAGMENT_BUG3 + Constants.UriFragment.ID)
  public Response updateUser_bug3(@PathParam(Constants.PathParam.ID) Integer id, User user) {
    logger.info(URI_FRAGMENT_BUG3 + " updateUser endpoint hit: id=" + id + ", user=" + user);
    return Response.status(Response.Status.OK).entity(user).build();
  }

  @DELETE
  @Path(URI_FRAGMENT_BUG3 + Constants.UriFragment.ID)
  public Response deleteUser_bug3(@PathParam(Constants.PathParam.ID) Integer id) {
    logger.info(URI_FRAGMENT_BUG3 + " deleteUser endpoint hit: id=" + id);
    return Response.status(Response.Status.NO_CONTENT).build();
  }
}