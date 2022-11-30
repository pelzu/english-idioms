package com.example.idiom.service;

import com.example.idiom.model.IdiomModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class IdiomJsoupApproach {
    private static final String BASE_LINK = "https://www.ang.pl/slownictwo/idiomy/page/";

    private final static String PREFIX_LINK= "https://www.ang.pl";

    private static Elements allElements = new Elements();

    private static ArrayList<IdiomModel> idiomModels = new ArrayList<>();

    private static IdiomModel idiomModel = new IdiomModel();

    private static int incrementator = 1;

    public void getIdiom() throws IOException {


        Document tempDoc = Jsoup.connect(BASE_LINK).get();

        for (int i = 1; i <= getNumberOfPageIdiom(tempDoc) ; i++) {
            Document doc = Jsoup.connect(BASE_LINK +i).get();
            parseToIdiomModel(doc);
            System.out.println(BASE_LINK +i);
            System.out.println(idiomModels.toArray().length);
        }






    }

    public void parseToIdiomModel (Document document) {
        Elements elements = document.select("div[style*=border-bottom: 1px solid #ccc;]");

        for (Element element : elements
        ) {

            idiomModel.setId(Integer.toString(this.incrementator));
            idiomModel.setAudioTranslateLink(getMp3TranslateLink(element));
            idiomModel.setLinkToIdiom(getLinkToIdiom(element));
            idiomModel.setEnglishMeaning(getEnglishTranslation(element));
            idiomModel.setPolishMeaning(getPolishTranslation(element));
            idiomModel.setAudioExampleLink(getExampleMp3Link(element));
            idiomModel.setEnglishExample(getExampleEnglish(element));
            idiomModels.add(idiomModel);
            System.out.println(idiomModel);
            this.incrementator++;

        }

    }

    public String getExampleEnglish(Element el) {
//        String exampleEnglish = el.select("div[class=medium-5 columns]").select("p").text();
        Node node =el.select("div[class=medium-5 columns]").select("p").first().childNodes().get(1);
        return node.toString();

    }


    public String getExampleMp3Link(Element el) {
        String exampleMp3Link = el.select("div[class=medium-5 columns]").select("a[href]").attr("href");
        return PREFIX_LINK+exampleMp3Link;

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
        return PREFIX_LINK+idiomLink;
    }

    public String getMp3TranslateLink(Element el) {
        String mp3El = el.select("p[class=big mtop]").select("a[href]").attr("href");
        return PREFIX_LINK+mp3El;
    }

    public int getNumberOfPageIdiom(Document document) {
        Elements elements =document.getElementsByClass("pagination");
        String numberOfPage= elements.first().lastElementChild().text();
        return Integer.parseInt(numberOfPage) ;
    }


}
