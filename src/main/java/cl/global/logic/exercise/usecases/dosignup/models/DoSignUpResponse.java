package cl.global.logic.exercise.usecases.dosignup.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.io.Serializable;
import java.util.Objects;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DoSignUpResponse implements Serializable {

  private static final long serialVersionUID = -310523115907002661L;
  private String created;
  private boolean active;

  public DoSignUpResponse() {}

  public DoSignUpResponse(final String created, final boolean active) {
    this.created = created;
    this.active = active;
  }

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public String getCreated() {
    return created;
  }

  public boolean isActive() {
    return active;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final DoSignUpResponse that = (DoSignUpResponse) o;
    return isActive() == that.isActive() && Objects.equals(getCreated(), that.getCreated());
  }

  @Override
  public int hashCode() {
    return Objects.hash(created, active);
  }

  @Override
  public String toString() {
    return "DoSignUpResponse{"
        + "created='"
        + getCreated()
        + '\''
        + ", isActive="
        + isActive()
        + '}';
  }
}
