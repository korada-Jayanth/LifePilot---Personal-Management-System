package com.LifePilot.LifePilot.controller;

import com.LifePilot.LifePilot.dto.AuthResponse;
import com.LifePilot.LifePilot.dto.LoginRequest;
import com.LifePilot.LifePilot.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest request
    ) {

        return ResponseEntity.ok(
                authService.login(request)
        );
    }
}