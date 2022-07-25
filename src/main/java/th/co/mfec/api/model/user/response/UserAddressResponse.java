package th.co.mfec.api.model.user.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserAddressResponse {

  private List<AddressResponse> addresses;
}
