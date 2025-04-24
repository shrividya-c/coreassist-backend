//package com.demo.ChatBot.controller;
//
//import org.springframework.web.bind.annotation.*;
//import org.springframework.http.*;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api")
//public class ImageGenerationController {
//
//    private final RestTemplate restTemplate = new RestTemplate();
//
//    // Your Groq API key and Replicate API key
//    private final String GROQ_API_KEY = System.getenv("GROQ_API_KEY");
//    private final String REPLICATE_API_KEY = System.getenv("REPLICATE_API_KEY");
//
//    // Groq API endpoint to generate image prompts
//    private final String GROQ_API_URL = "https://api.groq.com/openai/v1/chat/completions";
//
//    // Replicate API endpoint to generate the image
//    private final String REPLICATE_API_URL = "https://api.replicate.com/v1/predictions";
//
//    // Endpoint to generate image prompt using Groq
//    @PostMapping("/generate-prompt")
//    private String generatePrompt(String userInput) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearerAuth(GROQ_API_KEY);
//
//        Map<String, Object> body = Map.of(
//                "model", "llama3-70b-8192",
//                "messages", List.of(
//                        Map.of("role", "user", "content", "Write a detailed, creative image prompt for: " + userInput)
//                )
//        );
//
//        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
//        ResponseEntity<Map> response = restTemplate.postForEntity(GROQ_API_URL, request, Map.class);
//
//        List<Map<String, Object>> choices = (List<Map<String, Object>>) response.getBody().get("choices");
//        Map<String, Object> message = choices.get(0);
//        Map<String, Object> innerMessage = (Map<String, Object>) message.get("message");
//
//        return (String) innerMessage.get("content");
//    }
//
//    // Endpoint to generate an image using Replicate from the Groq generated prompt
//    @PostMapping("/generate-image")
//    public ResponseEntity<String> generateImage(@RequestBody String prompt) {
//        // Replicate API request to generate image
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearerAuth(REPLICATE_API_KEY);
//
//        // Set up request body for Replicate
//        Map<String, Object> body = Map.of(
//                "version", "db21e45a53c1e579ed3803f26ed1439c2df6b05a0e572d2b24ce68ef6cda145c", // Stable Diffusion 1.5
//                "input", Map.of("prompt", prompt)
//        );
//
//        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
//
//        ResponseEntity<Map> response = restTemplate.postForEntity(REPLICATE_API_URL, request, Map.class);
//
//        // Extract image URL from the response
//        Map<String, String> urls = (Map<String, String>) response.getBody().get("urls");
//        String predictionUrl = urls.get("get");
//
//        // Poll for result (replicate generates asynchronously)
//        while (true) {
//            ResponseEntity<Map> pollResponse = restTemplate.exchange(predictionUrl, HttpMethod.GET, new HttpEntity<>(headers), Map.class);
//            String status = (String) pollResponse.getBody().get("status");
//
//            if ("succeeded".equals(status)) {
//                List<String> output = (List<String>) pollResponse.getBody().get("output");
//                return ResponseEntity.ok(output.get(0)); // Return the image URL
//            } else if ("failed".equals(status)) {
//                throw new RuntimeException("Image generation failed.");
//            }
//
//            try {
//                Thread.sleep(1500); // Poll every 1.5 seconds
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
//
