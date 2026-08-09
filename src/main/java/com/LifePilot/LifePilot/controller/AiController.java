package com.LifePilot.LifePilot.controller;

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

    @PostMapping("/chat")
    public String chat(@RequestBody String message) {

        ChatClient chatClient = chatClientBuilder.build();

        return chatClient
                .prompt()
                .system("""
                        You are LifePilot AI, an assistant that helps
                        users manage their personal tasks.

                        You have access to task management tools.

                        When the user asks you to create a task,
                        you MUST use the createTask tool.

                        Do not simply tell the user that you created
                        the task. Actually call the tool.
                        """)
                .user(message)
                .tools(taskAiTools)
                .call()
                .content();
    }
}