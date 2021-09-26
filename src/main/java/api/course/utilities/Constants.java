package api.course.utilities;

public class Constants {

  public static class UriFragment {
    public static final String API_V1 = "/api/v1";
    public static final String ID = "/{" + PathParam.ID + "}";
  }

  public static class PathParam {
    public static final String ID = "id";
  }

  public static class Endpoint {
    public static final String USERS = "/users";
  }

  public static class Error {
    public static final String MUST_NOT_BE_NULL = "must not be null";
    public static final String MUST_NOT_BE_BLANK = "must not be blank";
    public static final String MUST_NOT_BE_EMPTY = "must not be empty";
    public static final String INVALID_ID = "invalid id";
  }
}
