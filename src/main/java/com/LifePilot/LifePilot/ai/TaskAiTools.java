package com.LifePilot.LifePilot.ai;

import com.LifePilot.LifePilot.dto.CreateTaskRequest;
import com.LifePilot.LifePilot.dto.TaskResponse;
import com.LifePilot.LifePilot.entity.TaskPriority;
import com.LifePilot.LifePilot.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TaskAiTools {

    private final TaskService taskService;

    @Tool(description = """
        Creates a new task for the current LifePilot user.
        Use this when the user asks you to create, add, or remember a task.
        The task can have a title, description, priority, and due date.
        """)
    public TaskResponse createTask(
            String title,
            String description,
            TaskPriority priority,
            LocalDateTime dueDate
    ) {

        System.out.println("🔥 AI TOOL CALLED: createTask()");
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Priority: " + priority);
        System.out.println("Due date: " + dueDate);

        String email = "jayanthkorada2003@example.com";

        CreateTaskRequest request = new CreateTaskRequest(
                title,
                description,
                priority,
                dueDate
        );

        return taskService.createTask(request, email);
    }
}