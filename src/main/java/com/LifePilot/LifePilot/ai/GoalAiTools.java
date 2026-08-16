package com.LifePilot.LifePilot.ai;

import com.LifePilot.LifePilot.dto.CreateGoalRequest;
import com.LifePilot.LifePilot.dto.GoalResponse;
import com.LifePilot.LifePilot.service.GoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GoalAiTools {

    private final GoalService goalService;

    @Tool(description = """
            Retrieves all goals belonging to the currently authenticated user.
            Use this when the user asks to see, show, list, or review their goals.
            """)
    public List<GoalResponse> getMyGoals() {

        String email = getAuthenticatedEmail();

        return goalService.getMyGoals(email);
    }

    @Tool(description = """
            Retrieves all active goals belonging to the currently authenticated user.
            Use this when the user asks about their current or active goals.
            """)
    public List<GoalResponse> getActiveGoals() {

        String email = getAuthenticatedEmail();

        return goalService.getActiveGoals(email);
    }

    @Tool(description = """
            Searches the currently authenticated user's goals by title.
            Use this when the user refers to a goal by its name or part of its name.
            """)
    public List<GoalResponse> searchGoals(String title) {

        String email = getAuthenticatedEmail();

        return goalService.searchGoals(title, email);
    }

    @Tool(description = """
        Creates a new goal for the currently authenticated user.

        Use this when the user explicitly asks to create or start a goal.

        The user may provide a duration such as:
        "in 30 days", "within 2 weeks", or "by next month".

        Calculate the target date based on the current date.
        Do not invent a past start date.
        """)
    public GoalResponse createGoal(
            String title,
            String description,
            int durationDays
    ) {

        String email = getAuthenticatedEmail();

        LocalDate startDate = LocalDate.now();

        LocalDate targetDate =
                startDate.plusDays(durationDays);

        CreateGoalRequest request = new CreateGoalRequest(
                title,
                description,
                startDate,
                targetDate
        );

        return goalService.createGoal(
                request,
                email
        );
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