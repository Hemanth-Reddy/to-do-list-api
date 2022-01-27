package com.seneca.todolist.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * This is the java persistence entity mapping to a table in database.
 *
 * @author hemanth.nagireddy
 *
 */
@Entity
@Table(name = "tasks")
public class TaskEntity {

  /**
   * Id associated with the task. 
   */
  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "task_id")
  private String taskId;
  /**
   * Description of the task.
   */
  @Column(name = "description")
  private String description;
  /**
   * Status of the task whether it is completed or not.
   */
  @Column(name = "completed")
  private boolean completed;
  /**
   * Created time of the task.
   */
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @JsonProperty(access = Access.READ_ONLY)
  @Column(name = "created_at")
  private Date createdAt;
  /**
   * Updated time of the task.
   */
  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @JsonProperty(access = Access.READ_ONLY)
  @Column(name = "updated_at")
  private Date updatedAt;
  /**
   * User to which this task belongs.
   */
  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "owner_id", referencedColumnName = "user_id")
  private UserEntity user;

  /**
   * Default constructor.
   */
  public TaskEntity() {
  }

  /**
   * Constructor for taskEntity.
   *
   * @param taskId Indicates the id of task.
   * @param description Contains the description of the task.
   * @param completed Represents whether task is completed or not.
   * @param createdAt Indicates the created time of the task.
   * @param updatedAt Indicates the updated time of the task.
   */
  public TaskEntity(final String taskId, final String description, final boolean completed,
      final Date createdAt, final Date updatedAt) {
    super();
    this.taskId = taskId;
    this.description = description;
    this.completed = completed;
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
   * Getter for UserEntity.
   * @return user returns the user.
   */
  public UserEntity getUser() {
    return user;
  }

  /**
   * Setter for the user.
   * @param user User associated with the task.
   */
  public void setUser(final UserEntity user) {
    this.user = user;
  }

}
