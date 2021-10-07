package api.course.api.v1.rest.integrationtests;

import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import api.course.api.v1.models.UserT;
import api.course.api.v1.utilities.UserRestClient;
import api.course.api.v1.utilities.UserGenerator;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

@QuarkusTest
class UpdateUserTests extends BaseTest {

  UserRestClient userRestClient = new UserRestClient();

  @Test
  void updateUser_GivenExistingUserId_UserUpdated() {
    // Arrange - create a user
    UserT oldUser = UserGenerator.generateDefaultUser();
    Response postResponse = userRestClient.createUser(oldUser);
    assumeTrue(CREATED.getStatusCode() == postResponse.statusCode(), "User creation failed.");
    UserT createdUser = postResponse.as(new TypeRef<>() {});
    String existingUserId = createdUser.getId();
    UserT newUser = UserGenerator.generateRequiredFields();

    // Act - updates the user
    Response updateResponse = userRestClient.updateUser(existingUserId, newUser);

    // Assert - verifies the user was properly updated
    assertThat(updateResponse.getStatusCode(), is(OK.getStatusCode()));
    UserT putUser = updateResponse.as(new TypeRef<>() {});
    assertAll(
        "Validate PUT Response Body",
        () -> assertThat(putUser, equalTo(newUser)),
        () -> assertThat(putUser.getCreatedOn(), equalTo(createdUser.getCreatedOn())),
        () -> assertThat(putUser.getCreatedOn(), not(equalTo(putUser.getUpdatedOn()))));

    Response getResponse = userRestClient.getUser(existingUserId);
    UserT getUser = getResponse.as(new TypeRef<>() {});
    assertAll(
        "Validate GET Response Body",
        () -> assertThat(getUser, equalTo(newUser)),
        () -> assertThat(getUser.getCreatedOn(), equalTo(createdUser.getCreatedOn())),
        () -> assertThat(getUser.getUpdatedOn(), equalTo(putUser.getUpdatedOn())));
  }

  // TODO add coverage - write more tests (functional, negative, integration, etc.)

}
