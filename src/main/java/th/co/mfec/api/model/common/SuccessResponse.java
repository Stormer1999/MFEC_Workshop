package th.co.mfec.api.model.common;

import lombok.Getter;
import lombok.Setter;
import th.co.mfec.api.constant.StatusCode;

import java.util.Date;

@Getter
@Setter
public class SuccessResponse<T> {

  private StatusResponse status;

  private T data;

  public SuccessResponse() {
    this.status = new StatusResponse(StatusCode.SUC_CODE_200, StatusCode.SUC_DESC_200, new Date());
  }

  public SuccessResponse(T data) {
    this.status = new StatusResponse(StatusCode.SUC_CODE_200, StatusCode.SUC_DESC_200, new Date());
    this.data = data;
  }

  public SuccessResponse(String code, String desc, T data) {
    this.status = new StatusResponse(code, desc, new Date());
    this.data = data;
  }
}
