package com.seneca.todolist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This is a custom exception created to be thrown in case of invalid request exception.
 *
 * @author hemanth.nagireddy
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRequestException extends RuntimeException {

  /**
   * This is the constructor for the invalidrequestException class.
   *
   * @param message This is the message associated with this class.
   */
  public InvalidRequestException(final String message) {
    super(message);
  }
}
