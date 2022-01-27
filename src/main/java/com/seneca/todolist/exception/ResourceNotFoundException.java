package com.seneca.todolist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This is a custom exception created to be thrown in case if resource does not exist.
 *
 * @author hemanth.nagireddy
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

  /**
   * This is the constructor for the ResourceNotFoundException class.
   *
   * @param message This is the message associated with this class.
   */
  public ResourceNotFoundException(final String message) {
    super(message);
  }

}
