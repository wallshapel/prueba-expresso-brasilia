package com.legato.task.controllers;

import com.legato.task.dto.ApiResponse;
import com.legato.task.dto.UserDTO;
import com.legato.task.services.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse<UserDTO>> createUser(@Valid @RequestBody UserDTO userDto) {
        UserDTO createdUser = userService.createUser(userDto);
        ApiResponse<UserDTO> response = new ApiResponse<>("success", "User created successfully", createdUser);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDTO>>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        ApiResponse<List<UserDTO>> response = new ApiResponse<>("success", "All users retrieved successfully", users);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> getUserById(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        ApiResponse<UserDTO> response = new ApiResponse<>("success", "User retrieved successfully", user);
        return ResponseEntity.ok(response);
    }
}
