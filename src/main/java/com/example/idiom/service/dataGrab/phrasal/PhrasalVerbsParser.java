package com.example.idiom.service.dataGrab.phrasal;

import com.example.idiom.model.phrasal.PhrasalIdComparator;
import com.example.idiom.model.phrasal.PhrasalVerb;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class PhrasalVerbsParser {

    private final String PREFIX_LINK = "https://www.ang.pl";

    public List<PhrasalVerb> parseToPhrasalVerbs(Elements elements) {
        List<PhrasalVerb> phrasalVerbList = new ArrayList<>();

        elements.forEach(element -> {
            PhrasalVerb phrasalVerb = PhrasalVerb.builder()
                    .id(getId(element))
                    .englishMeaning(getEnglishMeaning(element))
                    .polishMeaning(getPolishTranslation(element))
                    .englishExample(getExampleEnglish(element))
                    .linkToPhrasalVerb(getLinkToPhrasalVerb(element)).
                    build();
            log.info(phrasalVerb.toString());
            phrasalVerbList.add(phrasalVerb);
        });
        phrasalVerbList.sort(new PhrasalIdComparator());
        return phrasalVerbList;
    }

    public String getPolishTranslation(Element el) {
        return el.select("p[class=pol]").text();
    }

    public String getExampleEnglish(Element el) {
        return el.select("div[class=medium-5 columns]").select("p").text();
    }

    public String getEnglishMeaning(Element el) {
        return el.select("p[class=big mtop]").select("a[href]").text();
    }

    public String getLinkToPhrasalVerb(Element el) {
        return PREFIX_LINK + el.select("p[class=big mtop]").select("a[href]").attr("href");
    }

    public String getId(Element el) {
        String idNumber = el.select("p[class=big mtop]").select("a[href]").attr("href");
        idNumber = idNumber.substring(idNumber.lastIndexOf("/") + 1);
        return idNumber;
    }
}
