package cl.global.logic.exercise.usecases.finduserbyemail;

import cl.global.logic.exercise.data.GlobalLogicRepository;
import cl.global.logic.exercise.data.dtos.User;
import cl.global.logic.exercise.usecases.dosignin.models.DoSignInRequest;
import cl.global.logic.exercise.usecases.dosignin.models.DoSignInResponse;
import cl.global.logic.exercise.usecases.dosignup.DoSignUpUseCase;
import cl.global.logic.exercise.usecases.finduserbyemail.models.FindUserByEmailRequest;
import cl.global.logic.exercise.usecases.finduserbyemail.models.FindUserByEmailResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static cl.global.logic.exercise.utilities.AppConstant.USER_DOES_NOT_EXIST_EXCEPTION_MESSAGE;

@Service
public class FindUserByEmailUseCase {

  private GlobalLogicRepository globalLogicRepository;
  private final Logger logsFindUserByEmailUseCase;

  @Autowired
  public FindUserByEmailUseCase(final GlobalLogicRepository globalLogicRepository) {
    this.globalLogicRepository = globalLogicRepository;
    this.logsFindUserByEmailUseCase = LogManager.getLogger(DoSignUpUseCase.class);
  }

  public FindUserByEmailResponse findUserByEmail(
      final FindUserByEmailRequest findUserByEmailRequest) {
    // Do log class
    logsFindUserByEmailUseCase.info(
        "Here I Am: Class:FindUserByEmailUseCase, Method: findUserByEmail, Message: {}",
        findUserByEmailRequest);
    // Do find email user
    final User user = globalLogicRepository.findByEmail(findUserByEmailRequest.getEmail());
    // Do log find email user
    logsFindUserByEmailUseCase.info(
        "Here I Am: Class:FindUserByEmailUseCase, Method: findUserByEmail, Action: findUserByEmail, Message: {}",
        user);
    // Do exception user doesn't exist
    Objects.requireNonNull(user, USER_DOES_NOT_EXIST_EXCEPTION_MESSAGE);
    // Do create response find user by email
    final FindUserByEmailResponse findUserByEmailResponse =
        new FindUserByEmailResponse(
            user.getId(),
            user.getCreated(),
            user.getModified(),
            user.getLastLogin(),
            user.getToken(),
            user.isActive());
    // Do log response sign in user
    logsFindUserByEmailUseCase.info(
        "Here I Am: Class:FindUserByEmailUseCase, Method: findUserByEmail, Action: create response, Message: {}",
        findUserByEmailResponse);
    // return response
    return findUserByEmailResponse;
  }
}
