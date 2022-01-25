package com.seneca.todolist.controller;

import com.seneca.todolist.exception.ImageException;
import com.seneca.todolist.exception.InvalidRequestException;
import com.seneca.todolist.model.UserDto;
import com.seneca.todolist.model.UserUpdateDto;
import com.seneca.todolist.service.UserService;
import java.io.IOException;
import java.util.Objects;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * This controller class is responsible for users actions on the user details
 * manipulation.
 *
 * @author hemanth.nagireddy
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

  /**
   * This specifies the content type of the image if the image is png.
   */
  private static final String PNG_TYPE = "image/png";

  /**
   * This specifies the content type of the image if the image is jpeg.
   */
  private static final String JPEG_TYPE = "image/jpeg";

  /**
   * Maximum limit of the file which can be uploaded is specified through this
   * variable.
   */
  @Value("${filesize.limit.in.bytes}")
  private Integer fileSizeLimit;

  /**
   * UserService interface injection into this class.
   */
  @Autowired
  private UserService iuserService;

  /**
   * This method is used to fetch the details of logged in user.
   *
   * @param token This is the authentication token.
   * @return userDto This is the dto containing details of logged in user.
   */
  @GetMapping("/me")
  public ResponseEntity<UserDto> fetchLoggedInUser(
      @RequestHeader("Authorization") final String token) {

    final UserDto loggedInUser = iuserService.fetchLoggedInUser(token);
    return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
  }

  /**
   * This method is used to update the logged in user. The logged in user must
   * be found from the token.
   *
   * @param token         This is the authentication token.
   * @param user This request contains the details to be updated in
   *                      database.
   * @return userDto This is the dto with updated user details.
   */
  @PutMapping("/me")
  public ResponseEntity<UserDto> updateUser(
      @RequestHeader("Authorization") final String token,
      @Valid @RequestBody final UserUpdateDto user) {

    final UserDto updatedUser = iuserService.updateUser(user, token);
    return new ResponseEntity<>(updatedUser, HttpStatus.OK);
  }

  /**
   * This method deletes the logged in user from database using the request
   * header 'Authorization'.
   *
   * @param token This is the authentication token.
   * @return integer Integer denoting the success response.
   */
  @DeleteMapping("/me")
  public int deleteUser(@RequestHeader("Authorization") final String token) {
    return iuserService.deleteUser(token);
  }

  /**
   * This method is used for uploading the image of user.
   *
   * @param token This is the authentication token.
   * @param file  This file is taken as a Multipart file to accommodate
   *              jpg,png,etc. file formats.
   * @return response with status code
   */
  @PostMapping("me/avatar")
  public ResponseEntity<Object> uploadImageOfUser(
      @RequestHeader("Authorization") final String token,
      @RequestParam("imageFile") final MultipartFile file)
      throws ImageException, IOException {

    validateFileInput(file);
    final boolean uploadStatus = iuserService.uploadImage(file.getBytes(),
        token);
    return uploadStatus ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
        : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * This method is to fetch the image uploaded by the user.
   *
   * @param userId This corresponds to the user id.
   * @return imageBytes Returns the byte array of the user image.
   * @throws ImageException thrown in case of violation of some custom rules related to the image
   *                        attributes.
   */
  @GetMapping(value = "{id}/avatar", produces = MediaType.IMAGE_JPEG_VALUE)
  public ResponseEntity<Object> fetchImageOfUser(
      @PathVariable("id") final String userId)
      throws ImageException {

    if (!StringUtils.hasText(userId.trim())) {
      throw new InvalidRequestException("User id cannot be null or empty.");
    }
    final byte[] imageBytes = iuserService.fetchImageOfUser(userId);
    return new ResponseEntity<>(imageBytes, HttpStatus.OK);
  }

  /**
   * This method deletes the image uploaded by the logged in user from database.
   *
   * @param token This is the authentication token.
   * @return status This is the http response status code.
   */
  @DeleteMapping("/me/avatar")
  public ResponseEntity<Object> deleteImage(
      @RequestHeader("Authorization") final String token) {
    boolean status = iuserService.deleteImage(token);
    return status ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
        : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * This is a method to specify the validations for the input file take in the
   * api: uploadImageOfUser.
   * 
   * @param file
   * @throws ImageException
   */
  private void validateFileInput(final MultipartFile file)
      throws ImageException {

    if (Objects.isNull(file) || file.isEmpty()) {
      throw new ImageException(
          "Invalid file! File may be empty or content type of the file is not correct.");
    } else if (Objects.nonNull(file.getContentType())
        && !(file.getContentType().equalsIgnoreCase(PNG_TYPE)
            || file.getContentType().equalsIgnoreCase(JPEG_TYPE))) {
      throw new ImageException("Please upload image of type png or jpeg!");
    } else if (file.getSize() > fileSizeLimit) {
      throw new ImageException("File size is expected to be under 10 MB");
    }
  }
}
