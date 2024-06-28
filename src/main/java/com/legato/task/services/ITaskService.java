package com.legato.task.services;

import com.legato.task.entities.Task;

import java.util.List;

public interface ITaskService {
    Task createTask(Long userId, Task task);
    List<Task> getTasksByUserId(Long userId);
    List<Task> getAllTasks();
    Task updateTaskStatusByUserId(Long userId, Long taskId, boolean completed);
    void deleteTaskByUserId(Long userId, Long taskId);
}
