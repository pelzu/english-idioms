package com.example.idiom.service.phrasal;

import com.example.idiom.model.phrasal.PhrasalVerb;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

@Slf4j
public class PhrasalCsvConverter {
    public void save(List<PhrasalVerb> phrasalVerbList) {
        createDirForCsv();
        PrintWriter printWriter;
        File csvFile = new File("src/main/resources/static/csv/phrasalVerb.csv");
        if (!csvFile.exists()) {
            {
                try {
                    printWriter = new PrintWriter(csvFile);
                    StringBuffer csvHeader = new StringBuffer();
                    csvHeader.append("id;polishMeaning;englishMeaning;englishExample;LinkToPhrasalVerb\n");
                    StringBuffer csvData = new StringBuffer();
                    printWriter.write(csvHeader.toString());
                    for (PhrasalVerb phrasalVerb : phrasalVerbList) {
                        csvData.append(phrasalVerb.getId() + ";");
                        csvData.append(phrasalVerb.getPolishMeaning() + ";");
                        csvData.append(phrasalVerb.getEnglishMeaning() + ";");
                        csvData.append(phrasalVerb.getEnglishExample() + ";");
                        csvData.append(phrasalVerb.getLinkToPhrasalVerb() + "\n");

                    }
                    printWriter.write(csvData.toString());
                    printWriter.close();
                    log.info("CSV file is created: " + csvFile.getAbsolutePath());

                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    public void createDirForCsv() {
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
