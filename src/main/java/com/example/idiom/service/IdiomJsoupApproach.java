package com.example.idiom.service;

import com.example.idiom.model.Idiom;
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
public class IdiomJsoupApproach {
    public static final String BASE_LINK = "https://www.ang.pl/slownictwo/idiomy/page/";

    private final static String PREFIX_LINK = "https://www.ang.pl";


    private static List<Idiom> idioms = new ArrayList<>();


//    private static int incrementator = 1;
    private static  int numberOfPage ;

    public void getIdiom() throws IOException {
        Document  tempDoc= Jsoup.connect(BASE_LINK).get();
        int numberOfPage=getNumberOfPageIdiom(tempDoc);

        for (int i = 1; i <= numberOfPage; i++) {
            Document doc = Jsoup.connect(BASE_LINK + i).get();
            parseToIdiomModel(doc,i);
            System.out.println(BASE_LINK + i);
            System.out.println(idioms.toArray().length);
        }

    }



    public void parseToIdiomModel(Document document, int paginationIncrement) {
        Elements elements = document.select("div[style*=border-bottom: 1px solid #ccc;]");
        for (int i = 0; i <elements.toArray().length ; i++) {

            Idiom idiom = Idiom.builder()
                    .id(Integer.toString((calculateId(paginationIncrement,i))))
                    .audioTranslateLink(getMp3TranslateLink(elements.get(i)))
                    .linkToIdiom(getLinkToIdiom(elements.get(i)))
                    .englishMeaning(getLinkToIdiom(elements.get(i)))
                    .polishMeaning(getPolishTranslation(elements.get(i)))
                    .audioExampleLink(getExampleMp3Link(elements.get(i)))
                    .englishExample(getExampleEnglish(elements.get(i)))
                    .build();


//
//            IdiomModel idiomModel = new IdiomModel();
//            idiomModel.setId(Integer.toString((calculateId(paginationIncrement,i))));
//            idiomModel.setAudioTranslateLink(getMp3TranslateLink(elements.get(i)));
//            idiomModel.setLinkToIdiom(getLinkToIdiom(elements.get(i)));
//            idiomModel.setEnglishMeaning(getEnglishTranslation(elements.get(i)));
//            idiomModel.setPolishMeaning(getPolishTranslation(elements.get(i)));
//            idiomModel.setAudioExampleLink(getExampleMp3Link(elements.get(i)));
//            idiomModel.setEnglishExample(getExampleEnglish(elements.get(i)));
            idioms.add(idiom);
            System.out.println(idiom);

        }

//        for (Element element : elements
//        ) {
//            IdiomModel idiomModel = new IdiomModel();
//            idiomModel.setId(Integer.toString(incrementator));
//            idiomModel.setAudioTranslateLink(getMp3TranslateLink(element));
//            idiomModel.setLinkToIdiom(getLinkToIdiom(element));
//            idiomModel.setEnglishMeaning(getEnglishTranslation(element));
//            idiomModel.setPolishMeaning(getPolishTranslation(element));
//            idiomModel.setAudioExampleLink(getExampleMp3Link(element));
//            idiomModel.setEnglishExample(getExampleEnglish(element));
//            idiomModels.add(idiomModel);
//            System.out.println(idiomModel);
//            incrementator++;
//
//        }


    }

    private int calculateId(int paginationIncrement, int i) {
        return ((paginationIncrement-1)*50+i+1);
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

    public String getMp3TranslateLink(Element el) {
        String mp3El = el.select("p[class=big mtop]").select("a[href]").attr("href");
        return PREFIX_LINK + mp3El;
    }

    public int getNumberOfPageIdiom(Document document) {
        Elements elements = document.getElementsByClass("pagination");
        String numberOfPage = elements.first().lastElementChild().text();
        return Integer.parseInt(numberOfPage);
    }


}
