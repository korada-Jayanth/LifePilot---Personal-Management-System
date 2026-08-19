package com.LifePilot.LifePilot.service;

import com.LifePilot.LifePilot.dto.CreateTaskRequest;
import com.LifePilot.LifePilot.dto.TaskResponse;
import com.LifePilot.LifePilot.dto.UpdateTaskRequest;
import com.LifePilot.LifePilot.entity.*;
import com.LifePilot.LifePilot.repository.GoalRepository;
import com.LifePilot.LifePilot.repository.TaskRepository;
import com.LifePilot.LifePilot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final GoalRepository goalRepository;

    @Transactional
    public TaskResponse createTask(
            CreateTaskRequest request,
            String email
    ) {

        User user = getUserByEmail(email);

        Goal goal = null;

        if (request.goalId() != null) {

            goal = goalRepository.findById(request.goalId())
                    .orElseThrow(() ->
                            new IllegalArgumentException(
                                    "Goal not found"
                            )
                    );

            if (!goal.getUser().getId().equals(user.getId())) {
                throw new SecurityException(
                        "You are not allowed to use this goal"
                );
            }
        }

        Task task = Task.builder()
                .title(request.title())
                .description(request.description())
                .priority(
                        request.priority() != null
                                ? request.priority()
                                : TaskPriority.MEDIUM
                )
                .status(TaskStatus.TODO)
                .dueDate(request.dueDate())
                .user(user)
                .goal(goal)
                .build();

        Task savedTask = taskRepository.save(task);

        return mapToResponse(savedTask);
    }

    @Transactional(readOnly = true)
    public List<TaskResponse> getMyTasks(String email) {

        User user = getUserByEmail(email);

        return taskRepository.findByUserId(user.getId())
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public TaskResponse getTaskById(
            Long taskId,
            String email
    ) {

        User user = getUserByEmail(email);

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Task not found")
                );

        verifyOwnership(task, user);

        return mapToResponse(task);
    }

    @Transactional
    public TaskResponse updateTask(
            Long taskId,
            UpdateTaskRequest request,
            String email
    ) {

        User user = getUserByEmail(email);

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Task not found")
                );

        verifyOwnership(task, user);

        if (request.title() != null) {
            task.setTitle(request.title());
        }

        if (request.description() != null) {
            task.setDescription(request.description());
        }

        if (request.priority() != null) {
            task.setPriority(request.priority());
        }

        if (request.status() != null) {
            task.setStatus(request.status());
        }

        if (request.dueDate() != null) {
            task.setDueDate(request.dueDate());
        }

        return mapToResponse(task);
    }

    @Transactional
    public void deleteTask(
            Long taskId,
            String email
    ) {

        User user = getUserByEmail(email);

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Task not found")
                );

        verifyOwnership(task, user);

        taskRepository.delete(task);
    }

    private User getUserByEmail(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found")
                );
    }

    private void verifyOwnership(Task task, User user) {

        if (!task.getUser().getId().equals(user.getId())) {
            throw new SecurityException(
                    "You are not allowed to access this task"
            );
        }
    }

    private TaskResponse mapToResponse(Task task) {

        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getDueDate(),
                task.getGoal() != null
                        ? task.getGoal().getId()
                        : null,
                task.getCreatedAt(),
                task.getUpdatedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<TaskResponse> searchTasks(
            String title,
            String email
    ) {

        User user = getUserByEmail(email);

        return taskRepository
                .findByUserIdAndTitleContainingIgnoreCase(
                        user.getId(),
                        title
                )
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
}