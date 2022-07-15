package th.co.mfec.api.model.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAuthenResponse {

  private Integer userId;

  private String username;

  private String token;
}
