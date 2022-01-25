package com.seneca.todolist.service;

import com.seneca.todolist.config.JwtTokenProvider;
import com.seneca.todolist.entity.UserEntity;
import com.seneca.todolist.exception.ImageException;
import com.seneca.todolist.exception.ResourceNotFoundException;
import com.seneca.todolist.model.LoginRequestDto;
import com.seneca.todolist.model.UserDto;
import com.seneca.todolist.model.UserResponseDto;
import com.seneca.todolist.model.UserUpdateDto;
import com.seneca.todolist.repository.UserRepository;
import com.seneca.todolist.utility.UserUtility;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * This is the implementation class for user service interface.
 *
 * @author hemanth.nagireddy
 *
 */
@Service
public class UserServiceImpl implements UserService {

  /**
   * This interface connects with the database via java persistence api and used
   * for interacting with database.
   */
  @Autowired
  public UserRepository iuserRepository;

  /**
   * This is a utility class.
   */
  @Autowired
  public UserUtility userUtility;

  /**
   * This is the class that provides the functionality required for token
   * generation and validations.
   */
  @Autowired
  public JwtTokenProvider jwtTokenProvider;

  /**
   * This is a password encoder class which is utilised for encoding the
   * password for authentication purposes.
   */
  @Autowired
  private PasswordEncoder bcryptPasswordEncoder;

  /**
   * This method is used for login purpose. Authentication of details happens in
   * this method and a new token is generated.
   *
   * @param loginRequestDto This dto consists of email and password required for
   *                        user to log in.
   * @return UserResponseDto This is the response dto consisting of the user
   *         details and token generated.
   */
  public UserResponseDto loginUser(final LoginRequestDto loginRequestDto) {

    final UserEntity user = iuserRepository
        .findByEmail(loginRequestDto.getEmail());
    if (user == null) {
      throw new ResourceNotFoundException(
          "User not found with email: " + loginRequestDto.getEmail());
    }
    authenticateUser(user, loginRequestDto.getPassword());
    return new UserResponseDto(userUtility.convertToUserDto(user),
        jwtTokenProvider.generateToken(user));
  }

  /**
   * This method is used to register the user into db.
   *
   * @param userDto The user details to be registered are given in this dto.
   * @return userResponseDTO This is the response dto consisting of the user
   *         details and token generated.
   */
  @Override
  public UserResponseDto registerUser(final UserDto userDto) {

    final UserEntity registeredUser = registerUserIntoDb(userDto);
    final String token = jwtTokenProvider.generateToken(registeredUser);
    return new UserResponseDto(userUtility.convertToUserDto(registeredUser),
        token);
  }

  /**
   * This is a method which fetches the user details in dto using the email
   * property.
   *
   * @param email This is the string containing the email of the user.
   * @return userDTO This dto contains the details of the user found by the
   *         email.
   */
  public UserDto findUserByEmail(final String email) {
    final UserEntity user = iuserRepository.findByEmail(email);
    if (user == null) {
      throw new ResourceNotFoundException(
          "User not found with email: " + email);
    }
    return userUtility.convertToUserDto(user);
  }

  /**
   * This is a method that is overriding the functionality of the method in
   * userdetailsservice.
   *
   * @param username This is the username of the user.
   */
  @Override
  public UserDetails loadUserByUsername(final String username) {
    return findUserByEmail(username);
  }

  /**
   * This is the method to register user into db by encoding the password.
   *
   * @param userDto This dto consists of the user details.
   * @return UserEntity This method returns the saved entity returned by the
   *         save api in jpa.
   */
  public UserEntity registerUserIntoDb(final UserDto userDto) {
    final UserEntity user = userUtility.convertToEntity(userDto);
    user.setPassword(encodePassword(user.getPassword()));
    return iuserRepository.save(user);
  }

  /**
   * This is a method that encodes the password.
   *
   * @param password The password to be encoded.
   * @return Returns the encoded password.
   */
  private String encodePassword(final String password) {
    return bcryptPasswordEncoder.encode(password);
  }

