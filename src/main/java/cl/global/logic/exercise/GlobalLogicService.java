package cl.global.logic.exercise;

import cl.global.logic.exercise.usecases.dosignin.models.DoSignInResponse;
import cl.global.logic.exercise.usecases.getusers.models.GetUsersResponse;
import cl.global.logic.exercise.usecases.dosignin.models.DoSignInRequest;
import cl.global.logic.exercise.usecases.finduserbyemail.models.FindUserByEmailRequest;
import cl.global.logic.exercise.usecases.finduserbyemail.models.FindUserByEmailResponse;

public interface GlobalLogicService {

  DoSignInResponse doSignIn(DoSignInRequest doSignInRequest);

  GetUsersResponse getUsers();

  FindUserByEmailResponse findUserByEmail(FindUserByEmailRequest findUserByEmailRequest);
}
