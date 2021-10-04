package api.course.api.v1.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;

public class UserGenerator {

  @Test
  void printRandomUser() throws JsonProcessingException {

    System.out.println(
        new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .writerWithDefaultPrettyPrinter()
            .writeValueAsString(api.course.utilities.UserGenerator.generateAllFields()));
  }
}
