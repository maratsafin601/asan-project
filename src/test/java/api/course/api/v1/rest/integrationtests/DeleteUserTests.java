package api.course.api.v1.rest.integrationtests;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import api.course.api.v1.models.UserT;
import api.course.api.v1.utilities.UserRestClient;
import api.course.api.v1.utilities.UserGenerator;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

@QuarkusTest
class DeleteUserTests extends BaseTest {

  UserRestClient userRestClient = new UserRestClient();

  /*
  Testing DELETE endpoint
  1. Positive test cases:
      Validate response status code and response body:
      When a valid DELETE request is sent to a specified resource,
      Then response status code is 204 (NO_CONTENT).
      When a valid GET request is sent to a just deleted resource,
      Then response status code is 404 (NOT_FOUND).
  2. Negative test cases:
      When a valid DELETE request is sent to non-existing resource, then response status code is 404 (NOT_FOUND).
      When a valid DELETE request is sent with a bad access token , then response status code is 401 (UNAUTHORIZED).
      When a valid DELETE request is sent to a forbidden resource, then response status code is 403 (FORBIDDEN).
     */

  @Test
  void deleteUser_GivenExistingUserId_UserDeleted() {
    // Arrange - creates a user
    UserT user = UserGenerator.generateDefaultUser();
    Response postResponse = userRestClient.createUser(user);
    String id = postResponse.as(new TypeRef<UserT>() {}).getId();

    // Act - removes the user
    Response deleteResponse = userRestClient.deleteUser(id);

    // Assert - verifies the user was removed
    // 1. Validate DELETE response status code
    assertThat(deleteResponse.getStatusCode(), is(NO_CONTENT.getStatusCode()));

    // 2. Validate GET response status code
    Response getResponse = userRestClient.getUser(id);
    assertThat(getResponse.getStatusCode(), is(NOT_FOUND.getStatusCode()));
  }

  // TODO add coverage - write more tests (functional, negative, integration, etc.)

}
