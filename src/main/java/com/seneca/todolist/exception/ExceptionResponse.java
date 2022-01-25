package com.seneca.todolist.exception;

import java.util.Date;

/**
 * This is the exception response class.
 *
 * @author hemanth.nagireddy
 *
 */
public class ExceptionResponse {

  private String name;
  private Date timeStamp;
  private String message;
  private String details;

  public ExceptionResponse() {
    super();
  }

  /**
   * This is the constructor for exceptionResponse class.
   *
   * @param name This is the name of the exception.
   * @param timeStamp This is the timestamp at which this object is created.
   * @param message This is the custom message associated with the exceptionresponse object.
   * @param details These are the details of the exception thrown.
   */
  public ExceptionResponse(String name, Date timeStamp, String message,
      String details) {
    super();
    this.name = name;
    this.timeStamp = timeStamp;
    this.message = message;
    this.details = details;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(Date timeStamp) {
    this.timeStamp = timeStamp;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getDetails() {
    return details;
  }

  public void setDetails(String details) {
    this.details = details;
  }

}
