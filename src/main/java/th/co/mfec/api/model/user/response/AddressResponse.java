package th.co.mfec.api.model.user.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponse {

  private String line1;

  private String line2;

  private String postcode;

  private String type;

  private String prefer;
}
