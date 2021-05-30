package api.course.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Set;

public class Users {

    Set<User> users;

    public Users() {
        /* no-op */
    }

    public Users(Set<User> users) {
        this.users = users;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Users)) return false;

        Users that = (Users) o;

        return new EqualsBuilder().append(this.getUsers(), that.getUsers()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getUsers()).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append(getUsers()).toString();
    }
}
