package com.example.idiom.service.idiom;

import com.example.idiom.model.idiom.Idiom;
import com.example.idiom.model.idiom.IdiomIdComparator;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class IdiomParser {


    private final String PREFIX_LINK = "https://www.ang.pl";


    public List<Idiom> parseToIdiom(Elements elements) {

        List<Idiom> idiomList = new ArrayList<>();
        elements.forEach(element -> {
            Idiom idiom = Idiom.builder()
                    .id(Long.valueOf(getIdNumber(element)))
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
        StringBuilder exampleEnglish = new StringBuilder();
        List<Node> exampleEnglishNodes = el.select("p[class=mb-1]").first().childNodes();

        for (Node node : exampleEnglishNodes) {
            if (node.childNodes().isEmpty()) {
                exampleEnglish.append(node);
            } else exampleEnglish.append(node.firstChild());
        }
        return exampleEnglish.toString();

    }


    public String getExampleMp3Link(Element el) {
        String linkToIdiom = el.select("div[class=col-5 col-sm-3 ang]").first().firstElementChild().attr("href");
        return PREFIX_LINK + linkToIdiom;
    }

    public String getPolishTranslation(Element el) {
        Node polishTranslation = el.select("div[class=col-7 col-sm-4 lh-1]").first().firstChild();
        return polishTranslation.toString();
    }

    public String getEnglishTranslation(Element el) {
        Node englishTranslation = el.select("div[class=col-5 col-sm-3 ang]").first().firstChild().firstChild();
        return englishTranslation.toString();
    }

    private String getLinkToIdiom(Element el) {
        String linkToIdiom = el.select("div[class=col-5 col-sm-3 ang]").first().firstElementChild().attr("href");
        return PREFIX_LINK + linkToIdiom;
    }

    private String getIdNumber(Element el) {
        String linkToIdiom = el.select("div[class=col-5 col-sm-3 ang]").first().firstElementChild().attr("href");
        linkToIdiom = linkToIdiom.substring(linkToIdiom.lastIndexOf("/") + 1);

        return linkToIdiom;
    }

    public String getMp3TranslateLink(Element el) {
        String linkToIdiom = el.select("div[class=col-5 col-sm-3 ang]").first().firstElementChild().attr("href");
        return PREFIX_LINK + linkToIdiom;
    }


}
