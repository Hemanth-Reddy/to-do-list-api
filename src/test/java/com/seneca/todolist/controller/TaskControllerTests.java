package com.seneca.todolist.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.seneca.todolist.config.JwtTokenProvider;
import com.seneca.todolist.config.ToDoAuthenticationEntryPoint;
import com.seneca.todolist.model.TaskInfoDto;
import com.seneca.todolist.model.TaskInfoUpdateDto;
import com.seneca.todolist.model.TaskListResponseDto;
import com.seneca.todolist.model.TaskResponseDto;
import com.seneca.todolist.service.TaskService;
import com.seneca.todolist.service.UserService;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(TaskController.class)
class TaskControllerTests {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private TaskService itaskService;

  @MockBean
  private JwtTokenProvider jwtTokenProvider;

  @MockBean
  private UserService iuserService;

  @MockBean
  private ToDoAuthenticationEntryPoint toDoAuthenticationEntryPoint;

  private static TaskInfoDto taskInfoDto;
  private static TaskResponseDto taskResponseDto;
  private static TaskListResponseDto taskListResponseDto;

  @BeforeAll
  static void initialisations() {
    taskInfoDto = new TaskInfoDto();
    taskInfoDto.setDescription("Reading book");
    taskInfoDto.setCompleted(false);
  }

  @Test
  void addTask_validation() throws Exception {

    taskResponseDto = new TaskResponseDto(true, taskInfoDto);
    when(itaskService.addTask(any(TaskInfoDto.class), any(String.class)))
        .thenReturn(taskResponseDto);
    RequestBuilder request = MockMvcRequestBuilders.post("/task")
        .accept(MediaType.APPLICATION_JSON).header("Authorization", "Bearer eyJhb")
        .content("{\"description\": \"Reading book\"}")
        .contentType(MediaType.APPLICATION_JSON);
    MvcResult result = mockMvc.perform(request).andReturn();

    assertEquals(201, result.getResponse().getStatus());
    String responseStr = result.getResponse().getContentAsString();
    System.out.println("===>" + responseStr);
    JSONAssert.assertEquals(
        "{\"status\":true,\"taskInfo\":{\"description\":\"Reading book\",\"completed\":false}}",
        responseStr, false);
  }

  @Test
  void getAllTasksTest() throws Exception {

    List<TaskInfoDto> tasks = Arrays.asList(
        new TaskInfoDto("1", "reading book", false, "a1", null, null),
        new TaskInfoDto("2", "work", false, "a2", null, null));
    taskListResponseDto = new TaskListResponseDto(2, tasks);
    when(itaskService.getAllTasks(any(String.class))).thenReturn(taskListResponseDto);
    RequestBuilder request = MockMvcRequestBuilders.get("/task")
        .accept(MediaType.APPLICATION_JSON).header("Authorization", "Bearer eyJhb");
    MvcResult result = mockMvc.perform(request).andReturn();

    assertEquals(200, result.getResponse().getStatus());
    String responseStr = result.getResponse().getContentAsString();
    JSONAssert.assertEquals(
        "{\"count\":2,\"taskInfoDtos\":"
        + "[{\"taskId\":\"1\",\"description\":\"reading book\",\"completed\":false},"
        + "{\"taskId\":\"2\",\"description\":\"work\",\"completed\":false}]}",
        responseStr, false);
  }

  @Test
  void getTaskByIdTest() throws Exception {

    taskResponseDto = new TaskResponseDto(true, taskInfoDto);
    when(itaskService.getTaskById(any(String.class), any(String.class)))
        .thenReturn(taskResponseDto);
    RequestBuilder request = MockMvcRequestBuilders.get("/task/a1b2c3")
        .accept(MediaType.APPLICATION_JSON).header("Authorization", "Bearer eyJhb");
    MvcResult result = mockMvc.perform(request).andReturn();

    assertEquals(200, result.getResponse().getStatus());
    String responseStr = result.getResponse().getContentAsString();
    JSONAssert.assertEquals(
        "{\"status\":true,\"taskInfo\":{\"description\":\"Reading book\",\"completed\":false}}",
        responseStr, false);
  }

