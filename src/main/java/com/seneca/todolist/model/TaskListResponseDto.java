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

  /**
   * Count of the taskInfoDtos returned.
   */
  private Integer count;
  /**
   * Message associated with the action.
   */
  private String message;
  /**
   * Task information dtos w.r.t. the action performed.
   */
  private List<TaskInfoDto> taskInfoDtos;

  /**
   * Default constructor.
   */
  public TaskListResponseDto() {
    super();
  }

  /**
   * This is the constructor for this class with three parameters.
   *
   * @param count This indicates the number of tasks returned in this dto.
   * @param message Message associated with the action.
   * @param taskInfoDtos These are the tasks returned.
   */
  public TaskListResponseDto(final Integer count, final String message,
      final List<TaskInfoDto> taskInfoDtos) {
    super();
    this.count = count;
    this.message = message;
    this.taskInfoDtos = taskInfoDtos;
  }

  /**
   * This is the constructor for this class with two parameters.
   *
   * @param count This indicates the number of tasks returned in this dto.
   * @param message Message associated with the action.
   */
  public TaskListResponseDto(final Integer count, final String message) {
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
  public TaskListResponseDto(final Integer count, final List<TaskInfoDto> taskInfoDtos) {
    super();
    this.count = count;
    this.taskInfoDtos = taskInfoDtos;
  }

  /**
   * Constructor with only the message.
   * @param message Message associated with the action.
   */
  public TaskListResponseDto(final String message) {
    this.message = message;
  }

  /**
   * Getter for the count.
   * @return count count of the tasks.
   */
  public Integer getCount() {
    return count;
  }

  /**
   * Setter for the count.
   * @param count count of the tasks.
   */
  public void setCount(final Integer count) {
    this.count = count;
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
   * Getter for the taskInfoDtos.
   * @return taskInfoDtos returns the tasks requested.
   */
  public List<TaskInfoDto> getTaskInfoDtos() {
    return taskInfoDtos;
  }

  /**
   * Setter for the taskInfoDtos.
   * @param taskInfoDtos list of tasks.
   */
  public void setTaskInfoDtos(final List<TaskInfoDto> taskInfoDtos) {
    this.taskInfoDtos = taskInfoDtos;
  }

}
