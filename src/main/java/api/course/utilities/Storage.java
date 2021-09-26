package api.course.utilities;

import api.course.api.v1.models.Address;
import api.course.api.v1.models.Status;
import api.course.api.v1.models.User;

import java.util.ArrayList;
import java.util.List;

public class Storage {

  private static List<User> userList = new ArrayList<>();

  static {
    Address address = new Address();
    address.setStreetAddress("Park St");
    address.setCity("Greenbow");
    address.setZipCode("12345");
    address.setCountry("USA");

    User user = new User();
    user.setStatus(Status.PENDING);
    user.setUuid("aba9e794-620f-4338-ad23-eb1d7e0ac2d6");
    user.setFirstName("Forrest");
    user.setLastName("Gump");
    user.setEmail(user.getFirstName() + "." + user.getLastName() + "@gmail.com");
    user.setAddress(address);

    userList.add(user);
  }

  private Storage() {
    /* No-op: prevents instantiation. */
  }

  public static List<User> getUserList() {
    return userList;
  }
}
