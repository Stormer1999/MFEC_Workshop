package th.co.mfec.api.model.user.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserResponse {

  private Integer id;

  private String username;

  private Date expiredDate;
}
