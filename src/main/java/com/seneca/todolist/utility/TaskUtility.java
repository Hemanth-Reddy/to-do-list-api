package com.seneca.todolist.utility;

import com.seneca.todolist.entity.TaskEntity;
import com.seneca.todolist.model.TaskInfoDto;
import com.seneca.todolist.model.TaskInfoUpdateDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * This is the utility class for the task related functionalities.
 *
 * @author hemanth.nagireddy
 *
 */
@Component
public class TaskUtility {

  /**
   * This is the method to convert a task entity to dto.
   *
   * @param task This is the entity to be converted.
   * @return taskInfoDto This is the taskInfoDto returned.
   */
  public TaskInfoDto convertToDto(final TaskEntity task) {

    TaskInfoDto taskInfo = new TaskInfoDto();
    BeanUtils.copyProperties(task, taskInfo);
    return taskInfo;
  }

  /**
   * This is the method to convert a dto to entity.
   *
   * @param taskInfo This is the taskInfoDto which is to be converted.
   * @return taskEntity This is the taskEntity returned.
   */
  public TaskEntity convertToEntity(final TaskInfoDto taskInfo) {

    TaskEntity taskEntity = new TaskEntity();
    BeanUtils.copyProperties(taskInfo, taskEntity);
    return taskEntity;
  }

  /**
   * This is the method to convert an entity into dto.
   *
   * @param taskEntities These are the task entities which are to be converted
   *                     into dtos.
   * @return taskInfoDtos These are the dtos returned.
   */
  public List<TaskInfoDto> convertToDtoList(
      final List<TaskEntity> taskEntities) {

    List<TaskInfoDto> taskInfoDtos = new ArrayList<>();
    for (TaskEntity taskEntity : taskEntities) {
      taskInfoDtos.add(convertToDto(taskEntity));
    }
    return taskInfoDtos;
  }

  /**
   * This is the method to update the task entity with the details provided by
   * the user to update.
   *
   * @param taskEntity  This is the task entity into which the details are to be
   *                    updated.
   * @param taskInfoDto This is the dto from which details are to be updated.
   */
  public void updateEntityWithDto(final TaskEntity taskEntity,
      final TaskInfoUpdateDto taskInfoDto) {

    if (Optional.ofNullable(taskInfoDto.getCompleted()).isPresent()) {
      taskEntity.setCompleted(taskInfoDto.getCompleted().get());
    }
    if (Optional.ofNullable(taskInfoDto.getDescription()).isPresent()) {
      taskEntity.setDescription(taskInfoDto.getDescription().get());
    }
  }

}
