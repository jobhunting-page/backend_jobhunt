package com.example.jobhuntwithjpa.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ChatGPTService {

    private String OPENAI_API_KEY;
    private final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";

    private final RestTemplate restTemplate = new RestTemplate();

    public ChatGPTService(@Value("${openai.api-key}") String secret){
        this.OPENAI_API_KEY = secret;
    }

    public String generateText(String prompt) {

        //System.out.println(OPENAI_API_KEY);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(OPENAI_API_KEY);

        /*Map<String, Object> requestBody = new HashMap<>();
        Map<String, Object> messageBody = new HashMap<>();
        messageBody.put("role", "user");
        messageBody.put("content", prompt);

        requestBody.put("model","gpt-3.5-turbo");
        requestBody.put("messages", messageBody);
        requestBody.put("temperature", 0.7);*/

        //String requestBody = "{\"model\": \"gpt-3.5-turbo\",\"messages\": [{\"role\": \"user\",\"content\": \""+ prompt + "\"}],\"temperature\": 0.7}";

        List<Map<String, Object>> messageList = new ArrayList<>();
        Map<String, Object> messageBody = new HashMap<>();
        messageBody.put("role", "user");
        messageBody.put("content", prompt);
        messageList.add(messageBody);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("messages", messageList);
        requestBody.put("temperature", 0.7);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(OPENAI_API_URL, HttpMethod.POST, requestEntity, String.class);
        String content = "";

        if (response.getStatusCode() == HttpStatus.OK) {
            JsonObject jsonObject = JsonParser.parseString(response.getBody()).getAsJsonObject();
            System.out.println(jsonObject.get("choices").getAsJsonArray().get(0).getAsJsonObject().get("message").getAsJsonObject().get("content"));
            content = jsonObject.get("choices").getAsJsonArray().get(0).getAsJsonObject().get("message").getAsJsonObject().get("content").toString();

        }
        /*RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.postForEntity(OPENAI_API_URL, requestEntity, Map.class);*/

        return content;
    }
}

