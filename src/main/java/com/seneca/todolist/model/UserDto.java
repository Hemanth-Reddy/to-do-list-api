package com.seneca.todolist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.seneca.todolist.entity.TaskEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@JsonInclude(Include.NON_NULL)
public class UserDto implements UserDetails, Serializable {

  private String userId;
  private String name;
  @Min(value = 7, message = "Age must be greater than or equal to 7")
  private Integer age;
  @JsonProperty(access = Access.WRITE_ONLY)
  @Pattern(
      regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",
      message = "Password must contain atleast one digit,atleast one lowercase alphabet, "
                + "atleast one uppercase alphabet, atleast one special character "
                + "and a size of 8-20 characters.")
  private String password;
  @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", 
           message = "Given email does not match the standard email pattern")
  private String email;
  @JsonProperty(access = Access.READ_ONLY)
  private byte[] image;
  @JsonProperty(access = Access.READ_ONLY)
  private Date createdAt;
  @JsonProperty(access = Access.READ_ONLY)
  private Date updatedAt;
  @JsonIgnore
  private List<TaskEntity> taskList;

  /**
   * Default constructor.
   */
  public UserDto() {
    super();
  }

  /**
   * Constructor with parameters.
   * @param userId Indicates the id of the user.
   * @param name Indicates the name of the user.
   * @param age Indicates the age of the user.
   * @param password Indicates the password of the user.
   * @param email Indicates the email of the user.
   * @param createdAt Indicates the created time of the user.
   * @param updatedAt Indicates the updated time of the user.
   */
  public UserDto(final String userId, final String name,
      @Min(value = 7, message = "Age must be greater than or equal to 7") 
      final Integer age,
      final String password,
      @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", 
               message = "Given email does not match the standard email pattern") 
      final String email,
      final Date createdAt, final Date updatedAt) {
    super();
    this.userId = userId;
    this.name = name;
    this.age = age;
    this.password = password;
    this.email = email;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  /**
   * Constructor with specific parameters.
   * @param name Indicates the name of the user.
   * @param age Indicates the name of the user.
   * @param password Indicates the password of the user.
   * @param email Indicates the email of the user.
   * @param createdAt Indicates the created time of the user.
   * @param updatedAt Indicates the updated time of the user.
   */
  public UserDto(final String name,
      @Min(value = 7, message = "Age must be greater than or equal to 7") 
      final Integer age,
      final String password,
      @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", 
               message = "Given email does not match the standard email pattern"
               ) final String email,
      final Date createdAt, final Date updatedAt) {
    super();
    this.name = name;
    this.age = age;
    this.password = password;
    this.email = email;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
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

  public List<TaskEntity> getTaskList() {
    return taskList;
  }

  public void setTaskList(final List<TaskEntity> taskList) {
    this.taskList = taskList;
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

  @Override
  @JsonProperty(access = Access.WRITE_ONLY)
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return new ArrayList<>();
  }

  @Override
  @JsonProperty(access = Access.WRITE_ONLY)
  public String getUsername() {
    return this.email;
  }

  @Override
  @JsonProperty(access = Access.WRITE_ONLY)
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  @JsonProperty(access = Access.WRITE_ONLY)
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  @JsonProperty(access = Access.WRITE_ONLY)
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  @JsonProperty(access = Access.WRITE_ONLY)
  public boolean isEnabled() {
    return true;
  }
}
