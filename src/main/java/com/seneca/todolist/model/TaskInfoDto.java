package com.seneca.todolist.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotBlank;

/**
 * This is the dto used for taskInfo.
 *
 * @author hemanth.nagireddy
 *
 */
@JsonInclude(Include.NON_NULL)
public class TaskInfoDto implements Serializable {

  /**
   * Id associated with the task.
   */
  @JsonProperty(access = Access.READ_ONLY)
  private String taskId;
  
  /**
   * Description of the task.
   */
  @NotBlank
  private String description;
  
  /**
   * Status of the task whether it is completed or not.
   */
  private boolean completed;
  
  /**
   * Created time of the task.
   */
  @JsonProperty(access = Access.READ_ONLY)
  private Date createdAt;
  
  /**
   * Updated time of the task.
   */
  @JsonProperty(access = Access.READ_ONLY)
  private Date updatedAt;
  
  /**
   * User id of the user who is the owner of this task.
   */
  @JsonProperty(access = Access.READ_ONLY)
  private String owner;

  /**
   * Default constructor.
   */
  public TaskInfoDto() {
    super();
  }

  /**
   * This is the constructor for this class.
   *
   * @param taskId      This indicates the id of the task.
   * @param description Indicates the description of the task.
   * @param completed   Indicates the status of the task.
   * @param owner       Indicates the id of the owner of this task.
   * @param createdAt   Indicates the created time of the task.
   * @param updatedAt   Indicates the updated time of the task.
   */
  public TaskInfoDto(final String taskId, final String description, final boolean completed,
      final String owner, final Date createdAt, final Date updatedAt) {
    super();
    this.taskId = taskId;
    this.description = description;
    this.completed = completed;
    this.owner = owner;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }
  
  /**
   * Getter for taskId.
   * @return taskId returns the taskId.
   */
  public String getTaskId() {
    return taskId;
  }

  /**
   * Setter for taskId.
   * @param taskId Id of the task.
   */
  public void setTaskId(final String taskId) {
    this.taskId = taskId;
  }
  
  /**
   * Getter for description.
   * @return description returns the description of the task.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Setter for description.
   * @param description description of the task.
   */
  public void setDescription(final String description) {
    this.description = description;
  }

  /**
   * Getter for completed.
   * @return completed returns the status of completion.
   */
  public boolean getCompleted() {
    return completed;
  }

  /**
   * Setter for completed.
   * @param completed completed status of the task.
   */
  public void setCompleted(final boolean completed) {
    this.completed = completed;
  }

  /**
   * Getter for createdAt.
   * @return createdAt returns the created time of the task.
   */
  public Date getCreatedAt() {
    return createdAt;
  }

  /**
   * Created time of the task setter method.
   * @param createdAt created time of the task
   */
  public void setCreatedAt(final Date createdAt) {
    this.createdAt = createdAt;
  }

  /**
   * Getter for updatedAt variable.
   * @return updatedAt returns the updated time of the task.
   */
  public Date getUpdatedAt() {
    return updatedAt;
  }

  /**
   * Setter for updated time of the task.
   * @param updatedAt updated time of the task
   */
  public void setUpdatedAt(final Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  
  /**
   * Getter for owner id of the task.
   * @return returns the owner id.
   */
  public String getOwner() {
    return owner;
  }

  /**
   * Setter for owner id of the task.
   * @param owner Takes the owner id.
   */
  public void setOwner(final String owner) {
    this.owner = owner;
  }
}
