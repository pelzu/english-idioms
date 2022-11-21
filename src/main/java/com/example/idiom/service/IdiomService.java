package com.example.idiom.service;

import com.example.idiom.model.IdiomModel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IdiomService {
    final static String audioUri = "/sound/idioms/example/";
    final static String idiomUri = "https://www.ang.pl/slownictwo/idiomy/";
    final static String englishWordBegin = "class=\"sm2_button\">play</a>";
    final static String polishWordBegin = "<tr><td><span class=\"pl_flag\">tłumaczenie:</span></td><td class=\"pol\">";
    final static String polengWordEnd = "</td></tr>";
    final static String exampleWordEnd = "</td>";

    RestTemplate restTemplate = new RestTemplate();

    public IdiomModel getOneIdiom(int httpEnd) {

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




