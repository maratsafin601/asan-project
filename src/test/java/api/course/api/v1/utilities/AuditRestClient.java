package api.course.api.v1.utilities;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AuditRestClient {

  public Response listEvents() {
    return RestAssured.given()
        .contentType(ContentType.JSON)
        .accept(ContentType.JSON)
        .when()
        .get("/events");
  }
}
