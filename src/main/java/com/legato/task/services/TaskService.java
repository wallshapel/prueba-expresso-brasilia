package com.legato.task.services;

import com.legato.task.entities.Task;
import com.legato.task.entities.User;
import com.legato.task.exceptions.ResourceNotFoundException;
import com.legato.task.repositories.TaskRepository;
import com.legato.task.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService implements ITaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public Task createTask(Long userId, Task task) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
        // Asignar el usuario a la tarea
        task.setUser(user);
        // Guardar la tarea en la base de datos
        return taskRepository.save(task);
    }

    public List<Task> getTasksByUserId(Long userId) {
        userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
        return taskRepository.findByUserId(userId);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task updateTaskStatusByUserId(Long userId, Long taskId, boolean completed) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
        Task taskToUpdate = user.getTasks().stream()
                .filter(task -> task.getId().equals(taskId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + taskId + " for user " + userId));
        taskToUpdate.setCompleted(completed);
        return taskRepository.save(taskToUpdate);
    }

    public void deleteTaskByUserId(Long userId, Long taskId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
        taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + taskId));
        taskRepository.deleteById(taskId);
    }

}