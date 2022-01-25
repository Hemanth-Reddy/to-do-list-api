package com.seneca.todolist.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * This is the java persistence entity that is 
 * mapped to a table in database.
 *
 * @author hemanth.nagireddy
 *
 */
@Entity
@Table(name = "users")
public class UserEntity {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid")
  @Column(name = "user_id")
  private String userId;
  @Column(name = "name")
  private String name;
  @Column(name = "age")
  @Min(value = 7, message = "Age must be greater than or equal to 7")
  private Integer age;
  @Column(name = "user_password")
  private String password;
  @Column(name = "email", unique = true)
  @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", 
           message = "Given email does not match the standard email pattern")
  private String email;
  @Column(name = "image")
  private byte[] image;
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
  @OneToMany(mappedBy = "user", 
             fetch = FetchType.LAZY, 
             cascade = CascadeType.ALL)
  private List<TaskEntity> taskList;

  public UserEntity() {
    super();
  }

  /**
   * Constructor for user entity.
   *
   * @param userId    Indicates the user id.
   * @param name      Indicates the name of the user.
   * @param age       Indicates the age of the user.
   * @param password  Indicates the password of the user.
   * @param email     Indicates the email of the user.
   * @param image     Contains the image of the user.
   * @param createdAt Indicates the created time of the user.
   * @param updatedAt Indicates the updated time of the user.
   * @param taskList  Indicates the taskList associated with the user.
   */
  public UserEntity(final String userId, final String name,
      @Min(value = 7, message = "Age must be greater than or equal to 7") final Integer age,
      final String password,
      @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Given email does not match the standard email pattern") final String email,
      final byte[] image, final Date createdAt, final Date updatedAt,
      final List<TaskEntity> taskList) {
    super();
    this.userId = userId;
    this.name = name;
    this.age = age;
    this.password = password;
    this.email = email;
    this.image = image;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.taskList = taskList;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(final String userId) {
    this.userId = userId;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(final Integer age) {
    this.age = age;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(final String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  public byte[] getImage() {
    return image;
  }

  public void setImage(final byte[] image) {
    this.image = image;
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

  public List<TaskEntity> getTaskList() {
    return taskList;
  }

  public void setTaskList(final List<TaskEntity> taskList) {
    this.taskList = taskList;
  }

}
