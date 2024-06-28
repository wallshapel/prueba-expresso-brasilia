package com.legato.task.services;

import com.legato.task.components.Mapper;
import com.legato.task.dto.TaskDTO;
import com.legato.task.entities.Task;
import com.legato.task.entities.User;
import com.legato.task.exceptions.ResourceNotFoundException;
import com.legato.task.repositories.TaskRepository;
import com.legato.task.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskService implements ITaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final Mapper mapper;

    @Override
    @Transactional
    public TaskDTO createTask(Long userId, TaskDTO taskDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
        Task task = mapper.toEntity(taskDto, Task.class);
        task.setUser(user);
        Task savedTask = taskRepository.save(task);
        return mapper.toDto(savedTask, TaskDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskDTO> getTasksByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
        List<TaskDTO> taskDTOs = user.getTasks().stream()
                .map(task -> mapper.toDto(task, TaskDTO.class))
                .collect(Collectors.toList());
        return taskDTOs;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskDTO> getAllTasks() {
        List<TaskDTO> taskDTOs = taskRepository.findAll().stream()
                .map(task -> mapper.toDto(task, TaskDTO.class))
                .collect(Collectors.toList());
        return taskDTOs;
    }

    @Override
    @Transactional
    public TaskDTO updateTaskStatusByUserId(Long userId, Long taskId, boolean completed) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
        Task taskToUpdate = user.getTasks().stream()
                .filter(task -> task.getId().equals(taskId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + taskId + " for user " + userId));
        taskToUpdate.setCompleted(completed);
        Task updatedTask = taskRepository.save(taskToUpdate);
        return mapper.toDto(updatedTask, TaskDTO.class);
    }

    @Override
    @Transactional
    public void deleteTaskByUserId(Long userId, Long taskId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
        taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + taskId));
        taskRepository.deleteById(taskId);
    }
}
