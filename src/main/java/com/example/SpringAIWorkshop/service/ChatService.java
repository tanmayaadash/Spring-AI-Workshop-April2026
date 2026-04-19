package com.example.SpringAIWorkshop.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatModel chatModel;

    public ChatService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String askAI(String prompt){
        OpenAiChatOptions openAiChatOptions = OpenAiChatOptions.builder()
                .model("gpt-5.1")
                .maxCompletionTokens(500)
                .temperature(0.7)
                .build();
        ChatResponse chatResponse = chatModel.call(new Prompt(prompt,openAiChatOptions));


        return chatResponse.getResult().getOutput().getText();
    }
}
