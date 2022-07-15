package th.co.mfec.api.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import th.co.mfec.api.exception.BaseException;
import th.co.mfec.api.model.common.ErrorResponse;

@RestControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

  @ExceptionHandler(BaseException.class)
  public ResponseEntity<Object> handleBaseException(BaseException ex) {
    ex.printStackTrace();
    return ResponseEntity.status(ex.getStatus())
        .body(new ErrorResponse<>(ex.getCode(), ex.getMessage()));
  }

  @ExceptionHandler
  public ResponseEntity<Object> handleException(Exception ex) {
    ex.printStackTrace();
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse<>());
  }
}
