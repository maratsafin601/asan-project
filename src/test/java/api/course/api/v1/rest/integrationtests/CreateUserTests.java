package api.course.api.v1.rest.integrationtests;

import static javax.ws.rs.core.Response.Status.CREATED;

import api.course.api.v1.models.Address;
import api.course.api.v1.models.User;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.eclipse.microprofile.config.ConfigProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Application;

@QuarkusTest
public class CreateUserTests {

  private static final String apiHost =
      ConfigProvider.getConfig().getValue("asan.api.host", String.class);
  private static final Integer apiPort =
      ConfigProvider.getConfig().getValue("asan.api.port", Integer.class);

  @Test
  void createUser_ValidRequest_CreatedStatusCode() {
    // Arrange
    RestAssured.baseURI = "http://" + apiHost + "/asan/api/v1";
    RestAssured.port = apiPort;

    Address address = new Address();
    address.setStreetAddress("201 Zoe Valley");
    address.setCity("East Virginia");
    address.setCountry("Dominica");
    address.setZipCode("54120");

    User user = new User();
    user.setStatus(User.Status.ACTIVE);
    user.setFirstName("John");
    user.setLastName("Doe");
    user.setEmail("john.doe@gmail.com");
    user.setAddress(address);

    // Act
    Response response =

        RestAssured.given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .when()
            .body(user) // serialization - convert Java object to JSON object
            .post("/users");

    // Assert
    // Assertions.assertEquals(201, response.statusCode());
    // OR
    Assertions.assertEquals(CREATED.getStatusCode(), response.statusCode());
  }
}
