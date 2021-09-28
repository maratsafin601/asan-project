package api.course.api.v1.rest;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import javax.ws.rs.core.Response;
import static io.restassured.RestAssured.given;

@QuarkusTest
public class UserResourceTests {

    @Test
    public void get_GivenValidUser_ReturnsOkStatusCode() {
    given()
        .when()
        .get("/asan/api/v1/users")
        .then()
        .statusCode(Response.Status.OK.getStatusCode());
    }
}