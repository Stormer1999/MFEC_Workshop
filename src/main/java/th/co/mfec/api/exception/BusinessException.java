package th.co.mfec.api.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends BaseException {

  public BusinessException(String code, String message) {
    super(HttpStatus.INTERNAL_SERVER_ERROR, code, message);
  }
}
