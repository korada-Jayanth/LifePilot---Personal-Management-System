package com.LifePilot.LifePilot.controller;

import com.LifePilot.LifePilot.dto.CreateGoalRequest;
import com.LifePilot.LifePilot.dto.GoalResponse;
import com.LifePilot.LifePilot.service.GoalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goals")
@RequiredArgsConstructor
public class GoalController {

    private final GoalService goalService;

    @PostMapping
    public ResponseEntity<GoalResponse> createGoal(
            @Valid @RequestBody CreateGoalRequest request,
            Authentication authentication
    ) {

        GoalResponse response =
                goalService.createGoal(
                        request,
                        authentication.getName()
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<GoalResponse>> getMyGoals(
            Authentication authentication
    ) {

        return ResponseEntity.ok(
                goalService.getMyGoals(
                        authentication.getName()
                )
        );
    }

    @GetMapping("/active")
    public ResponseEntity<List<GoalResponse>> getActiveGoals(
            Authentication authentication
    ) {

        return ResponseEntity.ok(
                goalService.getActiveGoals(
                        authentication.getName()
                )
        );
    }

    @GetMapping("/search")
    public ResponseEntity<List<GoalResponse>> searchGoals(
            @RequestParam String title,
            Authentication authentication
    ) {

        return ResponseEntity.ok(
                goalService.searchGoals(
                        title,
                        authentication.getName()
                )
        );
    }
}