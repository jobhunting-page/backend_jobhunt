package com.example.jobhuntwithjpa.Service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChatGPTService {

    private final String OPENAI_API_KEY = "sk-";
    private final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";


    private RestTemplate restTemplate = new RestTemplate();

    public String generateText(String prompt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(OPENAI_API_KEY);

        String requestBody = "{\"model\": \"gpt-3.5-turbo\",\"messages\": [{\"role\": \"user\",\"content\": \""+ prompt + "?\"}],\"temperature\": 0.7}";


        HttpEntity<String> request = new HttpEntity<String>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(OPENAI_API_URL, request, String.class);

        return response.getBody();
    }
}

