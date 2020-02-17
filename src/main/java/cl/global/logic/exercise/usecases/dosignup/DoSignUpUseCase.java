package cl.global.logic.exercise.usecases.dosignup;

import cl.global.logic.exercise.data.GlobalLogicRepository;
import cl.global.logic.exercise.data.dtos.User;
import cl.global.logic.exercise.usecases.dosignup.models.DoSignUpRequest;
import cl.global.logic.exercise.usecases.dosignup.models.DoSignUpResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static cl.global.logic.exercise.utilities.formats.Date.dateNow;

@Service
public class DoSignUpUseCase {

  // region fields
  private final GlobalLogicRepository globalLogicRepository;
  private final PasswordEncoder passwordEncoder;
  private final Logger logsDoSignUpUseCase;
  // endregion

  @Autowired
  public DoSignUpUseCase(
      final GlobalLogicRepository globalLogicRepository, final PasswordEncoder passwordEncoder) {
    this.globalLogicRepository = globalLogicRepository;
    this.passwordEncoder = passwordEncoder;
    this.logsDoSignUpUseCase = LogManager.getLogger(DoSignUpUseCase.class);
  }

  public DoSignUpResponse doSignUp(final DoSignUpRequest doSignUpRequest) {
    // EncryptPassword
    final DoSignUpRequest encryptDoSignUpRequest =
        new DoSignUpRequest(
            doSignUpRequest.getName(),
            doSignUpRequest.getEmail(),
            passwordEncoder.encode(doSignUpRequest.getPassword()),
            doSignUpRequest.getPhones());
    // Do Log Class
    logsDoSignUpUseCase.info(
        "Here I Am: Class:DoSignUpUseCase, Method: doSignUp, Message: {}", encryptDoSignUpRequest);
    // Do Register User
    final User users =
        globalLogicRepository.save(
            new User(
                encryptDoSignUpRequest.getName(),
                encryptDoSignUpRequest.getEmail(),
                encryptDoSignUpRequest.getPassword(),
                encryptDoSignUpRequest.getPhones(),
                dateNow()));
    // Do Log Action
    logsDoSignUpUseCase.info(
        "Here I Am: Class:DoSignUpUseCase, Method: doSignUp, Action: Create User, Message: {}",
        users);
    // Do Generate Response
    final DoSignUpResponse doSignUpResponse =
        new DoSignUpResponse(users.getCreated(), users.isActive());
    // Do Return Response
    logsDoSignUpUseCase.info(
        "Here I Am: Class:DoSignUpUseCase, Method: doSignUp, Action: Return Response, Message: {}",
        doSignUpResponse);
    // Do Return Response
    return doSignUpResponse;
  }
}
