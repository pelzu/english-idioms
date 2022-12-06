package com.example.idiom.inter;

import com.example.idiom.model.PhrasalVerb;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
public class PhrasalVerbsImpl implements PhrasalVerbsInterface {


    private Elements elements;

    @Override
    public void getPhrasalVerbs() throws IOException {
        parseToPhrasalVerbs(getDocument());
    }

    @Override
    public Elements getDocument() throws IOException {
        this.elements = Jsoup.connect(BASE_LINK).get().select("div[style*=border-bottom: 1px solid #ccc;]");
        return elements;
    }


    @Override
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

        String tempField = el.select("p[class=pol]").text();
        return tempField;
    }
    public String getExampleEnglish(Element el) {
        String englishExample = el.select("div[class=medium-5 columns]").select("p").text();
        return englishExample;
    }
    public String getEnglishMeaning(Element el) {
        String englishTranslation = el.select("p[class=big mtop]").select("a[href]").text();
        return englishTranslation;
    }
    private String getLinkToPhrasalVerb(Element el) {
        String phrasalVerbLink = el.select("p[class=big mtop]").select("a[href]").attr("href");
        return PREFIX_LINK + phrasalVerbLink;
    }
    private String getId (Element el) {
        String idNumber = el.select("p[class=big mtop]").select("a[href]").attr("href");
        idNumber = idNumber.substring(idNumber.lastIndexOf("/") + 1);
        return idNumber;
    }


}
