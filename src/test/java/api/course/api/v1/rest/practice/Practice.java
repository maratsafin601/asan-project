package api.course.api.v1.rest.practice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import api.course.api.v1.models.AddressT;
import api.course.api.v1.models.UserT;
import api.course.api.v1.utilities.ConvertUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import java.time.Instant;

@QuarkusTest
class Practice {

  private static final String USER_JSON_GSON =
      "{\"id\":\"aba9e794-620f-4338-ad23-eb1d7e0ac2d6\",\"status\":\"PENDING\",\"firstName\":\"Forrest\",\"lastName\":\"Gump\",\"email\":\"Forrest.Gump@gmail.com\",\"address\":{\"streetAddress\":\"Park St\",\"city\":\"Greenbow\",\"country\":\"USA\",\"zipCode\":\"12345\"},\"createdOn\":{\"seconds\":1633641830,\"nanos\":537091000},\"updatedOn\":{\"seconds\":1633641830,\"nanos\":537091000}}";
  private static final String USER_JSON_OBJECT_MAPPER =
      "{\"id\":\"aba9e794-620f-4338-ad23-eb1d7e0ac2d6\",\"status\":\"PENDING\",\"firstName\":\"Forrest\",\"lastName\":\"Gump\",\"email\":\"Forrest.Gump@gmail.com\",\"address\":{\"streetAddress\":\"Park St\",\"city\":\"Greenbow\",\"country\":\"USA\",\"zipCode\":\"12345\"},\"createdOn\":1633641830.537091000,\"updatedOn\":1633641830.537091000}";

  private final UserT USER_POJO = new UserT();

  {
    AddressT address = new AddressT();
    address.setStreetAddress("Park St");
    address.setCity("Greenbow");
    address.setZipCode("12345");
    address.setCountry("USA");

    USER_POJO.setStatus(UserT.UserStatus.PENDING);
    USER_POJO.setId("aba9e794-620f-4338-ad23-eb1d7e0ac2d6");
    USER_POJO.setFirstName("Forrest");
    USER_POJO.setLastName("Gump");
    USER_POJO.setEmail(USER_POJO.getFirstName() + "." + USER_POJO.getLastName() + "@gmail.com");
    USER_POJO.setAddress(address);
    USER_POJO.setCreatedOn(Instant.ofEpochSecond(1633641830, 537091000));
    USER_POJO.setUpdatedOn(Instant.ofEpochSecond(1633641830, 537091000));
  }

  /** Serialization with GSON */
  @Test
  void serialization_convertPojoToJson_Gson() {
    Gson gson = new Gson();

    String actualJson = gson.toJson(USER_POJO);

    assertEquals(USER_JSON_GSON, actualJson);
  }

  /** Serialization with Jackson Object Mapper */
  @Test
  void serialization_convertPojoToJson_ObjectMapper() throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();

    String actualJson =
            objectMapper
                    .registerModule(new JavaTimeModule()) // for timestamps to support java.time.Instant
                    .writeValueAsString(USER_POJO);

    assertEquals(USER_JSON_OBJECT_MAPPER, actualJson);
  }

  /** Deserialization with GSON */
  @Test
  void deserialization_convertJsonToPojo_Gson() {
    Gson gson = new Gson();

    UserT actualPojo = gson.fromJson(USER_JSON_GSON, UserT.class);

    assertEquals(USER_POJO, actualPojo);
  }

  /** Deserialization with Jackson Object Mapper */
  @Test
  void deserialization_convertJsonToPojo_ObjectMapper() throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();

    UserT actualPojo1 =
        objectMapper
            .registerModule(new JavaTimeModule()) // for timestamps to support java.time.Instant
            .readValue(USER_JSON_OBJECT_MAPPER, UserT.class);
    UserT actualPojo2 =
        objectMapper
            .registerModule(new JavaTimeModule()) // for timestamps to support java.time.Instant
            .readValue(USER_JSON_OBJECT_MAPPER, new TypeReference<>() {});

    assertEquals(USER_POJO, actualPojo1);
    assertEquals(USER_POJO, actualPojo2);
  }

  @Test
  void serialization_examples() throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(
        new JavaTimeModule()); // for timestamps to support java.time.Instant
    objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    String oneLineJson = objectMapper.writeValueAsString(USER_POJO);
    System.out.println(oneLineJson); // will print out json in one line
    System.out.println(ConvertUtils.toJsonStringWithObjectMapper(USER_POJO)); // same

    String prettyJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(USER_POJO);
    System.out.println(prettyJson); // will print out json in pretty format
    System.out.println(ConvertUtils.toPrettyJsonStringWithObjectMapper(USER_POJO)); // same
  }
}
