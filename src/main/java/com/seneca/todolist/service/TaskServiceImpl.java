package com.seneca.todolist.service;

import com.seneca.todolist.entity.TaskEntity;
import com.seneca.todolist.entity.UserEntity;
import com.seneca.todolist.model.TaskInfoDto;
import com.seneca.todolist.model.TaskInfoUpdateDto;
import com.seneca.todolist.model.TaskListResponseDto;
import com.seneca.todolist.model.TaskResponseDto;
import com.seneca.todolist.repository.ITaskRepository;
import com.seneca.todolist.utility.TaskUtility;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class is responsible for interacting with data layer of the task
 * resource.
 *
 * @author hemanth.nagireddy
 */
@Service
public class TaskServiceImpl implements TaskService {

  /**
   * Jpa repository for task related info.
   */
  @Autowired
  private ITaskRepository itaskRepository;

  /**
   * User service for fetching user details as and when required.
   */
  @Autowired
  private UserService iuserService;

  /**
   * Utility class for task related dtos and entities.
   */
  @Autowired
  private TaskUtility taskUtility;

  /**
   * This method is to add new task to database.
   *
   * @param taskInfoDto Takes the task info given by user.
   * @param token       This is the jwt token to identify the user logged in and
   *                    to add the task in his/her name.
   * @return TaskResponseDto This is the updated task info.
   */
  @Override
  public TaskResponseDto addTask(final TaskInfoDto taskInfoDto,
      final String token) {

    final UserEntity user = iuserService.getUserByToken(token);
    final TaskEntity taskInfo = taskUtility.convertToEntity(taskInfoDto);
    taskInfo.setUser(user);
    final TaskInfoDto updatedTask = taskUtility
        .convertToDto(itaskRepository.save(taskInfo));
    return new TaskResponseDto(true, updatedTask);
  }

  /**
   * This method is to retrieve all the tasks assigned to the logged in user.
   *
   * @param token This is the jwt token to identify the logged in user.
   * @return TaskListResponseDto This contains all the tasks pertaining to the
   *         user.
   */
  @Override
  public TaskListResponseDto getAllTasks(final String token) {

    final UserEntity user = iuserService.getUserByToken(token);
    final Optional<List<TaskEntity>> optionalTasks = itaskRepository
        .findAllByUser(user);
    List<TaskEntity> taskEntities;
    TaskListResponseDto tasksRespDto;
    if (optionalTasks.isPresent()) {
      taskEntities = optionalTasks.get();
      final List<TaskInfoDto> taskInfoDtos = taskUtility
          .convertToDtoList(taskEntities);
      tasksRespDto = new TaskListResponseDto(taskInfoDtos.size(), taskInfoDtos);
    } else {
      tasksRespDto = new TaskListResponseDto("No tasks found");
    }
    return tasksRespDto;

  }

  /**
   * This method is to retrieve the task with the given task id.
   *
   * @param token This is the jwt token to identify the logged in user.
   * @return TaskResponseDto This contains task information requested by task
   *         id.
   */
  @Override
  public TaskResponseDto getTaskById(final String taskId, final String token) {

    final UserEntity user = iuserService.getUserByToken(token);
    final Optional<TaskEntity> optionalTask = itaskRepository
        .findByUserAndTaskId(user, taskId);
    TaskEntity taskEntity;
    TaskResponseDto taskResponseDto;
    if (optionalTask.isPresent()) {
      taskEntity = optionalTask.get();
      taskResponseDto = new TaskResponseDto(true,
          "Retrieved the task with id:" + taskId,
          taskUtility.convertToDto(taskEntity));
    } else {
      taskResponseDto = new TaskResponseDto(false,
          "No task found with this id." + taskId);
    }
    return taskResponseDto;
  }

