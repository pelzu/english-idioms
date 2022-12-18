package com.example.idiom.service.dataGrab.idiom;

import com.example.idiom.model.idiom.Idiom;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

@Service
public class IdiomCsVConverter {

    public void save(List<Idiom> idioms) {

        createDirForCsv();
        PrintWriter printWriter;
        {
            try {
                printWriter = new PrintWriter(new File("src/main/resources/static/csv/idiom.csv"));
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
                csvData.ensureCapacity(csvData.length());
                printWriter.write(csvData.toString());
                printWriter.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void createDirForCsv() {
        File csvDir = new File("src/main/resources/static/csv");
        csvDir.mkdir() ;

    }
}








