package com.legato.task.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDTO {

    @NotBlank(message = "title cannot be null or empty")
    @Size(min = 2, max = 50, message = "title must be between 2 and 50 characters")
    private String title;

    @NotBlank(message = "description cannot be null or empty")
    @Size(min = 2, max = 255, message = "description must be between 2 and 255 characters")
    private String description;

    @NotNull(message = "completed cannot be null")
    private boolean completed;

    private Long userId;

}
