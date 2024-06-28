package com.legato.task.helpers;

import com.legato.task.exceptions.AlreadyExistException;
import com.legato.task.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UniqueUsernameValidator {

    private final UserRepository userRepository;

    public void validateUniqueUsername(String username) {
        if (userRepository.existsByUsername(username))
            throw new AlreadyExistException("Username already exists");
    }

}
