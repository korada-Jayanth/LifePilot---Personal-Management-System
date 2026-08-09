package com.LifePilot.LifePilot.dto;

import com.LifePilot.LifePilot.entity.TaskPriority;
import com.LifePilot.LifePilot.entity.TaskStatus;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record UpdateTaskRequest(

        @Size(max = 150, message = "Title must not exceed 150 characters")
        String title,

        String description,

        TaskPriority priority,

        TaskStatus status,

        LocalDateTime dueDate
) {
}