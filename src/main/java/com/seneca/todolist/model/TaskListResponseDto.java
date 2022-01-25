package com.seneca.todolist.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;

/**
 * This is the dto used for taskListResponse.
 *
 * @author hemanth.nagireddy
 *
 */
@JsonInclude(Include.NON_NULL)
public class TaskListResponseDto {

  private Integer count;
  private String message;
  private List<TaskInfoDto> taskInfoDtos;

  public TaskListResponseDto() {
    super();
  }

  /**
   * This is the constructor for this class with three parameters.
   *
   * @param count This indicates the number of tasks returned in this dto.
   * @param message This is the message if required.
   * @param taskInfoDtos These are the tasks returned.
   */
  public TaskListResponseDto(Integer count, String message,
      List<TaskInfoDto> taskInfoDtos) {
    super();
    this.count = count;
    this.message = message;
    this.taskInfoDtos = taskInfoDtos;
  }

  /**
   * This is the constructor for this class with two parameters.
   *
   * @param count This indicates the number of tasks returned in this dto.
   * @param message This is the message if required.
   */
  public TaskListResponseDto(Integer count, String message) {
    super();
    this.count = count;
    this.message = message;
  }

  /**
   * This is the constructor for this class with two parameters.
   *
   * @param count This indicates the number of tasks returned in this dto.
   * @param taskInfoDtos These are the tasks returned.
   */
  public TaskListResponseDto(Integer count, List<TaskInfoDto> taskInfoDtos) {
    super();
    this.count = count;
    this.taskInfoDtos = taskInfoDtos;
  }

  public TaskListResponseDto(String message) {
    this.message = message;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public List<TaskInfoDto> getTaskInfoDtos() {
    return taskInfoDtos;
  }

  public void setTaskInfoDtos(List<TaskInfoDto> taskInfoDtos) {
    this.taskInfoDtos = taskInfoDtos;
  }

}
