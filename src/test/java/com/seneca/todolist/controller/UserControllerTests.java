package com.seneca.todolist.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seneca.todolist.config.ToDoAuthenticationEntryPoint;
import com.seneca.todolist.config.JwtTokenProvider;
import com.seneca.todolist.model.UserDto;
import com.seneca.todolist.model.UserResponseDto;
import com.seneca.todolist.model.UserUpdateDto;
import com.seneca.todolist.service.UserService;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(UserController.class)
public class UserControllerTests {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private JwtTokenProvider jwtTokenProvider;

  @MockBean
  private UserService iuserService;

  @MockBean
  private ToDoAuthenticationEntryPoint toDoAuthenticationEntryPoint;

  private static UserDto user;
  private static UserResponseDto userResponse;
  private static String token;
  private ObjectMapper mapper = new ObjectMapper();

  @BeforeAll
  static void intialisationsBeforeTests() {
    token = "eyJhbGciOiJIUzUxMiJ9"
        + ".eyJzdWIiOiJBa2hpbC5uYWdpcmVkZHlAZ21haWwuY29tIiwiZXhwI"
        + "joxNjQyMDI3NDYxLCJpYXQiOjE2NDIwMDk0NjF9"
        + ".xrLlG90Xc725Rm7YMj-f-X4cR0Fmp81DBYkf7IB5akMy-vk1DMtWNOzuegwo_-ReRKoIi84XzULxlpJ55Ya8HQ";
    user = new UserDto("Swarupa", 26, "Swarupa@123", "swarupa.nagireddy@gmail.com", null,
        null);
    userResponse = new UserResponseDto(user, token);
  }

  @Test
  void fetchLoggedInUserTest() throws Exception {
    when(iuserService.fetchLoggedInUser(anyString())).thenReturn(user);
    RequestBuilder request = MockMvcRequestBuilders.get("/user/me")
        .header("Authorization", "eyJhb").accept(MediaType.APPLICATION_JSON);
    mockMvc.perform(request).andExpect(status().isOk())
        .andExpect(content().json(mapper.writeValueAsString(user)));
  }

  @Test
  void updateUserTest() throws Exception {

    user.setName("Hemanth Reddy");
    user.setAge(24);
    user.setEmail("test123@gmail.com");
    user.setPassword("123Test@");

    when(iuserService.updateUser(any(UserUpdateDto.class), anyString())).thenReturn(user);

    String requestJson = "{\"name\":\"Hemanth Reddy\",\"age\":24,\"password\":\"123Test@\","
        + "\"email\":\"test123@gmail.com\"}"; // userupdatedto can be prepared and used with 
    // objectmapper but due to optional properties in it, it becomes necessary to add a
    // dependency(jackson-datatype-jdk8) for mapper to work
    // accurately with optional datatypes.
    
    RequestBuilder request = MockMvcRequestBuilders.put("/user/me")
        .accept(MediaType.APPLICATION_JSON).header("Authorization", "Bearer eyJhb")
        .contentType(MediaType.APPLICATION_JSON).content(requestJson);
    mockMvc.perform(request).andExpect(status().isOk())
        .andExpect(content().json(mapper.writeValueAsString(user))).andReturn();

  }

  @Test
  void deleteUserTest() throws Exception {
    when(iuserService.deleteUser(anyString())).thenReturn(1);
    RequestBuilder request = MockMvcRequestBuilders.delete("/user/me")
        .header("Authorization", "eyJhb").accept(MediaType.APPLICATION_JSON);
    mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().json("1"));
  }

  @Test
  void uploadImageTest() throws Exception {
    MockMultipartFile file = new MockMultipartFile("imageFile", "sample image.png",
        "image/png", "dummyByteArray".getBytes());
    when(iuserService.uploadImage(any(byte[].class), any(String.class))).thenReturn(true);
    MockHttpServletRequestBuilder request = MockMvcRequestBuilders
        .multipart("/user/me/avatar").file(file)
        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token);
    MvcResult result = mockMvc.perform(request).andReturn();
    assertEquals(204, result.getResponse().getStatus());
  }
  
  @Test
  void fetchImageTest() throws Exception {
    when(iuserService.fetchImageOfUser(any(String.class)))
        .thenReturn("DummyByteArrayForImage".getBytes());
    RequestBuilder request = MockMvcRequestBuilders.get("/user/a1b2c3/avatar")
        .header("Authorization", "Bearer eyJhb").contentType(MediaType.APPLICATION_JSON);
    mockMvc.perform(request).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.IMAGE_JPEG_VALUE)).andReturn();
  }
  
  @Test
  void deleteImageTest() throws Exception {
    when(iuserService.deleteImage(any(String.class))).thenReturn(true);
    RequestBuilder request = MockMvcRequestBuilders.delete("/user/me/avatar")
        .header("Authorization", "eyJhb").accept(MediaType.APPLICATION_JSON);
    mockMvc.perform(request).andExpect(status().isNoContent());
  }
  
  @Test
  void deleteImageFailTest() throws Exception {
    when(iuserService.deleteImage(any(String.class))).thenReturn(false);
    RequestBuilder request = MockMvcRequestBuilders.delete("/user/me/avatar")
        .header("Authorization", "eyJhb").accept(MediaType.APPLICATION_JSON);
    mockMvc.perform(request).andExpect(status().is(500));
  }

}
