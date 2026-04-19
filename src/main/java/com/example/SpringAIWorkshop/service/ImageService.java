package com.example.SpringAIWorkshop.service;

import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ImageService {

    private final OpenAiImageModel openAiImageModel;

    public ImageService(OpenAiImageModel openAiImageModel) {
        this.openAiImageModel = openAiImageModel;
    }


    public String generateImage(String prompt)
    {
        OpenAiImageOptions openAiImageOptions = OpenAiImageOptions.builder()
                .quality("hd")
                .N(1)
                .height(1024)
                .width(1024)
                .build();

        ImageResponse imageResponse = openAiImageModel.call(new ImagePrompt(prompt,openAiImageOptions));

        System.out.println(imageResponse.getResult());
        return Objects.requireNonNull(imageResponse.getResult()).getOutput().getUrl();
    }

}
