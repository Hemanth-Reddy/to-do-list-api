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
 * This is the java persistence entity that is mapped to a table in database.
 *
 * @author hemanth.nagireddy
 *
 */
@Entity
@Table(name = "users")
public class UserEntity {

  /**
   * Id associated with the user.
   */
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid")
  @Column(name = "user_id")
  private String userId;
  
  /**
   * Name of the user.
   */
  @Column(name = "name")
  private String name;
  
  /**
   * Age of the user.
   */
  @Column(name = "age")
  @Min(value = 7, message = "Age must be greater than or equal to 7")
  private Integer age;
  
  /**
   * Password of the user.
   */
  @Column(name = "user_password")
  private String password;
  
  /**
   * Email of the user.
   */
  @Column(name = "email", unique = true)
  @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",
           message = "Given email does not match the standard email pattern")
  private String email;
  
  /**
   * Image uploaded by the user.
   */
  @Column(name = "image")
  private byte[] image;
  
  /**
   * Created time of the user.
   */
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @JsonProperty(access = Access.READ_ONLY)
  @Column(name = "created_at")
  private Date createdAt;
  
  /**
   * Updated time of the user.
   */
  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @JsonProperty(access = Access.READ_ONLY)
  @Column(name = "updated_at")
  private Date updatedAt;
  
  /**
   * Task list of the user.
   */
  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<TaskEntity> taskList;

  /**
   * Default constructor.
   */
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
      @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", 
               message = "Given email does not match the standard email pattern") 
      final String email,
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

  /**
   * Getter for the userId.
   * 
   * @return userId Id of the user.
   */
  public String getUserId() {
    return userId;
  }

  /**
   * Setter for userId.
   * 
   * @param userId Id of the user.
   */
  public void setUserId(final String userId) {
    this.userId = userId;
  }

  /**
   * Getter for name.
   * 
   * @return name name of the user.
   */
  public String getName() {
    return name;
  }

  /**
   * Setter for name.
   * 
   * @param name name of the user.
   */
  public void setName(final String name) {
    this.name = name;
  }

  /**
   * Getter for age.
   * 
   * @return age age of the user.
   */
  public Integer getAge() {
    return age;
  }

  /**
   * Setter for age.
   * 
   * @param age age of the user.
   */
  public void setAge(final Integer age) {
    this.age = age;
  }

  /**
   * Getter for password.
   * 
   * @return password password of the user.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Setter for the password of the user.
   * 
   * @param password password of the user.
   */
  public void setPassword(final String password) {
    this.password = password;
  }

  /**
   * Getter for email.
   * 
   * @return email email of the user.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Setter for the email.
   * 
   * @param email email of the user.
   */
  public void setEmail(final String email) {
    this.email = email;
  }

  /**
   * Getter for image.
   * 
   * @return image image of the user.
   */
  public byte[] getImage() {
    return image;
  }

  /**
   * Setter for image uploaded by the user.
   * 
   * @param image image in bytes.
   */
  public void setImage(final byte[] image) {
    this.image = image;
  }

  /**
   * Getter for created time.
   * 
   * @return createdAt created time of the user.
   */
  public Date getCreatedAt() {
    return createdAt;
  }

  /**
   * Setter for created time of the user.
   * 
   * @param createdAt created time of the user
   */
  public void setCreatedAt(final Date createdAt) {
    this.createdAt = createdAt;
  }

  /**
   * Getter for updatedAt.
   * 
   * @return updatedAt updated time of the user.
   */
  public Date getUpdatedAt() {
    return updatedAt;
  }

  /**
   * Setter for updated time of the user.
   * 
   * @param updatedAt updated time of the user.
   */
  public void setUpdatedAt(final Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  /**
   * Getter for task list.
   * 
   * @return taskList taskList of the user.
   */
  public List<TaskEntity> getTaskList() {
    return taskList;
  }

  /**
   * setter for the list of tasks associated with the user.
   * 
   * @param taskList list of tasks.
   */
  public void setTaskList(final List<TaskEntity> taskList) {
    this.taskList = taskList;
  }

}
