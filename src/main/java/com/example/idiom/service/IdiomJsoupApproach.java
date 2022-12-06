package com.example.idiom.service;

import com.example.idiom.model.Idiom;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class IdiomJsoupApproach {
    public static final String BASE_LINK = "https://www.ang.pl/slownictwo/idiomy/page/";

    private final static String PREFIX_LINK = "https://www.ang.pl";


    private static final List<Idiom> idioms = new ArrayList<>();
    List<Document> paginationDocuments = new ArrayList<Document>();
    int numberOfPage;


    public void getIdiom() throws IOException {

        this.numberOfPage = getNumberOfPageIdiom();

        getPaginationDocuments(numberOfPage);
        paginationDocuments.forEach(document -> {
            parseToIdiomModel(document);
            log.info(document.location());
            log.info(String.valueOf(idioms.toArray().length));
        });


    }

    private void getPaginationDocuments(int pageNumber) throws IOException {
        for (int i = 1; i <= pageNumber; i++) {
            Document doc = Jsoup.connect(BASE_LINK + i).get();
            paginationDocuments.add(doc);
        }
    }


    public void parseToIdiomModel(Document document) {
        Elements elements = document.select("div[style*=border-bottom: 1px solid #ccc;]");
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
            idioms.add(idiom);
            log.info(String.valueOf(idiom));
        });
    }


    public String getExampleEnglish(Element el) {
//        String exampleEnglish = el.select("div[class=medium-5 columns]").select("p").text();
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

    public int getNumberOfPageIdiom() throws IOException {
        Document tempDoc = Jsoup.connect(BASE_LINK).get();
        Elements elements = tempDoc.getElementsByClass("pagination");
        String numberOfPage = elements.first().lastElementChild().text();
        return Integer.parseInt(numberOfPage);
    }

    public List<Idiom> getIdioms() {
        return idioms;
    }


}
