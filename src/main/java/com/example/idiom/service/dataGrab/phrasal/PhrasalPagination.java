package com.example.idiom.service.dataGrab.phrasal;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class PhrasalPagination {
    private final String PHRASAL_VERB_LINK = "https://www.ang.pl/slownictwo/phrasal-verbs/page/";


    public int getNumberOfPagePhrasalVerb() {
        try {
            Document tempDoc = null;
            tempDoc = Jsoup.connect(PHRASAL_VERB_LINK).get();
            Elements elements = tempDoc.getElementsByClass("pagination");
            String numberOfPage = elements.first().lastElementChild().text();
            return Integer.parseInt(numberOfPage);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
