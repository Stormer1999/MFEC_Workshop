package th.co.mfec.api.controller.advice;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import th.co.mfec.api.constant.StatusCode;
import th.co.mfec.api.exception.BaseException;
import th.co.mfec.api.model.common.ErrorResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex,
        HttpHeaders headers,
        HttpStatus status,
        WebRequest request) {
      List<String> errors =
          ex.getBindingResult().getFieldErrors().stream()
              .map(DefaultMessageSourceResolvable::getDefaultMessage)
              .collect(Collectors.toList());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ErrorResponse<>(StatusCode.ERR_CODE_400, StatusCode.ERR_DESC_400, errors));
    }

//  @Override
//  protected ResponseEntity<Object> handleMethodArgumentNotValid(
//      MethodArgumentNotValidException ex,
//      HttpHeaders headers,
//      HttpStatus status,
//      WebRequest request) {
//    Map<String, String> errors = new HashMap<>();
//    ex.getBindingResult()
//        .getFieldErrors()
//        .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
//    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//        .body(new ErrorResponse<>(StatusCode.ERR_CODE_400, StatusCode.ERR_DESC_400, errors));
//  }

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
