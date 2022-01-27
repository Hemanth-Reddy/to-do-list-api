package com.seneca.todolist.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.io.Serializable;
import java.util.Optional;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@JsonInclude(Include.NON_NULL)
public class UserUpdateDto implements Serializable {

  /**
   * Name of the user.
   */
  private Optional<@Size(min = 1) String> name;
  /**
   * Age of the user.
   */
  private Optional<@Min(value = 7, 
      message = "Age must be greater than or equal to 7") Integer> age;
  /**
   * Password of the user.
   */
  @JsonProperty(access = Access.WRITE_ONLY)
  private Optional<@Pattern(
      regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])"
               + "(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",
      message = "Password must contain atleast one digit,"
          + "atleast one lowercase alphabet,"
          + " atleast one uppercase alphabet,"
          + " atleast one special character and a size of 8-20 characters."
      ) String> password;
  
  /**
   * Email of the user.
   */
  private Optional<@Email(
      message = "Given email does not match the standard email pattern"
      ) String> email;

  /**
   * Default constructor.
   */
  public UserUpdateDto() {
    super();
  }

  /**
   * Constructor with all the parameters.
   * @param name Name of the user.
   * @param age Age of the user.
   * @param password Password of the user.
   * @param email Email of the user.
   */
  public UserUpdateDto(final Optional<@Size(min = 1) String> name,
      final Optional<@Min(value = 7, 
               message = "Age must be greater than or equal to 7") 
               Integer> age,
      final Optional<@Pattern(
               regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])"
                   + "(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",
               message = "Password must contain atleast one digit,atleast one lowercase alphabet, "
               + "atleast one uppercase alphabet,"
               + " atleast one special character and a size of 8-20 characters.")
               String> password,
      final Optional<@Email(message = "Given email does not match the standard email pattern")
               String> email) {
    super();
    this.name = name;
    this.age = age;
    this.password = password;
    this.email = email;
  }

  /**
   * Getter for the optionalName object.
   * @return name optionalName object.
   */
  public Optional<String> getName() {
    return name;
  }

  /**
   * Setter for the name of the user.
   * @param name Name of the user.
   */
  public void setName(final Optional<String> name) {
    this.name = name;
  }

  /**
   * Getter for the age of the user.
   * @return age Returns the age of the user.
   */
  public Optional<Integer> getAge() {
    return age;
  }

  /**
   * Setter for the age of the user.
   * @param age Age of the user.
   */
  public void setAge(final Optional<Integer> age) {
    this.age = age;
  }

  /**
   * Getter for the password.
   * @return password Password of the user.
   */
  public Optional<String> getPassword() {
    return password;
  }

  /**
   * Setter for the password of the user.
   * @param password Password of the user.
   */
  public void setPassword(final Optional<String> password) {
    this.password = password;
  }

  /**
   * Getter for the email.
   * @return email Email of the user.
   */
  public Optional<String> getEmail() {
    return email;
  }

  /**
   * Setter for the email.
   * @param email Email of the user.
   */
  public void setEmail(final Optional<String> email) {
    this.email = email;
  }

}
