package com.seneca.todolist.exception;

import java.util.Date;

/**
 * This is the exception response class.
 *
 * @author hemanth.nagireddy
 *
 */
public class ExceptionResponse {

  /**
   * Class name of the exception.
   */
  private String name;
  
  /**
   * Timestamp at the creation of this object.
   */
  private Date timeStamp;
  
  /**
   * Message within the exception.
   */
  private String message;
  
  /**
   * Details of the exception.
   */
  private String details;

  /**
   * Default constructor.
   */
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
  public ExceptionResponse(final String name, final Date timeStamp, final String message,
      final String details) {
    super();
    this.name = name;
    this.timeStamp = timeStamp;
    this.message = message;
    this.details = details;
  }

  /**
   * Getter for the class name of the exception.
   * @return name returns the class name.
   */
  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  /**
   * Getter for the time stamp.
   * @return timeStamp returns the timeStamp.
   */
  public Date getTimeStamp() {
    return timeStamp;
  }

  /**
   * Setter for the timestamp.
   * @param timeStamp Timestamp associated with the exception.
   */
  public void setTimeStamp(final Date timeStamp) {
    this.timeStamp = timeStamp;
  }

  /**
   * Getter of the message.
   * @return message Message within the exception.
   */
  public String getMessage() {
    return message;
  }

  /**
   * Setter of the message.
   * @param message Message within the exception.
   */
  public void setMessage(final String message) {
    this.message = message;
  }

  /**
   * @return details returns the details of the exception.
   */
  public String getDetails() {
    return details;
  }

  /**
   * Setter for details.
   * @param details details of the exception.
   */
  public void setDetails(final String details) {
    this.details = details;
  }

}
