package api.course.api.v1.rest.componenttests;

import static javax.ws.rs.core.Response.Status.CREATED;

import api.course.api.v1.models.Address;
import api.course.api.v1.models.User;
import api.course.utilities.UserGenerator;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

// component tests test component
@QuarkusTest
public class UserResourceComponentTests {

  @Test // test method naming convention: functionalityUnderTest_Given_Result
  void getUser_GivenValidRequest_Returns200StatusCode() {
    // GIVEN step - to arrange a test state
    // WHEN step - action that is under test
    // THEN step - assertion, validation, verification
    // whole test is in one expression - bad practice, harder to debug
    RestAssured.given()
        .contentType(ContentType.JSON)
        .accept(ContentType.JSON)
        .when()
        .pathParams("id", "aba9e794-620f-4338-ad23-eb1d7e0ac2d6")
        .get("/asan/api/v1/users/{id}")
        .then()
        .statusCode(200); // status code as magic integer
  }

  @Test
  void createUser_GivenValidRequest_ReturnsCreateStatusCode() {
    // Arrange
    String userJsonString =
        "{\n"
            + "    \"status\": \"BLOCKED\",\n"
            + "    \"firstName\": \"Alicia\",\n"
            + "    \"lastName\": \"Huel\",\n"
            + "    \"email\": \"Alicia.Huel@gmail.com\",\n"
            + "    \"address\": {\n"
            + "        \"streetAddress\": \"3283 Hank Knoll\",\n"
            + "        \"city\": \"Watsicafort\",\n"
            + "        \"country\": \"Bulgaria\",\n"
            + "        \"zipCode\": \"19163\"\n"
            + "    }\n"
            + "}";

    // Act
    Response response =
        RestAssured.given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body(userJsonString)
            .when()
            .post("/asan/api/v1/users");

    // Assert
    response.then().statusCode(CREATED.getStatusCode()); // verbose status code
  }
}
