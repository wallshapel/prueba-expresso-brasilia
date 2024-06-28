package com.legato.task.controllers;

import com.legato.task.dto.ApiResponse;
import com.legato.task.dto.TaskDTO;
import com.legato.task.services.TaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/{userId}")
    public ResponseEntity<ApiResponse<TaskDTO>> createTask(@PathVariable Long userId, @Valid @RequestBody TaskDTO taskDto) {
        TaskDTO createdTask = taskService.createTask(userId, taskDto);
        ApiResponse<TaskDTO> response = new ApiResponse<>("success", "Task created successfully", createdTask);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/user-id/{userId}")
    public ResponseEntity<ApiResponse<List<TaskDTO>>> getTasksByUserId(@PathVariable Long userId) {
        List<TaskDTO> tasks = taskService.getTasksByUserId(userId);
        ApiResponse<List<TaskDTO>> response = new ApiResponse<>("success", "Tasks retrieved successfully", tasks);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TaskDTO>>> getAllTasks() {
        List<TaskDTO> allTasks = taskService.getAllTasks();
        ApiResponse<List<TaskDTO>> response = new ApiResponse<>("success", "All tasks retrieved successfully", allTasks);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/user-id/{userId}/task-id/{taskId}")
    public ResponseEntity<ApiResponse<TaskDTO>> updateTaskStatusByUserId(@PathVariable Long userId, @PathVariable Long taskId, @RequestBody Map<String, Boolean> requestBody) {
        Object completedObj = requestBody.get("completed");
        if (completedObj == null)
            throw new IllegalArgumentException("Field 'completed' must be provided and cannot be null.");
        boolean completed = (Boolean) completedObj;
        TaskDTO updatedTask = taskService.updateTaskStatusByUserId(userId, taskId, completed);
        ApiResponse<TaskDTO> response = new ApiResponse<>("success", "Task status updated successfully", updatedTask);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/user-id/{userId}/task-id/{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTaskByUserId(@PathVariable Long userId, @PathVariable Long taskId) {
        taskService.deleteTaskByUserId(userId, taskId);
    }
}
