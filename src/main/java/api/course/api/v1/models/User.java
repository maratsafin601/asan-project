package api.course.api.v1.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class User {

  private String uuid;
  private Status status;
  private String firstName;
  private String lastName;
  private String email;
  private Address address;

  public User() {}

  public User(
      String uuid,
      Status status,
      String firstName,
      String lastName,
      String email,
      Address address) {
    this.uuid = uuid;
    this.status = status;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.address = address;
  }

  @NotBlank
  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  @NotNull
  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  @NotBlank
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @NotBlank
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @NotBlank
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof User)) return false;
    User that = (User) o;
    return new EqualsBuilder()
        .append(getUuid(), that.getUuid())
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
        .append(getUuid())
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
        .append("uuid", getUuid())
        .append("status", getStatus())
        .append("firstName", getFirstName())
        .append("lastName", getLastName())
        .append("email", getEmail())
        .append("address", getAddress())
        .toString();
  }
}
