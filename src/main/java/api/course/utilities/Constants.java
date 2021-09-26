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
}
