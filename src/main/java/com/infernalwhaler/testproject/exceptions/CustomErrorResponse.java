package com.infernalwhaler.testproject.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author sDeseure
 * @project testProjectSpring
 * @date 1/09/2021
 */
@Getter
@Setter
@NoArgsConstructor
class CustomErrorResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;
    private int statusCode;
    private String message;
    private String description;
}
