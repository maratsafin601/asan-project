package api.course.api.v1.impl;

import api.course.api.v1.models.Error;
import api.course.api.v1.models.User;
import api.course.utilities.Constants;
import api.course.utilities.Storage;
import org.jboss.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserService {

  private static final Logger logger = Logger.getLogger(UserService.class.getName());

  public User createUser(User user) {
    logger.info("createUser invoked: user=" + user);
    final String id = UUID.randomUUID().toString();
    user.setId(id);
    Storage.getUserMap().put(id, user);
    return user;
  }

  public User getUser(String id) {
    logger.info("getUser invoked: id=" + id);
    if (Storage.getUserMap().containsKey(id)) {
      return Storage.getUserMap().get(id);
    }
    return null;
  }

  public List<User> listUsers() {
    logger.info("listUsers invoked");
    return new ArrayList<>(Storage.getUserMap().values());
  }

  public User updateUser(String id, User user) {
    logger.info("updateUser invoked: id=" + id + ", user=" + user);
    if (Storage.getUserMap().containsKey(id)) {
      Storage.getUserMap().put(id, user);
      return Storage.getUserMap().get(id);
    }
    return null;
  }

  public boolean deleteUser(String id) {
    logger.info("deleteUser invoked: id=" + id);
    if (Storage.getUserMap().containsKey(id)) {
      Storage.getUserMap().remove(id);
      return true;
    }
    return false;
  }
}