  /**
   * This method is to retrieve all the tasks that are completed by the user.
   *
   * @param token This is the jwt token to identify the logged in user.
   * @param status This is the status queried by the user.
   * @return TaskListResponseDto This contains all the completed tasks.
   */
  @Override
  public TaskListResponseDto getAllTasksByCompletedStatus(final Boolean status,
      final String token) {

    final UserEntity user = iuserService.getUserByToken(token);
    final Optional<List<TaskEntity>> optionalTask = itaskRepository
        .findAllByUserAndCompletedStatus(user, status);
    List<TaskEntity> taskEntities;
    TaskListResponseDto tasksRespDto;
    if (optionalTask.isPresent()) {
      taskEntities = optionalTask.get();
      final List<TaskInfoDto> taskInfoDtos = taskUtility
          .convertToDtoList(taskEntities);
      tasksRespDto = new TaskListResponseDto(taskInfoDtos.size(), taskInfoDtos);
    } else {
      tasksRespDto = new TaskListResponseDto("Found zero completed tasks.");
    }
    return tasksRespDto;
  }

  /**
   * This method is to update the task w.r.t to the given id.
   *
   * @param taskInfoDto This is task information updated by the user.
   * @param taskId      This is the task id of a task that user is willing to
   *                    update.
   * @param token       This is the jwt token to identify the logged in user.
   * @return TaskListResponseDto This contains all the completed tasks.
   */
  @Override
  public TaskResponseDto updateTaskById(final TaskInfoUpdateDto taskInfoDto,
      final String taskId, final String token) {

    final UserEntity user = iuserService.getUserByToken(token);
    final Optional<TaskEntity> optionalTask = itaskRepository
        .findByUserAndTaskId(user, taskId);
    TaskResponseDto taskResponse;
    if (optionalTask.isPresent()) {
      TaskEntity taskEntity = optionalTask.get();
      taskUtility.updateEntityWithDto(taskEntity, taskInfoDto);
      taskEntity = itaskRepository.save(taskEntity);
      taskResponse = new TaskResponseDto(true,
          taskUtility.convertToDto(taskEntity));
    } else {
      taskResponse = new TaskResponseDto(false, "No task found with this id.");
    }
    return taskResponse;
  }

  /**
   * This method is to retrieve the tasks with given limit and offset(skip).
   *
   * @param token This is the authorization token.
   * @param limit This is the number of records to be retrieved.
   * @param skip  This is the offset or the number of items to be skipped before
   *              fetching the required number of records.
   * @return TaskListResponseDto This is an object with the count and tasks
   *         within the paginated result.
   */
  @Override
  public TaskListResponseDto getPaginatedTasks(final String token, final Integer limit,
      final Integer skip) {

    final UserEntity user = iuserService.getUserByToken(token);
    /*
     * Pageable pageable = PageRequest.of(skip, limit); final Page<TaskEntity>
     * page = iTaskRepository.findAllByUser(user, pageable);
     */
    final Optional<List<TaskEntity>> optionalTasks = itaskRepository
        .findAllByPagination(user, limit, skip);
    List<TaskEntity> taskEntities;
    TaskListResponseDto tasksRespDto;
    if (optionalTasks.isPresent()) {
      taskEntities = optionalTasks.get();
      final List<TaskInfoDto> taskInfoDtos = taskUtility
          .convertToDtoList(taskEntities);
      tasksRespDto = new TaskListResponseDto(taskInfoDtos.size(), taskInfoDtos);
    } else {
      tasksRespDto = new TaskListResponseDto("Found zero completed tasks.");
    }
    return tasksRespDto;
  }

  /**
   * This method is to delete the task by taskId and email from the token.
   *
   * @param token This is the jwt token to identify the logged in user.
   * @return true/false This is the status whether deletion is failed or success.
   */
  @Override
  public boolean deleteTaskById(final String token, final String taskId) {

    final UserEntity user = iuserService.getUserByToken(token);
    try {
      itaskRepository.deleteByUserAndTaskId(user, taskId);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

}
