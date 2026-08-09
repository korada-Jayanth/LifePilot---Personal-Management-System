package com.LifePilot.LifePilot.controller;

import com.LifePilot.LifePilot.dto.CreateTaskRequest;
import com.LifePilot.LifePilot.dto.TaskResponse;
import com.LifePilot.LifePilot.dto.UpdateTaskRequest;
import com.LifePilot.LifePilot.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(
            @Valid @RequestBody CreateTaskRequest request,
            Authentication authentication
    ) {

        TaskResponse response = taskService.createTask(
                request,
                authentication.getName()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getMyTasks(
            Authentication authentication
    ) {

        return ResponseEntity.ok(
                taskService.getMyTasks(
                        authentication.getName()
                )
        );
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponse> getTask(
            @PathVariable Long taskId,
            Authentication authentication
    ) {

        return ResponseEntity.ok(
                taskService.getTaskById(
                        taskId,
                        authentication.getName()
                )
        );
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable Long taskId,
            @Valid @RequestBody UpdateTaskRequest request,
            Authentication authentication
    ) {

        return ResponseEntity.ok(
                taskService.updateTask(
                        taskId,
                        request,
                        authentication.getName()
                )
        );
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable Long taskId,
            Authentication authentication
    ) {

        taskService.deleteTask(
                taskId,
                authentication.getName()
        );

        return ResponseEntity.noContent().build();
    }
}