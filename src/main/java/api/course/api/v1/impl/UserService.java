package api.course.api.v1.impl;

import api.course.api.v1.models.User;
import api.course.utilities.Constants;
import api.course.utilities.Storage;
import org.jboss.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.PathParam;
import java.util.List;

@ApplicationScoped
public class UserService {

  private static final Logger logger = Logger.getLogger(UserService.class.getName());

  public User createUser(User user) {
    logger.info("createUser invoked: user=" + user);
    // TODO validate user
    Storage.getUserList().add(user);
    return user;
  }

  public User getUser(Integer id) {
    logger.info("getUser invoked: id=" + id);
    // TODO validate id
    User foundUser = Storage.getUserList().get(id);
    return foundUser;
  }

  public List<User> listUsers() {
    logger.info("listUsers invoked");
    // TODO page list
    List<User> userList = Storage.getUserList();
    return userList;
  }

  public User updateUser(Integer id, User user) {
    logger.info("updateUser invoked: id=" + id + ", user=" + user);
    // TODO validate id and user
    Storage.getUserList().remove((int) id);
    Storage.getUserList().add(id, user);
    return user;
  }

  public void deleteUser(Integer id) {
    logger.info("deleteUser invoked: id=" + id);
    Storage.getUserList().remove((int) id);
  }
}
