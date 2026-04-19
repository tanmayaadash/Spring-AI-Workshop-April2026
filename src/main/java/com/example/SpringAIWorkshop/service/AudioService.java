package com.example.SpringAIWorkshop.service;

import org.springframework.ai.audio.tts.TextToSpeechPrompt;
import org.springframework.ai.audio.tts.TextToSpeechResponse;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.OpenAiAudioSpeechOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class AudioService {

    private final OpenAiAudioSpeechModel audioSpeechModel;

    public AudioService(OpenAiAudioSpeechModel audioSpeechModel) {
        this.audioSpeechModel = audioSpeechModel;
    }

    public String convertTextToSpeech(String text){

        OpenAiAudioSpeechOptions openAiAudioSpeechOptions = OpenAiAudioSpeechOptions.builder()
                .model("gpt-4o-mini-tts")
                .voice(OpenAiAudioApi.SpeechRequest.Voice.SHIMMER)
                .responseFormat(OpenAiAudioApi.SpeechRequest.AudioResponseFormat.MP3)
                .speed(1.0)
                .build();

        TextToSpeechPrompt textToSpeechPrompt = new TextToSpeechPrompt(text,openAiAudioSpeechOptions);
        TextToSpeechResponse textToSpeechResponse = audioSpeechModel.call(textToSpeechPrompt);

        byte[] audio = textToSpeechResponse.getResult().getOutput();

        //convert byte array to mp3 file
        try (FileOutputStream f = new FileOutputStream("voice.mp3")) {
            f.write(audio);
            System.out.println("Audio file saved as output.mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Audio generation successful. Check mp3 file.";
    }
}
