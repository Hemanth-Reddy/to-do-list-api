package com.seneca.todolist.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.seneca.todolist.config.JwtTokenProvider;
import com.seneca.todolist.config.ToDoAuthenticationEntryPoint;
import com.seneca.todolist.model.LoginRequestDto;
import com.seneca.todolist.model.UserDto;
import com.seneca.todolist.model.UserResponseDto;
import com.seneca.todolist.service.UserService;

@WebMvcTest(UserAuthenticationController.class)
class UserAuthenticationControllerTests {

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

  @BeforeAll
  static void intialisationsBeforeTests() {
    token = "eyJhb";
    user = new UserDto("Swarupa", 45,
        "$2a$10$e4brDxSjGZdfzyALiW52LuTEUfAPyl.m14.dUkKbEStTq6rQMnny6",
        "swarupa.nagireddy@gmail.com", null, null);
    userResponse = new UserResponseDto(user, token);

  }

  @Test
  void registerUser_ageValidation() throws Exception {

    String reqJsonAsStr = "{\"name\":\"Swarupa\",\"age\":5,\"password\":\"\",\"email\":\"swarupa.nagireddy@gmail.com\"}";
    RequestBuilder request = MockMvcRequestBuilders.post("/user/register")
        .accept(MediaType.APPLICATION_JSON).content(reqJsonAsStr)
        .contentType(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(request).andReturn();
    assertEquals(400, result.getResponse().getStatus());
    assertTrue(result.getResponse().getContentAsString()
        .contains("MethodArgumentNotValidException"));
  }

  @Test
  void registerUser_passwordValidation() throws Exception {

    String reqJsonAsStr = "{\"name\":\"Swarupa\",\"age\":5,\"password\":\"\",\"email\":\"swarupa.nagireddy@gmail.com\"}";
    RequestBuilder request = MockMvcRequestBuilders.post("/user/register")
        .accept(MediaType.APPLICATION_JSON).content(reqJsonAsStr)
        .contentType(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(request).andReturn();
    assertEquals(400, result.getResponse().getStatus());
    assertTrue(result.getResponse().getContentAsString()
        .contains("MethodArgumentNotValidException"));
  }

  @Test
  void registerUser_functionality() throws Exception {

    String reqJsonAsStr = "{\"name\":\"Swarupa\",\"age\":45,\"password\":\"STuv@123\",\"email\":\"swarupa.nagireddy@gmail.com\"}";

    when(iuserService.registerUser(any(UserDto.class))).thenReturn(userResponse);
    RequestBuilder request = MockMvcRequestBuilders.post("/user/register")
        .accept(MediaType.APPLICATION_JSON).content(reqJsonAsStr)
        .contentType(MediaType.APPLICATION_JSON);
    MvcResult result = mockMvc.perform(request).andReturn();

    assertEquals(201, result.getResponse().getStatus());
    String responseStr = result.getResponse().getContentAsString();
    JSONAssert.assertEquals(
        "{token:eyJhb,userDTO:{name:Swarupa,age:45,email:swarupa.nagireddy@gmail.com}}",
        responseStr, false);
  }
  // .header("Authorization", "eyJhb")

  @Test
  void login_validations() throws Exception {

    String reqJsonAsStr = "{\"email\":\"swarupa.nagireddy@gmail.com\",\"password\":\"\"}";
    RequestBuilder request = MockMvcRequestBuilders.post("/user/login")
        .accept(MediaType.APPLICATION_JSON).content(reqJsonAsStr)
        .contentType(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(request).andReturn();
    assertEquals(400, result.getResponse().getStatus());
    assertTrue(result.getResponse().getContentAsString()
        .contains("MethodArgumentNotValidException"));
  }

  @Test
  void login_functionality() throws Exception {

    String reqJsonAsStr = "{\"email\":\"swarupa.nagireddy@gmail.com\",\"password\":\"APts@123\"}";
    when(iuserService.loginUser(any(LoginRequestDto.class))).thenReturn(userResponse);
    RequestBuilder request = MockMvcRequestBuilders.post("/user/login")
        .accept(MediaType.APPLICATION_JSON).content(reqJsonAsStr)
        .contentType(MediaType.APPLICATION_JSON);

    mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().json(
        "{token:eyJhb,userDTO:{name:Swarupa,age:45,email:swarupa.nagireddy@gmail.com}}"))
        .andReturn();
  }

}
