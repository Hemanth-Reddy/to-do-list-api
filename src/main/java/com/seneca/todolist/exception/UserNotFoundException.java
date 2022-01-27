package com.seneca.todolist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This is a custom exception created to be thrown in case of UserNotFound exception.
 *
 * @author hemanth.nagireddy
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

  /**
   * This is the constructor for the UserNotFoundException class.
   *
   * @param message This is the message associated with this class.
   */
  public UserNotFoundException(final String message) {
    super(message);
  }

}
