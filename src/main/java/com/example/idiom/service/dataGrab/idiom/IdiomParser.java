package com.example.idiom.service.dataGrab.idiom;

import com.example.idiom.model.idiom.Idiom;
import com.example.idiom.model.idiom.IdiomIdComparator;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class IdiomParser {


    private final String PREFIX_LINK = "https://www.ang.pl";

    @Autowired
    ServletContext servletContext;


    public List<Idiom> parseToIdiom(Elements elements) {
        String absolutePath = servletContext.getRealPath("resources/static/csv");
        log.info("*****Context is :"+absolutePath+"****");
        List<Idiom> idiomList = new ArrayList<>();
        elements.forEach(element -> {
            Idiom idiom = Idiom.builder()
                    .id(getIdNumber(element))
                    .audioTranslateLink(getMp3TranslateLink(element))
                    .linkToIdiom(getLinkToIdiom(element))
                    .englishMeaning(getEnglishTranslation(element))
                    .polishMeaning(getPolishTranslation(element))
                    .audioExampleLink(getExampleMp3Link(element))
                    .englishExample(getExampleEnglish(element))
                    .build();
            idiomList.add(idiom);
        });
        idiomList.sort(new IdiomIdComparator());
        log.info("Parsed and added " + idiomList.size() + " number of Idiom to list");


        return idiomList;
    }

    public String getExampleEnglish(Element el) {
        Node node = el.select("div[class=medium-5 columns]").select("p").first().childNodes().get(1);
        return node.toString();
    }

    public String getExampleMp3Link(Element el) {
        String exampleMp3Link = el.select("div[class=medium-5 columns]").select("a[href]").attr("href");
        return PREFIX_LINK + exampleMp3Link;
    }

    public String getPolishTranslation(Element el) {
        String polishTranslation = el.select("p[class=pol]").text();
        return polishTranslation;
    }

    public String getEnglishTranslation(Element el) {
        String englishTranslation = el.select("p[class=big mtop]").select("a[href]").next("a[href]").text();
        return englishTranslation;
    }

    private String getLinkToIdiom(Element el) {
        String idiomLink = el.select("p[class=big mtop]").select("a[href]").next("a[href]").attr("href");
        return PREFIX_LINK + idiomLink;
    }

    private String getIdNumber(Element el) {

        String idNumber = el.select("p[class=big mtop]").select("a[href]").next("a[href]").attr("href");
        idNumber = idNumber.substring(idNumber.lastIndexOf("/") + 1);

        return idNumber;
    }

    public String getMp3TranslateLink(Element el) {
        String mp3El = el.select("p[class=big mtop]").select("a[href]").attr("href");
        return PREFIX_LINK + mp3El;
    }


}
