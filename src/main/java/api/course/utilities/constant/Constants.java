package api.course.utilities.constant;

public class Constants {

  private Constants() {
    /* No-op: prevents instantiation */
  }

  public static class UriFragment {
    public static final String API_V1 = "/api/v1";
    public static final String ID = "/{" + PathParam.ID + "}";
  }

  public static class PathParam {
    public static final String ID = "id";
  }

  public static class QueryParam {
    public static final String SORT = "sort";
    public static final String ASC = "asc";
    public static final String DESC = "desc";
  }

  public static class Endpoint {
    public static final String USERS = "/users";
    public static final String EVENTS = "/events";
  }

  public static class Error {
    public static final String MUST_NOT_BE_NULL = "must not be null";
    public static final String MUST_NOT_BE_BLANK = "must not be blank";
    public static final String MUST_NOT_BE_EMPTY = "must not be empty";
    public static final String INVALID_ID = "invalid id";
  }

  public static class EventMessage {
    public static final String USER_CREATED = "user {id=%s} has been successfully created";
    public static final String USER_RETRIEVED = "user {id=%s} has been successfully retrieved";
    public static final String USER_UPDATED = "user {id=%s} has been successfully updated";
    public static final String USER_REMOVED = "user {id=%s} has been successfully REMOVED";
  }
}
