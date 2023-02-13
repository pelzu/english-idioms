package com.example.idiom.service.idiom;

import com.example.idiom.model.idiom.Idiom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
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
        for (Idiom idiom : idiomList) {
            executorService.submit(() -> {
                Thread.currentThread().setName("AudioDownloadThread");

                getTranslatedMp3(idiom);
                getExampleMp3File(idiom);
            });
        }
        executorService.shutdown();
    }

    public void createDirForMp3() {
        List<File> folderList = new ArrayList<>();
        File static_folder = new File("src/main/resources/static");
        File static_mp3_folder = new File("src/main/resources/static/mp3");
        File static_mp3_example_folder = new File("src/main/resources/static/mp3/example");
        File static_mp3_translation_folder = new File("src/main/resources/static/mp3/translation");
        folderList.add(static_folder);
        folderList.add(static_mp3_folder);
        folderList.add(static_mp3_example_folder);
        folderList.add(static_mp3_translation_folder);
        for (File file : folderList
        ) {
            if (!file.exists()) {
                file.mkdirs();
                log.info("Directory created " + file.getAbsolutePath());
            }
        }
    }

    public void getTranslatedMp3(Idiom idiom) {
        File transMp3File
                = new File(MP3_TRANSLATION_DESTINATION + idiom.getId() +"_Translated"+ "_" + deleteDots(idiom.getEnglishMeaning()) + ".mp3");

        if (!transMp3File.exists()) {
            restTemplate.execute(idiom.getAudioTranslateLink(), HttpMethod.GET, null, clientHttpResponse -> {
                transMp3File.createNewFile();

                try (FileOutputStream fos = new FileOutputStream(transMp3File)) {
                    StreamUtils.copy(clientHttpResponse.getBody(), fos);
                }
                log.info("Created translated: " + transMp3File.getAbsolutePath());
                return transMp3File;
            });
        }
    }

    public void getExampleMp3File(Idiom idiom) {
        File exMp3File = new File(MP3_EXAMPLE_DESTINATION + idiom.getId() +"_Example"+ "_" + deleteDots(idiom.getEnglishExample()) + ".mp3");
        if (!exMp3File.exists()) {
            restTemplate.execute(idiom.getAudioExampleLink(), HttpMethod.GET, null, clientHttpResponse -> {
                exMp3File.createNewFile();
                try (FileOutputStream fos = new FileOutputStream(exMp3File)) {
                    StreamUtils.copy(clientHttpResponse.getBody(), fos);
                }
                log.info("Created example: " + exMp3File.getAbsolutePath());
                return exMp3File;
            });
        }
    }

    private String deleteDots(String englishExample) {
        return   englishExample.replace(".","").replace(" ","_");
    }


}


