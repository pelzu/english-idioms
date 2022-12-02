package com.example.idiom.service;

import com.example.idiom.model.Idiom;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class IdiomPageService {
    final static String IDIOM_URL = "https://www.ang.pl/slownictwo/idiomy/";
    final static String AUDIO_EXAMPLE_URL = "https://www.ang.pl/sound/idioms/example/";
    final static String AUDIO_MEANING_URL = "https://www.ang.pl/sound/idioms/";
    private static final String DOMAIN_URL = "https://www.ang.pl/slownictwo/idiomy/page/";
    private static final String TRIM_RESULT_BEGIN = "<div class=\"row\" style=\"border-bottom: 1px solid #ccc;\">";
    private static final String TRIM_RESULT_END = "<div class=\"mtop\">";

    private static final String AUDIO_MEANING_BEGIN_PHRASE = "<a href=\"/sound/idioms/";

    private static final String IDIOM_LINK_BEGIN_PHRASE = "<a href=\"/slownictwo/idiomy/";

    private static final String ENGLISH_MEANING_BEGIN_PHRASE = ">";

    private static final String POLISH_MEANING_BEGIN_PHRASE = "<p class=\"pol\">";

    private static final String AUDIO_EXAMPLE_BEGIN_PHRASE = "<a href=\"/sound/idioms/example/";

    private static final String ENGLISH_EXAMPLE_BEGIN_PHRASE = "class=\"sm2_button\">play</a>";


    private final static RestTemplate restTemplate = new RestTemplate();


    private static final int arraySize = 521;
    private static final ArrayList<Idiom> IDIOM_ARRAY_LIST = new ArrayList<>();

    private int temResultIndex = 0;

    public IdiomPageService() {
    }

    public void getOnePageOfIdioms(int httpEnd) {

        String result = restTemplate.getForObject(DOMAIN_URL + httpEnd, String.class);
        result = getTrimResult(result);

        for (int i = 0; i <= 49; i++) {
            IDIOM_ARRAY_LIST.add(getIdiomModel(result));
//               System.out.println(idiomModelArrayList.get(idiomModelArrayList.size()-1));

        }
        System.out.println(IDIOM_ARRAY_LIST.size());


    }

    public String getTrimResult(String htmlResult) {
        int trimIndexBegin = htmlResult.indexOf(TRIM_RESULT_BEGIN);
        int trimIndexEnd = htmlResult.indexOf(TRIM_RESULT_END);

        htmlResult = htmlResult.replaceAll("<br />" + System.lineSeparator(), "");

        return htmlResult.substring(trimIndexBegin, trimIndexEnd + TRIM_RESULT_END.length());
    }

    public Idiom getIdiomModel(String trimedResult) {


        Idiom idiom = new Idiom();
        int audioMeaningBeginIndex = trimedResult.indexOf(AUDIO_MEANING_BEGIN_PHRASE, temResultIndex) + AUDIO_MEANING_BEGIN_PHRASE.length();
        int audioMeaningEndIndex = trimedResult.indexOf("\"", audioMeaningBeginIndex);
        idiom.setAudioTranslateLink(AUDIO_MEANING_URL + trimedResult.substring(audioMeaningBeginIndex, audioMeaningEndIndex));

        int idiomLinkBeginIndex = trimedResult.indexOf(IDIOM_LINK_BEGIN_PHRASE, audioMeaningEndIndex) + IDIOM_LINK_BEGIN_PHRASE.length();
        int idiomLinkEndIndex = trimedResult.indexOf("\"", idiomLinkBeginIndex);
        idiom.setLinkToIdiom(IDIOM_URL + trimedResult.substring(idiomLinkBeginIndex, idiomLinkEndIndex));
        idiom.setId(trimedResult.substring(idiomLinkBeginIndex, idiomLinkEndIndex));

        int englishMeaningBeginIndex = trimedResult.indexOf(ENGLISH_MEANING_BEGIN_PHRASE, idiomLinkEndIndex) + ENGLISH_MEANING_BEGIN_PHRASE.length();
        int englishMeaningEndIndex = trimedResult.indexOf("</a>", englishMeaningBeginIndex);
        idiom.setEnglishMeaning(trimedResult.substring(englishMeaningBeginIndex, englishMeaningEndIndex));

        int polishMeaningBeginIndex = trimedResult.indexOf(POLISH_MEANING_BEGIN_PHRASE, englishMeaningEndIndex) + POLISH_MEANING_BEGIN_PHRASE.length();
        int polishMeaningEndIndex = trimedResult.indexOf("</p>", polishMeaningBeginIndex);
        idiom.setPolishMeaning(trimedResult.substring(polishMeaningBeginIndex, polishMeaningEndIndex));

        int audioExampleBeginIndex = trimedResult.indexOf(AUDIO_EXAMPLE_BEGIN_PHRASE, polishMeaningBeginIndex) + AUDIO_EXAMPLE_BEGIN_PHRASE.length();
        int audioExampleingEndIndex = trimedResult.indexOf("\"", audioExampleBeginIndex);
        idiom.setAudioExampleLink(AUDIO_EXAMPLE_URL + trimedResult.substring(audioExampleBeginIndex, audioExampleingEndIndex));

        int englishExampleBeginIndex = trimedResult.indexOf(ENGLISH_EXAMPLE_BEGIN_PHRASE, audioExampleingEndIndex) + ENGLISH_EXAMPLE_BEGIN_PHRASE.length();
        int englishExampleingEndIndex = trimedResult.indexOf("</p>", englishExampleBeginIndex);
        idiom.setEnglishExample(trimedResult.substring(englishExampleBeginIndex, englishExampleingEndIndex));

        setTemResultIndex(englishExampleingEndIndex);
        System.out.println(idiom);
        return idiom;
    }

    public void setTemResultIndex(int temResultIndex) {
        this.temResultIndex = temResultIndex;
    }


    public void getArraySize() {
        System.out.println("Rozmiar Tablicy=" + IDIOM_ARRAY_LIST.size());
    }

    public void getArray() {
        for (Idiom idiom : IDIOM_ARRAY_LIST
        ) {
            System.out.println(idiom);

        }
    }


}
