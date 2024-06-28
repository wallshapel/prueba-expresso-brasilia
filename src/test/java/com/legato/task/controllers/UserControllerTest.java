package com.legato.task.controllers;

import com.legato.task.dto.ApiResponse;
import com.legato.task.dto.UserDTO;
import com.legato.task.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void testCreateUser() {
        // Data test
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("john_doe");

        // Expected response
        ApiResponse<UserDTO> apiResponse = new ApiResponse<>("success", "User created successfully", userDTO);

        // Configure mock behavior
        when(userService.createUser(any(UserDTO.class))).thenReturn(userDTO);

        // Run the controller
        ResponseEntity<ApiResponse<UserDTO>> responseEntity = userController.createUser(userDTO);

        // Verifications
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(apiResponse.getStatus(), responseEntity.getBody().getStatus());
        assertEquals(apiResponse.getMessage(), responseEntity.getBody().getMessage());
        assertEquals(apiResponse.getData().getUsername(), responseEntity.getBody().getData().getUsername());
    }

}
