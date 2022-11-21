package com.example.idiom.service;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class AudioService {
    RestTemplate restTemplate = new RestTemplate();
    private String FILE_URL = "https://www.ang.pl/sound/idioms/example/mr-brown-has-a-heart-of-gold-he.mp3";

    public void downLoadAudio() throws IOException {
        File fileOrzechowe = new File("C:\\Users\\pelzu\\IdeaProjects\\idiom\\src\\main\\resources\\static\\mr-brown-has-a-heart-of-gold-he.mp3");
        File file = restTemplate.execute(FILE_URL, HttpMethod.GET, null, clientHttpResponse -> {
            File ret = File.createTempFile("download", "tmp");
            fileOrzechowe.createNewFile();
            StreamUtils.copy(clientHttpResponse.getBody(), new FileOutputStream(fileOrzechowe));
            return ret;
        });


    }

}
