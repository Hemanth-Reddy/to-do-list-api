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

  /**
   * Email entered the user.
   */
  @NotBlank
  @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
  private String email;
  
  /**
   * Password entered by the user.
   */
  @NotBlank
  private String password;

  /**
   * This is a constructor for this class.
   *
   * @param email Indicates the email entered by the user.
   * @param password Indicates the password entered by the user.
   */
  public LoginRequestDto(final String email, final String password) {
    super();
    this.email = email;
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

}
