package com.legato.task.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDTO {
    private String title;
    private String description;
    private boolean completed;
    private Long userId;
}
