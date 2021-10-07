package api.course.api.v1.rest.integrationtests;

import io.restassured.RestAssured;
import org.eclipse.microprofile.config.ConfigProvider;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseTest {

  private static final String apiHost =
      ConfigProvider.getConfig().getValue("asan.api.host", String.class);
  private static final Integer apiPort =
      ConfigProvider.getConfig().getValue("asan.api.port", Integer.class);

  @BeforeEach
  void beforeEach() {
    initializeRestAssured();
  }

  private void initializeRestAssured() {
    RestAssured.baseURI = "http://" + apiHost + "/asan/api/v1";
    RestAssured.port = apiPort;
  }

  // TODO add cleanup method - to delete all test objects

}
