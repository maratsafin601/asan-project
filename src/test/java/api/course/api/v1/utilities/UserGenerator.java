package api.course.api.v1.utilities;

import api.course.api.v1.models.AddressT;
import api.course.api.v1.models.UserT;
import com.github.javafaker.Faker;

import java.time.Instant;
import java.util.Random;
import java.util.UUID;

public class UserGenerator {

  private static Faker faker = new Faker();

  private UserGenerator() {
    /* No-op to prevent instantiation. */
  }

  public static UserT generateDefaultUser() {
    AddressT address = new AddressT();
    address.setStreetAddress("Default Street");
    address.setCity("Default City");
    address.setCountry("Default Country");
    address.setZipCode("00000");

    UserT user = new UserT();
    user.setStatus(UserT.UserStatus.ACTIVE);
    user.setFirstName("Default");
    user.setLastName("Default");
    user.setEmail("default@default.com");
    user.setAddress(address);

    return user;
  }

  public static UserT generateRequiredFields() {
    AddressT address = new AddressT();
    address.setStreetAddress(generateStreetAddress());
    address.setCity(generateCity());
    address.setZipCode(generateZipCode());
    address.setCountry(generateCountry());

    UserT user = new UserT();
    user.setStatus(
        UserT.UserStatus.values()[new Random().nextInt(UserT.UserStatus.values().length)]);
    user.setFirstName(generateFirstName());
    user.setLastName(generateLastName());
    user.setEmail(user.getFirstName() + "." + user.getLastName() + "@gmail.com");
    user.setAddress(address);

    return user;
  }

  public static UserT generateAllFields() {
    UserT user = generateRequiredFields();
    user.setId(UUID.randomUUID().toString());
    Instant now = Instant.now();
    user.setCreatedOn(now);
    user.setUpdatedOn(now);

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
