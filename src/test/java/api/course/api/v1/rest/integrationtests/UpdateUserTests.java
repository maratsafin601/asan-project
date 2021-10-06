package api.course.api.v1.rest.integrationtests;

import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.OK;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import api.course.api.v1.models.User;
import api.course.api.v1.utilities.UserRestClient;
import api.course.utilities.UserGenerator;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class UpdateUserTests extends BaseTest {

  UserRestClient userRestClient = new UserRestClient();

  @Test
  void updateUser_GivenExistingUserId_UserUpdated() {
    // Arrange - create a user
    User oldUser = UserGenerator.generateDefaultUser();
    Response postResponse = userRestClient.createUser(oldUser);
    assumeTrue(CREATED.getStatusCode() == postResponse.statusCode(), "User creation failed.");
    User createdUser = postResponse.as(new TypeRef<>() {});
    String existingUserId = createdUser.getId();
    User newUser = UserGenerator.generateRequiredFields();

    // Act - updates the user
    Response updateResponse = userRestClient.updateUser(existingUserId, newUser);

    // Assert - verifies the user was properly updated
    assertThat(updateResponse.getStatusCode(), is(OK.getStatusCode()));
    User putUser = updateResponse.as(new TypeRef<>() {});
    assertAll(
        "Validate PUT Response Body",
        () -> assertThat(putUser, equalTo(newUser)),
        () -> assertThat(putUser.getCreatedOn(), equalTo(createdUser.getCreatedOn())),
        () -> assertThat(putUser.getCreatedOn(), not(equalTo(putUser.getUpdatedOn()))));

    Response getResponse = userRestClient.getUser(existingUserId);
    User getUser = getResponse.as(new TypeRef<>() {});
    assertAll(
        "Validate GET Response Body",
        () -> assertThat(getUser, equalTo(newUser)),
        () -> assertThat(getUser.getCreatedOn(), equalTo(createdUser.getCreatedOn())),
        () -> assertThat(getUser.getUpdatedOn(), equalTo(putUser.getUpdatedOn())));
  }
}
