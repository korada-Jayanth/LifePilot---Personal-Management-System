package com.LifePilot.LifePilot.controller;

import com.LifePilot.LifePilot.dto.CreateUserRequest;
import com.LifePilot.LifePilot.dto.UserReponse;
import com.LifePilot.LifePilot.entity.User;
import com.LifePilot.LifePilot.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    // Creating  the User
    @PostMapping
    public ResponseEntity<UserReponse> createUser(@Valid @RequestBody CreateUserRequest request){
        UserReponse userReponse = userService.createUser(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userReponse);
    }

    // User logging with token
    @GetMapping("/me")
    public ResponseEntity<UserReponse> getCurrentUser(
            Authentication authentication
    ){
        User user = (User) authentication.getPrincipal();

        UserReponse response = new UserReponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
        return ResponseEntity.ok(response);
    }
}
