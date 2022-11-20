package com.example.idiom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

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
        int idiomLenght=521;
        idiomsArrayList.ensureCapacity(idiomLenght);
        for (int i = 1; i <= idiomLenght; i++) {
            idiomsArrayList.add(getOneIdiom(i));
        }
        CsVWriter csVWriter = new CsVWriter();
        csVWriter.save(idiomsArrayList);


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
