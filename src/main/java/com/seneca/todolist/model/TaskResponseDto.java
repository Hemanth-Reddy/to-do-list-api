package com.seneca.todolist.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class TaskResponseDto {

  private Boolean status;
  private String message;
  private TaskInfoDto taskInfo;

  public TaskResponseDto() {
    super();
  }

  public TaskResponseDto(Boolean status, String message) {
    super();
    this.status = status;
    this.message = message;
  }

  public TaskResponseDto(Boolean status, TaskInfoDto taskInfo) {
    super();
    this.status = status;
    this.taskInfo = taskInfo;
  }

  public TaskResponseDto(Boolean status, String message, TaskInfoDto taskInfo) {
    this(status, message);
    this.taskInfo = taskInfo;
  }

  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public TaskInfoDto getTaskInfo() {
    return taskInfo;
  }

  public void setTaskInfo(TaskInfoDto taskInfo) {
    this.taskInfo = taskInfo;
  }

}
