package com.seneca.todolist.utility;

import com.seneca.todolist.entity.UserEntity;
import com.seneca.todolist.model.UserDto;
import com.seneca.todolist.model.UserUpdateDto;
import java.util.Optional;
import org.springframework.stereotype.Component;

/**
 * This is the user utility class for all user related tasks.
 *
 * @author hemanth.nagireddy
 *
 */
@Component
public class UserUtility {

  /**
   * This is the method to convert an entity to user dto.
   *
   * @param user This is the user entity which is to be converted.
   * @return userDto This is the userDto returned.
   */
  public UserDto convertToUserDto(final UserEntity user) {

    UserDto userDto = new UserDto();
    userDto.setUserId(user.getUserId());
    userDto.setName(user.getName());
    userDto.setAge(user.getAge());
    userDto.setPassword(user.getPassword());
    userDto.setEmail(user.getEmail());
    userDto.setCreatedAt(user.getCreatedAt());
    userDto.setUpdatedAt(user.getUpdatedAt());
    return userDto;

  }

  /**
   * This is the method to convert a dto to user entity.
   *
   * @param user This is the userDto which is to be converted.
   * @return userEntity This is the userEntity returned.
   */
  public UserEntity convertToEntity(final UserDto user) {

    UserEntity userEntity = new UserEntity();
    userEntity.setUserId(user.getUserId());
    userEntity.setName(user.getName());
    userEntity.setAge(user.getAge());
    userEntity.setPassword(user.getPassword());
    userEntity.setEmail(user.getEmail());
    userEntity.setCreatedAt(user.getCreatedAt());
    userEntity.setUpdatedAt(user.getUpdatedAt());
    return userEntity;

  }

  /**
   * This is the method to update the entity with dto.
   *
   * @param userDto This is the userDto from which user entity is updated.
   * @param user    This is the entity which is to be updated.
   */
  public void updateEntityWithDto(final UserEntity user, 
      final UserUpdateDto userDto) {

    if (Optional.ofNullable(userDto.getName()).isPresent()) {
      user.setName(userDto.getName().get());
    }
    if (Optional.ofNullable(userDto.getEmail()).isPresent()) {
      user.setEmail(userDto.getEmail().get());
    }
    if (Optional.ofNullable(userDto.getPassword()).isPresent()) {
      user.setPassword(userDto.getPassword().get());
    }
    if (Optional.ofNullable(userDto.getAge()).isPresent()) {
      user.setAge(userDto.getAge().get());
    }
  }

}
