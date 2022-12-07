package com.example.idiom.inter;

import com.example.idiom.model.PhrasalVerb;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class PhrasalVerbsImpl implements DataGrabberAngPl {


    @Override
    public void getObject() throws IOException {
        parseToPhrasalVerbs(getElements());
    }

    public Elements getElements() throws IOException {
        int paginationNumbers = getNumberOfPagePhrasalVerb();
        Elements elements = new Elements();
        for (int i = 1; i <= paginationNumbers; i++) {
            elements.addAll(Jsoup.connect(PHRASAL_VERB_LINK + i).get().select("div[style*=border-bottom: 1px solid #ccc;]"));
        }
        return elements;
    }

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

    public int getNumberOfPagePhrasalVerb() throws IOException {
        Document tempDoc = Jsoup.connect(PHRASAL_VERB_LINK).get();
        Elements elements = tempDoc.getElementsByClass("pagination");
        String numberOfPage = elements.first().lastElementChild().text();
        return Integer.parseInt(numberOfPage);

    }


}
