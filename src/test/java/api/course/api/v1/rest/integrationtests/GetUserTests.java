package api.course.api.v1.rest.integrationtests;

import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.OK;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import api.course.api.v1.models.User;
import api.course.api.v1.utilities.UserRestClient;
import api.course.utilities.UserGenerator;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.Set;

@QuarkusTest
public class GetUserTests extends BaseTest {

  private static final Set<String> EXPECTED_FIELDS =
      Set.of("id", "firstName", "lastName", "status", "email", "address", "createdOn", "updatedOn");
  UserRestClient userRestClient = new UserRestClient();

  /*
  Testing GET endpoint
  1. Positive test cases:
      Validate response status code and response body:
      When a valid GET request is sent to a specified resource,
      Then response status code is 200 (OK),
      And response body has expected data.
      Verify that returned data is in the right format, and required fields and values are correct and not missing.
  2. Negative test cases:
      When a valid GET request is sent to a non-existing resource, then response status code is 404 (NOT_FOUND).
      When a valid GET request is sent with a bad access token, then response status code is 401 (UNAUTHORIZED).
      When a valid GET request is sent to a forbidden resource, then response status code is 403 (FORBIDDEN).
     */

  @Test
  void getUser_GivenValidRequest_UserRetrieved() {
    // Arrange - creates a user
    User user = UserGenerator.generateDefaultUser();
    Response postResponse = userRestClient.createUser(user);
    assumeTrue(
        CREATED.getStatusCode() == postResponse.statusCode(),
        "POST request failed.");
    String id = postResponse.as(new TypeRef<User>() {}).getId();

    // Act - retrieves the user
    Response getResponse = userRestClient.getUser(id);

    // Assert - validates response status code and response body
    Assertions.assertEquals(OK.getStatusCode(), getResponse.statusCode()); // hard assertion

    // Verify that actual response body schema has expected fields
    Set<String> actualFields = getResponse.as(new TypeRef<Map<String, Object>>() {}).keySet();
    assertThat(actualFields, equalTo(EXPECTED_FIELDS));
  }
}
