package com.example.idiom;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class CsVWriter {
    public void save(ArrayList<IdiomModel> idiomModels) {
        PrintWriter printWriter;
        {
            try {
                printWriter = new PrintWriter(new File("test.csv"));
                StringBuffer csvHeader = new StringBuffer("");
                csvHeader.append("id;polishMeaning;englishMeaning;englishExample\n");
                StringBuffer csvData = new StringBuffer("");
                printWriter.write(csvHeader.toString());

                for (IdiomModel idiom : idiomModels) {
                    csvData.append(idiom.getId() + ";");
                    csvData.append(idiom.getPolishMeaning() + ";");
                    csvData.append(idiom.getEnglishMeaning() + ";");
                    csvData.append(idiom.getEnglishExample() + "\n");

                }
                System.out.println(csvData);
                printWriter.write(csvData.toString());
                printWriter.close();


            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }
}






