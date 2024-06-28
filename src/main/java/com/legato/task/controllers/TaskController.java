package com.legato.task.controllers;

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
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public Task createTask(@PathVariable Long userId, @Valid @RequestBody Task task) {
        return taskService.createTask(userId, task);
    }

    @GetMapping
    @Transactional(readOnly = true)
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> allTasks = taskService.getAllTasks();
        return ResponseEntity.ok(allTasks);
    }

    @GetMapping("/user-id/{userId}")
    @Transactional(readOnly = true)
    public ResponseEntity<List<Task>> getTasksByUserId(@PathVariable Long userId) {
        List<Task> tasks = taskService.getTasksByUserId(userId);
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/user-id/{userId}/task-id/{taskId}")
    @Transactional
    public ResponseEntity<Task> updateTaskStatusByUserId(@PathVariable Long userId, @PathVariable Long taskId, @RequestBody Map<String, Boolean> requestBody) {
        boolean completed = requestBody.get("completed");
        Task updatedTask = taskService.updateTaskStatusByUserId(userId, taskId, completed);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/user-id/{userId}/task-id/{taskId}")
    @Transactional
    public ResponseEntity<Void> deleteTaskByUserId(@PathVariable Long userId, @PathVariable Long taskId) {
        taskService.deleteTaskByUserId(userId, taskId);
        return ResponseEntity.noContent().build();
    }

}
