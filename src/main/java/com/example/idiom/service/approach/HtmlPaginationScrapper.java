package com.example.idiom.service.approach;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

@Slf4j
public class HtmlPaginationScrapper {


    public static int getNumberOfPage(String BASE_LINK) {
        log.info("Downloading number of pagination:" + BASE_LINK);
        int numberOfPage = parseNumberFromSite(BASE_LINK);
        log.info("Number of pagination is :" + numberOfPage);
        return numberOfPage;
    }

    private static int parseNumberFromSite(String BASE_LINK) {
        String numberOfPage;
        try {
            Document tempDoc = null;
            tempDoc = Jsoup.connect(BASE_LINK).get();
            Elements elements = tempDoc.getElementsByClass("pagination");
            numberOfPage = elements.first().lastElementChild().text();
        } catch (
                IOException e) {
            throw new RuntimeException(e);
            //TODO Add hand RuntimeException(e) handling

        }
        return Integer.parseInt(numberOfPage);

    }
}
