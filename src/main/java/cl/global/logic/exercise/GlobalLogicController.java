package cl.global.logic.exercise;

import cl.global.logic.exercise.usecases.dosignin.models.DoSignInRequest;
import cl.global.logic.exercise.usecases.dosignin.models.DoSignInResponse;
import cl.global.logic.exercise.usecases.dosignup.models.DoSignUpRequest;
import cl.global.logic.exercise.usecases.dosignup.models.DoSignUpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/unprotected")
public class GlobalLogicController {

  // region fields
  private final GlobalLogicServiceFacade globalLogicServiceFacade;
  // endregion

  // region constructor
  @Autowired
  public GlobalLogicController(final GlobalLogicServiceFacade globalLogicServiceFacade) {
    this.globalLogicServiceFacade = globalLogicServiceFacade;
  }
  // endregion

  //
  @RequestMapping(
      value = "/doSignIn",
      method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public DoSignInResponse doSignIn(@Valid @RequestBody final DoSignInRequest doSignInRequest) {
    // redirect to ServiceFacade
    return globalLogicServiceFacade.doSignIn(doSignInRequest);
  }

  //
  @RequestMapping(
      value = "/doSignUp",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public DoSignUpResponse doSignUp(@RequestBody @Valid final DoSignUpRequest doSignUpRequest) {
    // redirect to ServiceFacade
    return globalLogicServiceFacade.doSignUp(doSignUpRequest);
  }
}
