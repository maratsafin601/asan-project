package api.course.api.v1.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.Instant;

public class Event {

  private String id;
  private Status status;
  private String message;
  private Instant createdOn;

  public Event() {}

  public Event(String id, Status status, String message, Instant createdOn) {
    this.id = id;
    this.status = status;
    this.message = message;
    this.createdOn = createdOn;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Instant getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(Instant createdOn) {
    this.createdOn = createdOn;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Event)) return false;
    Event that = (Event) o;
    return new EqualsBuilder()
        .append(getId(), that.getId())
        .append(getStatus(), that.getStatus())
        .append(getMessage(), that.getMessage())
        .append(getCreatedOn(), that.getCreatedOn())
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(getId())
        .append(getStatus())
        .append(getMessage())
        .append(getCreatedOn())
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
            .append("id", getId())
            .append("status", getStatus())
            .append("message", getMessage())
            .append("createdOn", getCreatedOn())
            .toString();
  }

  public enum Status {
    SUCCESS,
    FAILURE,
    ALERT
  }
}
