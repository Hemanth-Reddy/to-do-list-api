package com.seneca.todolist.service;

import com.seneca.todolist.entity.UserEntity;
import com.seneca.todolist.exception.ImageException;
import com.seneca.todolist.model.LoginRequestDto;
import com.seneca.todolist.model.UserDto;
import com.seneca.todolist.model.UserResponseDto;
import com.seneca.todolist.model.UserUpdateDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Interface with all the user related functionalities along with register and
 * login.
 *
 * @author hemanth.nagireddy
 *
 */
@Service
public interface UserService extends UserDetailsService {

  /**
   * This method is used for updating the user details.
   *
   * @param userDto The user dto contains the updated details provided by the
   *                user.
   * @param token   This is the authentication token.
   */
  UserDto updateUser(UserUpdateDto user, String token);

  /**
   * This is the method used to upload an image into database.
   *
   * @param bytes This is the byte array representing the image.
   * @param token This is the authentication token.
   * @return True if image is uploaded sucessfully.
   */
  boolean uploadImage(byte[] imageBytes, String token);

  /**
   * This is the method to get the user by id.
   *
   * @param userId This is the id of the user to be fetched.
   * @return This returns the details of user fetched.
   */
  UserDto getUserById(String id);

  /**
   * This method deletes the user from the database with a valid token.
   *
   * @param token This is the token of a logged in user.
   * @return This is the integer indicating the deletion is successful.
   */
  int deleteUser(String token);

  /**
   * This is a service method to fetch the image of the user in byte array.
   *
   * @param userId The id associated with the user.
   * @return byte[] The return type is the image in byte array.
   */
  byte[] fetchImageOfUser(String id) throws ImageException;

  /**
   * This method fetches the user details by token.
   *
   * @param token This is the token of a logged in user.
   * @return user This is the user entity containing user details.
   */
  UserEntity getUserByToken(String token);

  /**
   * This is a method which fetches the user details in dto using the email
   * property.
   *
   * @param email This is the string containing the email of the user.
   * @return userDTO This dto contains the details of the user found by the
   *         email.
   */
  UserDto findUserByEmail(String email);

  /**
   * This method is used for login purpose. Authentication of details happens in
   * this method and a new token is generated.
   *
   * @param loginRequestDto This dto consists of email and password required for
   *                        user to log in.
   * @return UserResponseDto This is the response dto consisting of the user
   *         details and token generated.
   */
  UserResponseDto loginUser(LoginRequestDto loginRequestDto);

  /**
   * This method is used to register the user into db.
   *
   * @param userDto The user details to be registered are given in this dto.
   * @return userResponseDTO This is the response dto consisting of the user
   *         details and token generated.
   */
  UserResponseDto registerUser(UserDto userDto);

  /**
   * This method is used to delete the image of the user by setting the image
   * attribute associated with the user.
   *
   * @param token This is the authentication token.
   */
  boolean deleteImage(String token);

  /**
   * This method is used to fetch the logged in user. This is a method
   * overriding the functionality from userdetails interface extended by the
   * iuserservice interface.
   *
   * @param token This is the authentication token.
   */
  UserDto fetchLoggedInUser(String token);

}
