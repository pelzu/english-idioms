package com.example.idiom.service;

import com.example.idiom.model.Idiom;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;

@Service
public class AudioService {
    private static final String MP3_DESTINATION = "C:\\Users\\pelzu\\IdeaProjects\\idiom\\src\\main\\resources\\static\\mp3\\";
    RestTemplate restTemplate = new RestTemplate();

    public void downLoadAudio(Idiom idiom) {

        File file = restTemplate.execute(idiom.getAudioTranslateLink(), HttpMethod.GET, null, clientHttpResponse -> {
            File mp3File = new File(MP3_DESTINATION + idiom.getId() + ".mp3");
            mp3File.createNewFile();
            StreamUtils.copy(clientHttpResponse.getBody(), new FileOutputStream(mp3File));
            return mp3File;
        });


    }

}
