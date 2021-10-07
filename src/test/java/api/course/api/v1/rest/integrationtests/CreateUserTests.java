package api.course.api.v1.rest.integrationtests;

import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.OK;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.blankString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import api.course.api.v1.models.Event;
import api.course.api.v1.models.UserT;
import api.course.api.v1.utilities.AuditRestClient;
import api.course.api.v1.utilities.UserRestClient;
import api.course.api.v1.utilities.UserGenerator;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;

@QuarkusTest
class CreateUserTests extends BaseTest {

  UserRestClient userRestClient = new UserRestClient();
  AuditRestClient auditRestClient = new AuditRestClient();

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

  @Test // Smoke (Happy-Path) Functional Test - validating critical functionality of API endpoint
  void createUser_GivenValidRequest_UserCreated() {
    // Arrange - creates test state
    UserT expectedUser = UserGenerator.generateDefaultUser();

    // Act - functionality under test performs action
    Response postResponse = userRestClient.createUser(expectedUser);

    // Assert - validates results
    // 1. Validate POST response status code
    assertEquals(CREATED.getStatusCode(), postResponse.statusCode()); // JUnit (expected, actual)
    assertThat(
        postResponse.statusCode(), is(CREATED.getStatusCode())); // Hamcrest (actual, expected)

    // 2. Validate POST response body
    // deserialization (conversion) from JSON object to Java POJO object
    UserT postUser = postResponse.getBody().as(UserT.class);
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
    UserT getUser = getResponse.as(new TypeRef<>() {}); // generic deserialization
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

  @Test // Smoke (Happy-Path) Integration Test - validating integration between services
  void createUser_GivenValidRequest_EventCreated() {
    UserT user = UserGenerator.generateDefaultUser();
    Response userResponse = userRestClient.createUser(user);
    String id = userResponse.as(new TypeRef<UserT>() {}).getId();

    // Act
    Response auditResponse = auditRestClient.listEvents();

    // Assert
    assertThat(auditResponse.statusCode(), is(OK.getStatusCode()));
    List<Event> eventList = auditResponse.as(new TypeRef<>() {});

    int count = 0;
    for (Event e : eventList) {
      if (e.getMessage().contains(id)) {
        count++;
        if(count >= 2) {
          Assertions.fail("more or equal to 2");
        }
      }
    }

    assertThat(count, equalTo(1));
  }

  @ParameterizedTest // Regression Suite - positive and negative test cases
  @NullAndEmptySource // null, ""
  @ValueSource(strings = {"   ", "1", "X Æ A-12", "中华人民共和国国务院总理", "☺", "aaaaaaaaaaa"})
  void createUser_GivenBadFirstName_ReturnsBadRequest(String badFirstName) {
    // Arrange
    UserT userWithBadFirstName = UserGenerator.generateDefaultUser();
    userWithBadFirstName.setFirstName(badFirstName);

    // Act
    Response response = userRestClient.createUser(userWithBadFirstName);

    // Assert
    assertThat(response.statusCode(), is(BAD_REQUEST.getStatusCode()));
  }

  // TODO add coverage - write more tests (functional, negative, integration, etc.)

}
