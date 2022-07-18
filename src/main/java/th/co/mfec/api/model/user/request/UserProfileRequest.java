package th.co.mfec.api.model.user.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class UserProfileRequest {

  @NotEmpty(message = "The first name thai is required.")
  private String firstNameTh;

  @NotEmpty(message = "The last name thai is required.")
  private String lastNameTh;

  @NotEmpty(message = "The first name english is required.")
  private String firstNameEn;

  @NotEmpty(message = "The last name english is required.")
  private String lastNameEn;

  @NotEmpty(message = "The mobile number is required.")
  private String mobileNumber;

  @NotNull(message = "The birth date is required.")
  private Date birthDate;
}
