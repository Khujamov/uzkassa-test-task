package uz.kassa.test.exception.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uz.kassa.test.dto.ApiResponse;
import uz.kassa.test.exception.CurrencyInfoNotFoundException;
import uz.kassa.test.exception.EntityNotFoundException;
import uz.kassa.test.exception.LoginException;

import java.util.HashMap;
import java.util.Map;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {LoginException.class})
  public ResponseEntity<?> handleLoginException(LoginException ex) {
    ApiResponse apiResponse = new ApiResponse(0, ex.getMessage());
    return buildResponseEntity(apiResponse);
  }

  @ExceptionHandler(value = {EntityNotFoundException.class})
  public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException ex) {
    ApiResponse apiResponse = new ApiResponse(0, ex.getMessage());
    return buildResponseEntity(apiResponse);
  }

  @ExceptionHandler(value = {CurrencyInfoNotFoundException.class})
  public ResponseEntity<?> handleEntityNotFoundException(CurrencyInfoNotFoundException ex) {
    ApiResponse apiResponse = new ApiResponse(0, ex.getMessage());
    return buildResponseEntity(apiResponse);
  }

  /**
   * Handle MethodArgumentNotValidException. Triggered when an object fails @Valid validation.
   *
   * @param ex the MethodArgumentNotValidException that is thrown when @Valid validation fails
   * @param headers HttpHeaders
   * @param status HttpStatus
   * @param request WebRequest
   * @return the ApiError object
   */
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult()
        .getAllErrors()
        .forEach(
            (error) -> {
              String fieldName = ((FieldError) error).getField();
              String errorMessage = error.getDefaultMessage();
              errors.put(fieldName, errorMessage);
            });
    ApiResponse apiResponse = new ApiResponse(0, errors);
    return (ResponseEntity<Object>) buildResponseEntity(apiResponse);
  }

  @ExceptionHandler(value = {Exception.class})
  public ResponseEntity<?> handleException(Exception ex) {
    ApiResponse apiResponse = new ApiResponse(0, ex.getMessage());
    return buildResponseEntity(apiResponse);
  }

  private ResponseEntity<?> buildResponseEntity(ApiResponse apiResponse) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
  }
}
