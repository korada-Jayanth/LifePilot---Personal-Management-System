package com.LifePilot.LifePilot.controller;

import com.LifePilot.LifePilot.ai.GoalAiTools;
import com.LifePilot.LifePilot.ai.TaskAiTools;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiController {

    private final ChatClient.Builder chatClientBuilder;
    private final TaskAiTools taskAiTools;
    private final GoalAiTools goalAiTools;

    @PostMapping("/chat")
    public String chat(@RequestBody String message) {

        ChatClient chatClient = chatClientBuilder.build();

        return chatClient
                .prompt()
                .system("""
        You are LifePilot AI, a personal productivity assistant.

        You can help the user manage tasks and goals.

        TASKS:
        - Create tasks when requested.
        - Retrieve the user's tasks when requested.
        - Search tasks when the user refers to a task by name.
        - Update tasks when requested.
        - Delete tasks only when the user explicitly asks.

        GOALS:
        - Create goals when requested.
        - Retrieve the user's goals when requested.
        - Retrieve active goals when requested.
        - Search goals when the user refers to a goal by name.

        Never claim that a task or goal was created, updated,
        or deleted unless the corresponding tool was successfully called.

        Never access another user's data.
        """)
                .user(message)
                .tools(
                        taskAiTools,
                        goalAiTools
                )
                .call()
                .content();
    }
}