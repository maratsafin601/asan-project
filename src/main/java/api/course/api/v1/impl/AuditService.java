package api.course.api.v1.impl;

import api.course.api.v1.models.Action;
import api.course.api.v1.models.Event;
import api.course.utilities.EventStorage;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class AuditService {

  private static final Logger logger = Logger.getLogger(AuditService.class.getName());

  public List<Event> listEvents() {
    logger.info("listEvents invoked");
    List<Event> eventList = EventStorage.getEventList();
    return eventList;
  }

  public Event createEvent(Action action, Event.Status status) {
    logger.info("createEvent invoked: action=" + action + ", status=" + status);
    return createEvent(action, status, "");
  }

  public Event createEvent(Action action, Event.Status status, String message) {
    logger.info(
        "createEvent invoked: action=" + action + ", status=" + status + ", message=" + message);
    Event event = new Event();
    event.setId(UUID.randomUUID().toString());
    event.setStatus(status);
    event.setMessage(String.format("action=%s, id=%s", action, message));
    event.setCreatedOn(Instant.now());
    EventStorage.addEvent(event);
    return event;
  }
}
