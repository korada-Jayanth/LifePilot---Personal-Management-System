package com.LifePilot.LifePilot.dto;

import com.LifePilot.LifePilot.entity.TaskPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record CreateTaskRequest(

        @NotBlank(message = "Title is required")
        @Size(max = 150, message = "Title must not exceed 150 characters")
        String title,

        String description,

        TaskPriority priority,

        LocalDateTime dueDate,

        Long goalId
) {
}