package api.course.api.v1.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.ws.rs.core.Response;

public class Error {

    private Response.Status status;
    private String message;

    public Error() {
    }

    public Error(Response.Status status, String message) {
        this.status = status;
        this.message = message;
    }

    public Response.Status getStatus() {
        return status;
    }

    public void setStatus(Response.Status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Error)) return false;
        Error that = (Error) o;
        return new EqualsBuilder().append(getStatus(), that.getStatus()).append(getMessage(), that.getMessage()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getStatus()).append(getMessage()).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("status", getStatus())
                .append("message", getMessage())
                .toString();
    }
}
