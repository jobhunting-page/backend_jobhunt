package com.example.jobhuntwithjpa.controller;

import com.example.jobhuntwithjpa.Service.ChatGPTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatGPTController {
    @Autowired
    private ChatGPTService chatGPTService;

    @PostMapping("/chat")
    public String getResponse(@RequestParam(value = "prompt") String prompt) throws Exception {
        return chatGPTService.generateText(prompt);
    }
}