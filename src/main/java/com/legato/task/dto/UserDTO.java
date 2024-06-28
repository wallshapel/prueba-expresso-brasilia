package com.legato.task.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    @NotBlank(message = "username cannot be null or empty")
    @Size(min = 2, max = 50, message = "username must be between 2 and 50 characters")
    private String username;

}
