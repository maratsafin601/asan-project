package api.course.api.v1.rest.integrationtests;

import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.blankString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import api.course.api.v1.models.User;
import api.course.api.v1.utilities.UserRestClient;
import api.course.utilities.UserGenerator;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CreateUserTests extends BaseTest {

  UserRestClient userRestClient = new UserRestClient();

  /*
  Testing POST endpoint
  1. Positive test cases:
      Validate response status code and response body:
      When a valid POST request is sent with a good payload,
      Then response status code is 201 (CREATED) (in some cases 200 (OK)),
      And response body has expected data.
      When a valid GET request is sent to a just created resource,
      Then response body has expected data,
      And retrieved resource is equal to posted.
      Why do I send GET request? To ensure that a newly created resource is properly saved. Also, don't forget to verify that returned data is in the right format, and required fields and values are correct and not missing.
  2. Negative test cases:
      When a valid POST request is sent with a bad payload, then response status code is 400 (BAD_REQUEST).
      When a valid POST request is sent with a bad access token , then response status code is 401 (UNAUTHORIZED).
      When a valid POST request is sent to a forbidden resource, then response status code is 403 (FORBIDDEN).
     */

  @Test // Smoke (Happy-Path) Functional Test
  void createUser_GivenValidRequest_UserCreated() {
    // Arrange - creates test state
    User expectedUser = UserGenerator.generateDefaultUser();

    // Act - functionality under test performs action
    Response postResponse = userRestClient.createUser(expectedUser);

    // Assert - validates results
    // 1. Validate POST response status code
    assertEquals(CREATED.getStatusCode(), postResponse.statusCode()); // JUnit (expected, actual)
    assertThat(
        postResponse.statusCode(), is(CREATED.getStatusCode())); // Hamcrest (actual, expected)

    // 2. Validate POST response body
    // deserialization (conversion) from JSON object to Java POJO object
    User postUser = postResponse.getBody().as(User.class);
    String userId = postUser.getId();
    assertAll( // soft assertion
        "Validate POST Response Body",
        // validate required fields
        () -> assertThat(postUser, equalTo(expectedUser)),
        // validate optional fields
        // TODO assertion for id - uuid v4 => assertThat(id, matchesPattern(regexp));
        () -> assertThat(postUser.getCreatedOn(), notNullValue()),
        () -> assertThat(postUser.getCreatedOn().toString(), not(blankString())),
        () -> assertThat(postUser.getCreatedOn(), equalTo(postUser.getUpdatedOn())));

    // 3. Validate GET response body
    Response getResponse = userRestClient.getUser(userId);
    assertThat(getResponse.statusCode(), is(OK.getStatusCode()));
    User getUser = getResponse.as(new TypeRef<>() {}); // generic deserialization
    assertAll( // soft assertion
        "Validate GET Response Body",
        // validate required fields
        () -> assertThat(getUser, equalTo(expectedUser)),
        // validate optional fields
        () -> assertThat(getUser.getId(), is(userId)),
        () -> assertThat(getUser.getCreatedOn(), notNullValue()),
        () -> assertThat(getUser.getCreatedOn().toString(), not(blankString())),
        () -> assertThat(getUser.getCreatedOn(), equalTo(postUser.getUpdatedOn())));
  }
}
