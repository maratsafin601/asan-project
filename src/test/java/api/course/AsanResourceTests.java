package api.course;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class AsanResourceTests {

    @Test
    public void getHelloApiEndpoint_GivenValidRequest_Resturns200StatusCode() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("Hello User!"));
    }

}