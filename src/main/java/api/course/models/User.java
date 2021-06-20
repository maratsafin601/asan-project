package api.course.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class User {

    private String username;

    public User() {
        /* no-op */
    }

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof  User)) return false;

        User that = (User) o;

        return new EqualsBuilder().append(this.getUsername(), that.getUsername()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getUsername()).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append(getUsername()).toString();
    }
}
