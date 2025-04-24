package com.demo.ChatBot.service;


import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    private final OpenAiImageModel imageModel;

    public ImageService(OpenAiImageModel imageModel){
        this.imageModel = imageModel;
    }

    public ImageResponse generateImage(String prompt){
//        ImageResponse imageResponse = imageModel.call(new ImagePrompt(prompt));
        ImageResponse imageResponse = imageModel.call(
                                        new ImagePrompt(prompt,
                                                OpenAiImageOptions.builder()
                                                        .withModel("dall-e-2")
                                                        .N(1)
                                                        .height(256)
                                                        .width(256).build()));

        return imageResponse;
    }


}
