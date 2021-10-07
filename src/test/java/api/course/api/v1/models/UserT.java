package api.course.api.v1.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.Instant;

public class UserT {

  private String id;
  private UserStatus status;
  private String firstName;
  private String lastName;
  private String email;
  private AddressT address;
  private Instant createdOn;
  private Instant updatedOn;

  // non-argument constructor
  public UserT() {}

  // custom constructor for required fields only
  public UserT(UserStatus status, String firstName, String lastName, String email, AddressT address) {
    this.status = status;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.address = address;
  }

  // all-argument constructor
  public UserT(
      String id,
      UserStatus status,
      String firstName,
      String lastName,
      String email,
      AddressT address,
      Instant createdOn,
      Instant updatedOn) {
    this.id = id;
    this.status = status;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.address = address;
    this.createdOn = createdOn;
    this.updatedOn = updatedOn;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public UserStatus getStatus() {
    return status;
  }

  public void setStatus(UserStatus status) {
    this.status = status;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public AddressT getAddress() {
    return address;
  }

  public void setAddress(AddressT address) {
    this.address = address;
  }

  public Instant getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(Instant createdOn) {
    this.createdOn = createdOn;
  }

  public Instant getUpdatedOn() {
    return updatedOn;
  }

  public void setUpdatedOn(Instant updatedOn) {
    this.updatedOn = updatedOn;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UserT)) return false;
    UserT that = (UserT) o;
    return new EqualsBuilder()
        .append(getStatus(), that.getStatus())
        .append(getFirstName(), that.getFirstName())
        .append(getLastName(), that.getLastName())
        .append(getEmail(), that.getEmail())
        .append(getAddress(), that.getAddress())
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(getStatus())
        .append(getFirstName())
        .append(getLastName())
        .append(getEmail())
        .append(getAddress())
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("id", getId())
        .append("status", getStatus())
        .append("firstName", getFirstName())
        .append("lastName", getLastName())
        .append("email", getEmail())
        .append("address", getAddress())
        .append("createdOn", getCreatedOn())
        .append("updatedOn", getUpdatedOn())
        .toString();
  }

  private String toJsonString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("id", getId())
        .append("status", getStatus())
        .append("firstName", getFirstName())
        .append("lastName", getLastName())
        .append("email", getEmail())
        .append("address", getAddress()) // FIXME recursive to json string style
        .append("createdOn", getCreatedOn())
        .append("updatedOn", getUpdatedOn())
        .toString();
  }

  public enum UserStatus {
    ACTIVE,
    PENDING,
    VERIFIED,
    BLOCKED,
    RETIRED
  }
}
