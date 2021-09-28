package api.course.utilities;

import api.course.api.v1.models.Address;
import api.course.api.v1.models.Status;
import api.course.api.v1.models.User;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class Storage {

  private static Map<String, User> userMap = new HashMap<>();

  static {
    Address address = new Address();
    address.setStreetAddress("Park St");
    address.setCity("Greenbow");
    address.setZipCode("12345");
    address.setCountry("USA");

    User user = new User();
    user.setStatus(Status.PENDING);
    user.setId("aba9e794-620f-4338-ad23-eb1d7e0ac2d6");
    user.setFirstName("Forrest");
    user.setLastName("Gump");
    user.setEmail(user.getFirstName() + "." + user.getLastName() + "@gmail.com");
    user.setAddress(address);

    final Instant now = Instant.now();
    user.setCreatedOn(now);
    user.setUpdatedOn(now);

    userMap.put(user.getId(), user);
  }

  private Storage() {
    /* No-op: prevents instantiation. */
  }

  public static Map<String, User> getUserMap() {
    return userMap;
  }
}
