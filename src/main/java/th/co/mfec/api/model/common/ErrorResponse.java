package th.co.mfec.api.model.common;

import lombok.Getter;
import lombok.Setter;
import th.co.mfec.api.constant.StatusCode;

import java.util.Date;

@Getter
@Setter
public class ErrorResponse<T> {

  private StatusResponse status;

  private T error;

  public ErrorResponse() {
    this.status = new StatusResponse(StatusCode.ERR_CODE_500, StatusCode.ERR_DESC_500, new Date());
  }

  public ErrorResponse(String code, String desc) {
    this.status = new StatusResponse(code, desc, new Date());
  }

  public ErrorResponse(T error) {
    this.status = new StatusResponse(StatusCode.ERR_CODE_500, StatusCode.ERR_DESC_500, new Date());
    this.error = error;
  }

  public ErrorResponse(String code, String desc, T error) {
    this.status = new StatusResponse(code, desc, new Date());
    this.error = error;
  }
}
