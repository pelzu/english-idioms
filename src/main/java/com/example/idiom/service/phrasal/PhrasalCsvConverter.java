package com.example.idiom.service.phrasal;

import com.example.idiom.model.phrasal.PhrasalVerb;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

@Slf4j
public class PhrasalCsvConverter {
    public void savePhrasalVerbToCsvFile(List<PhrasalVerb> phrasalVerbList) {
        createDefaultDirectoryForCsv();
        PrintWriter phrasalVerbWriter;
        File csvFile = new File("src/main/resources/static/csv/phrasalVerb.csv");
        if (!csvFile.exists()) {
            {
                try {
                    phrasalVerbWriter = new PrintWriter(csvFile);
                    StringBuffer csvHeader = new StringBuffer();
                    csvHeader.append("id;polishMeaning;englishMeaning;englishExample;LinkToPhrasalVerb\n");
                    StringBuffer csvData = new StringBuffer();
                    phrasalVerbWriter.write(csvHeader.toString());
                    for (PhrasalVerb phrasalVerb : phrasalVerbList) {
                        csvData.append(phrasalVerb.getId() + ";");
                        csvData.append(phrasalVerb.getPolishMeaning() + ";");
                        csvData.append(phrasalVerb.getEnglishMeaning() + ";");
                        csvData.append(phrasalVerb.getEnglishExample() + ";");
                        csvData.append(phrasalVerb.getLinkToPhrasalVerb() + "\n");

                    }
                    phrasalVerbWriter.write(csvData.toString());
                    phrasalVerbWriter.close();
                    log.info("CSV file is created: " + csvFile.getAbsolutePath());

                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    public void createDefaultDirectoryForCsv() {
        File staticFolder = new File("src/main/resources/static");
        if (!staticFolder.exists()) {
            staticFolder.mkdir();
            log.info("Directory is created: " + staticFolder.getAbsolutePath());

        }
        File csvFolder = new File("src/main/resources/static/csv");
        if (!csvFolder.exists()) {
            csvFolder.mkdirs();
            log.info("Directory is created: " + csvFolder.getAbsolutePath());
        }

    }

}
