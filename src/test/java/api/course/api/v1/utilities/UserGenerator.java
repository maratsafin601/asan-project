package api.course.api.v1.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

public class UserGenerator {

  @Test
  void printRandomUser() throws JsonProcessingException {
    System.out.println(
        new ObjectMapper()
            .writerWithDefaultPrettyPrinter()
            .writeValueAsString(api.course.utilities.UserGenerator.generate()));
  }
}
