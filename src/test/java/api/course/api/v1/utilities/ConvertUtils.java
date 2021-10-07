package api.course.api.v1.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.List;

public class ConvertUtils {

  // region serialization helpers

  /** Converts Java object to JSON string. */
  public static String toJsonStringWithObjectMapper(Object pojo) {
    try {
      return new ObjectMapper()
          .registerModule(new JavaTimeModule())
          .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
          .writeValueAsString(pojo);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return "";
  }

  /** Converts Java object to pretty JSON string. */
  public static String toPrettyJsonStringWithObjectMapper(Object pojo) {
    try {
      return new ObjectMapper()
          .registerModule(new JavaTimeModule())
          .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
          .writerWithDefaultPrettyPrinter()
          .writeValueAsString(pojo);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return "";
  }

  // endregion serialization helpers

  // region deserialization helpers

  /** Converts JSON string to Java object. */
  public static <T> T toPojo(String json, Class<T> clazz) {
    try {
      return new ObjectMapper().readValue(json, clazz);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return null;
  }

  /** Converts JSON string to List of Java objects. */
  public static <T> List<T> toPojoList(String json, Class<T[]> clazz) {
    try {
      return List.of(new ObjectMapper().readValue(json, clazz));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return null;
  }

  // endregion deserialization helpers
}
