package com.example.idiom.service.dataGrab.phrasal;

import com.example.idiom.model.PhrasalVerb;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

@Service
public class PhrasalCsvConverter {
    public void save(List<PhrasalVerb> phrasalVerbList) {
        PrintWriter printWriter;

        {
            try {
                printWriter = new PrintWriter(new File("src/main/resources/static/csv/phrasalVerb.csv"));
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
                csvData.ensureCapacity(csvData.length());
                System.out.println(csvData);
                printWriter.write(csvData.toString());
                printWriter.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
