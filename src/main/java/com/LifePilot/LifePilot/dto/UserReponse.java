package com.LifePilot.LifePilot.dto;

import java.time.LocalDateTime;

public record UserReponse(
        Long id,
        String firstName,
        String LastName,
        String email,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    // Password not included in response for security purpose

}
