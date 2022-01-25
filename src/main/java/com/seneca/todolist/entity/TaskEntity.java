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

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "task_id")
  private String taskId;
  @Column(name = "description")
  private String description;
  @Column(name = "completed")
  private boolean completed;
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @JsonProperty(access = Access.READ_ONLY)
  @Column(name = "created_at")
  private Date createdAt;
  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @JsonProperty(access = Access.READ_ONLY)
  @Column(name = "updated_at")
  private Date updatedAt;
  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "owner_id", referencedColumnName = "user_id")
  private UserEntity user;

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

  public String getTaskId() {
    return taskId;
  }

  public void setTaskId(final String taskId) {
    this.taskId = taskId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(final String description) {
    this.description = description;
  }

  public boolean getCompleted() {
    return completed;
  }

  public void setCompleted(final boolean completed) {
    this.completed = completed;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(final Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(final Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  public UserEntity getUser() {
    return user;
  }

  public void setUser(final UserEntity user) {
    this.user = user;
  }

}
