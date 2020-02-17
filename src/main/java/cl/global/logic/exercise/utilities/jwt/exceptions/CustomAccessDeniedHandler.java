package cl.global.logic.exercise.utilities.jwt.exceptions;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

import static cl.global.logic.exercise.utilities.AppConstant.ACCESS_DENIED_EXCEPTION_MESSAGE;
import static cl.global.logic.exercise.utilities.formats.HandlerMessage.formatMessageException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler, Serializable {

  private static final long serialVersionUID = -6859297070553441327L;

  public CustomAccessDeniedHandler() {}

  @Override
  public void handle(
      HttpServletRequest request, HttpServletResponse response, AccessDeniedException arg2)
      throws IOException {
    formatMessageException(
        response, HttpServletResponse.SC_UNAUTHORIZED, ACCESS_DENIED_EXCEPTION_MESSAGE);
  }
}
