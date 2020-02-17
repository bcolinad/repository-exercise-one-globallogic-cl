package cl.global.logic.exercise.usecases.dosignin;

import cl.global.logic.exercise.data.GlobalLogicRepository;
import cl.global.logic.exercise.data.dtos.User;
import cl.global.logic.exercise.usecases.dosignin.models.DoSignInRequest;
import cl.global.logic.exercise.usecases.dosignin.models.DoSignInResponse;
import cl.global.logic.exercise.utilities.jwt.JwtTokenProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Objects;

import static cl.global.logic.exercise.utilities.AppConstant.USER_DOES_NOT_EXIST_EXCEPTION_MESSAGE;
import static cl.global.logic.exercise.utilities.formats.Date.dateNow;

@Service
public class DoSignInUseCase {

  // region fields
  private final Logger logsDoSignUpUseCase;
  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider jwtTokenProvider;
  private final GlobalLogicRepository globalLogicRepository;
  private final PasswordEncoder passwordEncoder;
  // endregion

  @Autowired
  public DoSignInUseCase(
      final AuthenticationManager authenticationManager,
      final JwtTokenProvider jwtTokenProvider,
      final GlobalLogicRepository globalLogicRepository,
      final PasswordEncoder passwordEncoder) {
    this.logsDoSignUpUseCase = LogManager.getLogger(DoSignInUseCase.class);
    this.authenticationManager = authenticationManager;
    this.jwtTokenProvider = jwtTokenProvider;
    this.globalLogicRepository = globalLogicRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public DoSignInResponse doSignIn(final DoSignInRequest doSignInRequest) {
    // Do log class
    logsDoSignUpUseCase.info(
        "Here I Am: Class:DoSignInUseCase, Method: doSignIn, Message: {}",
        new DoSignInRequest(
            doSignInRequest.getEmail(), passwordEncoder.encode(doSignInRequest.getPassword())));
    // Do find email user
    final User findEmail = globalLogicRepository.findByEmail(doSignInRequest.getEmail());
    // Do log find email user
    logsDoSignUpUseCase.info("Here I Am: Class:DoSignInUseCase, Method: doSignIn, Action: getUser");
    // Do exception user doesn't exist
    Objects.requireNonNull(findEmail, USER_DOES_NOT_EXIST_EXCEPTION_MESSAGE);
    // Do create variable username
    final String username = doSignInRequest.getEmail();
    // Do authenticate user
    final Authentication doAuthentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, doSignInRequest.getPassword()));
    // Do log authenticate user
    logsDoSignUpUseCase.info(
        "Here I Am: Class:DoSignInUseCase, Method: doSignIn, Action: user authenticate, Message: {}",
        doAuthentication.getAuthorities());
    // Do create token user
    final String token = jwtTokenProvider.createToken(username, findEmail.getRoles());
    // Do log create token user
    logsDoSignUpUseCase.info(
        "Here I Am: Class:DoSignInUseCase, Method: doSignIn, Action: create token user");
    // Do create dateNow
    final String dateNow = dateNow();
    // Do update lastLogin data to user
    final int updateUser = globalLogicRepository.updateUser(username, token, dateNow, dateNow);
    // Do log update user
    logsDoSignUpUseCase.info(
        "Here I Am: Class:DoSignInUseCase, Method: doSignIn, Action: update user, Message: {}",
        updateUser);
    // Do create response sign in user
    final DoSignInResponse doSignInResponse = new DoSignInResponse(token, dateNow);
    // Do log response sign in user
    logsDoSignUpUseCase.info(
        "Here I Am: Class:DoSignInUseCase, Method: doSignIn, Action: create response, Message: {}",
        doSignInResponse);
    // create token persistence
    // return response
    return doSignInResponse;
  }
}
