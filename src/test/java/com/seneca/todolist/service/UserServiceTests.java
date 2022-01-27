package com.seneca.todolist.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import com.seneca.todolist.config.JwtTokenProvider;
import com.seneca.todolist.entity.UserEntity;
import com.seneca.todolist.exception.ImageException;
import com.seneca.todolist.model.LoginRequestDto;
import com.seneca.todolist.model.UserDto;
import com.seneca.todolist.model.UserResponseDto;
import com.seneca.todolist.model.UserUpdateDto;
import com.seneca.todolist.repository.IJwtBlockListRepository;
import com.seneca.todolist.repository.UserRepository;
import com.seneca.todolist.utility.UserUtility;
import java.util.Objects;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

  @InjectMocks
  private UserServiceImpl userServiceImpl;

  @Mock
  private UserRepository iuserRepository;

  @Mock
  private static PasswordEncoder bCryptPasswordEncoder;

  @Mock
  private UserUtility userUtility;

  @Mock
  private JwtTokenProvider jwtTokenProvider;
  
  @Mock
  public IJwtBlockListRepository iJwtBlockListRepository;

  private UserEntity user;
  private UserDto userDto;
  private UserUpdateDto userUpdateDto;

  private String email = "A@gmail.com";
  private String password = "A1@a";
  private String token = "eyJhb";

  @BeforeEach
  void setUp() {

    when(bCryptPasswordEncoder.encode(any(String.class))).thenReturn("A1@a");

    user = new UserEntity();
    user.setUserId("a1");
    user.setEmail(email);
    user.setPassword(bCryptPasswordEncoder.encode(password));
    userDto = new UserDto();
    userDto.setUserId("a1");
    userDto.setEmail(email);
    userDto.setPassword(password);
    userUpdateDto = new UserUpdateDto();
    userUpdateDto.setEmail(Optional.of(email));
    userUpdateDto.setPassword(Optional.of(password));

  }

  @Test
  void loginServiceTest() {

    when(jwtTokenProvider.generateToken(any(UserEntity.class))).thenReturn("eyJhb");
    when(userUtility.convertToUserDto(any(UserEntity.class))).thenReturn(userDto);
    when(bCryptPasswordEncoder.matches(any(String.class), any(String.class)))
        .thenReturn(true);
    when(iuserRepository.findByEmail(any(String.class))).thenReturn(user);

    UserResponseDto resp = userServiceImpl.loginUser(new LoginRequestDto(email, password));
    assertEquals("A@gmail.com", resp.getUserDTO().getEmail());
  }

  @Test
  void registerUser() {

    when(jwtTokenProvider.generateToken(any(UserEntity.class))).thenReturn("eyJhb");
    when(userUtility.convertToUserDto(any(UserEntity.class))).thenReturn(userDto);
    when(userUtility.convertToEntity(any(UserDto.class))).thenReturn(user);
    when(iuserRepository.save(any(UserEntity.class))).thenReturn(user);

    UserResponseDto user = userServiceImpl.registerUser(userDto);
    assertEquals("A@gmail.com", user.getUserDTO().getEmail());
  }

  @Test
  void findUserByEmailTest() {

    when(iuserRepository.findByEmail(any(String.class))).thenReturn(user);
    when(userUtility.convertToUserDto(any(UserEntity.class))).thenReturn(userDto);
    UserDto userDtoInternal = userServiceImpl.findUserByEmail(email);
    assertEquals(email, userDtoInternal.getEmail());
  }
  
  @Test
  void updateUserTest() {

    when(jwtTokenProvider.getUsernameFromToken(any(String.class))).thenReturn("A@gmail.com");
    when(iuserRepository.findByEmail(any(String.class))).thenReturn(user);
    doNothing().when(userUtility)
    .updateEntityWithDto(any(UserEntity.class), any(UserUpdateDto.class));
    when(userUtility.convertToUserDto(any(UserEntity.class))).thenReturn(userDto);
    when(iuserRepository.save(any(UserEntity.class))).thenReturn(user);
    
    UserDto userDtoInternal = userServiceImpl.updateUser(userUpdateDto, token);
    assertEquals(email, userDtoInternal.getEmail());
  }
  
  @Test
  void uploadImageTest() {

    when(jwtTokenProvider.getUsernameFromToken(any(String.class))).thenReturn("A@gmail.com");
    when(iuserRepository.findByEmail(any(String.class))).thenReturn(user);
    when(iuserRepository.save(any(UserEntity.class))).thenReturn(user);
    
    Boolean status = Objects.nonNull(userServiceImpl.uploadImage("DummyByteArray".getBytes(), token));
    assertEquals(true, status);
  }
  
  @Test
  void getUserByIdTest() {

    when(iuserRepository.getById(any(String.class))).thenReturn(user);
    when(userUtility.convertToUserDto(any(UserEntity.class))).thenReturn(userDto);
    
    UserDto userDtoInternal = userServiceImpl.getUserById(user.getUserId());
    assertEquals(user.getUserId(), userDtoInternal.getUserId());
  }
  
  @Test
  void fetchImageTest() throws ImageException {

    byte[] imageBytes = "DummyByteArray".getBytes();
    UserEntity userForImageTest = new UserEntity();
    userForImageTest.setUserId("a1");
    userForImageTest.setEmail(email);
    userForImageTest.setImage(imageBytes);
    when(iuserRepository.findById(any(String.class))).thenReturn(Optional.of(userForImageTest));
    
    assertEquals(imageBytes.length, userServiceImpl.fetchImageOfUser(email).length);
  }
  
  @Test
  void deleteImageTest() throws ImageException {
    
    UserEntity userForImageTest = new UserEntity();
    byte[] imageBytes = "DummyByteArray".getBytes();
    userForImageTest.setUserId(user.getUserId());
    userForImageTest.setEmail(user.getEmail());
    userForImageTest.setImage(imageBytes);
    
    when(jwtTokenProvider.getUsernameFromToken(any(String.class))).thenReturn("A@gmail.com");
    when(iuserRepository.findByEmail(any(String.class))).thenReturn(userForImageTest);
    when(iuserRepository.save(any(UserEntity.class))).thenReturn(user);
    
    Boolean status = Objects.nonNull(userServiceImpl.deleteImage(token));
    assertEquals(true, status);
  }
  
  @Test
  void fetchLoggedInUserTest() throws ImageException {

    when(jwtTokenProvider.getUsernameFromToken(any(String.class))).thenReturn("A@gmail.com");
    when(iuserRepository.findByEmail(any(String.class))).thenReturn(user);
    when(userUtility.convertToUserDto(any(UserEntity.class))).thenReturn(userDto);
    
    assertEquals(userDto.getEmail(), userServiceImpl.fetchLoggedInUser(token).getEmail());
  }

}
