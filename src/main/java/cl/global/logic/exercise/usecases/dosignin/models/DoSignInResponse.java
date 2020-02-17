package cl.global.logic.exercise.usecases.dosignin.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.io.Serializable;
import java.util.Objects;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DoSignInResponse implements Serializable {

  private static final long serialVersionUID = -4258338544898137945L;
  private String token;
  private String lastLogin;

  public DoSignInResponse() {}

  public DoSignInResponse(final String token, final String lastLogin) {
    this.token = token;
    this.lastLogin = lastLogin;
  }

  public String getLastLogin() {
    return lastLogin;
  }

  public String getToken() {
    return token;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final DoSignInResponse that = (DoSignInResponse) o;
    return Objects.equals(getToken(), that.getToken())
        && Objects.equals(getLastLogin(), that.getLastLogin());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getLastLogin(), getLastLogin());
  }

  @Override
  public String toString() {
    return "DoSignInResponse{"
        + "token='"
        + getToken()
        + '\''
        + ", lastLogin='"
        + getToken()
        + '\''
        + '}';
  }
}
