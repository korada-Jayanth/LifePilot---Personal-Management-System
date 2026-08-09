package com.LifePilot.LifePilot.repository;

import com.LifePilot.LifePilot.entity.Task;
import com.LifePilot.LifePilot.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUserId(Long userId);

    List<Task> findByUserIdAndStatus(
            Long userId,
            TaskStatus status
    );
}