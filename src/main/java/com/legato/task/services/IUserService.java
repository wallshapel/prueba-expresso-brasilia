package com.legato.task.services;

import com.legato.task.entities.User;

import java.util.List;

public interface IUserService {
    User createUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
}
