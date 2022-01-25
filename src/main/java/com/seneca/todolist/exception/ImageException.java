package com.seneca.todolist.exception;

/**
 * This is a custom exception class for all image related exceptions.
 *
 * @author hemanth.nagireddy
 *
 */
public class ImageException extends Exception {

  /**
   * This is a constructor for imageexception class.
   *
   * @param message This is the message associated with the exception thrown.
   */
  public ImageException(String message) {
    super(message);
  }
}
