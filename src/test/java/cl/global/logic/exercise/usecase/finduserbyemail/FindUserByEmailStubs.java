package cl.global.logic.exercise.usecase.finduserbyemail;

import cl.global.logic.exercise.usecases.finduserbyemail.models.FindUserByEmailRequest;
import cl.global.logic.exercise.usecases.finduserbyemail.models.FindUserByEmailResponse;
import cl.global.logic.exercise.utilities.formats.LoadStubs;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class FindUserByEmailStubs {
  public FindUserByEmailStubs() {}

  public static FindUserByEmailResponse getFindUserByEmailResponse() throws IOException {
    return new ObjectMapper()
        .readValue(
            LoadStubs.getStubs("find-user-by-email-response.json"),
            new TypeReference<FindUserByEmailResponse>() {});
  }

  public static FindUserByEmailRequest getFindUserByEmailRequest() throws IOException {
    return new ObjectMapper()
        .readValue(
            LoadStubs.getStubs("find-user-by-email-request.json"),
            new TypeReference<FindUserByEmailRequest>() {});
  }
}
