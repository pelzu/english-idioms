package com.example.idiom.service.dataGrab.idiom;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class IdiomPagination {


    private final String BASE_LINK = "https://www.ang.pl/slownictwo/idiomy/page/";

    public int getNumberOfPageIdiom() {
        try {
            Document tempDoc = null;
            tempDoc = Jsoup.connect(BASE_LINK).get();
            Elements elements = tempDoc.getElementsByClass("pagination");
            String numberOfPage = elements.first().lastElementChild().text();
            return Integer.parseInt(numberOfPage);

        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }

}

