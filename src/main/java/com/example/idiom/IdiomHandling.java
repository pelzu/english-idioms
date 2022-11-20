package com.example.idiom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class IdiomHandling {
    final String idiomUri = "https://www.ang.pl/slownictwo/idiomy/";
    private String englishWordBegin = "class=\"sm2_button\">play</a>";
    private String polishWordBegin = "<tr><td><span class=\"pl_flag\">tłumaczenie:</span></td><td class=\"pol\">";
    private String polengWordEnd = "</td></tr>";
    private String exampleWordEnd = "</td>";
    ArrayList<IdiomModel> idiomsArrayList = new ArrayList<>();


    @GetMapping("/idiom")
    public void getEnglishMeaning() {

//        for (int i = 1; i <= 521; i++) {
//            idiomsArrayList.add(getOneIdiom(i));
//        }
        idiomsArrayList.add(getOneIdiom(1));
        idiomsArrayList.add(getOneIdiom(2));


    }

    public IdiomModel getOneIdiom(int httpEnd) {

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(this.idiomUri + httpEnd, String.class);

        int englishBeginingIndex = result.indexOf(englishWordBegin);
        int englishLastIndex = result.indexOf(polengWordEnd, englishBeginingIndex);
        int polishBeginningIndex = result.indexOf(polishWordBegin);
        int polishLastIndex = result.indexOf(polengWordEnd, polishBeginningIndex);
        int exampleBeginingIndex = result.indexOf(englishWordBegin, englishBeginingIndex + englishWordBegin.length());
        int exampleLastIndex = result.indexOf(exampleWordEnd, exampleBeginingIndex);

        IdiomModel idioms = new IdiomModel();
        idioms.setEnglishMeaning(result.substring(englishBeginingIndex + englishWordBegin.length(), englishLastIndex));
        idioms.setPolishMeaning(result.substring(polishBeginningIndex + polishWordBegin.length(), polishLastIndex));
        idioms.setEnglishExample(result.substring(exampleBeginingIndex + englishWordBegin.length(), exampleLastIndex));
        idioms.setId(httpEnd);
        System.out.println(idioms);
        return idioms;

    }

}
