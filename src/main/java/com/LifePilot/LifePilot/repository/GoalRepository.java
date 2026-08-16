package com.LifePilot.LifePilot.repository;

import com.LifePilot.LifePilot.entity.Goal;
import com.LifePilot.LifePilot.entity.GoalStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoalRepository extends JpaRepository<Goal, Long> {

    List<Goal> findByUserId(Long userId);

    List<Goal> findByUserIdAndStatus(
            Long userId,
            GoalStatus status
    );

    List<Goal> findByUserIdAndTitleContainingIgnoreCase(
            Long userId,
            String title
    );
}