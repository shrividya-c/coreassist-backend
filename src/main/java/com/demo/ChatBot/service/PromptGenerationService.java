package com.demo.ChatBot.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PromptGenerationService {
    private final ChatModel chatModel;

    public PromptGenerationService(ChatModel chatModel){
        this.chatModel = chatModel;
    }

    public String createRecipe(String ingredients,
                               String cuisine,
                               String dietaryRestrictions){
        PromptTemplate promptTemplate = getRecipePromptTemplate();
        Map<String, Object> params = Map.of(
                "ingredients", ingredients,
                "cuisine",cuisine,
                "dietaryRestrictions", dietaryRestrictions
        );
        Prompt prompt = promptTemplate.create(params);
        return chatModel.call(prompt).getResult().getOutput().getText();
    }

    private static PromptTemplate getRecipePromptTemplate() {
        String template= """
                I want to create a recipe using the following ingredients: {ingredients}. 
                The cuisine type I prefer is {cuisine}. 
                Please consider the following dietary restrictions: {dietaryRestrictions}.
                Please provide me a detailed  recipe including title, list of cooking ingredients along with their measurements and cooking instructions.
                """;
        PromptTemplate promptTemplate = new PromptTemplate(template);
        return promptTemplate;
    }

    public String holidayPlanner(String destination,
                                 String areasOfInterest,
                                 int duration,
                                 int noOfTravelers,
                                 int budget,
                                 String tentativeTravelDates,
                                 String typeOfStay){
        PromptTemplate promptTemplate = getVacationPromptTemplate();
        Map<String, Object> params = Map.of(
                "destination", destination,
                "areasOfInterest", areasOfInterest,
                "duration",duration,
                "noOfTravelers", noOfTravelers,
                "budget", budget,
                "tentativeTravelDates", tentativeTravelDates,
                "typeOfStay", typeOfStay
        );
        Prompt prompt = promptTemplate.create(params);
        return chatModel.call(prompt).getResult().getOutput().getText();
    }

    private static PromptTemplate getVacationPromptTemplate(){
        String template = """
              I want to plan an exciting, tailor-made vacation plan crafted around the following preferences and constraints:
              Destination: {destination}.
              Areas of Interest: {areasOfInterest} (e.g., beaches, historical sites, nature, food, adventure, etc.).
              Trip Duration: {duration} days.
              Number of Travelers: {noOfTravelers}
              Tentative Travel Dates: {tentativeTravelDates}.
              Total Budget: {budget} INR.
              Preferred Type of Accommodation: {typeOfStay} (e.g., hotel, homestay, resort, hostel).
              Please generate a comprehensive vacation plan that includes the following:.
              1. Day-wise itinerary covering key attractions and activities aligned with my interests.
              2. Recommendations for comfortable and affordable accommodations, matching my preferred stay type and within budget (include names, price range per night, and contact info or booking link).
              3. Cost-effective travel options for reaching the destination and for local transport during the trip (include approximate fares and providers if possible).
              4. Dining suggestions with good, hygienic, and affordable local restaurants or cafes (include average cost per meal and contact/location details).
              5. Ensure that the entire trip fits within the specified budget, including all major expenses.
              6. At the end, provide a summary table of approximate total cost, breaking down accommodation, travel, food, and miscellaneous expenses, and confirm whether it stays within the specified budget.
              """;
        PromptTemplate promptTemplate = new PromptTemplate(template);
        return promptTemplate;
    }
}
