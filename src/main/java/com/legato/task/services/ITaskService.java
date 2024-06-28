package com.legato.task.services;

import com.legato.task.dto.TaskDTO;

import java.util.List;

public interface ITaskService {
    TaskDTO createTask(Long userId, TaskDTO taskDto);
    List<TaskDTO> getTasksByUserId(Long userId);
    List<TaskDTO> getAllTasks();
    TaskDTO updateTaskStatusByUserId(Long userId, Long taskId, boolean completed);
    void deleteTaskByUserId(Long userId, Long taskId);
}
