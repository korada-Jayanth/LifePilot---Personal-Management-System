package com.LifePilot.LifePilot.dto;

import com.LifePilot.LifePilot.entity.GoalStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record GoalResponse(

        Long id,

        String title,

        String description,

        LocalDate startDate,

        LocalDate targetDate,

        GoalStatus status,

        LocalDateTime createdAt,

        LocalDateTime updatedAt
) {
}