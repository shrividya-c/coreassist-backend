package com.demo.ChatBot.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private final ChatModel chatModel;

    public ChatService(ChatModel chatModel){
        this.chatModel = chatModel;
    }

    public String getResponse(String prompt){
        String response = chatModel.call(prompt);
        return response;
    }

    public String getResponseOptions(String prompt){
        ChatResponse chatResponse = chatModel.call(
                                        new Prompt(
                                                prompt,
                                                OpenAiChatOptions.builder()
                                                        .model("gpt-3.5-turbo")
                                                        .temperature(0.4)
                                                        .build()
                                        ));
        String response = chatResponse
                            .getResult()
                            .getOutput()
                            .getText();
        return response;
    }
}
