package api.course.api.v1.utilities;

import api.course.api.v1.models.UserT;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserRestClient {

  /*
  POST method is used to SEND the data to a server to CREATE a new resource.
   */
  public Response createUser(UserT user) {
    return RestAssured.given()
        .contentType(ContentType.JSON)
        .accept(ContentType.JSON)
        .when()
        .body(user)
        .post("/users");
  }

  /*
  GET method is used to RETRIEVE the data from a server at the specified resource.
  */
  public Response getUser(String id) {
    return RestAssured.given()
        .contentType(ContentType.JSON)
        .accept(ContentType.JSON)
        .when()
        .pathParams("id", id)
        .get("/users/{id}");
  }

  public Response listUsers() {
    return RestAssured.given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .when()
            .get("/users");
  }

  /*
  PUT method is used to SEND the data to a server to UPDATE the specified resource.
   */
  public Response updateUser(String id, UserT user) {
    return RestAssured.given()
        .contentType(ContentType.JSON)
        .accept(ContentType.JSON)
        .when()
        .body(user)
        .pathParams("id", id)
        .put("/users/{id}");
  }

  /*
  DELETE method is used to REMOVE the specified resource from a server.
   */
  public Response deleteUser(String id) {
    return RestAssured.given()
        .contentType(ContentType.JSON)
        .accept(ContentType.JSON)
        .when()
        .pathParams("id", id)
        .delete("/users/{id}");
  }
}
