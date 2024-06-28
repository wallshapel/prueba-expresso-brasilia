package com.legato.task.services;

import com.legato.task.dto.UserDTO;

import java.util.List;

public interface IUserService {
    UserDTO createUser(UserDTO userDto);
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
}
