package com.legato.task.services;

import com.legato.task.components.Mapper;
import com.legato.task.dto.UserDTO;
import com.legato.task.entities.User;
import com.legato.task.exceptions.ResourceNotFoundException;
import com.legato.task.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final Mapper mapper;

    @Override
    @Transactional
    public UserDTO createUser(UserDTO userDto) {
        User user = mapper.toEntity(userDto, User.class);
        User savedUser = userRepository.save(user);
        return mapper.toDto(savedUser, UserDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> mapper.toDto(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        return mapper.toDto(user, UserDTO.class);
    }

}
