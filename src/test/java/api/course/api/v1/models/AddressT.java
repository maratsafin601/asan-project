package api.course.api.v1.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class AddressT {

  private String streetAddress;
  private String city;
  private String country;
  private String zipCode;

  public AddressT() {}

  public AddressT(String streetAddress, String city, String country, String zipCode) {
    this.streetAddress = streetAddress;
    this.city = city;
    this.country = country;
    this.zipCode = zipCode;
  }

  public String getStreetAddress() {
    return streetAddress;
  }

  public void setStreetAddress(String streetAddress) {
    this.streetAddress = streetAddress;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof AddressT)) return false;
    AddressT that = (AddressT) o;
    return new EqualsBuilder()
        .append(getStreetAddress(), that.getStreetAddress())
        .append(getCity(), that.getCity())
        .append(getCountry(), that.getCountry())
        .append(getZipCode(), that.getZipCode())
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(getStreetAddress())
        .append(getCity())
        .append(getCountry())
        .append(getZipCode())
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("streetAddress", getStreetAddress())
        .append("city", getCity())
        .append("country", getCountry())
        .append("zipCode", getZipCode())
        .toString();
  }

  public String toStringValidate() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }

  public String toJsonStringWithToStringBuilder() {
//    return new ReflectionToStringBuilder(this, ToStringStyle.JSON_STYLE).toString();
            return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                    .append("streetAddress", getStreetAddress())
                    .append("city", getCity())
                    .append("country", getCountry())
                    .append("zipCode", getZipCode())
                    .toString();
  }
}
