package th.co.mfec.api.model.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserRequest {

  @NotEmpty(message = "The username is required.")
  private String username;

  @NotEmpty(message = "The password is required.")
  private String password;
}
