package th.co.mfec.api.model.user.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAuthenRequest {

  private String username;

  private String password;
}
