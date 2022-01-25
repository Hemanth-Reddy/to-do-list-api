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

  private Optional<@Size(min = 1) String> name;
  private Optional<@Min(value = 7, 
      message = "Age must be greater than or equal to 7") Integer> age;
  @JsonProperty(access = Access.WRITE_ONLY)
  private Optional<@Pattern(
      regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])"
               + "(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",
      message = "Password must contain atleast one digit,"
          + "atleast one lowercase alphabet,"
          + " atleast one uppercase alphabet,"
          + " atleast one special character and a size of 8-20 characters."
      ) String> password;
  private Optional<@Email(
      message = "Given email does not match the standard email pattern"
      ) String> email;

  public UserUpdateDto() {
    super();
  }

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

  public Optional<String> getName() {
    return name;
  }

  public void setName(final Optional<String> name) {
    this.name = name;
  }

  public Optional<Integer> getAge() {
    return age;
  }

  public void setAge(final Optional<Integer> age) {
    this.age = age;
  }

  public Optional<String> getPassword() {
    return password;
  }

  public void setPassword(final Optional<String> password) {
    this.password = password;
  }

  public Optional<String> getEmail() {
    return email;
  }

  public void setEmail(final Optional<String> email) {
    this.email = email;
  }

}
