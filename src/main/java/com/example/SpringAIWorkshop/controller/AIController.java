package com.example.SpringAIWorkshop.controller;

import com.example.SpringAIWorkshop.service.AudioService;
import com.example.SpringAIWorkshop.service.ChatService;
import com.example.SpringAIWorkshop.service.ImageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AIController {

    private final ChatService chatService;
    private final AudioService audioService;
    private final ImageService imageService;

    public AIController(ChatService chatService, AudioService audioService, ImageService imageService) {
        this.chatService = chatService;
        this.audioService = audioService;
        this.imageService = imageService;
    }

    @GetMapping("/ask")
    public String askAI(@RequestParam String prompt){
        return chatService.askAI(prompt);
    }

    @GetMapping("/generate-audio")
    public String generateAudio(@RequestParam String text){
        return audioService.convertTextToSpeech(text);
    }

    @GetMapping("/generate-image")
    public String generateImage(@RequestParam String prompt){
        return imageService.generateImage(prompt);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Cloud Platform!";
    }
}
