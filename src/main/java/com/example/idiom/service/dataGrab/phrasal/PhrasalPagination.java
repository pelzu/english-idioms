package com.example.idiom.service.dataGrab.phrasal;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

@Slf4j
public class PhrasalPagination {
    private final String PHRASAL_VERB_LINK = "https://www.ang.pl/slownictwo/phrasal-verbs/page/";

    public int getNumberOfPagePhrasalVerb() {
        try {
            log.info("Downloading number of pagination:" + PHRASAL_VERB_LINK);
            Document tempDoc = null;
            tempDoc = Jsoup.connect(PHRASAL_VERB_LINK).get();
            Elements elements = tempDoc.getElementsByClass("pagination");
            String numberOfPage = elements.first().lastElementChild().text();
            log.info("Number of pagination is :" + numberOfPage);
            return Integer.parseInt(numberOfPage);

        } catch (IOException e) {
            throw new RuntimeException(e);
            //TODO Add hand RuntimeException(e) handling

        }


    }
}
