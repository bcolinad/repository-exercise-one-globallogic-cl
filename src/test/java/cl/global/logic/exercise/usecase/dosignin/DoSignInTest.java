package cl.global.logic.exercise.usecase.dosignin;

import cl.global.logic.exercise.GlobalLogicController;
import cl.global.logic.exercise.GlobalLogicServiceFacade;
import cl.global.logic.exercise.data.GlobalLogicRepository;
import cl.global.logic.exercise.data.dtos.User;
import cl.global.logic.exercise.usecases.dosignin.DoSignInUseCase;
import cl.global.logic.exercise.usecases.dosignin.models.DoSignInResponse;
import cl.global.logic.exercise.usecases.finduserbyemail.FindUserByEmailUseCase;
import cl.global.logic.exercise.usecases.getusers.GetUsersUseCase;
import cl.global.logic.exercise.utilities.exceptions.ExceptionHandlerResponse;
import cl.global.logic.exercise.utilities.jwt.JwtTokenProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static cl.global.logic.exercise.usecase.UserStubs.getUser;
import static cl.global.logic.exercise.usecase.dosignin.DoSignInStubs.getDoSignInRequest;
import static cl.global.logic.exercise.usecase.dosignin.DoSignInStubs.getDoSignInResponse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class DoSignInTest {

  @Mock private GetUsersUseCase getUsersUseCase;
  @Mock private FindUserByEmailUseCase findUserByEmailUseCase;
  @Mock private GlobalLogicRepository globalLogicRepository;
  @MockBean AuthenticationManager authenticationManager;
  @MockBean JwtTokenProvider jwtTokenProvider;

  private GlobalLogicController globalLogicController;

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(16);
  }

  @Before
  public void setup() throws IOException {

    final DoSignInUseCase doSignInUseCase =
        new DoSignInUseCase(
            authenticationManager, jwtTokenProvider, globalLogicRepository, passwordEncoder());

    final GlobalLogicServiceFacade globalLogicServiceFacade =
        new GlobalLogicServiceFacade(doSignInUseCase, getUsersUseCase, findUserByEmailUseCase);

    globalLogicController = new GlobalLogicController(globalLogicServiceFacade);

    final User user =
        new User(
            getUser().getId(),
            getUser().getName(),
            getUser().getEmail(),
            getUser().getPassword(),
            getUser().getPhones(),
            getUser().getCreated(),
            getUser().getModified(),
            getUser().getLastLogin(),
            getUser().getToken(),
            getUser().getRoles(),
            getUser().isActive());
    when(globalLogicRepository.save(any(User.class))).thenReturn(user);
    when(globalLogicRepository.findByEmail(any(String.class))).thenReturn(user);
    when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
        .thenReturn(
            new UsernamePasswordAuthenticationToken(
                getDoSignInRequest().getEmail(), getDoSignInRequest().getPassword()));
    when(globalLogicRepository.updateUser(
            any(String.class), any(String.class), any(String.class), any(String.class)))
        .thenReturn(1);
    when(jwtTokenProvider.createToken(any(), any())).thenReturn(getDoSignInResponse().getToken());
  }

  @Test
  public void itShouldDoSignInWhenApiSuccess() throws IOException {
    assertNotNull(getDoSignInRequest());

    final DoSignInResponse doSignInResponse = globalLogicController.doSignIn(getDoSignInRequest());

    assertNotNull(getDoSignInResponse());
    assertNotNull(doSignInResponse);
    assertEquals(
        doSignInResponse,
        new DoSignInResponse(
            getDoSignInResponse().getId(),
            getDoSignInResponse().getCreated(),
            getDoSignInResponse().getModified(),
            getDoSignInResponse().getLastLogin(),
            getDoSignInResponse().getToken(),
            getDoSignInResponse().isActive()));
  }

  @Test
  public void itShouldDoSignInWhenApiFailure() throws IOException {
    assertNotNull(getDoSignInRequest());
    try {
      globalLogicController.doSignIn(null);
    } catch (NullPointerException ex) {
      assertEquals(new ExceptionHandlerResponse<>(null).getMessage(), ex.getMessage());
    }
  }
}