  @Test
  void getTaskByCompletedTest() throws Exception {

    List<TaskInfoDto> tasks = Arrays.asList(
        new TaskInfoDto("1", "reading book", false, "a1", null, null),
        new TaskInfoDto("2", "work", false, "a2", null, null));
    taskListResponseDto = new TaskListResponseDto(2, tasks);
    when(itaskService.getAllTasksByCompletedStatus(any(Boolean.class), any(String.class)))
        .thenReturn(taskListResponseDto);
    RequestBuilder request = MockMvcRequestBuilders.get("/task")
        .header("Authorization", "Bearer eyJhb").param("completed", "false");
    MvcResult result = mockMvc.perform(request).andReturn();

    assertEquals(200, result.getResponse().getStatus());
    String responseStr = result.getResponse().getContentAsString();
    JSONAssert.assertEquals(
        "{\"count\":2,\"taskInfoDtos\":"
        + "[{\"taskId\":\"1\",\"description\":\"reading book\",\"completed\":false},"
        + "{\"taskId\":\"2\",\"description\":\"work\",\"completed\":false}]}",
        responseStr, false);
  }

  @Test
  void updateTaskByIdTest() throws Exception {

    TaskInfoUpdateDto updateTask = new TaskInfoUpdateDto();
    updateTask.setDescription("Reading magazine");
    updateTask.setCompleted(false);
    taskResponseDto = new TaskResponseDto(true,
        new TaskInfoDto(null, updateTask.getDescription().get(),
            updateTask.getCompleted().get(), null, null, null));
    when(itaskService.updateTaskById(any(TaskInfoUpdateDto.class), any(String.class),
        any(String.class))).thenReturn(taskResponseDto);
    RequestBuilder request = MockMvcRequestBuilders.put("/task/a1b2c3")
        .accept(MediaType.APPLICATION_JSON).header("Authorization", "Bearer eyJhb")
        .content("{\"description\": \"Reading magazine\"}")
        .contentType(MediaType.APPLICATION_JSON);
    MvcResult result = mockMvc.perform(request).andReturn();

    assertEquals(200, result.getResponse().getStatus());
    String responseStr = result.getResponse().getContentAsString();
    JSONAssert.assertEquals(
        "{\"status\":true,\"taskInfo\":{\"description\":\"Reading magazine\",\"completed\":false}}",
        responseStr, false);

  }

  @Test
  void updateTaskByIdTestWithBlankDesc() throws Exception {

    taskInfoDto.setDescription("Reading magazine");
    taskResponseDto = new TaskResponseDto(true, taskInfoDto);
    when(itaskService.updateTaskById(any(TaskInfoUpdateDto.class), any(String.class),
        any(String.class))).thenReturn(taskResponseDto);
    RequestBuilder request = MockMvcRequestBuilders.put("/task/a1b2c3")
        .accept(MediaType.APPLICATION_JSON).header("Authorization", "Bearer eyJhb")
        .content("{\"description\": \"\"}").contentType(MediaType.APPLICATION_JSON);
    MvcResult result = mockMvc.perform(request).andReturn();
    /*
     * assertThrows(MethodArgumentNotValidException.class, () -> {
     * mockMvc.perform(request).andReturn(); });
     */
    assertEquals(400, result.getResponse().getStatus());
    assertTrue(result.getResponse().getContentAsString()
        .contains("MethodArgumentNotValidException"));
  }

  @Test
  void getTaskByPaginationTest() throws Exception {

    List<TaskInfoDto> tasks = Arrays.asList(
        new TaskInfoDto("1", "reading book", false, "a1", null, null),
        new TaskInfoDto("2", "work", false, "a2", null, null));
    taskListResponseDto = new TaskListResponseDto(2, tasks);
    when(itaskService.getPaginatedTasks(any(String.class), any(Integer.class),
        any(Integer.class))).thenReturn(taskListResponseDto);
    RequestBuilder request = MockMvcRequestBuilders.get("/task")
        .accept(MediaType.APPLICATION_JSON).header("Authorization", "Bearer eyJhb")
        .param("limit", "2").param("skip", "0");
    MvcResult result = mockMvc.perform(request).andReturn();

    assertEquals(200, result.getResponse().getStatus());
    String responseStr = result.getResponse().getContentAsString();
    JSONAssert.assertEquals(
        "{\"count\":2,\"taskInfoDtos\":"
        + "[{\"taskId\":\"1\",\"description\":\"reading book\",\"completed\":false},"
        + "{\"taskId\":\"2\",\"description\":\"work\",\"completed\":false}]}",
        responseStr, false);
  }

  @Test
  void deleteTaskByIdTest() throws Exception {

    when(itaskService.deleteTaskById(any(String.class), any(String.class))).thenReturn(true);
    RequestBuilder request = MockMvcRequestBuilders.delete("/task/a1b2c3")
        .accept(MediaType.APPLICATION_JSON).header("Authorization", "Bearer eyJhb");
    MvcResult result = mockMvc.perform(request).andExpect(status().isNoContent())
        .andReturn();

    assertEquals(204, result.getResponse().getStatus());
    verify(itaskService, times(1)).deleteTaskById("Bearer eyJhb", "a1b2c3");
  }
}
