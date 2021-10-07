package api.course.api.v1.models;

import lombok.Data;
import org.junit.jupiter.api.Test;

import java.time.Instant;

@Data // Lombok Project
class UserLombokExample {

  private String id;
  private Status status;
  private String firstName;
  private String lastName;
  private String email;
  private AddressT address;
  private Instant createdOn;
  private Instant updatedOn;
  private Instant updatedOn1;

  public enum Status {
    ACTIVE,
    PENDING,
    VERIFIED,
    BLOCKED,
    RETIRED
  }

  @Data
  class AddressT {

    private String streetAddress;
    private String city;
    private String country;
    private String zipCode;
  }

  @Test
  void lombokAutomaticallyProvidesConstructorGettersSettersToString() {
    UserLombokExample user = new UserLombokExample();
    AddressT address = new AddressT();
    address.setStreetAddress("Default Street");
    address.setCity("Default City");
    address.setCountry("Default Country");
    address.setZipCode("00000");

    user.setStatus(UserLombokExample.Status.ACTIVE);
    user.setFirstName("Default");
    user.setLastName("Default");
    user.setEmail("default@default.com");
    user.setAddress(address);

    System.out.println("userLombok = " + user);
    System.out.println("id = " + user.getId());
    System.out.println("address.city = " + user.getAddress().getCity());
  }
}
