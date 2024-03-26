package com.seneca.todolist.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.seneca.todolist.exception.InvalidRequestException;
import com.seneca.todolist.model.LoginRequestDto;
import com.seneca.todolist.model.UserDto;
import com.seneca.todolist.model.UserResponseDto;
import com.seneca.todolist.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * This is the controller that handles the token generation apis which need not be authenticated.
 *
 * @author hemanth.nagireddy 
 * 
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserAuthenticationController {

  /**
   * This is the user service class which interacts with database related to the
   * user. Used for the same purpose.
   */
  @Autowired
  private UserService iuserService;

  /**
   * This API is responsible for the users to login. It contains email and
   * password fields which are mandatory for the user to login
   *
   *@param loginRequestDto This is the dto containing the login username and password.
   * 
   *@return user Returns the logged in user details if credentials are correct.
   */
  @PostMapping("/login")
  public ResponseEntity<?> loginUser(
      final @Valid @RequestBody LoginRequestDto loginRequestDto) {

    UserResponseDto userResponse = iuserService.loginUser(loginRequestDto);

    SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
        .serializeAllExcept("success");
    FilterProvider filterProvider = new SimpleFilterProvider()
        .addFilter("DynamicFilter", filter);
    MappingJacksonValue mapping = new MappingJacksonValue(userResponse);
    mapping.setFilters(filterProvider);

    return new ResponseEntity<>(mapping, HttpStatus.OK);
  }

  /**
   * This method is responsible for user registration in this application. It
   * contains the user data such as email, password and age.
   *
   * @param userDto This dto contains the user details that are to be registered.
   * 
   * @return userDto This dto contains the user details that are saved.
   */
  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@Valid @RequestBody final UserDto userDto) {

    if (!StringUtils.hasText(userDto.getPassword())) {
      throw new InvalidRequestException("Password required..!");
    }
    UserResponseDto userResponse = iuserService.registerUser(userDto);
    SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
        .serializeAllExcept("success");
    FilterProvider filterProvider = new SimpleFilterProvider()
        .addFilter("DynamicFilter", filter);
    MappingJacksonValue mapping = new MappingJacksonValue(userResponse);
    mapping.setFilters(filterProvider);
    return new ResponseEntity<>(mapping, HttpStatus.CREATED);
  }
  
  /**
   * This is the method for user to logout.
   * @param token This is the authentication token.
   * @return status success/failed.
   */
  @PostMapping("/logout")
  public ResponseEntity<?> logoutUser(@RequestHeader("Authorization") final String token) {

    Boolean status = iuserService.logoutUser(token);
    return new ResponseEntity<>(status ? "success" : "failed", HttpStatus.OK);
  }

}