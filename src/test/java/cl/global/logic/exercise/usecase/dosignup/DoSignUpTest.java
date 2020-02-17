package cl.global.logic.exercise.usecase.dosignup;

import cl.global.logic.exercise.GlobalLogicController;
import cl.global.logic.exercise.GlobalLogicServiceFacade;
import cl.global.logic.exercise.data.GlobalLogicRepository;
import cl.global.logic.exercise.data.dtos.User;
import cl.global.logic.exercise.usecases.dosignin.DoSignInUseCase;
import cl.global.logic.exercise.usecases.dosignup.DoSignUpUseCase;
import cl.global.logic.exercise.usecases.dosignup.models.DoSignUpResponse;
import cl.global.logic.exercise.usecases.finduserbyemail.FindUserByEmailUseCase;
import cl.global.logic.exercise.usecases.getusers.GetUsersUseCase;
import cl.global.logic.exercise.utilities.exceptions.ExceptionHandlerResponse;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.io.IOException;

import static cl.global.logic.exercise.usecase.dosignin.DoSignInStubs.getDoSignInRequest;
import static cl.global.logic.exercise.usecase.dosignup.DoSignUpStubs.getDoSignUpRequest;
import static cl.global.logic.exercise.usecase.dosignup.DoSignUpStubs.getDoSignUpResponse;
import static cl.global.logic.exercise.usecase.getusers.GetUsersStubs.getGetUsersResponse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class DoSignUpTest {

  @Mock private DoSignInUseCase doSignInUseCase;
  @Mock private GetUsersUseCase getUsersUseCase;
  @Mock private FindUserByEmailUseCase findUserByEmailUseCase;
  @Mock private GlobalLogicRepository globalLogicRepository;

  private GlobalLogicController globalLogicController;

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(16);
  }

  @Before
  public void setup() throws IOException {

    final DoSignUpUseCase doSignUpUseCase =
        new DoSignUpUseCase(globalLogicRepository, passwordEncoder());

    final GlobalLogicServiceFacade globalLogicServiceFacade =
        new GlobalLogicServiceFacade(
            doSignUpUseCase, doSignInUseCase, getUsersUseCase, findUserByEmailUseCase);

    globalLogicController = new GlobalLogicController(globalLogicServiceFacade);

    when(globalLogicRepository.save(any(User.class)))
        .thenReturn(
            new User(
                getDoSignUpRequest().getName(),
                getDoSignUpRequest().getEmail(),
                getDoSignUpRequest().getPassword(),
                getDoSignUpRequest().getPhones(),
                getDoSignUpResponse().getCreated()));
  }

  @Test
  public void itShouldDoSignUpWhenApiSuccess() throws IOException {
    assertNotNull(getDoSignUpRequest());

    final DoSignUpResponse doSignUpResponse = globalLogicController.doSignUp(getDoSignUpRequest());

    assertNotNull(getDoSignUpResponse());
    assertNotNull(doSignUpResponse);
    assertEquals(doSignUpResponse, getDoSignUpResponse());
  }

  @Test
  public void itShouldDoSignUpWhenApiFailure() throws IOException {
    assertNotNull(getDoSignUpRequest());
    try {
      globalLogicController.doSignUp(null);
    } catch (NullPointerException ex) {
      assertEquals(new ExceptionHandlerResponse<>(null).getMessage(), ex.getMessage());
    }
  }
}
