package com.example.idiom.service.dataGrab.idiom;

import com.example.idiom.model.idiom.Idiom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Service
@Slf4j
public class IdiomAudioGrabber {
    private final String MP3_TRANSLATION_DESTINATION = "src/main/resources/static/mp3/translation/";
    private final String MP3_EXAMPLE_DESTINATION = "src/main/resources/static/mp3/example/";
    RestTemplate restTemplate = new RestTemplate();

    public void downLoadAudio(List<Idiom> idiomList) {
        createDirForMp3();
        for (Idiom idiom : idiomList) {
            Runnable r = () -> {
                log.info(idiom.toString());
                File translatedFileMp3 = restTemplate.execute(idiom.getAudioTranslateLink(), HttpMethod.GET, null, clientHttpResponse -> {
                    File mp3File = new File(MP3_TRANSLATION_DESTINATION + idiom.getId() + "translated" + ".mp3");
                    mp3File.createNewFile();
                    StreamUtils.copy(clientHttpResponse.getBody(), new FileOutputStream(mp3File));
                    return mp3File;
                });

                File exampleFileMp3 = restTemplate.execute(idiom.getAudioExampleLink(), HttpMethod.GET, null, clientHttpResponse -> {
                    File mp3File = new File(MP3_EXAMPLE_DESTINATION + idiom.getId() + "example" + ".mp3");
                    mp3File.createNewFile();
                    StreamUtils.copy(clientHttpResponse.getBody(), new FileOutputStream(mp3File));
                    return mp3File;
                });
            };
            var t = new Thread(r);
            t.start();
        }
    }

    public void createDirForMp3() {
        new File("src/main/resources/static/mp3").mkdir();
        new File("src/main/resources/static/mp3/example").mkdir();
        new File("src/main/resources/static/mp3/translation").mkdir();


    }

}


