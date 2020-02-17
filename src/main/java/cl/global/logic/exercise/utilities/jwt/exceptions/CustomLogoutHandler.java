package cl.global.logic.exercise.utilities.jwt.exceptions;

import cl.global.logic.exercise.data.GlobalLogicRepository;
import cl.global.logic.exercise.data.dtos.User;
import cl.global.logic.exercise.utilities.jwt.JwtTokenProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

import static cl.global.logic.exercise.utilities.AppConstant.NAME_TOKEN_SESSION;

public class CustomLogoutHandler implements LogoutHandler, Serializable {

  private static final long serialVersionUID = -3409865897460953769L;

  private final transient JwtTokenProvider jwtTokenProvider;
  private final transient GlobalLogicRepository globalLogicRepository;

  public CustomLogoutHandler(
      final JwtTokenProvider jwtTokenProvider, GlobalLogicRepository globalLogicRepository) {
    this.globalLogicRepository = globalLogicRepository;
    this.jwtTokenProvider = jwtTokenProvider;
  }

  @Override
  public void logout(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
    String tokenSearch = request.getHeader(NAME_TOKEN_SESSION);
    final User findEmail = globalLogicRepository.findUserByToken(tokenSearch);
    final String updateToken =
        jwtTokenProvider.revocateToken(
            jwtTokenProvider.getUsername(tokenSearch), findEmail.getRoles());
    globalLogicRepository.revocateToken(tokenSearch, updateToken);
  }
}
