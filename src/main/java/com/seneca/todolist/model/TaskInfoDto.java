package com.seneca.todolist.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.util.Date;

/**
 * This is the dto used for taskInfo.
 *
 * @author hemanth.nagireddy
 *
 */
@JsonInclude(Include.NON_NULL)
public class TaskInfoDto implements Serializable {

  private String taskId;
  private String description;
  private boolean completed;
  private String owner;
  private Date createdAt;
  private Date updatedAt;

  public TaskInfoDto() {
    super();
  }

  /**
   * This is the constructor for this class.
   *
   * @param taskId This indicates the id of the task. 
   * @param description Indicates the description of the task.
   * @param completed Indicates the status of the task.
   * @param owner Indicates the id of the owner of this task.
   * @param createdAt Indicates the created time of the task.
   * @param updatedAt Indicates the updated time of the task.
   */
  public TaskInfoDto(String taskId, String description, boolean completed,
      String owner, Date createdAt, Date updatedAt) {
    super();
    this.taskId = taskId;
    this.description = description;
    this.completed = completed;
    this.owner = owner;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public String getTaskId() {
    return taskId;
  }

  public void setTaskId(String taskId) {
    this.taskId = taskId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Boolean getCompleted() {
    return completed;
  }

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

}
