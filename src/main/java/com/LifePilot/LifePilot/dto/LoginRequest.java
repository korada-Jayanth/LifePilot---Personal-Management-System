package com.LifePilot.LifePilot.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.aspectj.weaver.ast.Not;

public record LoginRequest(
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "Password is required")
        String password
) {
}
