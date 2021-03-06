package th.co.mfec.api.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BaseException extends RuntimeException {

  private final HttpStatus status;

  private final String code;

  private final Exception causeException;

  public BaseException(HttpStatus status, String code, String message) {
    super(message);
    this.status = status;
    this.code = code;
    this.causeException = null;
  }

  public BaseException(HttpStatus status, String code, String message, Exception causeException) {
    super(message);
    this.status = status;
    this.code = code;
    this.causeException = causeException;
  }
}
