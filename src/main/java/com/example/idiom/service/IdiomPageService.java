package com.example.idiom.service;

import com.example.idiom.model.IdiomModel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class IdiomPageService {
    private static final String DOMAIN_URL = "https://www.ang.pl/slownictwo/idiomy/page/" ;

    private static final String TRIM_RESULT_BEGIN ="<div class=\"row\" style=\"border-bottom: 1px solid #ccc;\">";
    private static final String TRIM_RESULT_END="<div class=\"mtop\">";

    private static final String ENGLISH_MEANING_BEGIN="class=\"sm2_button\">play</a>";

    private static final String ENGLISH_AUDIO__BEGIN="<a href=\"/sound/idioms/>";


    private static final String POLISH_MEANING_BEGIN="<div class=\"medium-3 columns\"><p class=\"pol\">";
    private RestTemplate restTemplate = new RestTemplate();
    private ArrayList<IdiomModel> idiomModel=new ArrayList<>();


    public void getOnePageOfIdioms(int httpEnd) {
        String result = restTemplate.getForObject(this.DOMAIN_URL + httpEnd, String.class);
        result=getTrimResult(result);
        System.out.println(result);
        getIdiomModel(result);
    }

    public String getTrimResult(String htmlResult) {
        int trimIndexBegin=htmlResult.indexOf(TRIM_RESULT_BEGIN);
        int trimIndexEnd=htmlResult.indexOf(TRIM_RESULT_END);

        return htmlResult.substring(trimIndexBegin,trimIndexEnd+TRIM_RESULT_END.length());
    }

    public void getIdiomModel(String trimedResult){


         int polishMeaningBeginIndex=   trimedResult.indexOf(ENGLISH_MEANING_BEGIN);
         polishMeaningBeginIndex=polishMeaningBeginIndex+trimedResult.indexOf(">",polishMeaningBeginIndex);
        System.out.println(trimedResult.substring(polishMeaningBeginIndex,polishMeaningBeginIndex+120));




    }
}
