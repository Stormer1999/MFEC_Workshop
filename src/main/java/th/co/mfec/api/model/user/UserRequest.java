package th.co.mfec.api.model.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

  private String username;

  private String password;
}