package com.example.idiom.service.idiom;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

@Slf4j
public class IdiomPagination {


    private final String BASE_LINK = "https://www.ang.pl/slownictwo/idiomy/page/1";

    public int getNumberOfPageIdiom() {
        try {
            log.info("Downloading number of pagination:" + BASE_LINK);
            Document tempDoc = null;
            tempDoc = Jsoup.connect(BASE_LINK).get();
            Elements elements = tempDoc.getElementsByClass("pagination");
            String numberOfPage = elements.first().lastElementChild().text();
            log.info("Number of pagination is :" + numberOfPage);
            return Integer.parseInt(numberOfPage);

        } catch (
                IOException e) {
            throw new RuntimeException(e);
            //TODO Add hand RuntimeException(e) handling

        }
    }

}

