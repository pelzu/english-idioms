package com.example.idiom.service.dataGrab.phrasal;

import com.example.idiom.model.phrasal.PhrasalVerb;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

@Service
public class PhrasalCsvConverter {
    public void save(List<PhrasalVerb> phrasalVerbList) {


        createDirForCsv();
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
                printWriter.write(csvData.toString());
                printWriter.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public void createDirForCsv() {
        File csvDir=new File("src/main/resources/static/csv") ;
        csvDir.mkdirs();

    }

}
