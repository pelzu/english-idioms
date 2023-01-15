package com.example.idiom.service.dataGrab.phrasal;

import com.example.idiom.model.phrasal.PhrasalComparator;
import com.example.idiom.model.phrasal.PhrasalVerb;
import com.example.idiom.model.phrasal.PhrasalVerbDao;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PhrasalVerbsParser {

    private final String PREFIX_LINK = "https://www.ang.pl";



    public List<PhrasalVerb> parseToPhrasalVerbs(Elements elements) {
        List<PhrasalVerb> phrasalVerbList = new ArrayList<>();

        elements.forEach(element -> {
            PhrasalVerb phrasalVerb = PhrasalVerb.builder()
                    .id(Long.valueOf(getId(element)))
                    .englishMeaning(getEnglishMeaning(element))
                    .polishMeaning(getPolishTranslation(element))
                    .englishExample(getExampleEnglish(element))
                    .linkToPhrasalVerb(getLinkToPhrasalVerb(element)).
                    build();

            phrasalVerbList.add(phrasalVerb);
        });
        phrasalVerbList.sort(new PhrasalComparator());

        log.info("Parsed and added " + phrasalVerbList.size() + " number of Phrasal to list");
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
