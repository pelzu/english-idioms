package com.example.idiom.service.dataGrab.idiom;

import com.example.idiom.model.idiom.Idiom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Slf4j
public class IdiomAudioGrabber {
    private final String MP3_TRANSLATION_DESTINATION = "src/main/resources/static/mp3/translation/";
    private final String MP3_EXAMPLE_DESTINATION = "src/main/resources/static/mp3/example/";

    RestTemplate restTemplate = new RestTemplate();


    public void downLoadAudio(List<Idiom> idiomList) {
        ExecutorService executorService = Executors.newFixedThreadPool(30);
        createDirForMp3();
        long start = System.currentTimeMillis();
        for (Idiom idiom : idiomList) {
            executorService.submit(() -> {
                getTranslatedMp3(idiom);
                getExampleMp3File(idiom);
                long end = System.currentTimeMillis();
                log.warn("Time: " + (end - start));
            });
        }
        executorService.shutdown();
    }

    public void createDirForMp3() {
        new File("src/main/resources/static").mkdir();
        new File("src/main/resources/static/mp3").mkdir();
        new File("src/main/resources/static/mp3/example").mkdir();
        new File("src/main/resources/static/mp3/translation").mkdir();
    }

    public void getTranslatedMp3(Idiom idiom) {
        File transMp3File
                = new File(MP3_TRANSLATION_DESTINATION + idiom.getId() + "_translated" + ".mp3");
        if (!transMp3File.exists()) {
            restTemplate.execute(idiom.getAudioTranslateLink(), HttpMethod.GET, null, clientHttpResponse -> {
                transMp3File.createNewFile();

                try (FileOutputStream fos = new FileOutputStream(transMp3File)) {
                    StreamUtils.copy(clientHttpResponse.getBody(), fos);
                }
                log.info("Created " + transMp3File.getName());
                return transMp3File;
            });
        }
    }

    public void getExampleMp3File(Idiom idiom) {
        File exMp3File = new File(MP3_EXAMPLE_DESTINATION + idiom.getId() + "_example" + ".mp3");
        if (!exMp3File.exists()) {
            restTemplate.execute(idiom.getAudioExampleLink(), HttpMethod.GET, null, clientHttpResponse -> {
                exMp3File.createNewFile();
                try (FileOutputStream fos = new FileOutputStream(exMp3File)) {
                    StreamUtils.copy(clientHttpResponse.getBody(), fos);
                }
                log.info("Created " + exMp3File.getName());
                return exMp3File;
            });
        }
    }


}


