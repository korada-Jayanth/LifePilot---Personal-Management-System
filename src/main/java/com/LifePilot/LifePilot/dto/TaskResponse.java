package com.LifePilot.LifePilot.dto;

import com.LifePilot.LifePilot.entity.TaskPriority;
import com.LifePilot.LifePilot.entity.TaskStatus;

import java.time.LocalDateTime;

public record TaskResponse(

        Long id,

        String title,

        String description,

        TaskStatus status,

        TaskPriority priority,

        LocalDateTime dueDate,

        LocalDateTime createdAt,

        LocalDateTime updatedAt
) {
}