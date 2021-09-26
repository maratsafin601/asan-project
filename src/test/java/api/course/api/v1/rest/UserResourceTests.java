package api.course.api.v1.rest;

import api.course.utilities.UserGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class UserResourceTests {


    @Test
    public void get_GivenValidRequest_Returns200StatusCode() {

        given()
                .when()
                .get("/asan/hello")
                .then()
                .statusCode(200)
                .body(is("Hello!"));
    }

    @Test
    public void post_GivenValidRequest_Returns201StatusCode() {

        String name = "John";

        given()
                .body(name)
                .when()
                .post("/asan/hello")
                .then()
                .statusCode(200)
                .body(is("Hello " + name + "!"));
    }

}