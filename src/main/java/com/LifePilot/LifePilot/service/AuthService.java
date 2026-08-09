package com.LifePilot.LifePilot.service;

import com.LifePilot.LifePilot.dto.AuthResponse;
import com.LifePilot.LifePilot.dto.LoginRequest;
import com.LifePilot.LifePilot.entity.User;
import com.LifePilot.LifePilot.repository.UserRepository;
import com.LifePilot.LifePilot.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse login(LoginRequest request) {

        User user = userRepository
                .findByEmail(request.email())
                .orElseThrow(() ->
                        new BadCredentialsException("Invalid email or password")
                );

        if (!passwordEncoder.matches(
                request.password(),
                user.getPassword()
        )) {
            throw new BadCredentialsException("Invalid email or password");
        }

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(
                token,
                "Bearer"
        );
    }
}