package cl.global.logic.exercise.usecase.dosignup;

import cl.global.logic.exercise.usecases.dosignup.models.DoSignUpRequest;
import cl.global.logic.exercise.usecases.dosignup.models.DoSignUpResponse;
import cl.global.logic.exercise.utilities.formats.LoadStubs;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class DoSignUpStubs {
  public DoSignUpStubs() {}

  public static DoSignUpRequest getDoSignUpRequest() throws IOException {
    return new ObjectMapper()
        .readValue(
            LoadStubs.getStubs("sign-up-request.json"), new TypeReference<DoSignUpRequest>() {});
  }

  public static DoSignUpResponse getDoSignUpResponse() throws IOException {
    return new ObjectMapper()
        .readValue(
            LoadStubs.getStubs("sign-up-response.json"), new TypeReference<DoSignUpResponse>() {});
  }
}
