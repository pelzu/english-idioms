package com.example.idiom.service;

import com.example.idiom.model.Idiom;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IdiomService {
    final static String DOMAIN_ANG_PL = "https://www.ang.pl";
    final static String AUDIO_URI_TRANSLATE = "/sound/idioms/";

    final static String AUDIO_URI_EXAMPLE = "/sound/idioms/example";

    final static String AUDIO_URI_END = ".mp3\"";
    final static String IDIOM_URI = "https://www.ang.pl/slownictwo/idiomy/";
    final static String ENGLISH_WORD_BEGIN = "class=\"sm2_button\">play</a>";
    final static String POLISH_WORD_BEGIN = "<tr><td><span class=\"pl_flag\">tłumaczenie:</span></td><td class=\"pol\">";
    final static String POLENG_WORD_END = "</td></tr>";
    final static String EXAMPLE_WORD_END = "<";



    RestTemplate restTemplate = new RestTemplate();

    public Idiom getOneIdiom(int httpEnd) {

        Idiom idioms = new Idiom();


        String result = restTemplate.getForObject(this.IDIOM_URI + httpEnd, String.class);

        int englishBeginingIndex = result.indexOf(ENGLISH_WORD_BEGIN);
        int englishLastIndex = result.indexOf(POLENG_WORD_END, englishBeginingIndex);
        idioms.setEnglishMeaning(result.substring(englishBeginingIndex + ENGLISH_WORD_BEGIN.length(), englishLastIndex));


        int polishBeginningIndex = result.indexOf(POLISH_WORD_BEGIN);
        int polishLastIndex = result.indexOf(POLENG_WORD_END, polishBeginningIndex);
        idioms.setPolishMeaning(result.substring(polishBeginningIndex + POLISH_WORD_BEGIN.length(), polishLastIndex));


        int exampleBeginingIndex = result.indexOf(ENGLISH_WORD_BEGIN, englishBeginingIndex + ENGLISH_WORD_BEGIN.length());
        int exampleLastIndex = result.indexOf(EXAMPLE_WORD_END,exampleBeginingIndex+ ENGLISH_WORD_BEGIN.length());
        idioms.setEnglishExample(result.substring(exampleBeginingIndex + ENGLISH_WORD_BEGIN.length(), exampleLastIndex));




        int audioTranslateBeginingIndex = result.indexOf(AUDIO_URI_TRANSLATE);
        int audioTranslateLastIndex = result.indexOf(AUDIO_URI_END, audioTranslateBeginingIndex);
        idioms.setAudioTranslateLink(DOMAIN_ANG_PL+result.substring(audioTranslateBeginingIndex, audioTranslateLastIndex+AUDIO_URI_END.length()-1));

        int audioExampleBeginingIndex = result.indexOf(AUDIO_URI_EXAMPLE);
        int audioExampleLastIndex = result.indexOf(AUDIO_URI_END, audioExampleBeginingIndex);
        idioms.setAudioExampleLink(DOMAIN_ANG_PL+result.substring(audioExampleBeginingIndex, audioExampleLastIndex+AUDIO_URI_END.length()-1));

        idioms.setLinkToIdiom(IDIOM_URI+httpEnd);

        idioms.setId(new Integer(httpEnd).toString());

        System.out.println(idioms);

        return idioms;
    }

}




