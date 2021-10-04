package api.course.utilities;

import api.course.api.v1.models.Event;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class EventStorage {

  private static List<Event> eventList = new ArrayList<>();

  static {
    Event event = new Event();
    event.setId("2ce778a7-24d5-40e3-a18c-2997699a0185");
    event.setStatus(Event.Status.SUCCESS);
    event.setMessage(UserStorage.getUserList().get(0).getId());
    event.setCreatedOn(Instant.now());

    eventList.add(event);
  }

  private EventStorage() {
    /* No-op: prevents instantiation. */
  }

  public static List<Event> getEventList() {
    return eventList;
  }

  public static void addEvent(Event event) {
    getEventList().add(event);
  }
}
