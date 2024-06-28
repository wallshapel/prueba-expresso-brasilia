package com.legato.task.controllers;

import com.legato.task.dto.ApiResponse;
import com.legato.task.entities.Task;
import com.legato.task.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/{userId}")
    @Transactional
    public ResponseEntity<ApiResponse<Task>> createTask(@PathVariable Long userId, @Valid @RequestBody Task task) {
        Task createdTask = taskService.createTask(userId, task);
        ApiResponse<Task> response = new ApiResponse<>("success", "Task created successfully", createdTask);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse<List<Task>>> getAllTasks() {
        List<Task> allTasks = taskService.getAllTasks();
        ApiResponse<List<Task>> response = new ApiResponse<>("success", "All tasks retrieved successfully", allTasks);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user-id/{userId}")
    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse<List<Task>>> getTasksByUserId(@PathVariable Long userId) {
        List<Task> tasks = taskService.getTasksByUserId(userId);
        ApiResponse<List<Task>> response = new ApiResponse<>("success", "Tasks retrieved successfully", tasks);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/user-id/{userId}/task-id/{taskId}")
    @Transactional
    public ResponseEntity<ApiResponse<Task>> updateTaskStatusByUserId(@PathVariable Long userId, @PathVariable Long taskId, @RequestBody Map<String, Boolean> requestBody) {
        boolean completed = requestBody.get("completed");
        Task updatedTask = taskService.updateTaskStatusByUserId(userId, taskId, completed);
        ApiResponse<Task> response = new ApiResponse<>("success", "Task status updated successfully", updatedTask);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/user-id/{userId}/task-id/{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void deleteTaskByUserId(@PathVariable Long userId, @PathVariable Long taskId) {
        taskService.deleteTaskByUserId(userId, taskId);
    }
}