  /**
   * This method is used for updating the user details.
   *
   * @param userDto The user dto contains the updated details provided by the
   *                user.
   * @param token   This is the authentication token.
   */
  @Override
  public UserDto updateUser(final UserUpdateDto userDto, final String token) {

    final UserEntity user = getUserByToken(token);
    userUtility.updateEntityWithDto(user, userDto);
    return userUtility.convertToUserDto(iuserRepository.save(user));
  }

  /**
   * This is the method used to upload an image into database.
   *
   * @param bytes This is the byte array representing the image.
   * @param token This is the authentication token.
   * @return True if image is uploaded sucessfully.
   */
  @Override
  public boolean uploadImage(final byte[] bytes, final String token) {
    final UserEntity user = getUserByToken(token);
    user.setImage(bytes);
    return Objects.nonNull(iuserRepository.save(user));
  }

  /**
   * This is the method to get the user by id.
   *
   * @param userId This is the id of the user to be fetched.
   * @return This returns the details of user fetched.
   */
  @Override
  public UserDto getUserById(final String userId) {
    return userUtility.convertToUserDto(iuserRepository.getById(userId));
  }

  /**
   * This method deletes the user from the database with a valid token.
   *
   * @param token This is the token of a logged in user.
   * @return This is the integer indicating the deletion is successful.
   */
  @Override
  public int deleteUser(final String token) {

    final String emailFromToken = jwtTokenProvider.getUsernameFromToken(token);
    return iuserRepository.deleteByEmail(emailFromToken);
  }

  /**
   * This method fetches the user details by token.
   *
   * @param token This is the token of a logged in user.
   * @return user This is the user entity containing user details.
   */
  @Override
  public UserEntity getUserByToken(final String token) {
    final String emailFromToken = jwtTokenProvider.getUsernameFromToken(token);
    final UserEntity user = iuserRepository.findByEmail(emailFromToken);
    if (user == null) {
      throw new ResourceNotFoundException(
          "User not found with email: " + emailFromToken);
    }
    return user;
  }

  /**
   * This is a service method to fetch the image of the user in byte array.
   *
   * @param userId The id associated with the user.
   * @return byte[] The return type is the image in byte array.
   */
  @Override
  public byte[] fetchImageOfUser(final String userId) throws ImageException {

    final Optional<UserEntity> userOptional = iuserRepository.findById(userId);
    UserEntity user;
    if (userOptional.isPresent()) {
      user = userOptional.get();
    } else {
      throw new ResourceNotFoundException("User not found with id: " + userId);
    }
    if (Objects.isNull(user.getImage()) || user.getImage().length == 0) {
      throw new ResourceNotFoundException(
          "Image not found for the user with id: " + userId);
    }
    return user.getImage();
  }

  /**
   * This method authenticates user by verifying user given password with the
   * password obtained from database by email.
   *
   * @param user     User data obtained from database to check for authentication.
   * @param password Password given by user while logging in.
   * @throws BadCredentialsException thrown if incase of invalid credentials
   */
  private void authenticateUser(final UserEntity user, final String password) {

    if (!bcryptPasswordEncoder.matches(password, user.getPassword())) {
      throw new BadCredentialsException("Invalid credentials!");
    }
  }

  /**
   * This method is used to delete the image of the user by setting the image
   * attribute associated with the user.
   *
   * @param token This is the authentication token.
   */
  @Override
  public boolean deleteImage(final String token) {

    final UserEntity user = getUserByToken(token);
    if (user == null) {
      throw new ResourceNotFoundException("User not found with the token!");
    } else if (Objects.isNull(user.getImage()) || user.getImage().length == 0) {
      throw new ResourceNotFoundException(
          "Image not found for the user with id: " + user.getUserId());
    }
    user.setImage(null);
    return Objects.nonNull(iuserRepository.save(user));
  }

  /**
   * This method is used to fetch the logged in user. This is a method
   * overriding the functionality from userdetails interface extended by the
   * iuserservice interface.
   *
   * @param token This is the authentication token.
   */
  @Override
  public UserDto fetchLoggedInUser(final String token) {

    final UserEntity user = getUserByToken(token);
    return userUtility.convertToUserDto(user);
  }
}
