package com.seneca.todolist.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import com.seneca.todolist.config.JwtTokenProvider;
import com.seneca.todolist.entity.TaskEntity;
import com.seneca.todolist.entity.UserEntity;
import com.seneca.todolist.model.TaskInfoDto;
import com.seneca.todolist.model.TaskInfoUpdateDto;
import com.seneca.todolist.model.TaskListResponseDto;
import com.seneca.todolist.model.TaskResponseDto;
import com.seneca.todolist.repository.ITaskRepository;
import com.seneca.todolist.utility.TaskUtility;
import java.util.Arrays;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTests {

  @InjectMocks
  private TaskServiceImpl taskServiceImpl;
  
  @Mock
  private UserService iuserService;
  
  @Mock
  private ITaskRepository itaskRepository;
  
  @Mock
  private TaskUtility taskUtility;
  
  @Mock
  private JwtTokenProvider jwtTokenProvider;
  
  private String token;
  private UserEntity user;
  private TaskInfoDto taskInfoDto;
  private TaskInfoDto taskInfoDtoSecond;
  private TaskInfoUpdateDto taskUpdateDto;
  private TaskEntity taskEntity;
  private TaskEntity taskEntitySecond;
  private TaskResponseDto taskResponse;
  private TaskListResponseDto tasksResponse;
  
  @BeforeEach
  public void setUp() {
    
    token = "eyJhb" ;
    user = new UserEntity();
    user.setUserId("a1");
    user.setEmail("A@gmail.com");
    
    taskInfoDto = new TaskInfoDto();
    taskInfoDto.setCompleted(false);
    taskInfoDto.setDescription("Reading books");
    taskInfoDto.setTaskId("a1");
    taskInfoDto.setOwner("a1");
    
    taskInfoDtoSecond = new TaskInfoDto();
    taskInfoDtoSecond.setCompleted(true);
    taskInfoDtoSecond.setDescription("Office work");
    taskInfoDtoSecond.setTaskId("a2");
    taskInfoDtoSecond.setOwner("a1");
    
    taskUpdateDto = new TaskInfoUpdateDto();
    taskUpdateDto.setCompleted(false);
    taskUpdateDto.setDescription("Reading books");
    
    taskEntity = new TaskEntity();
    taskEntity.setCompleted(false);
    taskEntity.setDescription("Reading books");
    taskEntity.setTaskId("a1");
    taskEntity.setUser(user);
    
    taskEntitySecond = new TaskEntity();
    taskEntitySecond.setCompleted(false);
    taskEntitySecond.setDescription("Office work");
    taskEntitySecond.setTaskId("a2");
    taskEntitySecond.setUser(user);
    
    taskResponse = new TaskResponseDto(true, taskInfoDto);
    
    tasksResponse = new TaskListResponseDto(2, Arrays.asList(taskInfoDto, taskInfoDtoSecond));
    
    when(iuserService.getUserByToken(any(String.class))).thenReturn(user);
  }
  
  @Test
  public void addTaskTest() {
    
    when(taskUtility.convertToEntity(any(TaskInfoDto.class))).thenReturn(taskEntity);
    when(taskUtility.convertToDto(any(TaskEntity.class))).thenReturn(taskInfoDto);
    when(itaskRepository.save(any(TaskEntity.class))).thenReturn(taskEntity);
    
    TaskResponseDto resp = taskServiceImpl.addTask(taskInfoDto, token);
    assertEquals(true, resp.getStatus().booleanValue());
  }
  
  @Test
  public void getAllTasksTest() {
    
    when(itaskRepository.findAllByUser(any(UserEntity.class)))
        .thenReturn(Optional.of(Arrays.asList(taskEntity, taskEntitySecond)));
    when(taskUtility.convertToDtoList(Arrays.asList(taskEntity, taskEntitySecond)))
        .thenReturn(tasksResponse.getTaskInfoDtos());
    
    TaskListResponseDto respDto = taskServiceImpl.getAllTasks(token);
    assertEquals(2, respDto.getCount());
  }
  
  @Test
  public void getTaskByIdTest() {
    
    when(itaskRepository
        .findByUserAndTaskId(any(UserEntity.class), any(String.class)))
        .thenReturn(Optional.of(taskEntity));
    when(taskUtility.convertToDto(any(TaskEntity.class))).thenReturn(taskInfoDto);
    
    TaskResponseDto taskResp = taskServiceImpl.getTaskById("a1", token);
    assertEquals("a1", taskResp.getTaskInfo().getTaskId());
  }
  
  @Test
  public void getAllTasksByCompletedTest() {
    
    when(itaskRepository.findAllByUserAndCompletedStatus(any(UserEntity.class), any(Boolean.class)))
        .thenReturn(Optional.of(Arrays.asList(taskEntitySecond)));
    when(taskUtility.convertToDtoList(Arrays.asList(taskEntitySecond)))
        .thenReturn(Arrays.asList(taskInfoDtoSecond));
    
    TaskListResponseDto respDto = taskServiceImpl.getAllTasksByCompletedStatus(true, token);
    assertEquals(1, respDto.getCount());
  }
  
  @Test
  public void updateTaskByIdTest() {
    
    when(itaskRepository.findByUserAndTaskId(any(UserEntity.class), any(String.class)))
        .thenReturn(Optional.of(taskEntity));
    when(taskUtility.convertToDto(any(TaskEntity.class))).thenReturn(taskInfoDto);
    doNothing().when(taskUtility)
      .updateEntityWithDto(any(TaskEntity.class), any(TaskInfoUpdateDto.class));
    when(itaskRepository.save(any(TaskEntity.class))).thenReturn(taskEntity);
    
    TaskResponseDto resp = taskServiceImpl.updateTaskById(taskUpdateDto, "a1", token);
    assertEquals(true, resp.getStatus().booleanValue());
  }
  
  @Test
  public void getPaginatedTasksTest() {
    
    when(itaskRepository
        .findAllByPagination(any(UserEntity.class), any(Integer.class), any(Integer.class)))
        .thenReturn(Optional.of(Arrays.asList(taskEntitySecond)));
    when(taskUtility.convertToDtoList(Arrays.asList(taskEntitySecond)))
        .thenReturn(Arrays.asList(taskInfoDtoSecond));
    
    TaskListResponseDto respDto = taskServiceImpl.getPaginatedTasks(token, 1, 1);
    assertEquals(1, respDto.getCount());
  }
  
  @Test
  public void deleteTaskByIdTest() {
    
    doNothing()
        .when(itaskRepository)
        .deleteByUserAndTaskId(any(UserEntity.class), any(String.class));
   
    assertEquals(true, taskServiceImpl.deleteTaskById(token, "a1"));
  }
  
}
