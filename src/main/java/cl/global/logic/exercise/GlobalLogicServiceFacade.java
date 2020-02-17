package cl.global.logic.exercise;

import cl.global.logic.exercise.usecases.getusers.GetUsersUseCase;
import cl.global.logic.exercise.usecases.getusers.models.GetUsersResponse;
import cl.global.logic.exercise.usecases.dosignin.DoSignInUseCase;
import cl.global.logic.exercise.usecases.dosignin.models.DoSignInRequest;
import cl.global.logic.exercise.usecases.dosignin.models.DoSignInResponse;
import cl.global.logic.exercise.usecases.dosignup.DoSignUpUseCase;
import cl.global.logic.exercise.usecases.dosignup.models.DoSignUpRequest;
import cl.global.logic.exercise.usecases.dosignup.models.DoSignUpResponse;
import cl.global.logic.exercise.usecases.finduserbyemail.FindUserByEmailUseCase;
import cl.global.logic.exercise.usecases.finduserbyemail.models.FindUserByEmailRequest;
import cl.global.logic.exercise.usecases.finduserbyemail.models.FindUserByEmailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GlobalLogicServiceFacade implements GlobalLogicService {

  // region field
  private final DoSignUpUseCase doSignUpUseCase;
  private final DoSignInUseCase doSignInUseCase;
  private final GetUsersUseCase getUsersUseCase;
  private final FindUserByEmailUseCase findUserByEmailUseCase;
  // endregion

  @Autowired
  public GlobalLogicServiceFacade(
      final DoSignUpUseCase doSignUpUseCase,
      final DoSignInUseCase doSignInUseCase,
      final GetUsersUseCase getUsersUseCase,
      final FindUserByEmailUseCase findUserByEmailUseCase) {
    this.doSignUpUseCase = doSignUpUseCase;
    this.doSignInUseCase = doSignInUseCase;
    this.getUsersUseCase = getUsersUseCase;
    this.findUserByEmailUseCase = findUserByEmailUseCase;
  }

  @Override
  public DoSignInResponse doSignIn(final DoSignInRequest doSignInRequest) {
    // Redirect to doSignInUseCase
    return doSignInUseCase.doSignIn(doSignInRequest);
  }

  @Override
  public DoSignUpResponse doSignUp(final DoSignUpRequest doSignUpRequest) {
    // Redirect to doSignUpUseCase
    return doSignUpUseCase.doSignUp(doSignUpRequest);
  }

  @Override
  public GetUsersResponse getUsers() {
    // Redirect to doGetUsers
    return getUsersUseCase.getUsers();
  }

  @Override
  public FindUserByEmailResponse findUserByEmail(FindUserByEmailRequest findUserByEmailRequest) {
    // Redirect to findUserByEmail
    return findUserByEmailUseCase.findUserByEmail(findUserByEmailRequest);
  }
}
