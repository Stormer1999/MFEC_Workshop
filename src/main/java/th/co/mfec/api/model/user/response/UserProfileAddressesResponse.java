package th.co.mfec.api.model.user.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileAddressesResponse {

  private UserResponse user;

  private UserProfileResponse profile;

  private UserAddressResponse address;
}
