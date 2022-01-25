package com.seneca.todolist.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("DynamicFilter")
public class UserResponseDto implements Serializable {

  private UserDto userDto;
  private String token;
  private Boolean success;

  public UserResponseDto() {
    super();
  }

  public UserResponseDto(UserDto userDto, String token, Boolean success) {
    super();
    this.userDto = userDto;
    this.token = token;
    this.success = success;
  }

  public UserResponseDto(UserDto userDto, String token) {
    super();
    this.userDto = userDto;
    this.token = token;
  }

  public UserResponseDto(Boolean success) {
    super();
    this.success = success;
  }

  public UserDto getUserDTO() {
    return userDto;
  }

  public void setUserDTO(UserDto userDto) {
    this.userDto = userDto;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Boolean getSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }
}