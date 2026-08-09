package com.LifePilot.LifePilot.dto;

public record AuthResponse(
        String accessToken,
        String tokenType
) {
}
