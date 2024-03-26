package com.seneca.todolist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@JsonInclude(Include.NON_NULL)
public class UserDto implements UserDetails, Serializable {

  /**
   * Id associated with the user.
   */
  @JsonProperty(access = Access.READ_ONLY)
  private String userId;
  
  /**
   * Name of the user.
   */
  @Size(min = 1)
  private String name;
  
  /**
   * Age of the user.
   */
  @Min(value = 7, message = "Age must be greater than or equal to 7")
  private Integer age;
  
  /**
   * Password of the user.
   */
  @JsonProperty(access = Access.WRITE_ONLY)
//  @Pattern(
//      regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",
//      message = "Password must contain atleast one digit,atleast one lowercase alphabet, "
//                + "atleast one uppercase alphabet, atleast one special character "
//                + "and a size of 8-20 characters.")
  private String password;
  
  /**
   * Email of the user.
   */
  @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", 
           message = "Given email does not match the standard email pattern")
  private String email;
  
  /**
   * Image uploaded by the user.
   */
  @JsonProperty(access = Access.READ_ONLY)
  private byte[] image;
  
  /**
   * Created time of the user.
   */
  @JsonProperty(access = Access.READ_ONLY)
  private Date createdAt;
  
  /**
   * Updated time of the user.
   */
  @JsonProperty(access = Access.READ_ONLY)
  private Date updatedAt;
  
  /**
   * Task list of the user.
   */
  @JsonIgnore
  private List<TaskInfoDto> taskList;

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
  public UserDto(final String userId,  @Size(min = 1) final String name,
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
  public UserDto(@Size(min = 1) final String name,
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
   * Getter for taskList of the user.
   * @return returns tasks of the user.
   */
  public List<TaskInfoDto> getTaskList() {
    return taskList;
  }

  /**
   * Setter of the taskList of the user.
   * @param taskList takes the list of tasks.
   */
  public void setTaskList(final List<TaskInfoDto> taskList) {
    this.taskList = taskList;
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
