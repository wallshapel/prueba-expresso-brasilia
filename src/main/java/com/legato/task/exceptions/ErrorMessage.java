package com.legato.task.exceptions;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {

    private String status;
    private String message;

}
