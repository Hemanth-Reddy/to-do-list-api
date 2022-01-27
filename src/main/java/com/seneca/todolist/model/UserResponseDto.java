package com.seneca.todolist.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import java.io.Serializable;

@JsonFilter("DynamicFilter")
public class UserResponseDto implements Serializable {

  /**
   * User dto containing the data of user.
   */
  private UserDto userDto;
  
  /**
   * Jwt token.
   */
  private String token;
  
  /**
   * Success or failure of the action.
   */
  private Boolean success;

  /**
   * Default constructor.
   */
  public UserResponseDto() {
    super();
  }

  /**
   * Constructor with all the variables.
   * @param userDto user information dto.
   * @param token Jwt token.
   * @param success success/failure of the token.
   */
  public UserResponseDto(final UserDto userDto, final String token, final Boolean success) {
    super();
    this.userDto = userDto;
    this.token = token;
    this.success = success;
  }

  /**
   * Constructor with userDto and token.
   * @param userDto user information dto.
   * @param token Jwt authentication token.
   */
  public UserResponseDto(final UserDto userDto, final String token) {
    super();
    this.userDto = userDto;
    this.token = token;
  }

  /**
   * Constructor with only success variable.
   * @param success success/failure of the action.
   */
  public UserResponseDto(final Boolean success) {
    super();
    this.success = success;
  }

  /**
   * Getter for the user dto.
   * @return userDto Returns user information dto.
   */
  public UserDto getUserDTO() {
    return userDto;
  }

  /**
   * Setter for the user dto.
   * @param userDto user information dto.
   */
  public void setUserDTO(final UserDto userDto) {
    this.userDto = userDto;
  }

  /**
   * Getter for the token.
   * @return token Jwt Authentication token.
   */
  public String getToken() {
    return token;
  }

  /**
   * Setter for the token.
   * @param token Jwt Authentication token.
   */
  public void setToken(final String token) {
    this.token = token;
  }

  /**
   * Getter for the success variable.
   * @return success success/failure of the action.
   */
  public Boolean getSuccess() {
    return success;
  }

  /**
   * Setter for the success variable.
   * @param success success/failure of the action.
   */
  public void setSuccess(final Boolean success) {
    this.success = success;
  }
}