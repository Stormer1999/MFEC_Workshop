package th.co.mfec.api.model.user.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserAddressRequest {

  private List<AddressRequest> addresses;
}
