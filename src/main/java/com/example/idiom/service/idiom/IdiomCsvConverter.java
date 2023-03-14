package com.example.idiom.service.idiom;

import com.example.idiom.model.idiom.Idiom;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Slf4j
public class IdiomCsvConverter {


    public void saveIdiomListToCsvFile(List<Idiom> idioms) {
        createDefaultDirForCsv();
        PrintWriter idiomWriter;
        File csvFile = new File("src/main/resources/static/csv/idiom.csv");

        if (!csvFile.exists()) {
            try {
                idiomWriter = new PrintWriter(csvFile);
                StringBuffer csvIdiomHeader = new StringBuffer();
                csvIdiomHeader.append("id;polishMeaning;englishMeaning;LinkToIdiom;AudioTranslateLink;AudioExampleLink;englishExample\n");
                StringBuffer csvIdiom = new StringBuffer();
                idiomWriter.write(csvIdiomHeader.toString());
                for (Idiom idiom : idioms) {

                    csvIdiom.append(idiom.getId() + ";");
                    csvIdiom.append(idiom.getPolishMeaning() + ";");
                    csvIdiom.append(idiom.getEnglishMeaning() + ";");
                    csvIdiom.append(idiom.getLinkToIdiom() + ";");
                    csvIdiom.append(idiom.getAudioTranslateLink() + ";");
                    csvIdiom.append(idiom.getAudioExampleLink() + ";");
                    csvIdiom.append(idiom.getEnglishExample() + "\n");

                }
                idiomWriter.write(csvIdiom.toString());
                idiomWriter.close();
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








