package api.course.api.v1.impl;

import api.course.api.v1.models.User;
import api.course.utilities.Constants;
import api.course.utilities.Storage;
import org.jboss.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserService {

  private static final Logger logger = Logger.getLogger(UserService.class.getName());

  public User createUser(User user) {
    logger.info("createUser invoked: user=" + user);
    final String id = UUID.randomUUID().toString();
    final Instant now = Instant.now();
    user.setId(id);
    user.setCreatedOn(now);
    user.setUpdatedOn(now);
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

  public List<User> listUsers(String sort) {
    logger.info("listUsers invoked");
    List<User> userList = new ArrayList<>(Storage.getUserMap().values());
    if (sort.equals(Constants.QueryParam.ASC)) {
      return userList.stream()
          .sorted(Comparator.comparing(User::getFirstName))
          .collect(Collectors.toList());
    }
    if (sort.equals(Constants.QueryParam.DESC)) {
      return userList.stream()
              .sorted(Comparator.comparing(User::getFirstName).reversed())
              .collect(Collectors.toList());
    }
    return userList;
  }

  public User updateUser(String id, User user) {
    logger.info("updateUser invoked: id=" + id + ", user=" + user);
    if (Storage.getUserMap().containsKey(id)) {
      User existingUser = Storage.getUserMap().get(id);
      user.setId(id);
      user.setCreatedOn(existingUser.getCreatedOn());
      user.setUpdatedOn(Instant.now());
      Storage.getUserMap().put(id, user);
      return user;
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
