package com.seneca.todolist.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class TaskResponseDto {

  /**
   * Status of the action.
   */
  private Boolean status;
  /**
   * Message associated for the action.
   */
  private String message;
  /**
   * Task info related to the performed action.
   */
  private TaskInfoDto taskInfo;

  /**
   * Default constructor.
   */
  public TaskResponseDto() {
    super();
  }

  /**
   * Constructor with status and message variables.
   * @param status status of the action.
   * @param message message associated with the action.
   */
  public TaskResponseDto(final Boolean status, final String message) {
    super();
    this.status = status;
    this.message = message;
  }

  /**
   * Constructor with status and taskInfo.
   * @param status status of the action.
   * @param taskInfo task information dto.
   */
  public TaskResponseDto(final Boolean status, final TaskInfoDto taskInfo) {
    super();
    this.status = status;
    this.taskInfo = taskInfo;
  }

  /**
   * Constructor with all the attributes.
   * @param status status of the action.
   * @param message message associated with the action. 
   * @param taskInfo task information dto.
   */
  public TaskResponseDto(final Boolean status, final String message, final TaskInfoDto taskInfo) {
    this(status, message);
    this.taskInfo = taskInfo;
  }

  /**
   * Getter for the status.
   * @return status true or false.
   */
  public Boolean getStatus() {
    return status;
  }

  /**
   * Setter for the status.
   * @param status status of the action.
   */
  public void setStatus(final Boolean status) {
    this.status = status;
  }

  /**
   * Getter for the message.
   * @return message message associated with the action.
   */
  public String getMessage() {
    return message;
  }

  /**
   * Setter for the message.
   * @param message message associated with the action.
   */
  public void setMessage(final String message) {
    this.message = message;
  }

  /**
   * Getter for the Task information dto.
   * @return task returns the task information dto.
   */
  public TaskInfoDto getTaskInfo() {
    return taskInfo;
  }

  /**
   * Setter for the task information dto.
   * @param taskInfo task information dto.
   */
  public void setTaskInfo(final TaskInfoDto taskInfo) {
    this.taskInfo = taskInfo;
  }

}
