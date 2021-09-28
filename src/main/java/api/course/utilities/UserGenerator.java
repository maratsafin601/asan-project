package api.course.utilities;

import api.course.api.v1.models.Address;
import api.course.api.v1.models.Status;
import api.course.api.v1.models.User;

import java.util.Random;
import java.util.UUID;

import com.github.javafaker.Faker;

public class UserGenerator {

  private static Faker faker = new Faker();

  private UserGenerator() {
    /* No-op to prevent instantiation. */
  }

  public static User generate() {
    Address address = new Address();
    address.setStreetAddress(generateStreetAddress());
    address.setCity(generateCity());
    address.setZipCode(generateZipCode());
    address.setCountry(generateCountry());

    User user = new User();
    user.setStatus(Status.values()[new Random().nextInt(Status.values().length)]);
    user.setId(UUID.randomUUID().toString());
    user.setFirstName(generateFirstName());
    user.setLastName(generateLastName());
    user.setEmail(user.getFirstName() + "." + user.getLastName() + "@gmail.com");
    user.setAddress(address);

    return user;
  }

  private static String generateFirstName() {
    return faker.name().firstName();
  }

  private static String generateLastName() {
    return faker.name().lastName();
  }

  private static String generateStreetAddress() {
    return faker.address().streetAddress();
  }

  private static String generateCity() {
    return faker.address().city();
  }

  private static String generateZipCode() {
    return faker.address().zipCode();
  }

  private static String generateCountry() {
    return faker.address().country();
  }
}
