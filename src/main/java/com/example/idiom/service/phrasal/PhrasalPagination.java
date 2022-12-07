package com.example.idiom.service.phrasal;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.example.idiom.inter.DataGrabberAngPl.PHRASAL_VERB_LINK;

@Service
public class PhrasalPagination {
    public int getNumberOfPagePhrasalVerb() throws IOException {
        Document tempDoc = Jsoup.connect(PHRASAL_VERB_LINK).get();
        Elements elements = tempDoc.getElementsByClass("pagination");
        String numberOfPage = elements.first().lastElementChild().text();
        return Integer.parseInt(numberOfPage);

    }
}
