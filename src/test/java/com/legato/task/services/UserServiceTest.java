package com.legato.task.services;

import com.legato.task.components.Mapper;
import com.legato.task.dto.UserDTO;
import com.legato.task.entities.User;
import com.legato.task.helpers.UniqueUsernameValidator;
import com.legato.task.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private Mapper mapper;

    @Mock
    private UniqueUsernameValidator uniqueUsernameValidator;

    @InjectMocks
    private UserService userService;

    @Test
    public void testCreateUser() {
        // Data test
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("john_doe");

        User user = new User();
        user.setId(1L);
        user.setUsername("john_doe");

        // Configurar comportamiento del mock
        doNothing().when(uniqueUsernameValidator).validateUniqueUsername(userDTO.getUsername());
        when(mapper.toEntity(any(UserDTO.class), any())).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(mapper.toDto(any(User.class), any())).thenReturn(userDTO);

        // Run service
        UserDTO createdUser = userService.createUser(userDTO);

        // Verifications
        assertNotNull(createdUser);
        assertEquals(user.getUsername(), createdUser.getUsername());
    }

}
