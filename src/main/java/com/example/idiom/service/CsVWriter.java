package com.example.idiom.service;

import com.example.idiom.model.Idiom;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
@Service
public class CsVWriter {
    public void save(ArrayList<Idiom> idioms) {


        PrintWriter printWriter;
        {
            try {
                printWriter = new PrintWriter(new File("src/main/resources/static/csv/idiom.csv"));
                StringBuffer csvHeader = new StringBuffer("");
                csvHeader.append("id;polishMeaning;englishMeaning;englishExample\n");
                StringBuffer csvData = new StringBuffer("");
                printWriter.write(csvHeader.toString());

                for (Idiom idiom : idioms) {
                    csvData.append(idiom.getId() + ";");
                    csvData.append(idiom.getPolishMeaning() + ";");
                    csvData.append(idiom.getEnglishMeaning() + ";");
                    csvData.append(idiom.getEnglishExample() + "\n");

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






