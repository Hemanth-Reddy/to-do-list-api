package com.seneca.todolist.exception;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * This is the controller advice class to handle the exceptions thrown to the user.
 *
 * @author hemanth.nagireddy
 *
 */
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler
    extends ResponseEntityExceptionHandler {

  /**
   * This is the handler method for the exceptions of type Exception.class.
   *
   * @param exception This is the exception object.
   * @param req This is the webRequest.
   * @return response This is the response entity returned.
   */
  @ExceptionHandler(Exception.class)
  public final ResponseEntity<Object> handleExceptions(final Exception exception,
      final WebRequest req) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(
        exception.getClass().getName(), new Date(), exception.getMessage(), exception.getMessage());
    exception.printStackTrace();
    return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * This is the handler method for the exceptions of type InvalidRequestException.class.
   *
   * @param exception This is the InvalidRequestException object.
   * @param req This is the webRequest.
   * @return response This is the response entity returned.
   */
  @ExceptionHandler(InvalidRequestException.class)
  public final ResponseEntity<Object> handleExceptions(final InvalidRequestException exception,
      final WebRequest req) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(
        exception.getClass().getName(), new Date(), exception.getMessage(), exception.getMessage());
    exception.printStackTrace();
    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }

  /**
   * This is the handler method for the exceptions of type UserNotFoundException.class.
   *
   * @param exception This is the UserNotFoundException object.
   * @param request This is the webRequest.
   * @return response This is the response entity returned.
   */
  @ExceptionHandler(UserNotFoundException.class)
  public final ResponseEntity<Object> handleUserNotFoundExceptions(
      final UserNotFoundException exception, final WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(
        exception.getClass().getName(), new Date(), exception.getMessage(),
        request.getDescription(false));
    exception.printStackTrace();
    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
  }

  /**
   * This is the handler method for the exceptions of type MethodArgumentNotValidException.class.
   *
   * @param exception This is the MethodArgumentNotValidException object.
   * @param request This is the webRequest.
   * @return response This is the response entity returned.
   */
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      final MethodArgumentNotValidException exception, final HttpHeaders headers, 
      final HttpStatus status, final WebRequest request) {
    List<String> fieldErrorMessages = exception.getBindingResult().getFieldErrors().stream()
        .map(f -> f.getDefaultMessage()).collect(Collectors.toList());
    ExceptionResponse exceptionResponse = new ExceptionResponse(
        exception.getClass().getName(), new Date(), fieldErrorMessages.toString(),
        exception.getMessage());
    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }

  /**
   * This is the handler method for the exceptions of type ImageException.class.
   *
   * @param exception This is the ImageException object.
   * @param request This is the webRequest.
   * @return response This is the response entity returned.
   */
  @ExceptionHandler(ImageException.class)
  public final ResponseEntity<Object> handleImageNotFoundException(final ImageException exception,
      final WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(
        exception.getClass().getName(), new Date(), exception.getMessage(), exception.getMessage());
    exception.printStackTrace();
    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }

  /**
   * This is the handler method for the exceptions of type ResourceNotFoundException.class.
   *
   * @param exception This is the ResourceNotFoundException object.
   * @param request This is the webRequest.
   * @return response This is the response entity returned.
   */
  @ExceptionHandler(ResourceNotFoundException.class)
  public final ResponseEntity<Object> handleResourceNotFoundException(
      final ResourceNotFoundException exception, final WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(
        exception.getClass().getName(), new Date(), exception.getMessage(), exception.getMessage());
    exception.printStackTrace();
    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
  }
}
