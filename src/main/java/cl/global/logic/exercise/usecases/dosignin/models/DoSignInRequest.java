package cl.global.logic.exercise.usecases.dosignin.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

import static cl.global.logic.exercise.utilities.AppConstant.CREDENTIALS_EMPTY_ERROR_MESSAGE;
import static cl.global.logic.exercise.utilities.AppConstant.CREDENTIALS_FORMAT_ERROR_MESSAGE;
import static cl.global.logic.exercise.utilities.AppConstant.CREDENTIALS_NULL_ERROR_MESSAGE;
import static cl.global.logic.exercise.utilities.AppConstant.EMAIL_EMPTY_ERROR_MESSAGE;
import static cl.global.logic.exercise.utilities.AppConstant.EMAIL_FORMAT_ERROR_MESSAGE;
import static cl.global.logic.exercise.utilities.AppConstant.EMAIL_NULL_ERROR_MESSAGE;
import static cl.global.logic.exercise.utilities.AppConstant.PATTERN_CREDENTIALS;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DoSignInRequest implements Serializable {

  private static final long serialVersionUID = -5781422611283118332L;

  @NotNull(message = EMAIL_NULL_ERROR_MESSAGE)
  @NotBlank(message = EMAIL_EMPTY_ERROR_MESSAGE)
  @Email(message = EMAIL_FORMAT_ERROR_MESSAGE)
  private String email;

  @NotNull(message = CREDENTIALS_NULL_ERROR_MESSAGE)
  @NotBlank(message = CREDENTIALS_EMPTY_ERROR_MESSAGE)
  @Pattern(regexp = PATTERN_CREDENTIALS, message = CREDENTIALS_FORMAT_ERROR_MESSAGE)
  private String password;

  public DoSignInRequest() {}

  public DoSignInRequest(final String email, final String password) {
    this.email = email;
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  @Override
  public String toString() {
    return "DoSignInRequest{"
        + "email='"
        + getEmail()
        + '\''
        + ", password='"
        + getPassword()
        + '\''
        + '}';
  }
}
