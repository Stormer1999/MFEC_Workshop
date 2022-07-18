package th.co.mfec.api.model.user.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserProfileResponse {

  private String firstNameTh;

  private String lastNameTh;

  private String firstNameEn;

  private String lastNameEn;

  private String mobileNumber;

  private Date birthDate;
}
