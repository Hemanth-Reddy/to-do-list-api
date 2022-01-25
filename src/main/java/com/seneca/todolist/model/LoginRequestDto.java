package com.seneca.todolist.model;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * This is the dto used for login.
 *
 * @author hemanth.nagireddy
 *
 */
public class LoginRequestDto implements Serializable {

  @NotBlank
  @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
  private String email;
  @NotBlank
  private String password;

  /**
   * This is a constructor for this class.
   *
   * @param email Indicates the email entered by the user.
   * @param password Indicates the password entered by the user.
   */
  public LoginRequestDto(String email, String password) {
    super();
    this.email = email;
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
