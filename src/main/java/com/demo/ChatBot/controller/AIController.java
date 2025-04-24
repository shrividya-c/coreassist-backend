package com.demo.ChatBot.controller;

import com.demo.ChatBot.service.ChatService;
import com.demo.ChatBot.service.ImageService;
import com.demo.ChatBot.service.PromptGenerationService;
import com.demo.ChatBot.service.TranscriptionService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.image.ImageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class AIController {
    @Autowired
    private ChatService chatService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private PromptGenerationService recipeService;

    @Autowired
    private TranscriptionService transcriptionService;

    @GetMapping("/ask-ai/{prompt}")
    public String getResponse(@PathVariable("prompt") String prompt){
        return chatService.getResponse(prompt);
    }

    @GetMapping("/ask-ai-options")
    public String getResponseOptions(@RequestParam String prompt){
        return chatService.getResponseOptions(prompt);
    }

//    @GetMapping("/generate-image")
//    public void generateImage(@RequestParam String prompt, HttpServletResponse response) throws IOException {
//        ImageResponse imageResponse = imageService.generateImage(prompt);
//        String imageUrl = imageResponse.getResult().getOutput().getUrl();
//        response.sendRedirect(imageUrl);
//    }

    @GetMapping("/generate-image")
    public List<String> generateImage(@RequestParam String prompt, HttpServletResponse response) throws IOException {
        ImageResponse imageResponse = imageService.generateImage(prompt);
        List<String> imageUrls = imageResponse.getResults().stream()
                .map(result -> result.getOutput().getUrl())
                .toList();
        return imageUrls;
    }

    @GetMapping("/generate-recipe")
    public String generateRecipe(@RequestParam String ingredients,
                                      @RequestParam(defaultValue = "any") String cuisine,
                                      @RequestParam(defaultValue = "none") String dietaryRestrictions){
        return recipeService.createRecipe(ingredients, cuisine, dietaryRestrictions);
    }

    @PostMapping("/transcribe")
    public ResponseEntity<String> transcribeAudio(
            @RequestParam("file") MultipartFile file) throws IOException {
        return transcriptionService.transcribeAudio(file);
    }

    @GetMapping("/vacation-planner")
    public String planVacation(@RequestParam String destination,
                               @RequestParam String areasOfInterest,
                               @RequestParam int duration,
                               @RequestParam int noOfTravelers,
                               @RequestParam int budget,
                               @RequestParam String tentativeTravelDates,
                               @RequestParam String typeOfStay){
        return recipeService.holidayPlanner(destination, areasOfInterest, duration, noOfTravelers, budget, tentativeTravelDates, typeOfStay);
    }
}
