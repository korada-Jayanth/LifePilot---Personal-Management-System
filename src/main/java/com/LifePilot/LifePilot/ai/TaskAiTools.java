package com.LifePilot.LifePilot.ai;

import com.LifePilot.LifePilot.dto.CreateTaskRequest;
import com.LifePilot.LifePilot.dto.TaskResponse;
import com.LifePilot.LifePilot.dto.UpdateTaskRequest;
import com.LifePilot.LifePilot.entity.TaskPriority;
import com.LifePilot.LifePilot.entity.TaskStatus;
import com.LifePilot.LifePilot.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TaskAiTools {

    private final TaskService taskService;

    @Tool(description = """
            Create a new task for the currently authenticated user.
            Use this tool whenever the user asks to create, add,
            remember, or schedule a new task.
            """)
    public TaskResponse createTask(
            String title,
            String description,
            TaskPriority priority,
            LocalDateTime dueDate
    ) {

        String email = getAuthenticatedEmail();

        CreateTaskRequest request = new CreateTaskRequest(
                title,
                description,
                priority,
                dueDate
        );

        return taskService.createTask(request, email);
    }
    @Tool(description = """
            Retrieves all tasks belonging to the currently authenticated user.
            Use this tool when the user asks to see, show, list, review,
            or check their tasks.
            """)
    public List<TaskResponse> getMyTasks() {

        String email = getAuthenticatedEmail();

        return taskService.getMyTasks(email);
    }

    @Tool(description = """
        Searches the currently authenticated user's tasks by title.
        Use this when the user refers to a task by its name or part
        of its name, especially before updating or deleting a task.
        """)
    public List<TaskResponse> searchTasks(String title) {

        String email = getAuthenticatedEmail();

        return taskService.searchTasks(title, email);
    }


    @Tool(description = """
        Updates an existing task belonging to the currently authenticated user.
        Use this when the user asks to change a task's title, description,
        priority, status, or due date.
        The task ID must be provided by the application after finding
        the correct task.
        """)
    public TaskResponse updateTask(
            Long taskId,
            String title,
            String description,
            TaskPriority priority,
            TaskStatus status,
            LocalDateTime dueDate
    ) {

        String email = getAuthenticatedEmail();

        UpdateTaskRequest request = new UpdateTaskRequest(
                title,
                description,
                priority,
                status,
                dueDate
        );

        return taskService.updateTask(
                taskId,
                request,
                email
        );
    }

    @Tool(description = """
        Deletes a task belonging to the currently authenticated user.
        Use this when the user explicitly asks to delete a task.
        """)
    public String deleteTask(Long taskId) {

        String email = getAuthenticatedEmail();

        taskService.deleteTask(taskId, email);

        return "Task deleted successfully.";
    }

    private String getAuthenticatedEmail() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        if (authentication == null ||
                !authentication.isAuthenticated()) {

            throw new SecurityException(
                    "User is not authenticated"
            );
        }

        return authentication.getName();
    }


}