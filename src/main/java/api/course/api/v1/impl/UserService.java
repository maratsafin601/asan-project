package api.course.api.v1.impl;

import api.course.api.v1.models.Action;
import api.course.api.v1.models.Event;
import api.course.api.v1.models.User;
import api.course.utilities.UserStorage;
import org.jboss.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class UserService {

  private static final Logger logger = Logger.getLogger(UserService.class.getName());

  @Inject private AuditService auditService;

  public User createUser(User user) {
    logger.info("createUser invoked: user=" + user);
    final String id = UUID.randomUUID().toString();
    final Instant now = Instant.now();
    user.setId(id);
    user.setCreatedOn(now);
    user.setUpdatedOn(now);
    UserStorage.addUser(id, user);
    auditService.createEvent(Action.POST, Event.Status.SUCCESS, user.getId());
    return user;
  }

  public User getUser(String id) {
    logger.info("getUser invoked: id=" + id);
    if (UserStorage.hasUser(id)) {
      auditService.createEvent(Action.GET, Event.Status.SUCCESS, id);
      return UserStorage.getUser(id);
    }
    auditService.createEvent(Action.GET, Event.Status.FAILURE, id);
    return null;
  }

  // TODO query params
  public List<User> listUsers(String sort) {
    logger.info("listUsers invoked");
    List<User> userList = UserStorage.getUserList();
//    if (sort.equals(Constants.QueryParam.ASC)) {
//      return userList.stream()
//          .sorted(Comparator.comparing(User::getFirstName))
//          .collect(Collectors.toList());
//    }
//    if (sort.equals(Constants.QueryParam.DESC)) {
//      return userList.stream()
//              .sorted(Comparator.comparing(User::getFirstName).reversed())
//              .collect(Collectors.toList());
//    }
    auditService.createEvent(Action.LIST, Event.Status.SUCCESS);
    return userList;
  }

  public User updateUser(String id, User user) {
    logger.info("updateUser invoked: id=" + id + ", user=" + user);
    if (UserStorage.hasUser(id)) {
      User existingUser = UserStorage.getUser(id);
      user.setId(existingUser.getId());
      user.setCreatedOn(existingUser.getCreatedOn());
      user.setUpdatedOn(Instant.now());
      UserStorage.updateUser(id, user);
      auditService.createEvent(Action.PUT, Event.Status.SUCCESS, id);
      return user;
    }
    auditService.createEvent(Action.PUT, Event.Status.FAILURE, id);
    return null;
  }

  public boolean deleteUser(String id) {
    logger.info("deleteUser invoked: id=" + id);
    if (UserStorage.hasUser(id)) {
      UserStorage.deleteUser(id);
      auditService.createEvent(Action.DELETE, Event.Status.SUCCESS, id);
      return true;
    }
    auditService.createEvent(Action.DELETE, Event.Status.ALERT, id);
    return false;
  }
}
