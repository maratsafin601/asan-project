package api.course.api.v1.impl;

import api.course.api.v1.models.Error;
import api.course.api.v1.models.User;
import api.course.utilities.Constants;
import api.course.utilities.Storage;
import org.jboss.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class UserService {

  private static final Logger logger = Logger.getLogger(UserService.class.getName());

  public User createUser(User user) {
    logger.info("createUser invoked: user=" + user);
    Storage.getUserList().add(user);
    return user;
  }

  public User getUser(Integer id) {
    logger.info("getUser invoked: id=" + id);
    User foundUser = Storage.getUserList().get(id);
    return foundUser;
  }

  public List<User> listUsers() {
    logger.info("listUsers invoked");
    List<User> userList = Storage.getUserList();
    return userList;
  }

  public User updateUser(Integer id, User user) {
    logger.info("updateUser invoked: id=" + id + ", user=" + user);
    Storage.getUserList().remove((int) id);
    Storage.getUserList().add(id, user);
    return user;
  }

  public void deleteUser(Integer id) {
    logger.info("deleteUser invoked: id=" + id);
    Storage.getUserList().remove((int) id);
  }

  public Response checkId (Integer id) {
    logger.info("checkId invoked: id=" + id);
    if (id < 0) {
      logger.warn(Constants.Error.INVALID_ID + ": id=" + id);
      return Response.status(Response.Status.BAD_REQUEST)
              .entity(new Error(Response.Status.BAD_REQUEST, Constants.Error.INVALID_ID))
              .build();
    }
    if (id >= Storage.getUserList().size()) {
      logger.warn(Constants.Error.INVALID_ID + ": id=" + id);
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    return null;
  }
}
