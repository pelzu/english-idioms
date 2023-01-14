package com.example.idiom.service.dataGrab.idiom;

import com.example.idiom.model.idiom.Idiom;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Slf4j
public class IdiomCsVConverter {


    public void save(List<Idiom> idioms) {
        createDefaultDirForCsv();
        PrintWriter printWriter;
        File csvFile = new File("src/main/resources/static/csv/idiom.csv");

        if (!csvFile.exists()) {
            try {
                printWriter = new PrintWriter(csvFile);
                StringBuffer csvHeader = new StringBuffer();
                csvHeader.append("id;polishMeaning;englishMeaning;LinkToIdiom;AudioTranslateLink;AudioExampleLink;englishExample\n");
                StringBuffer csvData = new StringBuffer();
                printWriter.write(csvHeader.toString());
                for (Idiom idiom : idioms) {

                    csvData.append(idiom.getId() + ";");
                    csvData.append(idiom.getPolishMeaning() + ";");
                    csvData.append(idiom.getEnglishMeaning() + ";");
                    csvData.append(idiom.getLinkToIdiom() + ";");
                    csvData.append(idiom.getAudioTranslateLink() + ";");
                    csvData.append(idiom.getAudioExampleLink() + ";");
                    csvData.append(idiom.getEnglishExample() + "\n");

                }
                printWriter.write(csvData.toString());
                printWriter.close();
                log.info("CSV file is created: " + csvFile.getAbsolutePath());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void createDefaultDirForCsv() {
        File staticFolder = new File("src/main/resources/static");
        if (!staticFolder.exists()) {
            staticFolder.mkdir();
            log.info("Directory is created: " + staticFolder.getAbsolutePath());

        }
        File csvDir = new File("src/main/resources/static/csv");
        if (!csvDir.exists()) {
            csvDir.mkdirs();
            log.info("Directory is created: " + csvDir.getAbsolutePath());

        }


    }
}








