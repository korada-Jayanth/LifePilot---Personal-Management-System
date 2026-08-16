package com.LifePilot.LifePilot.service;

import com.LifePilot.LifePilot.dto.CreateGoalRequest;
import com.LifePilot.LifePilot.dto.GoalResponse;
import com.LifePilot.LifePilot.entity.Goal;
import com.LifePilot.LifePilot.entity.GoalStatus;
import com.LifePilot.LifePilot.entity.User;
import com.LifePilot.LifePilot.repository.GoalRepository;
import com.LifePilot.LifePilot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoalService {

    private final GoalRepository goalRepository;
    private final UserRepository userRepository;

    @Transactional
    public GoalResponse createGoal(
            CreateGoalRequest request,
            String email
    ) {

        User user = getUserByEmail(email);

        Goal goal = Goal.builder()
                .title(request.title())
                .description(request.description())
                .startDate(request.startDate())
                .targetDate(request.targetDate())
                .status(GoalStatus.ACTIVE)
                .user(user)
                .build();

        Goal savedGoal = goalRepository.save(goal);

        return mapToResponse(savedGoal);
    }

    @Transactional(readOnly = true)
    public List<GoalResponse> getMyGoals(String email) {

        User user = getUserByEmail(email);

        return goalRepository.findByUserId(user.getId())
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<GoalResponse> getActiveGoals(String email) {

        User user = getUserByEmail(email);

        return goalRepository.findByUserIdAndStatus(
                        user.getId(),
                        GoalStatus.ACTIVE
                )
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<GoalResponse> searchGoals(
            String title,
            String email
    ) {

        User user = getUserByEmail(email);

        return goalRepository
                .findByUserIdAndTitleContainingIgnoreCase(
                        user.getId(),
                        title
                )
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private User getUserByEmail(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found"
                        )
                );
    }

    private GoalResponse mapToResponse(Goal goal) {

        return new GoalResponse(
                goal.getId(),
                goal.getTitle(),
                goal.getDescription(),
                goal.getStartDate(),
                goal.getTargetDate(),
                goal.getStatus(),
                goal.getCreatedAt(),
                goal.getUpdatedAt()
        );
    }
}